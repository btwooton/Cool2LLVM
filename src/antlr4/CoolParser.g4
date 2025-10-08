parser grammar CoolParser;

options { tokenVocab=CoolLexer; }

@header {
package grammar;
}

program: (class SEMICOLON)+ EOF;
class: CLASS TypeID (INHERITS TypeID)? LBRACE (feature SEMICOLON)* RBRACE;
feature : ID LPAREN (formal (COMMA formal)*)? RPAREN COLON TypeID LBRACE expr RBRACE # methodFeature
        | ID COLON TypeID (ASSIGN expr)? # attributeFeature
        ;
formal: ID COLON TypeID;
expr
    : ID ASSIGN expr # assignExpr
    | expr (AT TypeID)? DOT ID LPAREN (expr (COMMA expr)*)? RPAREN # methodCallExpr
    | ID LPAREN (expr (COMMA expr)*)? RPAREN # implicitMethodCallExpr
    | IF expr THEN expr ELSE expr FI # conditionalExpr
    | WHILE expr LOOP expr POOL # loopExpr
    | LBRACE (expr SEMICOLON)+ RBRACE # blockExpr
    | LET ID COLON TypeID (ASSIGN expr)? (COMMA ID COLON TypeID (ASSIGN expr)?)* IN expr # letExpr
    | CASE expr OF (ID COLON TypeID ARROW expr SEMICOLON)+ ESAC # caseExpr
    | NEW TypeID # newExpr
    | ISVOID expr # isvoidExpr
    | expr MULT expr # multExpr
    | expr DIV expr # divExpr
    | expr PLUS expr # plusExpr
    | expr MINUS expr # minusExpr
    | TILDE expr # complementExpr
    | expr LT expr # ltExpr
    | expr LE expr # leExpr
    | expr EQ expr # eqExpr
    | NOT expr # notExpr
    | LPAREN expr RPAREN # parenExpr
    | ID # identifierExpr
    | Integer # integerLiteralExpr
    | String # stringLiteralExpr
    | TRUE # trueLiteralExpr
    | FALSE # falseLiteralExpr
    ;