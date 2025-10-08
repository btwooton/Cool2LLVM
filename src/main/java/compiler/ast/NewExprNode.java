package compiler.ast;
import compiler.visitors.ASTVisitor;

public class NewExprNode extends ExprNode {
    public String typeName;

    public NewExprNode(int lineNumber, String typeName) {
        super(lineNumber);
        this.typeName = typeName;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitNewExpr(this);
    }
    
}
