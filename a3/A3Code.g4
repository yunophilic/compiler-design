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

public class ScopeUtil {
	int scope = 0;

	void Enter() {
		scope++;
	}

	void Exit() {
		scope--;
	}

	int GetCurrent() {
		return scope;
	}

}

TempUtil tempUtil = new TempUtil();
IdUtil idUtil = new IdUtil();
ScopeUtil scopeUtil = new ScopeUtil();

public class Symbol {
	int id;
	String name;
	DataType dt;
	String arrSize; //decimal or hex string, null for non array types

	Symbol (String n, DataType d) {
		this(n, d, null);
	}

	Symbol (String n, DataType d, String s) {
		id = idUtil.GetNext();
		name = n;
		dt = d;
		arrSize = s;
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
		String type = dt + "";
		if (arrSize != null) {
			type = "ARRAY(" + dt + "," + arrSize + ")";
		}
		System.out.println(name + "\t" + type);
	}

	
	
}

public class SymTab {
	List<Symbol> st;

	SymTab() {
		st = new ArrayList<>();
	}

	int Find (String n) {
		for (int  i = 0; i < st.size(); i ++) {
			Symbol s = st.get(i);
			if (s.Equal(n))
				return s.GetId();
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

	int insert(String n, DataType d, String s) {
		int id = Find(n);
		if (id != -1) return id;
		
		Symbol sym = new Symbol(n, d, s);
		st.add(sym);
		return (sym.GetId());
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
		push(new SymTab()); //add global symTab
	}

	void push(SymTab st) {
		st.Print();
		stack.addLast(st);
	}

	int size() {
		return stack.size();
	}

	SymTab pop() {
		return stack.removeLast();
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
		ArrayList<SymTab> symTabList = new ArrayList<>();
		while (!stackCopy.isEmpty()) {
			symTabList.add(stackCopy.removeLast());
		}

		//System.out.println("list size: " + symTabList.size());

		for(SymTab st : symTabList) {
			int id = st.Find(n);
			if (id != -1) {
				return id;
			}
		}

		return -1;
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
		//System.out.println("get symbol by id in SymTabStack");
		for (SymTab st : stack) {
			Symbol s = st.getSymById(id);
			if (s != null)
				return s;
		}

		return null;
	}

	void Print() {
		ArrayDeque<SymTab> stackClone = stack.clone();
		int i=0;
		while (!stackClone.isEmpty()) {
			System.out.println("Symtab " + i);
			stackClone.removeFirst().Print();
			System.out.println();
			i++;
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
	int scope;


	Quad (int l, int d, int s1, int s2, String o) {
		label = l;
		dst = d;
		src1 = s1;
		src2 = s2;
		op = o;
		scope = scopeUtil.GetCurrent();
	}

	int GetScope() {
		return scope;
	}

	int GetSrc1() {
		return src1;
	}

	int GetSrc2() {
		return src2;
	}

	int GetDst() {
		return dst;
	}

	void Print() {
		if (symTabStack.GetName(src2).equals("") && op.equals("=")) { //assignment operation ('=')

			System.out.println("L_" + label + ": " + symTabStack.GetName(dst) + " " 
				+ op + " " + symTabStack.GetName(src1));

		} else if(symTabStack.GetName(src2).equals("")) { //unary operation ('-', '!')

			System.out.println("L_" + label + ": " + symTabStack.GetName(dst) + " = " 
				+ op + " " + symTabStack.GetName(src1));

		} else if(op.equals("[]r")) { //array access read

			System.out.println("L_" + label + ": " + symTabStack.GetName(dst) + " = " 
				+ symTabStack.GetName(src1) + " [ " + symTabStack.GetName(src2) + " ] ");

		} else if(op.equals("[]w")) { //array access write

			System.out.println("L_" + label + ": " + symTabStack.GetName(dst) + 
				" [ " + symTabStack.GetName(src1) + " ] = " + symTabStack.GetName(src2));

		} else { //binary operation

			System.out.println("L_" + label + ": " + symTabStack.GetName(dst) + " = " 
				+ symTabStack.GetName(src1) + " " + op + " " + symTabStack.GetName(src2));

		}
	}

	void debug() {
		System.out.println(label + "\t" + op + "\t" + src1 + "\t" + src2 + "\t" + dst + "\t" + scope);
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
		return (size ++);
	}

	void Print() {
		int prevScope = 0;
		for(Quad q : qt) {
			int currentScope = q.GetScope();

			if (prevScope > currentScope) {
				SymTab poppedSt = symTabStack.pop();
			}

			q.Print();

			prevScope = currentScope;
		}
	}

	void debug() {
		for(Quad q : qt) {
			q.debug();
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
	//Print
	symTabStack.Print();
	System.out.println("------------------------------------");
	q.debug();
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
| f=field_decl ',' Ident '[' num ']'
{
	$t = $f.t;
	symTabStack.getFirst().insert($Ident.text, $t, $num.text);
}
| Type Ident '[' num ']'
{
	$t = DataType.valueOf($Type.text.toUpperCase());
	symTabStack.getFirst().insert($Ident.text, $t, $num.text);
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
;

var_decls returns [SymTab st]
: v=var_decls var_decl ';'
{
	$st = $v.st;
	for (AbstractMap.SimpleEntry<String, DataType> p : $var_decl.symbols) {
		$st.insert(p.getKey(), p.getValue());
	}
}
| 
{
	$st = new SymTab();
	symTabStack.push($st);
	scopeUtil.Enter();
}
;


var_decl returns [List<AbstractMap.SimpleEntry<String, DataType>> symbols, DataType t]
: v=var_decl ',' Ident
{
	$t = $v.t;
	$symbols = $v.symbols;
	$symbols.add(new AbstractMap.SimpleEntry<String, DataType>($Ident.text, $t));
}
| Type Ident
{
	$t = DataType.valueOf($Type.text.toUpperCase());
	$symbols = new ArrayList<>();
	$symbols.add(new AbstractMap.SimpleEntry<String, DataType>($Ident.text, $t));
}
;



statements 
: statement t=statements
|
{
	scopeUtil.Exit();
}
;


statement 
: location eqOp expr ';'
{
	switch ($eqOp.text)
	{
		case "=":
			if ($location.isArrAccess){
				q.Add($location.id, $location.offset, $expr.id, "[]w");
			} else {
				q.Add($location.id, $expr.id, -1, "=");
			}
			break;
		case "+=":
			if ($location.isArrAccess){
				int tempId = symTabStack.getLast().Add(symTabStack.GetType($location.id));
				q.Add(tempId, $location.id, $location.offset, "[]r");

				int secondTempId = symTabStack.getLast().Add(symTabStack.GetType($location.id));
				q.Add(secondTempId, tempId, $expr.id, "=");

				q.Add($location.id, $location.offset, tempId, "[]w");
			} else {
				int tempId = symTabStack.getLast().Add(symTabStack.GetType($location.id));
				q.Add(tempId, $location.id, $expr.id, "=");
				q.Add($location.id, $expr.id, -1, "+=");
			}
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
	scopeUtil.Exit();
}
| Ret '(' expr ')' ';'
{
	scopeUtil.Exit();
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
	if ($location.isArrAccess) {
		$id = symTabStack.getLast().Add(symTabStack.GetType($location.id));
		q.Add($id, $location.id, $location.offset, "[]r");
	} else {
		$id = $location.id;
	}
}
| '(' e=expr ')'
{
	$id = $e.id;
}
| SubOp e=expr
{
	SymTab st = symTabStack.getLast();
	$id = st.Add(symTabStack.GetType($e.id));
	q.Add($id, $e.id, -1, $SubOp.text);
}
| '!' e=expr
{
	SymTab st = symTabStack.getLast();
	$id = st.Add(symTabStack.GetType($e.id));
	q.Add($id, $e.id, -1, "!");
}
| e1=expr MulDiv e2=expr
{
	SymTab st = symTabStack.getLast();
	$id = st.Add(symTabStack.GetType($e1.id));
	q.Add($id, $e1.id, $e2.id, $MulDiv.text);
}
| e1=expr AddOp e2=expr
{
	SymTab st = symTabStack.getLast();
	$id = st.Add(symTabStack.GetType($e1.id));
	q.Add($id, $e1.id, $e2.id, $AddOp.text);
}
| e1=expr SubOp e2=expr
{
	SymTab st = symTabStack.getLast();
	$id = st.Add(symTabStack.GetType($e1.id));
	q.Add($id, $e1.id, $e2.id, $SubOp.text);
}
| e1=expr RelOp e2=expr
{
	SymTab st = symTabStack.getLast();
	$id = st.Add(symTabStack.GetType($e1.id));
	q.Add($id, $e1.id, $e2.id, $RelOp.text);
}
| e1=expr AndOp e2=expr
{
	SymTab st = symTabStack.getLast();
	$id = st.Add(symTabStack.GetType($e1.id));
	q.Add($id, $e1.id, $e2.id, $AndOp.text);
}
| e1=expr OrOp e2=expr
{
	SymTab st = symTabStack.getLast();
	$id = st.Add(symTabStack.GetType($e1.id));
	q.Add($id, $e1.id, $e2.id, $OrOp.text);
}
/*| methodCall
{

}*/
;


location returns [int id, int offset, boolean isArrAccess]
:Ident
{
	$id = symTabStack.Find($Ident.text);
	$isArrAccess = false;
}
| Ident '[' expr ']'
{
	$id = symTabStack.Find($Ident.text);
	
	SymTab st = symTabStack.getLast();

	$offset = st.Add(DataType.INT);
	int idOfFour = st.insert("4", DataType.INT);
	q.Add($offset, idOfFour, $expr.id, "*");

	$isArrAccess = true;
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

