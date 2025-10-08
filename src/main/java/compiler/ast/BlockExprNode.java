package compiler.ast;
import java.util.List;

public class BlockExprNode extends ExprNode {
    public List<ExprNode> expressions;

    public BlockExprNode(int lineNumber, List<ExprNode> expressions) {
        super(lineNumber);
        this.expressions = expressions;
    }

    @Override
    public <T> T accept(compiler.visitors.ASTVisitor<T> visitor) {
        return visitor.visitBlockExpr(this);
    }

}
