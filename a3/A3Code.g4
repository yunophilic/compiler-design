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
	INT, BOOLEAN, VOID, 
	STR, CHAR, INVALID
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
}

public class SymTabStack {
	ArrayDeque<SymTab> stack;
	ArrayDeque<SymTab> popped;

	SymTabStack() {
		stack = new ArrayDeque<>();
		popped = new ArrayDeque<>();
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
		SymTab st = stack.removeLast();;
		popped.addLast(st);
		return st;
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
			(id = stackCopy.removeLast().Find(n)) == -1
		);

		return id;
	}

	DataType GetType (int id) {
		if (id == -1) {
			return DataType.INVALID;
		}
		return (getSymById(id).GetType());
	}

	String GetName (int id) {
		if (id == -1) {
			return ("");
		}
		return (getSymById(id).GetName());
	}

	Symbol getSymById(int id) {
		//search all (including the popped symtabs)

		for (SymTab st : stack) {
			Symbol s = st.getSymById(id);
			if (s != null) {
				return s;
			}
		}

		for (SymTab st : popped) {
			Symbol s = st.getSymById(id);
			if (s != null){
				return s;
			}
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

	Quad (int l, int d, int s1, int s2, String o) {
		label = l;
		dst = d;
		src1 = s1;
		src2 = s2;
		op = o;
	}

	String getOp() {
		return op;
	}

	int getSrc1() {
		return src1;
	}

	void setSrc1(int s1) {
		src1 = s1;
	}

	int getSrc2() {
		return src2;
	}

	void setSrc2(int s2) {
		src2 = s2;
	}

	void Print() {
		switch (op) {
			case "=": //assignment operation
				System.out.println("L_" + label + ": " + symTabStack.GetName(dst) + " " 
					+ op + " " + symTabStack.GetName(src1));
				break;

			case "!": //negation operation
				System.out.println("L_" + label + ": " + symTabStack.GetName(dst) + " = !" + symTabStack.GetName(src1));
				break;

			case "[]r": //array access read
				System.out.println("L_" + label + ": " + symTabStack.GetName(dst) + " = " 
					+ symTabStack.GetName(src1) + " [ " + symTabStack.GetName(src2) + " ] ");
				break;

			case "[]w": //array access write
				System.out.println("L_" + label + ": " + symTabStack.GetName(dst) + 
					" [ " + symTabStack.GetName(src1) + " ] = " + symTabStack.GetName(src2));
				break;

			case "method_decl":
				System.out.println(symTabStack.GetName(src1) + ": ");
				break;

			case "param":
				System.out.println("L_" + label + ": " + symTabStack.GetName(src1) + " param");
				break;

			case "if":
				System.out.println("L_" + label + ": if " + symTabStack.GetName(src1) 
					+ " goto L_" + symTabStack.GetName(src2));
				break;

			case "ifFalse":
				System.out.println("L_" + label + ": ifFalse " + symTabStack.GetName(src1) 
					+ " goto L_" + symTabStack.GetName(src2));
				break;

			case "goto":
				System.out.println("L_" + label + ": goto L_" + symTabStack.GetName(src1));
				break;

			case "end_of_meth":
				System.out.println("L_" + label);
				break;				

			default:
				System.out.println("L_" + label + ": " + symTabStack.GetName(dst) + " = " 
					+ symTabStack.GetName(src1) + " " + op + " " + symTabStack.GetName(src2));
				break;
		}
	}

	void debug() {
		System.out.println(label + "\t" + op + "\t" + src1 + "\t" + src2 + "\t" + dst);
	}

}

public class QuadTab {
	List<Quad> qt;

	QuadTab () {
		qt = new ArrayList<>();
	}

	int Add(int dst, int src1, int src2, String op) {
		qt.add(new Quad(qt.size(), dst, src1, src2, op));
		return (qt.size()-1);
	}

	void Set(int label, int dst, int src1, int src2, String op) {
		qt.set(label, new Quad(label, dst, src1, src2, op));
	}

	void backpatch(List<Integer> list, int targetLabel) {
		int targetLabelId = symTabStack.getLast().insert(targetLabel + "", DataType.INT);
		System.out.println("targetLabel: " + targetLabel);
		for (int item : list) {
			System.out.println("item: " + item);
			Quad q = qt.get(item);
			switch(q.getOp()) {
				case "goto":
					q.setSrc1(targetLabelId);
					break;
				case "if":
				case "ifFalse":
					q.setSrc2(targetLabelId);
					break;
			}
		}
	}

	int NextInstr() {
		return (qt.size());
	}

	void Print() {
		for(Quad q : qt) {
			q.Print();
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
: Class Program '{' field_decls method_decls '}'
{
	//Print
	//symTabStack.Print();
	System.out.println("------------------------------------");
	q.debug();
	System.out.println("------------------------------------");
	q.Print();
}
;

field_decls 
: f=field_decls field_decl ';'
| f=field_decls inited_field_decl ';'
| 
;


field_decl returns [DataType t]
: f=field_decl ',' Ident
{
	$t = $f.t;
	symTabStack.getFirst().insert($Ident.text, $t);
}
| f=field_decl ',' Ident '[' num ']'
{
	$t = $f.t;
	symTabStack.getFirst().insert($Ident.text, $t, $num.text);
}
| Type Ident
{
	$t = DataType.valueOf($Type.text.toUpperCase());
	symTabStack.getFirst().insert($Ident.text, $t);
}
| Type Ident '[' num ']'
{
	$t = DataType.valueOf($Type.text.toUpperCase());
	symTabStack.getFirst().insert($Ident.text, $t, $num.text);
}
;

inited_field_decl
: Type Ident '=' literal 
{
	int id = symTabStack.getFirst()
		.insert($Ident.text, DataType.valueOf($Type.text.toUpperCase()));
	q.Add(id, $literal.id, -1, "=");
}
;

method_decls
: m=method_decls method_decl
{
}
|
{
}
;


method_decl 
: Type Ident '(' params ')' markerTemp block
{
	//System.out.println("resolve method");
	int id = symTabStack.getFirst()
		.insert($Ident.text, DataType.valueOf($Type.text.toUpperCase()));

	q.Set($markerTemp.label, -1, id, -1, "method_decl"); //fill in temp slot
	q.Add(-1, -1, -1, "end_of_meth");
}
| Void Ident '(' params ')' markerTemp block
{
	//System.out.println("resolve method");
	int id = symTabStack.getFirst()
		.insert($Ident.text, DataType.valueOf($Void.text.toUpperCase()));	

	q.Set($markerTemp.label, -1, id, -1, "method_decl"); //fill in temp slot
	q.Add(-1, -1, -1, "end_of_meth");
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

block returns [List<Integer> nextList]
: '{' var_decls statements '}'
{
	//System.out.println("resolve block");
	$nextList = $statements.nextList;
}
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



statements returns [List<Integer> nextList]
: statement t=statements m=marker
{
	/*for(int item : $statement.nextList) {
		System.out.println("nextList item: " + item);
	}*/
	
	//$nextList = $t.nextList;
	$nextList = $statement.nextList;
	q.backpatch($nextList, $m.label);
}
|
{
	$nextList = new ArrayList<>();
	symTabStack.pop();
}
;


statement returns [List<Integer> nextList]
: location eqOp expr ';'
{
	$nextList = new ArrayList<>();
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
				q.Add(secondTempId, tempId, $expr.id, "+");

				q.Add($location.id, $location.offset, secondTempId, "[]w");
			} else {
				int tempId = symTabStack.getLast().Add(symTabStack.GetType($location.id));
				q.Add(tempId, $location.id, $expr.id, "+");

				q.Add($location.id, tempId, -1, "=");
			}
			break;

		case "-=":
			if ($location.isArrAccess){
				int tempId = symTabStack.getLast().Add(symTabStack.GetType($location.id));
				q.Add(tempId, $location.id, $location.offset, "[]r");

				int secondTempId = symTabStack.getLast().Add(symTabStack.GetType($location.id));
				q.Add(secondTempId, tempId, $expr.id, "-");

				q.Add($location.id, $location.offset, secondTempId, "[]w");
			} else {
				int tempId = symTabStack.getLast().Add(symTabStack.GetType($location.id));
				q.Add(tempId, $location.id, $expr.id, "-");

				q.Add($location.id, tempId, -1, "=");
			}
			break;
		default:
			break;
	}
}
| If '(' expr ')' m=marker block
{
	/*for(int item : $expr.trueList) {
		System.out.println("trueList item: " + item);
	}*/

	q.backpatch($expr.trueList, $m.label);
	
	List<Integer> merged = new ArrayList<>();
	merged.addAll($expr.falseList);
	merged.addAll($block.nextList);
	$nextList = merged;
}
| If '(' expr ')' b1=block Else b2=block
{

}
| For Ident '=' e1=expr ',' e2=expr block
{

}
| Ret ';'
{
	q.Add(-1, -1, -1, "ret");
}
| Ret '(' expr ')' ';'
{
	q.Add(-1, $expr.id, -1, "ret");
}
| Brk ';'
{

}
| Cnt ';'
{

}
| block
{
	$nextList = $block.nextList;
}
| methodCall ';'
{
	q.Add(-1, $methodCall.id, $methodCall.argsCount, "call");
}
;

methodCall returns [int id, int argsCount]
: Ident '(' args ')'
{
	$id = symTabStack.Find($Ident.text);
	$argsCount = $args.count;
}
| Callout '(' Str calloutArgs ')'
{
	$id = symTabStack.getLast().insert($Str.text, DataType.STR);
	$argsCount = $calloutArgs.count;
}
;

args returns [int count]
: someArgs
{
	$count = $someArgs.count;
}
|
{
	$count = 0;
}
;

someArgs returns [int count]
: t=someArgs ',' expr
{
	$count = $t.count + 1;
}
| expr
{
	$count = 1;
}
;

calloutArgs returns [int count]
: c=calloutArgs ',' expr
{
	q.Add(-1, $expr.id, -1, "param");
	
	$count = $c.count + 1;
}
| c=calloutArgs ',' Str
{
	int id = symTabStack.getLast().insert($Str.text, DataType.STR);
	q.Add(-1, id, -1, "param");

	$count = $c.count + 1;
}
|
{
	$count = 0;
}
;


expr returns [
	int id,
	List<Integer> trueList,
	List<Integer> falseList
]
: literal 
{
	$id = $literal.id;

	if ($literal.text.equals("true")) {
		$trueList = new ArrayList<>();
		$trueList.add(q.NextInstr());
		q.Add(-1, -1, -1, "goto");

	} else if ($literal.text.equals("false")) {
		$falseList = new ArrayList<>();
		$falseList.add(q.NextInstr());
		q.Add(-1, -1, -1, "goto");
	}
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
	$trueList = $e.trueList;
	$falseList = $e.falseList;
}
| SubOp e=expr
{
	SymTab st = symTabStack.getLast();
	$id = st.Add(symTabStack.GetType($e.id));
	int zeroId = st.insert("0", DataType.INT);
	q.Add($id, zeroId, $e.id, $SubOp.text);
}
| '!' e=expr
{
	SymTab st = symTabStack.getLast();
	$id = st.Add(symTabStack.GetType($e.id));
	q.Add($id, $e.id, -1, "!");

	$trueList = $e.falseList;
	$falseList = $e.trueList;
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

	$trueList = new ArrayList<>();
	$trueList.add(q.NextInstr());
	q.Add(-1, $id, -1, "if");

	$falseList = new ArrayList<>();
	$falseList.add(q.NextInstr());
	q.Add(-1, $id, -1, "ifFalse");
}
| e1=expr AndOp m=marker e2=expr
{
	$id = -1;
	q.backpatch($e1.trueList, $m.label);
	
	List<Integer> merged = new ArrayList<>();
	merged.addAll($e1.falseList);
	merged.addAll($e2.falseList);
	$falseList = merged;

	$trueList = $e2.trueList;
	/*SymTab st = symTabStack.getLast();
	$id = st.Add(symTabStack.GetType($e1.id));
	q.Add($id, $e1.id, $e2.id, $AndOp.text);*/

}
| e1=expr OrOp e2=expr
{
	$id = -1;
	/*SymTab st = symTabStack.getLast();
	$id = st.Add(symTabStack.GetType($e1.id));
	q.Add($id, $e1.id, $e2.id, $OrOp.text);*/
}
| methodCall
{
	SymTab st = symTabStack.getLast();
	$id = st.Add(symTabStack.GetType($methodCall.id));
	int argsNumId = st.insert(Integer.toString($methodCall.argsCount), DataType.INT);
	q.Add($id, $methodCall.id, $methodCall.argsCount, "call");
}
;


location returns [int id, int offset, boolean isArrAccess]
: Ident
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
{
	$id = symTabStack.getLast().insert($Char.text, DataType.CHAR);
}
| BoolLit
{
	$id = symTabStack.getLast().insert($BoolLit.text, DataType.BOOLEAN);
}
;

eqOp
: '='
| AssignOp
;

//helper markers 
//one only marks while the other adds a new row in the quad table and mark it

marker returns [int label]
:
{
	$label = q.NextInstr();
}
;

markerTemp returns [int label]
:
{
	$label = q.Add(-1, -1, -1, null);
}
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

