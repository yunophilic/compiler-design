grammar A2Syntax;

//---------------------------------------------------------------------------------------------------
// Session 1: ANTLR tree plotting API, You SHOULD NOT make any modification to this session
//---------------------------------------------------------------------------------------------------
@header {

import java.io.*;
}



@parser::members {
        //AST node count
	int count = 0;
	String graph = "";

	int GetId() {
		return count++;
	}

	
	public class MySet {

		


		int[] ids;
		int size;

		MySet () {
			System.out.println("\n\nInitArray\n-------------");


			ids = new int [100];		
			size = 0;

		
		}

		void ExtendArray(int val) {
			System.out.println("\n\nExtendArray\n-------------\nsize = " + size + "\nval = " + val);

			ids[size] = val;
			size ++;

		
		}

		void AppendArray(MySet s) {
			for (int i = 0; i < s.size; i ++) {
				ExtendArray(s.ids[i]);
			}
		}



	}//MySet

	String ProcessString(String s) {
		String x = "\\" + s.substring(0, s.length() - 1) + "\\\"";
		return x;
	}

	int PrintNode (String label) {
		System.out.println("\n\nPrintNode\n-------------\nlabel = " + label + "\nid = " + count);

		int id = GetId();
		graph += (id + " [label=\"" + label + "\"]\n");
		return id;

	}

	void PrintEdge (int id1, int id2) {
		System.out.println("\n\nPrintEdge\n-------------\nid1 = " + id1 + "\nid2 = " + id2);

		
		if ((id1 != -1) && (id2 != -1)) graph += (id1 + " -> " + id2 + "\n");
		
		

	}

	void PrintEdges (int id, MySet s) {
		System.out.println("\n\nPrintEdges\n-------------\nid = " + id + "\nsize = " + s.size);

		
		for (int i = 0; i < s.size; i ++) {
			PrintEdge(id, s.ids[i]);
		}
	}

	void PrintGraph () throws IOException {
		System.out.println("\n\nPrintGraph\n-------------");
		

		File file = new File("test.dot");
		file.createNewFile();
		FileWriter writer = new FileWriter(file); 
		writer.write("digraph G {\nordering=out\n" + graph + "\n}\n"); 
		writer.flush();
		writer.close();
		

		System.out.println("digraph G {\nordering=out\n" + graph + "\n}\n");
	}




	
}


//---------------------------------------------------------------------------------------------------
// Session 2: Fill the Grammer definition here
//---------------------------------------------------------------------------------------------------
prog
: Class Program '{' field_decls method_decls '}'
{
	
	int id = PrintNode("Program");

	if ($field_decls.s.size > 0) {
		int id2 = PrintNode("Field_decls");
		PrintEdges(id2, $field_decls.s);
		PrintEdge(id, id2);
	}

	if ($method_decls.s.size > 0) {
		int id2 = PrintNode("Method_decls");
		PrintEdges(id2, $method_decls.s);
		PrintEdge(id, id2);
	}

	
	

	try {PrintGraph();} catch(IOException e) {}
}
;

field_decls returns [MySet s]
: f=field_decls field_decl ';'
{
	$s = $f.s;
	$s.ExtendArray($field_decl.id);
}
| f=field_decls inited_field_decl ';'
{
	$s = $f.s;
	$s.ExtendArray($inited_field_decl.id);
}
| 
{
	$s = new MySet();
}
;


field_decl returns [int id]
: f=field_decl ',' Ident
{
	$id = $f.id;

	PrintEdge($f.id, PrintNode($Ident.text));
}
| Type Ident
{
	$id = PrintNode("Field_decl");

	PrintEdge($id, PrintNode($Type.text));
	PrintEdge($id, PrintNode($Ident.text));
}
;

// <field_decl> -> <type> <id> = <literal> ;
inited_field_decl returns [int id]
: Type Ident '=' literal 
{
	$id = PrintNode("Inited_field_decl");

	PrintEdge($id, PrintNode($Type.text));
	PrintEdge($id, PrintNode($Ident.text));
	PrintEdge($id, PrintNode($literal.text));
}
;



method_decls returns [MySet s]
: m=method_decls method_decl
{
	
	$s = $m.s;
	$s.ExtendArray($method_decl.id);
}
|
{
	$s = new MySet();
	
	
}
;

// <method_decl> -> ( <type> | void ) <id> ( ((<type> <id>) ( , <type> <id>)*)? ) 
method_decl returns [int id]
: Type Ident '(' params ')' block
{
	$id = PrintNode("Method_decl");

	PrintEdge($id, PrintNode($Type.text));
	PrintEdge($id, PrintNode($Ident.text));
	PrintEdge($id, $params.id);
	PrintEdge($id, $block.id);	
}
| Void Ident '(' params ')' block
{
	$id = PrintNode("Method_decl");

	PrintEdge($id, PrintNode("void"));
	PrintEdge($id, PrintNode($Ident.text));
	PrintEdge($id, $params.id);
	PrintEdge($id, $block.id);
	
}
;

// <method_call> -> <method_name> '(' (<expr> ( , <expr> )*)? ')'
method_call returns [int id]
: Ident '(' method_call_args ')'
{
	$id = PrintNode("Call_expr");
	PrintEdge($id, PrintNode($Ident.text));
	PrintEdges($id, $method_call_args.s);
}

// <method_call> -> callout ( <string_literal> ( , <callout_arg> )* )
| callout
{
	$id = PrintNode("Call_expr");
	PrintEdge($id, $callout.id);
}
;

callout returns [int id]
: Callout '(' callout_args ')'
{
	$id = PrintNode("Call");
	PrintEdges($id, $callout_args.s);
}
;

//===method call arguments===

method_call_args returns [MySet s]
: m=method_call_args ',' expr_arg
{
	$s = $m.s;
	$s.ExtendArray($expr_arg.id);
}
| expr_arg
{
	$s = new MySet();
	$s.ExtendArray($expr_arg.id);
}
|
{
	$s = new MySet();
}
;

//===callout arguments===

callout_args returns [MySet s]
: c=callout_args ',' callout_arg
{
	$s = $c.s;
	$s.ExtendArray($callout_arg.id);
}
| str_arg
{
	$s = new MySet();
	$s.ExtendArray($str_arg.id);
}
;

callout_arg returns [int id]
: str_arg 
{
	$id = $str_arg.id;
}
| expr_arg
{
	$id = $expr_arg.id;
}
;

//===method arguments===

expr_arg returns [int id]
: expr
{
	$id = PrintNode("Expr_arg");
	PrintEdge($id, $expr.id);
}
;

str_arg returns [int id]
: Str 
{
	$id = PrintNode("String_arg");
	PrintEdge($id, PrintNode(ProcessString($Str.text)));
}
;

//=======================

params returns [int id]
: Type Ident nextParams
{
	$id = PrintNode("Method_args");
	
	PrintEdge($id, PrintNode($Type.text));
	PrintEdge($id, PrintNode($Ident.text));

	PrintEdges($id, $nextParams.s);
}
|
{
	$id = -1;
}
;

nextParams returns [MySet s]
: n=nextParams ',' Type Ident
{
	$s = $n.s;
	
	$s.ExtendArray(PrintNode($Type.text));
	$s.ExtendArray(PrintNode($Ident.text));
}
|
{
	$s = new MySet();
}
;

// <block> -> { <var_decl>* <statement>* }
block returns [int id]
: '{' var_decls statements '}'
{
	$id = -1;
	if ($var_decls.s.size > 0) {
		$id = PrintNode("Block");
		int id2 = PrintNode("Var_decls");
		PrintEdges(id2, $var_decls.s);
		PrintEdge($id, id2);
	}
	if ($statements.id != -1) {
		if ($id == -1) $id = PrintNode("Block");
		PrintEdge($id, $statements.id);
	}
}
;

var_decls returns [MySet s]
: v=var_decls var_decl ';'
{
	$s = $v.s;
	$s.ExtendArray($var_decl.id);
}
| 
{
	$s = new MySet();
}
;


var_decl returns [int id]
: v=var_decl ',' Ident
{
	$id = $v.id;
	
	PrintEdge($id, PrintNode($Ident.text));
}
| Type Ident
{
	$id = PrintNode("Var_decl");
	
	PrintEdge($id, PrintNode($Type.text));
	PrintEdge($id, PrintNode($Ident.text));
}
;

statements returns [int id]
: statement t=statements
{
	if ($t.id != -1) {
		$id = PrintNode("Seq");
		PrintEdge($id, $statement.id);
		PrintEdge($id, $t.id);
	} else {
		$id = $statement.id;
	}
}
|
{
	$id = -1;
}
;


// <statement> -> <location> <assign_op> <expr> ';'
statement returns [int id]
: location eqOp expr ';'
{
	$id = PrintNode("Assign");
	PrintEdge($id, $location.id);
	PrintEdge($id, PrintNode($eqOp.text));
	PrintEdge($id, $expr.id);
}

// <statement> -> <method_call> ';'
| method_call ';'
{
	$id = $method_call.id;
}

// <statement> -> if '(' <expr> ')' <block> ( else <block> )?
| If '(' expr ')' block
{
	$id = PrintNode("If");
	PrintEdge($id, $expr.id);
	PrintEdge($id, $block.id);
}
| If '(' expr ')' b1=block Else b2=block 
{
	$id = PrintNode("If_Else");
	PrintEdge($id, $expr.id);
	PrintEdge($id, $b1.id);
	PrintEdge($id, $b2.id);
}

// <statement> -> for <id> = <expr> , <expr> <block>
| For Ident eqOp e1=expr ',' e2=expr block
{
	$id = PrintNode("For");
	PrintEdge($id, PrintNode($Ident.text));
	PrintEdge($id, $e1.id);
	PrintEdge($id, $e2.id);
	PrintEdge($id, $block.id);
}

// <statement> -> return ( <expr> )? ';'
| Ret (expr)? ';'
{
	$id = PrintNode("Ret");
	if ($expr.text != null)
	{
		PrintEdge($id, $expr.id);
	}
}

// <statement> -> break ';'
| Brk ';'
{
	$id = PrintNode("Break");
}

// <statement> -> continue ';'
| Cnt ';'
{
	$id = PrintNode("Cont");
}

// <statement> -> <block>
| block
{
	$id = $block.id;
}
;

// <expr> -> <location>
expr returns [int id]
: location
{
	$id = PrintNode("Loc_expr");
	PrintEdge($id, $location.id);
}

// <expr> -> <method_call>
| method_call
{
	$id  = $method_call.id;
}

// <expr> -> <literal>
| literal
{
	$id = PrintNode("Const_expr");
	PrintEdge($id, PrintNode($literal.text));
}

// <expr> -> <expr> <bin_op> <expr>
| e1=expr binOp e2=expr
{
	$id = PrintNode("Bin_expr");
	PrintEdge($id, $e1.id);
	PrintEdge($id, PrintNode($binOp.text));
	PrintEdge($id, $e2.id);
}

// <expr> -> - <expr>
| '-' e=expr
{
	$id = PrintNode("Neg_expr");
	PrintEdge($id, $e.id);
}

// <expr> -> ! <expr>
| '!' e=expr
{
	$id = PrintNode("Not_expr");
	PrintEdge($id, $e.id);
}

// <expr> -> ( <expr> )
| '(' e=expr ')'
{
	$id = $e.id;
}
;

// <location> -> <id>
location returns [int id]
: Ident
{
	$id = PrintNode("Loc");
	PrintEdge($id, PrintNode($Ident.text));
}

// <location> -> <id> [ <expr> ]
| Ident '[' expr ']'
{
	$id = PrintNode("Array_loc");
	PrintEdge($id, PrintNode($Ident.text));
	PrintEdge($id, $expr.id);
}
;

//--------------------------------------------- END OF SESSION 2 -----------------------------------


//---------------------------------------------------------------------------------------------------
// Session 3: Lexical definition, You SHOULD NOT make any modification to this session
//---------------------------------------------------------------------------------------------------
num
: DecNum
| HexNum
;

literal
: num
| Char
| BoolLit
;

eqOp
: '='
| AssignOp
;

mathOp
: '-'
| ArithOp
;

boolOp
: '!'
| CondOp
;

binOp
: mathOp
| RelOp
| CondOp
;

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

ArithOp
: '+'
| '*'
| '/'
| '%'
;

CondOp
: '&&'
| '||'
;




