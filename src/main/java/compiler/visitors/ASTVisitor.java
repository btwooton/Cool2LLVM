package compiler.visitors;
import compiler.ast.*;

public interface ASTVisitor<T> {
    T visitProgram(ProgramNode node);
    T visitClass(ClassNode node);
    T visitFeature(FeatureNode node); 
    T visitFormal(FormalNode node);
    T visitAssignExpr(AssignExprNode node);
    T visitMethodCallExpr(MethodCallExprNode node);
    T visitConditionalExpr(ConditionalExprNode node);
    T visitLoopExpr(LoopExprNode node);
    T visitBlockExpr(BlockExprNode node);
    T visitLetExpr(LetExprNode node);
    T visitCaseExpr(CaseExprNode node);
    T visitNewExpr(NewExprNode node);
    T visitIsVoidExpr(IsVoidExprNode node);
    T visitBinaryOpExpr(BinaryOpExprNode node);
    T visitUnaryOpExpr(UnaryOpExprNode node);
    T visitIdentifierExpr(IdentifierExprNode node);
    T visitLiteralExpr(LiteralExprNode node);
    T visitParenExpr(ParenExprNode node);
}
