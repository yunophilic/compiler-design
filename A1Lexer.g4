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

fragment Char
: (.)
;

//===Tokens===

WhiteSpace
: Delim+ -> skip
;

Callout
: 'callout'
;

OParen
: '('
;

CParen
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

Type
: ('int' | 'boolean')
;

Break
: 'break'
;

Continue
: 'continue'
;

AssignOp
: ['=' '+=' '-=']
;

ArithOp
: ['+' '-' '*' '/' '%']
;

RelOp
: ['<' '>' '<=' '>=']
;

EqOp
: ('==' | '!=')
;

CondOp
: ('&&' | '||')
;

DecimalLiteral
: Digit+
;

HexLiteral
: '0x'HexDigit+
;

IntLiteral
: (DecimalLiteral | HexLiteral)
;

BoolLiteral
: ('true' | 'false')
;

CharLiteral
: Char
;

StringLiteral
: Char*
;

Id
: (Alpha)(AlphaNum*)
;