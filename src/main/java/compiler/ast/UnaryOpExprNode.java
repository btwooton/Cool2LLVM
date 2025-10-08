package compiler.ast;
import compiler.visitors.ASTVisitor;

public class UnaryOpExprNode extends ExprNode {
    public enum Op {
        COMP, NOT
    }
    public Op operator;
    public ExprNode expr;

    public UnaryOpExprNode(int lineNumber, Op operator, ExprNode expr) {
        super(lineNumber);
        this.operator = operator;
        this.expr = expr;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitUnaryOpExpr(this);
    }
}