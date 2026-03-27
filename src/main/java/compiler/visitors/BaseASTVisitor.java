package compiler.visitors;

import compiler.ast.*;

public class BaseASTVisitor<T> implements ASTVisitor<T> {

    protected T defaultValue() {
        return null;
    }

    @Override
    public T visitProgram(ProgramNode node) {
        return defaultValue();
    }

    @Override
    public T visitClass(ClassNode node) {
        return defaultValue();
    }

    @Override
    public T visitAttributeFeature(AttributeFeatureNode node) {
        // TODO Auto-generated method stub
        return defaultValue();
    }

    @Override
    public T visitMethodFeature(MethodFeatureNode node) {
        return defaultValue();
    }

    @Override
    public T visitFormal(FormalNode node) {
        return defaultValue();
    }

    @Override
    public T visitAssignExpr(AssignExprNode node) {
        return defaultValue();
    }

    @Override
    public T visitMethodCallExpr(MethodCallExprNode node) {
        return defaultValue();
    }

    @Override
    public T visitConditionalExpr(ConditionalExprNode node) {
        return defaultValue();
    }

    @Override
    public T visitLoopExpr(LoopExprNode node) {
        return defaultValue();
    }

    @Override
    public T visitBlockExpr(BlockExprNode node) {
        return defaultValue();
    }

    @Override
    public T visitLetExpr(LetExprNode node) {
        return defaultValue();
    }

    @Override
    public T visitLetBinding(LetBindingNode node) {
        return defaultValue();
    }

    @Override
    public T visitCaseExpr(CaseExprNode node) {
        return defaultValue();
    }

    @Override
    public T visitNewExpr(NewExprNode node) {
        return defaultValue();
    }

    @Override
    public T visitIsVoidExpr(IsVoidExprNode node) {
        return defaultValue();
    }

    @Override
    public T visitBinaryOpExpr(BinaryOpExprNode node) {
        return defaultValue();
    }

    @Override
    public T visitUnaryOpExpr(UnaryOpExprNode node) {
        return defaultValue();
    }

    @Override
    public T visitIdentifierExpr(IdentifierExprNode node) {
        return defaultValue();
    }

    @Override
    public T visitLiteralExpr(LiteralExprNode node) {
        return defaultValue();
    }

    @Override
    public T visitParenExpr(ParenExprNode node) {
        return defaultValue();
    }

    @Override
    public T visitNoExpr(NoExprNode node) {
        return defaultValue();
    }

    @Override
    public T visitExpr(ExprNode node) {
        return defaultValue();
    }
    
}
