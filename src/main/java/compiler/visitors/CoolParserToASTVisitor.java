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
    public ASTNode visitStringLiteralExpr(grammar.CoolParser.StringLiteralExprContext ctx) {
        // Remove the surrounding quotes from the string literal
        String text = ctx.getStart().getText();
        String value = text.substring(1, text.length() - 1);
        return new compiler.ast.LiteralExprNode(
            ctx.getStart().getLine(),
            compiler.ast.LiteralExprNode.LiteralType.STRING,
            value
        );
    }

    @Override
    public ASTNode visitFalseLiteralExpr(grammar.CoolParser.FalseLiteralExprContext ctx) {
        return new compiler.ast.LiteralExprNode(
            ctx.getStart().getLine(),
            compiler.ast.LiteralExprNode.LiteralType.BOOL,
            false
        );
    }

    @Override
    public ASTNode visitTrueLiteralExpr(grammar.CoolParser.TrueLiteralExprContext ctx) {
        return new compiler.ast.LiteralExprNode(
            ctx.getStart().getLine(),
            compiler.ast.LiteralExprNode.LiteralType.BOOL,
            true
        );
    }

    @Override
    public ASTNode visitMultExpr(grammar.CoolParser.MultExprContext ctx) {
        compiler.ast.ExprNode left = (compiler.ast.ExprNode) visit(ctx.left);
        compiler.ast.ExprNode right = (compiler.ast.ExprNode) visit(ctx.right);
        return new compiler.ast.BinaryOpExprNode(
            ctx.getStart().getLine(),
            compiler.ast.BinaryOpExprNode.Op.MUL,
            left,
            right
        );
    }

    @Override
    public ASTNode visitPlusExpr(grammar.CoolParser.PlusExprContext ctx) {
        compiler.ast.ExprNode left = (compiler.ast.ExprNode) visit(ctx.left);
        compiler.ast.ExprNode right = (compiler.ast.ExprNode) visit(ctx.right);
        return new compiler.ast.BinaryOpExprNode(
            ctx.getStart().getLine(),
            compiler.ast.BinaryOpExprNode.Op.ADD,
            left,
            right
        );
    }

    @Override
    public ASTNode visitMinusExpr(grammar.CoolParser.MinusExprContext ctx) {
        compiler.ast.ExprNode left = (compiler.ast.ExprNode) visit(ctx.left);
        compiler.ast.ExprNode right = (compiler.ast.ExprNode) visit(ctx.right);
        return new compiler.ast.BinaryOpExprNode(
            ctx.getStart().getLine(),
            compiler.ast.BinaryOpExprNode.Op.SUB,
            left,
            right
        );
    }

    @Override
    public ASTNode visitDivExpr(grammar.CoolParser.DivExprContext ctx) {
        compiler.ast.ExprNode left = (compiler.ast.ExprNode) visit(ctx.left);
        compiler.ast.ExprNode right = (compiler.ast.ExprNode) visit(ctx.right);
        return new compiler.ast.BinaryOpExprNode(
            ctx.getStart().getLine(),
            compiler.ast.BinaryOpExprNode.Op.DIV,
            left,
            right
        );
    }

    @Override 
    public ASTNode visitEqExpr(grammar.CoolParser.EqExprContext ctx) {
        compiler.ast.ExprNode left = (compiler.ast.ExprNode) visit(ctx.left);
        compiler.ast.ExprNode right = (compiler.ast.ExprNode) visit(ctx.right);
        return new compiler.ast.BinaryOpExprNode(
            ctx.getStart().getLine(),
            compiler.ast.BinaryOpExprNode.Op.EQ,
            left,
            right
        );
    }

    @Override 
    public ASTNode visitLtExpr(grammar.CoolParser.LtExprContext ctx) {
        compiler.ast.ExprNode left = (compiler.ast.ExprNode) visit(ctx.left);
        compiler.ast.ExprNode right = (compiler.ast.ExprNode) visit(ctx.right);
        return new compiler.ast.BinaryOpExprNode(
            ctx.getStart().getLine(),
            compiler.ast.BinaryOpExprNode.Op.LT,
            left,
            right
        );
    }

    @Override 
    public ASTNode visitLeExpr(grammar.CoolParser.LeExprContext ctx) {
        compiler.ast.ExprNode left = (compiler.ast.ExprNode) visit(ctx.left);
        compiler.ast.ExprNode right = (compiler.ast.ExprNode) visit(ctx.right);
        return new compiler.ast.BinaryOpExprNode(
            ctx.getStart().getLine(),
            compiler.ast.BinaryOpExprNode.Op.LE,
            left,
            right
        );
    }

    @Override
    public ASTNode visitParenExpr(grammar.CoolParser.ParenExprContext ctx) {
        return new compiler.ast.ParenExprNode(
            ctx.getStart().getLine(),
            (compiler.ast.ExprNode) visit(ctx.getChild(1))
        );
    }

    @Override
    public ASTNode visitComplementExpr(grammar.CoolParser.ComplementExprContext ctx) {
        return new compiler.ast.UnaryOpExprNode(
            ctx.getStart().getLine(),
            compiler.ast.UnaryOpExprNode.Op.COMP,
            (compiler.ast.ExprNode) visit(ctx.getChild(1))
        );
    }

    @Override
    public ASTNode visitNotExpr(grammar.CoolParser.NotExprContext ctx) {
        return new compiler.ast.UnaryOpExprNode(
            ctx.getStart().getLine(),
            compiler.ast.UnaryOpExprNode.Op.NOT,
            (compiler.ast.ExprNode) visit(ctx.getChild(1))
        );
    }
    
}
