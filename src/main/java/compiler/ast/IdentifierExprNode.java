package compiler.ast;
import compiler.visitors.ASTVisitor;    

public class IdentifierExprNode extends ExprNode {
    public String name;

    public IdentifierExprNode(int lineNumber, String name) {
        super(lineNumber);
        this.name = name;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        // Assuming there's a visitIdentifierExpr method in ASTVisitor
        return visitor.visitIdentifierExpr(this);
    }
    
}
