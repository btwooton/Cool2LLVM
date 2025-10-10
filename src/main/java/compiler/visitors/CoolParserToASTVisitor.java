package compiler.visitors;

import compiler.ast.ASTNode;
import grammar.CoolParserBaseVisitor;
import java.util.stream.Collectors;


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

    @Override 
    public ASTNode visitIdentifierExpr(grammar.CoolParser.IdentifierExprContext ctx) {
        return new compiler.ast.IdentifierExprNode(
            ctx.getStart().getLine(),
            ctx.getText()
        );
    }

    @Override 
    public ASTNode visitIsvoidExpr(grammar.CoolParser.IsvoidExprContext ctx) {
        return new compiler.ast.IsVoidExprNode(
            ctx.getStart().getLine(),
            (compiler.ast.ExprNode) visit(ctx.getChild(1))
        );
    }

    @Override 
    public ASTNode visitNewExpr(grammar.CoolParser.NewExprContext ctx) {
        return new compiler.ast.NewExprNode(
            ctx.getStart().getLine(),
            ctx.getChild(1).getText()
        );
    }

    @Override 
    public ASTNode visitCaseExpr(grammar.CoolParser.CaseExprContext ctx) {
        return new compiler.ast.CaseExprNode(
            ctx.getStart().getLine(),
            (compiler.ast.ExprNode) visit(ctx.case_expr),
            ctx.identifiers.stream().map((id) -> {
                // Find the index of this identifier in the list
                int index = ctx.identifiers.indexOf(id);
                String varName = id.getText();
                String varType = ctx.types.get(index).getText();
                compiler.ast.ExprNode branchExpr = (compiler.ast.ExprNode) visit(ctx.sub_exprs.get(index));
                return new compiler.ast.CaseBranch(varName, varType, branchExpr);
            }).collect(Collectors.toList())
        );
    }

    @Override
    public ASTNode visitLetExpr(grammar.CoolParser.LetExprContext ctx) {
        return new compiler.ast.LetExprNode(
            ctx.getStart().getLine(),
            ctx.let_bindings.stream().map((bindingCtx) -> {
                return (compiler.ast.LetBindingNode) bindingCtx.accept(this);
            }).collect(Collectors.toList()),
            (compiler.ast.ExprNode) visit(ctx.body)
        );
    }

    @Override
    public ASTNode visitLet_binding(grammar.CoolParser.Let_bindingContext ctx) {
        String varName = ctx.identifier.getText();
        String varType = ctx.type.getText();
        compiler.ast.ExprNode initExpr = null;
        if (ctx.initializer != null) {
            initExpr = (compiler.ast.ExprNode) visit(ctx.initializer);
        }
        return new compiler.ast.LetBindingNode(
            ctx.getStart().getLine(),
            varName,
            varType,
            initExpr
        );
    }

    @Override
    public ASTNode visitBlockExpr(grammar.CoolParser.BlockExprContext ctx) {
        return new compiler.ast.BlockExprNode(
            ctx.getStart().getLine(),
            ctx.exprs.stream().map((exprCtx) -> {
                return (compiler.ast.ExprNode) visit(exprCtx);
            }).collect(Collectors.toList())
        );
    }

    @Override
    public ASTNode visitLoopExpr(grammar.CoolParser.LoopExprContext ctx) {
        return new compiler.ast.LoopExprNode(
            ctx.getStart().getLine(),
            (compiler.ast.ExprNode) visit(ctx.condition),
            (compiler.ast.ExprNode) visit(ctx.body)
        );
    }

    @Override
    public ASTNode visitAssignExpr(grammar.CoolParser.AssignExprContext ctx) {
        return new compiler.ast.AssignExprNode(
            ctx.getStart().getLine(),
            ctx.identifier.getText(),
            (compiler.ast.ExprNode) visit(ctx.expression)
        );
    }

    @Override
    public ASTNode visitConditionalExpr(grammar.CoolParser.ConditionalExprContext ctx) {
        return new compiler.ast.ConditionalExprNode(
            ctx.getStart().getLine(),
            (compiler.ast.ExprNode) visit(ctx.condition),
            (compiler.ast.ExprNode) visit(ctx.then_expr),
            (compiler.ast.ExprNode) visit(ctx.else_expr)
        );
    }
}
