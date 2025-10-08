package compiler.ast;
import java.util.List;

public class CaseExprNode extends ExprNode {
    public ExprNode expr;
    public List<CaseBranch> branches;

    public CaseExprNode(int lineNumber, ExprNode expr, List<CaseBranch> branches) {
        super(lineNumber);
        this.expr = expr;
        this.branches = branches;
    }

    @Override
    public <T> T accept(compiler.visitors.ASTVisitor<T> visitor) {
        return visitor.visitCaseExpr(this);
    }

}
