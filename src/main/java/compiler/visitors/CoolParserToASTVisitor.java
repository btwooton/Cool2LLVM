package compiler.visitors;

import compiler.ast.ASTNode;
import grammar.CoolParserBaseVisitor;


public class CoolParserToASTVisitor extends CoolParserBaseVisitor<ASTNode> {

    @Override
    public ASTNode visitIntegerLiteralExpr(grammar.CoolParser.IntegerLiteralExprContext ctx) {
        return new compiler.ast.LiteralExprNode(
            ctx.getStart().getLine(),
            compiler.ast.LiteralExprNode.LiteralType.INT,
            Integer.parseInt(ctx.getStart().getText())
        );
    }

    @Override
    public ASTNode visitMultExpr(grammar.CoolParser.MultExprContext ctx) {
        compiler.ast.ExprNode left = (compiler.ast.ExprNode) visit(ctx.getChild(0));
        compiler.ast.ExprNode right = (compiler.ast.ExprNode) visit(ctx.getChild(2));
        return new compiler.ast.BinaryOpExprNode(
            ctx.getStart().getLine(),
            compiler.ast.BinaryOpExprNode.Op.MUL,
            left,
            right
        );
    }

    @Override
    public ASTNode visitPlusExpr(grammar.CoolParser.PlusExprContext ctx) {
        compiler.ast.ExprNode left = (compiler.ast.ExprNode) visit(ctx.getChild(0));
        compiler.ast.ExprNode right = (compiler.ast.ExprNode) visit(ctx.getChild(2));
        return new compiler.ast.BinaryOpExprNode(
            ctx.getStart().getLine(),
            compiler.ast.BinaryOpExprNode.Op.ADD,
            left,
            right
        );
    }

    @Override
    public ASTNode visitMinusExpr(grammar.CoolParser.MinusExprContext ctx) {
        compiler.ast.ExprNode left = (compiler.ast.ExprNode) visit(ctx.getChild(0));
        compiler.ast.ExprNode right = (compiler.ast.ExprNode) visit(ctx.getChild(2));
        return new compiler.ast.BinaryOpExprNode(
            ctx.getStart().getLine(),
            compiler.ast.BinaryOpExprNode.Op.SUB,
            left,
            right
        );
    }

    @Override
    public ASTNode visitDivExpr(grammar.CoolParser.DivExprContext ctx) {
        compiler.ast.ExprNode left = (compiler.ast.ExprNode) visit(ctx.getChild(0));
        compiler.ast.ExprNode right = (compiler.ast.ExprNode) visit(ctx.getChild(2));
        return new compiler.ast.BinaryOpExprNode(
            ctx.getStart().getLine(),
            compiler.ast.BinaryOpExprNode.Op.DIV,
            left,
            right
        );
    }

    @Override 
    public ASTNode visitEqExpr(grammar.CoolParser.EqExprContext ctx) {
        compiler.ast.ExprNode left = (compiler.ast.ExprNode) visit(ctx.getChild(0));
        compiler.ast.ExprNode right = (compiler.ast.ExprNode) visit(ctx.getChild(2));
        return new compiler.ast.BinaryOpExprNode(
            ctx.getStart().getLine(),
            compiler.ast.BinaryOpExprNode.Op.EQ,
            left,
            right
        );
    }

    @Override 
    public ASTNode visitLtExpr(grammar.CoolParser.LtExprContext ctx) {
        compiler.ast.ExprNode left = (compiler.ast.ExprNode) visit(ctx.getChild(0));
        compiler.ast.ExprNode right = (compiler.ast.ExprNode) visit(ctx.getChild(2));
        return new compiler.ast.BinaryOpExprNode(
            ctx.getStart().getLine(),
            compiler.ast.BinaryOpExprNode.Op.LT,
            left,
            right
        );
    }

    @Override 
    public ASTNode visitLeExpr(grammar.CoolParser.LeExprContext ctx) {
        compiler.ast.ExprNode left = (compiler.ast.ExprNode) visit(ctx.getChild(0));
        compiler.ast.ExprNode right = (compiler.ast.ExprNode) visit(ctx.getChild(2));
        return new compiler.ast.BinaryOpExprNode(
            ctx.getStart().getLine(),
            compiler.ast.BinaryOpExprNode.Op.LE,
            left,
            right
        );
    }
    
}
