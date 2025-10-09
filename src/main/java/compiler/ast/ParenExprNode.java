package compiler.ast;
import compiler.visitors.ASTVisitor;

public class ParenExprNode extends ExprNode {
    public ExprNode expr;

    public ParenExprNode(int line, ExprNode expr) {
        super(line);
        this.expr = expr;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitParenExpr(this);
    }
}
