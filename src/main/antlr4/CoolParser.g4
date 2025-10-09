parser grammar CoolParser;

options { tokenVocab=CoolLexer; }

@header {
package grammar;
}

program: (classes+=class SEMICOLON)+ EOF;
class: CLASS class_name=TypeID (INHERITS parent_name=TypeID)? LBRACE (features+=feature SEMICOLON)* RBRACE;
feature : method_name=ID LPAREN (formal_params+=formal (COMMA formal_params+=formal)*)? RPAREN COLON return_type=TypeID LBRACE body=expr RBRACE # methodFeature
        | attribute_name=ID COLON attribute_type=TypeID (ASSIGN initializer=expr)? # attributeFeature
        ;
formal: parameter_name=ID COLON parameter_type=TypeID;
expr
    : identifier=ID ASSIGN expression=expr # assignExpr
    | target=expr (AT referenced_type=TypeID)? DOT method_name=ID LPAREN (arguments+=expr (COMMA arguments+=expr)*)? RPAREN # methodCallExpr
    | method_name=ID LPAREN (arguments+=expr (COMMA arguments+=expr)*)? RPAREN # implicitMethodCallExpr
    | IF condition=expr THEN then_expr=expr ELSE else_expr=expr FI # conditionalExpr
    | WHILE condition=expr LOOP body=expr POOL # loopExpr
    | LBRACE (exprs+=expr SEMICOLON)+ RBRACE # blockExpr
    | LET identifiers+=ID COLON types+=TypeID (ASSIGN initializers+=expr)? (COMMA identifiers+=ID COLON types+=TypeID (ASSIGN initializers+=expr)?)* IN body=expr # letExpr
    | CASE case_expr=expr OF (identifiers+=ID COLON types+=TypeID ARROW sub_exprs+=expr SEMICOLON)+ ESAC # caseExpr
    | NEW TypeID # newExpr
    | ISVOID expr # isvoidExpr
    | left=expr MULT right=expr # multExpr
    | left=expr DIV right=expr # divExpr
    | left=expr PLUS right=expr # plusExpr
    | left=expr MINUS right=expr # minusExpr
    | TILDE expr # complementExpr
    | left=expr LT right=expr # ltExpr
    | left=expr LE right=expr # leExpr
    | left=expr EQ right=expr # eqExpr
    | NOT expr # notExpr
    | LPAREN expr RPAREN # parenExpr
    | ID # identifierExpr
    | Integer # integerLiteralExpr
    | String # stringLiteralExpr
    | TRUE # trueLiteralExpr
    | FALSE # falseLiteralExpr
    ;