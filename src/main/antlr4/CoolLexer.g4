
lexer grammar CoolLexer;

@lexer::header {
package grammar;
}

CLASS: [cC][lL][aA][sS][sS];
ELSE: [eE][lL][sS][eE]; // case insensitive keywords
FI: [fF][iI];
IF: [iI][fF];
IN: [iI][nN];
INHERITS: [iI][nN][hH][eE][rR][iI][tT][sS];
ISVOID: [iI][sS][vV][oO][iI][dD];
LET: [lL][eE][tT];
LOOP: [lL][oO][oO][pP];
POOL: [pP][oO][oO][lL];
THEN: [tT][hH][eE][nN];
WHILE: [wW][hH][iI][lL][eE];
CASE: [cC][aA][sS][eE];
ESAC: [eE][sS][aA][cC];
NEW: [nN][eE][wW];
OF: [oO][fF];
NOT: [nN][oO][tT];
TRUE: [t][rR][uU][eE]; // true and false must start with lowercase
FALSE: [f][aA][lL][sS][eE];
Integer : [1-9][0-9]*;
TypeID: [A-Z][a-zA-Z0-9_]*;
ID: [a-zA-Z_][a-zA-Z0-9_]*;
Self: 'self';
SelfType: 'SELF_TYPE';
String: '"' ( ESC | ~["\\\r\n] )* '"';
fragment ESC : '\\' [btnf"\\];
WS: [ \t\n\f\r]+ -> skip; // skip whitespace
LINE_COMMENT: '--' .*? ('\r'? '\n' | EOF) -> skip; // skip line comments
BLOCK_COMMENT: '(*' .*? '*)' -> skip; // skip block comments
SEMICOLON: ';';
LBRACE: '{';
RBRACE: '}';
LPAREN: '(';
RPAREN: ')';
COMMA: ',';
COLON: ':';
ASSIGN: '<-';
AT: '@';
DOT: '.';
ARROW: '=>';
PLUS: '+';
MINUS: '-';
MULT: '*';
DIV: '/';
TILDE: '~';
LT: '<';
LE: '<=';
EQ: '=';