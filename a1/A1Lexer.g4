lexer grammar A1Lexer;

//===Regex Variables==

fragment Delim
: ' '
| '\t'
| '\n'
;

fragment Alpha
: [a-zA-Z_]
;

fragment Digit
: [0-9]
;

fragment HexDigit
: Digit | [a-fA-F]
;

fragment AlphaNum
: Alpha | Digit
;

fragment SingleQuote
: '\''
;

fragment DoubleQuote
: '\"'
;

fragment Char
: (.)
;

//===Tokens===

WhiteSpace
: Delim+ -> skip
;

//===Keywords===

If
: 'if'
;

Else
: 'else'
;

For
: 'for'
;

/*While
: 'while'
;*/

Break
: 'break'
;

Continue
: 'continue'
;

Return
: 'return'
;

Class
: 'class'
;

Program
: 'Program'
;

Callout
: 'callout'
;

Type
: 'int' | 'boolean'
;

Void
: 'void'
;

//===Symbols===

OParenthesis
: '('
;

CParenthesis
: ')'
;

OSquareBracket
: '['
;

CSquareBracket
: ']'
;

OBraces
: '{'
;

CBraces
: '}'
;

SemiColon
: ';'
;

Comma
: ','
;

//===Operators===

RelOp
: '<' | '>' | '<=' | '>='
;

EqOp
: '==' | '!='
;

CondOp
: '&&' | '||'
;

NegOp
: '!'
;

AssignOp
: '=' | '+=' | '-='
;

ArithOp
: '+' | '-' | '*' | '/' | '%'
;

//===Literals===

DecimalLiteral
: Digit+
;

HexLiteral
: '0x' HexDigit+
;

/*IntLiteral
: DecimalLiteral | HexLiteral
;*/

BoolLiteral
: ('true' | 'false')
;

CharLiteral
: (SingleQuote)(Char)(SingleQuote)
;

StringLiteral
: (DoubleQuote)(Char*?)(DoubleQuote)
;

//==========

Id
: (Alpha)(AlphaNum*)
;