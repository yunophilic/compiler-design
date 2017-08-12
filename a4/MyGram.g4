grammar MyGram;


@header {

import x86.*;
import java.io.*;

}



@parser::members {







	

	SymStack s = new SymStack();

	QuadTab q = new QuadTab(s);


}




//---------------------------------------------------------------------------------------------------


prog
: Class Program '{' field_decls
{
	s.BlockEntry();
}
method_decls '}'
{
	s.Print();
	//print global variables
	

	System.out.println(".globl main");
	System.out.println(".data");
	s.PrintGlobals();
	System.out.println(".text");

	q.AsmPrint();
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
	Symbol sym = s.Add($Ident.text, $t);
}
| f=field_decl ',' Ident '[' num ']'
{
	$t = $f.t;
	Symbol sym = s.Add($Ident.text, $t, Integer.parseInt($num.text));
}
| Type Ident
{
	$t = DataType.valueOf($Type.text.toUpperCase());
	Symbol sym = s.Add($Ident.text, $t);
}
| Type Ident '[' num ']'
{
	$t = DataType.valueOf($Type.text.toUpperCase());
	Symbol sym = s.Add($Ident.text, $t, Integer.parseInt($num.text));
}

;

inited_field_decl 
: Type Ident '=' literal 
{
	DataType t = DataType.valueOf($Type.text.toUpperCase());
	Symbol src1 = s.insert($literal.text, t, Boolean.TRUE);
	Symbol dst = s.Add($Ident.text, t, src1);
	//q.Add(dst, src1, null, "=");
}
;

method_decls 
: m=method_decls method_decl
|
;

method_decl returns [int stackSize, LocList retList]
: Type Ident 
{
	DataType t = DataType.valueOf($Type.text.toUpperCase());
	Symbol sym = s.Add($Ident.text, t);
	s.FunctionEntry();
	q.Add(sym);
	int entry = q.Add(null, null, null, "frame");

}
'(' params ')'
{

	$retList = new LocList();
	$retList.Add(entry);
}
 block marker
{
	
	s.PopSymTab(q);

	$retList.Merge($block.retList);
	$retList.BackPatch(q, s.insert("" + s.GetOffset(), DataType.INT, Boolean.TRUE));
}
| Void Ident 
{
	DataType t = DataType.VOID;
	Symbol sym = s.Add($Ident.text, t);
	s.FunctionEntry();
	q.Add(sym);
	int entry = q.Add(null, null, null, "frame");	
}
'(' params ')' 
{

	$retList = new LocList();
	$retList.Add(entry);	
}
block marker
{
	if (!$block.nextlist.IsEmpty()) {
		q.Add(null, null, null, "ret");
		$block.nextlist.BackPatch (q, $marker.label);
	}
	s.PopSymTab(q);

	$retList.Merge($block.retList);	
	$retList.BackPatch(q, s.insert("" + s.GetOffset(), DataType.INT, Boolean.TRUE));
}
;

params returns [int count]
: p=params ',' Type Ident
{
	DataType t = DataType.valueOf($Type.text.toUpperCase());
	Symbol sym = s.Add($Ident.text, t);

	$count = $p.count + 1;
	switch ($count) {
		case 1: q.Add (null, null, null, "push %rdi");
				break;
		case 2: q.Add (null, null, null, "push %rsi");
				break;
		case 3: q.Add (null, null, null, "push %rdx");
				break;
		case 4: q.Add (null, null, null, "push %rcx");
				break;
		case 5: q.Add (null, null, null, "push %r8");
				break;
		case 6: q.Add (null, null, null, "push %r9");
				break;
	}	

}
| Type Ident
{
	DataType t = DataType.valueOf($Type.text.toUpperCase());
	Symbol sym = s.Add($Ident.text, t);

	$count = 1;
	q.Add (null, null, null, "push %rdi");
}
|
{
	$count = 0;
}
; 



block returns [LocList nextlist, LocList brklist, LocList cntlist, LocList retList]
: '{' 
{	
	s.BlockEntry();
}
var_decls statements '}'
{
	$nextlist = $statements.nextlist;
	$brklist = $statements.brklist;
	$cntlist = $statements.cntlist;
	$retList = $statements.retList;

	s.PopSymTab(q);
}
;

var_decls 
: v=var_decls var_decl ';'
{
}
| 
;


var_decl returns [DataType t]
: v=var_decl ',' Ident
{
	$t = $v.t;
	Symbol sym = s.Add($Ident.text, $t);
}
| Type Ident
{
	$t = DataType.valueOf($Type.text.toUpperCase());
	Symbol sym = s.Add($Ident.text, $t);
}
;

statements returns [LocList nextlist, LocList brklist, LocList cntlist, LocList retList]
: t=statements marker statement
{
	$t.nextlist.BackPatch(q, $marker.label);
	$nextlist = $statement.nextlist;
	$brklist = $t.brklist;
	$brklist.Merge ($statement.brklist);
	$cntlist = $t.cntlist;
	$cntlist.Merge ($statement.cntlist);	

	$retList = $t.retList;
	$retList.Merge($statement.retList);

}
|
{
	$nextlist = new LocList ();
	$brklist = new LocList ();
	$cntlist = new LocList ();
	$retList = new LocList ();
}
;


statement returns [LocList nextlist, LocList brklist, LocList cntlist, LocList retList]
: location eqOp expr ';'
{
	if ($eqOp.text.equals("+=")) {
		Symbol sym1 = s.Add($location.base.GetType());
		if ($location.offset != null) q.Add(sym1, $location.base, $location.offset, "[]=");
		else q.Add(sym1, $location.base, null, "=");
		Symbol sym2 = s.Add($location.base.GetType());
		q.Add(sym2, sym1, $expr.sym, "+");
		if ($location.offset != null) {
			q.Add($location.base, $location.offset, sym2, "[]=");
		} else {
			q.Add($location.base, sym2, null, "=");
		}
	} else if ($eqOp.text.equals("-=")) {
		Symbol sym1 = s.Add($location.base.GetType());
		if ($location.offset != null) q.Add(sym1, $location.base, $location.offset, "[]=");
		else q.Add(sym1, $location.base, null, "=");
		Symbol sym2 = s.Add($location.base.GetType());
		q.Add(sym2, sym1, $expr.sym, "-");
		if ($location.offset != null) {
			q.Add($location.base, $location.offset, sym2, "[]=");
		} else {
			q.Add($location.base, sym2, null, "=");
		}	
	} else {
		if ($location.offset != null) {
			q.Add($location.base, $location.offset, $expr.sym, "[]=");
		} else {
			q.Add($location.base, $expr.sym, null, "=");
		}
	}
	$nextlist = new LocList();
	$brklist = new LocList ();
	$cntlist = new LocList ();
	$retList = new LocList ();
}
| If '(' expr ')' marker block
{
	
	$expr.truelist.BackPatch(q, $marker.label);
	$nextlist = $expr.falselist;
	$nextlist.Merge($block.nextlist);

	$brklist = $block.brklist;
	$cntlist = $block.cntlist;
	$retList = $block.retList;
}
| If '(' expr ')' m1=marker b1=block next Else m2=marker b2=block
{
	$expr.truelist.BackPatch(q, $m1.label);
	$expr.falselist.BackPatch(q, $m2.label);
	$nextlist = $b1.nextlist;
	$nextlist.Merge($b2.nextlist);
	$nextlist.Merge($next.nextlist);

	$brklist = $b1.brklist;
	$brklist.Merge ($b2.brklist);
	$cntlist = $b1.cntlist;
	$cntlist.Merge ($b2.brklist);
	$retList = $b1.retList;
	$retList.Merge ($b2.retList);
}
| For Ident '=' e1=expr ',' 
{
	q.Add(s.Find($Ident.text), $e1.sym, null, "=");

	$retList = new LocList ();	
}
m1=marker e2=expr 
{
	Symbol sym = s.Add(DataType.INT);
	q.Add(sym, s.Find($Ident.text), $e2.sym, "cmp");
	$nextlist = new LocList ();
	$nextlist.Add(q.Add(null, sym, null, "jle"));
}
block m2=marker 
{
	

	Symbol one = s.insert("1", DataType.INT);
	q.Add(s.Find($Ident.text), s.Find($Ident.text), one, "+");
	q.Add($m1.label, null, null, "goto");

	$block.nextlist.BackPatch(q, $m2.label);
	$nextlist.Merge ($block.brklist);
	$block.cntlist.BackPatch (q, $m2.label);

	$brklist = new LocList ();
	$cntlist = new LocList ();
	$retList = $block.retList;
}
| Brk ';'
{
	$nextlist = new LocList ();
		
	$brklist = new LocList ();
	$brklist.Add (q.Add (null, null, null, "goto"));

	$cntlist = new LocList ();
	$retList = new LocList ();
}
| Cnt ';'
{

	$nextlist = new LocList ();

	$cntlist = new LocList ();
	$cntlist.Add (q.Add (null, null, null, "goto"));

	$brklist = new LocList ();
	$retList = new LocList ();
}
| block
{
	$nextlist = $block.nextlist;
	$brklist = $block.brklist;
	$cntlist = $block.cntlist;
	$retList = $block.retList;
}
| Ret ';'
{
	int retins = q.Add (null, null, null, "ret");
	$nextlist = new LocList ();	
	$brklist = new LocList ();	
	$cntlist = new LocList ();	
	$retList = new LocList ();
	$retList.Add(retins);
}
| Ret '(' expr ')' ';'
{
	int retins = q.Add (null, $expr.sym, null, "ret");
	$nextlist = new LocList ();	
	$brklist = new LocList ();	
	$cntlist = new LocList ();
	$retList = new LocList ();
	$retList.Add(retins);
}
| methodCall ';'
{
	$nextlist = new LocList ();	
	$brklist = new LocList ();	
	$cntlist = new LocList ();
	$retList = new LocList ();
}
;

expr returns [Symbol sym, LocList truelist, LocList falselist]
: literal
{
	$sym = $literal.sym;
}
| location
{
	if ($location.offset != null) {
		$sym = s.Add($location.base.GetType());
		q.Add($sym, $location.base, $location.offset, "[]");
	} else {
		$sym = $location.base;
	}
}
| '(' e=expr ')'
{
	$sym = $e.sym;
	$truelist = $e.truelist;
	$falselist = $e.falselist;
}
| AddSub e=expr
{
	$sym = s.Add(DataType.INT);
	q.Add($sym, s.insert("0", DataType.INT), $e.sym, $AddSub.text); 
}
| e1=expr MulDiv e2=expr
{
	$sym = s.Add(DataType.INT);
	q.Add($sym, $e1.sym, $e2.sym, $MulDiv.text);
}
| e1=expr AddSub e2=expr
{
	$sym = s.Add(DataType.INT);
	q.Add($sym, $e1.sym, $e2.sym, $AddSub.text);
}
| e1=expr '%' e2=expr
{
	Symbol sym1 = s.Add(DataType.INT);
	q.Add(sym1, $e1.sym, $e2.sym, "/");
	Symbol sym2 = s.Add(DataType.INT);
	q.Add(sym2, sym1, $e2.sym, "*");
	$sym = s.Add(DataType.INT);
	q.Add($sym, $e1.sym, sym2, "-");
}
| e1=expr '<' e2=expr
{
	$sym = s.Add(DataType.BOOLEAN);
	q.Add($sym, $e1.sym, $e2.sym, "cmp");
	$truelist = new LocList ();
	$truelist.Add(q.Add(null, $sym, null, "jg"));
	$falselist = new LocList ();
	$falselist.Add(q.Add(null, $sym, null, "jle"));
}
| e1=expr '>' e2=expr
{
	$sym = s.Add(DataType.BOOLEAN);
	q.Add($sym, $e1.sym, $e2.sym, "cmp");
	$truelist = new LocList ();
	$truelist.Add(q.Add(null, $sym, null, "jl"));
	$falselist = new LocList ();
	$falselist.Add(q.Add(null, $sym, null, "jge"));
}
| e1=expr '<=' e2=expr
{
	$sym = s.Add(DataType.BOOLEAN);
	q.Add($sym, $e1.sym, $e2.sym, "cmp");
	$truelist = new LocList ();
	$truelist.Add(q.Add(null, $sym, null, "jge"));
	$falselist = new LocList ();
	$falselist.Add(q.Add(null, $sym, null, "jl"));
}
| e1=expr '>=' e2=expr
{
	$sym = s.Add(DataType.BOOLEAN);
	q.Add($sym, $e1.sym, $e2.sym, "cmp");
	$truelist = new LocList ();
	$truelist.Add(q.Add(null, $sym, null, "jle"));
	$falselist = new LocList ();
	$falselist.Add(q.Add(null, $sym, null, "jg"));
}
| e1=expr '==' e2=expr
{
	$sym = s.Add(DataType.BOOLEAN);
	q.Add($sym, $e1.sym, $e2.sym, "cmp");
	$truelist = new LocList ();
	$truelist.Add(q.Add(null, $sym, null, "je"));
	$falselist = new LocList ();
	$falselist.Add(q.Add(null, $sym, null, "jne"));
}
| e1=expr '!=' e2=expr
{
	$sym = s.Add(DataType.BOOLEAN);
	q.Add($sym, $e1.sym, $e2.sym, "cmp");
	$truelist = new LocList ();
	$truelist.Add(q.Add(null, $sym, null, "jne"));
	$falselist = new LocList ();
	$falselist.Add(q.Add(null, $sym, null, "je"));
}
| '!' e=expr 
{
	$truelist = $e.falselist;
	$falselist = $e.truelist;
}
| e1=expr '&&' marker e2=expr
{
	$e1.truelist.BackPatch(q, $marker.label);
	$truelist = $e2.truelist;
	$falselist = $e1.falselist;
	$falselist.Merge($e2.falselist);
}
| e1=expr '||' marker e2=expr
{
	$e1.falselist.BackPatch(q, $marker.label);
	$truelist = $e1.truelist;
	$falselist = $e2.falselist;
	$truelist.Merge($e2.truelist);
}
| Ident '(' args ')'
{
	$sym = s.Add (s.Find ($Ident.text).GetType());
	String count = "" + $args.count;
	q.Add ($sym, s.Find ($Ident.text) , s.insert(count, DataType.INT), "callexp");	
}
| Callout '(' Str calloutArgs ')'
{
	$sym = s.Add (DataType.INT);
	String count = "" + $calloutArgs.count;
	q.Add ($sym, s.insert ($Str.text, DataType.STR), s.insert(count, DataType.INT), "callexp");
};

methodCall 
: Ident '(' args ')'
{
	String count = "" + $args.count;
	q.Add (null, s.Find ($Ident.text) , s.insert(count, DataType.INT), "call");	
}
| Callout '(' Str calloutArgs ')'
{
	String count = "" + $calloutArgs.count;
	q.Add (null, s.insert ($Str.text.replace("\"", ""), DataType.STR), s.insert(count, DataType.INT), "call");
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

	switch ($count) {
		case 1: q.Add ($expr.sym, null, null, "rdi");
				break;
		case 2: q.Add ($expr.sym, null, null, "rsi");
				break;
		case 3: q.Add ($expr.sym, null, null, "rdx");
				break;
		case 4: q.Add ($expr.sym, null, null, "rcx");
				break;
		case 5: q.Add ($expr.sym, null, null, "r8");
				break;
		case 6: q.Add ($expr.sym, null, null, "r9");
				break;
	}	

}
| expr
{
	$count = 1;
	switch ($count) {
		case 1: q.Add ($expr.sym, null, null, "rdi");
				break;
		case 2: q.Add ($expr.sym, null, null, "rsi");
				break;
		case 3: q.Add ($expr.sym, null, null, "rdx");
				break;
		case 4: q.Add ($expr.sym, null, null, "rcx");
				break;
		case 5: q.Add ($expr.sym, null, null, "r8");
				break;
		case 6: q.Add ($expr.sym, null, null, "r9");
				break;
	}	
	
}

;

calloutArgs returns [int count]
: c=calloutArgs ',' expr
{
	
	$count = $c.count + 1;
	switch ($count) {
		case 1: q.Add ($expr.sym, null, null, "rdi");
				break;
		case 2: q.Add ($expr.sym, null, null, "rsi");
				break;
		case 3: q.Add ($expr.sym, null, null, "rdx");
				break;
		case 4: q.Add ($expr.sym, null, null, "rcx");
				break;
		case 5: q.Add ($expr.sym, null, null, "r8");
				break;
		case 6: q.Add ($expr.sym, null, null, "r9");
				break;
	}	
}
| c=calloutArgs ',' Str
{
	Symbol str = s.insertString($Str.text, DataType.STR);
	$count = $c.count + 1;
	switch ($count) {
		case 1: q.Add (str, null, null, "rdi");
				break;
		case 2: q.Add (str, null, null, "rsi");
				break;
		case 3: q.Add (str, null, null, "rdx");
				break;
		case 4: q.Add (str, null, null, "rcx");
				break;
		case 5: q.Add (str, null, null, "r8");
				break;
		case 6: q.Add (str, null, null, "r9");
				break;
	}	
}
|
{
	$count = 0;
}
;


marker returns [Symbol label]
:
{
	int l = q.GetNextLabel();
	$label = s.Add(l);
}
;

next returns [LocList nextlist]
:
{
	$nextlist = new LocList();
	$nextlist.Add(q.Add(null, null, null, "goto"));
}
;


location returns [Symbol base, Symbol offset]
:Ident
{
	$base = s.Find($Ident.text);
	$offset = null;
}
| Ident '[' expr ']'
{
	$base = s.Find($Ident.text);
	$offset = s.Add(DataType.INT);
	q.Add($offset, $expr.sym, s.insert("8", DataType.INT, Boolean.TRUE), "*");
}
;

num returns [Symbol sym]
: DecNum
{
	$sym = s.Add($text, DataType.INT, Boolean.TRUE);
}
| HexNum
{
	$sym = s.Add($text, DataType.INT, Boolean.TRUE);
}
;

literal returns [Symbol sym]
: num
{
	$sym = $num.sym;
}
| Char
{
	$sym = s.Add($text, DataType.INT, Boolean.TRUE);
}
| BoolLit
{
	$sym = s.Add($text, DataType.BOOLEAN, Boolean.TRUE);
}
;

eqOp
: '='
| AssignOp
;






//-----------------------------------------------------------------------------------------------------------
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



AssignOp
: '+='
| '-='
;

MulDiv
: '*'
| '/'
;

AddSub
: '+'
| '-'
;













