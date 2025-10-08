package compiler.ast;
import compiler.visitors.ASTVisitor;

public class AssignExprNode extends ExprNode {
    public String varName;
    public ExprNode rhs;
    public AssignExprNode(int lineNumber, String varName, ExprNode rhs) {
        super(lineNumber);
        this.varName = varName;
        this.rhs = rhs;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitAssignExpr(this);
    }
}