package compiler.ast;
import compiler.visitors.ASTVisitor;


public class IsVoidExprNode extends ExprNode {
    public ExprNode expr;

    public IsVoidExprNode(int lineNumber, ExprNode expr) {
        super(lineNumber);
        this.expr = expr;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitIsVoidExpr(this);
    }
}