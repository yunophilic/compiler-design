grammar A3Code;

//---------------------------------------------------------------------------------------------------
// Session 1: ANTLR API, You SHOULD NOT make any modification to this session
//---------------------------------------------------------------------------------------------------
@header {

import java.io.*;
import java.util.*;
}



@parser::members {

public enum DataType {
	INT, BOOLEAN, VOID, INVALID
}


public class TempUtil {
	int incr = 0;

	int GetNext() {
		return (incr ++);
	}
}

public class IdUtil {
	int incr = 0;
	
	int GetNext() {
		return (incr ++);
	}	
}

TempUtil tempUtil = new TempUtil();
IdUtil idUtil = new IdUtil();

public class Symbol {
	int id;
	String name;
	DataType dt;

	Symbol (String n, DataType d) {
		id = idUtil.GetNext();
		name = n;
		dt = d;
	}

	Symbol (DataType d) {
		this("t_" + tempUtil.GetNext(), d);
	}

	boolean Equal (String n) {
		return (name.equals(n));
	}

	DataType GetType () {
		return dt;
	}

	String GetName () {
		return name;
	}

	int GetId() {
		return id;
	}

	void Print() {
		System.out.println(name + "\t" + dt);
	}

	
	
}

public class SymTab {
	List<Symbol> st;

	SymTab() {
		st = new ArrayList<>();
	}

	int Find (String n) {
		for (int  i = 0; i < st.size(); i ++) {
			if (st.get(i).Equal(n)) return i;
		}
		
		return -1;
	}

	int insert(String n, DataType d) {
		int id = Find(n);
		if (id != -1) return id;
		
		Symbol s = new Symbol(n, d);
		st.add(s);
		return (s.GetId());
	}

	int Add (DataType d) {
		Symbol s = new Symbol(d);
		st.add(s);
		return (s.GetId());
	}

	DataType GetType (int id) {
		if (id == -1)
			return DataType.INVALID;
		return (getSymById(id).GetType());
	}

	String GetName (int id) {
		if (id == -1)
			return ("");
		return (getSymById(id).GetName());
	}

	Symbol getSymById(int id) {
		for (Symbol s : st) {
			if (s.GetId() == id) {
				return s;
			}
		}

		return null;
	}

	void Print() {
		for (Symbol s : st) {
			s.Print();
		}
	}

	void InsertMultiple(List<Symbol> symbols) {
		for(Symbol s : symbols) {
			this.insert(s.GetName(), s.GetType());
		}
	}

}

public class SymTabStack {
	ArrayDeque<SymTab> stack;

	SymTabStack() {
		stack = new ArrayDeque<>();
		stack.add(new SymTab()); //add global symTab
	}

	void push(SymTab st) {
		stack.push(st);
	}

	int size() {
		return stack.size();
	}

	SymTab pop() {
		return stack.pop();
	}

	SymTab getLast() {
		return stack.getLast();
	}

	SymTab getFirst() {
		return stack.getFirst();
	}

	int Find (String n) {
		//search from top of stack (most inner scope)
		ArrayDeque<SymTab> stackCopy = stack.clone();

		int id = -1;
		while (
			!stackCopy.isEmpty() && 
			(id = stackCopy.pop().Find(n)) == -1
		);

		return id;
	}

	DataType GetType (int id) {
		if (id == -1)
			return DataType.INVALID;
		return (getSymById(id).GetType());
	}

	String GetName (int id) {
		if (id == -1)
			return ("");
		return (getSymById(id).GetName());
	}

	Symbol getSymById(int id) {
		for (SymTab st : stack) {
			Symbol s = st.getSymById(id);
			if (s != null)
				return s;
		}

		return null;
	}

	void Print() {
		System.out.println("symtab");
		for (SymTab st : stack) {
			st.Print();
		}
	}
}

SymTabStack symTabStack = new SymTabStack();
SymTab globalSt = new SymTab();

public class Quad {

	int label;
	String op;
	int src1;
	int src2;
	int dst;


	Quad (int l, int d, int s1, int s2, String o) {
		label = l;
		dst = d;
		src1 = s1;
		src2 = s2;
		op = o;
	}

	void Print () {
		if (symTabStack.GetName(src2).equals("") && op.equals("=")) {
			System.out.println("L_" + label + ": " + symTabStack.GetName(dst) + " " 
				+ op + " " + symTabStack.GetName(src1));
		} else if(symTabStack.GetName(src2).equals("")) {
			System.out.println("L_" + label + ": " + symTabStack.GetName(dst) + " = " 
				+ op + " " + symTabStack.GetName(src1));
		} else {
			System.out.println("L_" + label + ": " + symTabStack.GetName(dst) + " = " 
				+ symTabStack.GetName(src1) + " " + op + " " + symTabStack.GetName(src2));
		}
	}

}

public class QuadTab {

	List<Quad> qt;
	int size;

	QuadTab () {
		qt = new ArrayList<>();
		size = 0;
	}

	int Add(int dst, int src1, int src2, String op) {
			
		qt.add(new Quad(size, dst, src1, src2, op));
		return (qt.size()-1);
	}

	void Print() {
		for(Quad q : qt) {
			q.Print();
		}
	}


}

QuadTab q = new QuadTab();

}



//---------------------------------------------------------------------------------------------------
// Session 2: Fill your code here
//---------------------------------------------------------------------------------------------------
prog
: Class Program '{' field_decls method_decl '}'
{
	/*SymTab globalSt = new SymTab();
	symTabStack.push(globalSt);*/

	//Print
	symTabStack.Print();
	System.out.println("------------------------------------");
	q.Print();
}
;

field_decls 
: f=field_decls field_decl ';'
| 
;


field_decl returns [DataType t]
: f=field_decl ',' Ident
{
	$t = $f.t;
	symTabStack.getFirst().insert($Ident.text, $t);
}
| Type Ident
{
	$t = DataType.valueOf($Type.text.toUpperCase());
	symTabStack.getFirst().insert($Ident.text, $t);
}
;




method_decl 
: Type Ident '(' params ')' block
{
	symTabStack.getFirst()
		.insert($Ident.text, DataType.valueOf($Type.text.toUpperCase()));
}
| Void Ident '(' params ')' block
{
	symTabStack.getFirst()
		.insert($Ident.text, DataType.valueOf($Void.text.toUpperCase()));	
}
;

params /*returns [int id]*/
: Type Ident nextParams
{

}
|
{

}
;

nextParams /*returns /*[MySet s]*/
: n=nextParams ',' Type Ident
{

}
|
{

}
;

block 
: '{' var_decls statements '}'
{
	symTabStack.push($var_decls.st);
}
;

var_decls returns [SymTab st]
: v=var_decls var_decl ';'
{
	$st = $v.st;
	$st.InsertMultiple($var_decl.symbols);
}
| 
{
	$st = new SymTab();
}
;


var_decl returns [List<Symbol> symbols, DataType t]
: v=var_decl ',' Ident
{
	$t = $v.t;
	$symbols = $v.symbols;
	$symbols.add(new Symbol($Ident.text, $t));
}
| Type Ident
{
	$t = DataType.valueOf($Type.text.toUpperCase());
	$symbols = new ArrayList();
	$symbols.add(new Symbol($Ident.text, $t));
}
;



statements 
: statement t=statements
|
{
	symTabStack.pop();
}
;


statement 
: location eqOp expr ';'
{
//	System.out.println("HEY");
	switch ($eqOp.text)
	{
		case "=":
			q.Add($location.id, $expr.id, -1, "=");
			break;
		case "+=":
			break;
		case "-=":
			break;
		default:
			break;
	}
}
| If '(' expr ')' block
{

}
| If '(' expr ')' b1=block Else b2=block
{
	
}
| For Ident '=' e1=expr ',' e2=expr block
{
	
}
| Ret ';'
{
	symTabStack.pop();
}
| Ret '(' expr ')' ';'
{
	symTabStack.pop();
}
| Brk ';'
{
	
}
| Cnt ';'
{
	
}
| block
{
	
}
/*| methodCall ';'
{
	
}*/
;


expr returns [int id]
: literal 
{
	$id = $literal.id;
}
| location
{
	$id = $location.id;
}
| '(' e=expr ')'
{
	$id = $e.id;
}
| SubOp e=expr
{
	SymTab s = symTabStack.getLast();
	$id = s.Add(s.GetType($e.id));
	q.Add($id, $e.id, -1, $SubOp.text);
}
| '!' e=expr
{
	SymTab s = symTabStack.getLast();
	$id = s.Add(s.GetType($e.id));
	q.Add($id, $e.id, -1, "!");
}
| e1=expr MulDiv e2=expr
{
	SymTab s = symTabStack.getLast();
	$id = s.Add(s.GetType($e1.id));
	q.Add($id, $e1.id, $e2.id, $MulDiv.text);
}
| e1=expr AddOp e2=expr
{
	SymTab s = symTabStack.getLast();
	$id = s.Add(s.GetType($e1.id));
	q.Add($id, $e1.id, $e2.id, $AddOp.text);
}
| e1=expr SubOp e2=expr
{
	SymTab s = symTabStack.getLast();
	$id = s.Add(s.GetType($e1.id));
	q.Add($id, $e1.id, $e2.id, $SubOp.text);
}
| e1=expr RelOp e2=expr
{
	SymTab s = symTabStack.getLast();
	$id = s.Add(s.GetType($e1.id));
	q.Add($id, $e1.id, $e2.id, $RelOp.text);
}
| e1=expr AndOp e2=expr
{
	SymTab s = symTabStack.getLast();
	$id = s.Add(s.GetType($e1.id));
	q.Add($id, $e1.id, $e2.id, $AndOp.text);
}
| e1=expr OrOp e2=expr
{
	SymTab s = symTabStack.getLast();
	$id = s.Add(s.GetType($e1.id));
	q.Add($id, $e1.id, $e2.id, $OrOp.text);
}
/*| methodCall
{

}*/
;


location returns [int id]
:Ident
{
	$id = symTabStack.Find($Ident.text);
}
;


num
: DecNum
| HexNum
;

literal returns [int id]
: num
{
	$id = symTabStack.getLast().insert($num.text, DataType.INT);
}
| Char
| BoolLit
;

eqOp
: '='
| AssignOp
;

//--------------------------------------------- END OF SESSION 2 -----------------------------------


//---------------------------------------------------------------------------------------------------
// Session 3: Lexical definition, You SHOULD NOT make any modification to this session
//---------------------------------------------------------------------------------------------------
fragment Delim
: ' '
| '\t'
| '\n'
;

fragment Letter
: [a-zA-Z]
;

fragment Digit
: [0-9]
;

fragment HexDigit
: Digit
| [a-f]
| [A-F]
;

fragment Alpha
: Letter
| '_'
;

fragment AlphaNum
: Alpha
| Digit
;


WhiteSpace
: Delim+ -> skip
;



Char
: '\'' ~('\\') '\''
| '\'\\' . '\'' 
;

Str
:'"' ((~('\\' | '"')) | ('\\'.))* '"'
; 



Class
: 'class'
;

Program
: 'Program'
;

Void
: 'void'
;

If
: 'if'
;

Else
: 'else'
;

For
: 'for'
;

Ret
: 'return'
;

Brk
: 'break'
;

Cnt
: 'continue'
;

Callout
: 'callout'
;

DecNum
: Digit+
;


HexNum
: '0x'HexDigit+
;




BoolLit
: 'true'
| 'false'
;

Type
: 'int'
| 'boolean'
;

Ident
: Alpha AlphaNum* 
;

RelOp
: '<='
| '>=' 
| '<'
| '>'
| '=='
| '!='
;

AssignOp
: '+='
| '-='
;

MulDiv
: '*'
| '/'
| '%'
;

AddOp
: '+'
;

SubOp
: '-'
;

AndOp
: '&&'
;

OrOp
: '||'
;

