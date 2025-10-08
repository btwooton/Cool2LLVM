package compiler.ast;
import compiler.visitors.ASTVisitor;

public class LiteralExprNode extends ExprNode {
    public enum LiteralType {
        INT, STRING, BOOL
    }

    public LiteralType type;
    public Object value;

    public LiteralExprNode(int lineNumber, LiteralType type, Object value) {
        super(lineNumber);
        this.type = type;
        this.value = value;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        // Assuming there's a visitLiteralExpr method in ASTVisitor
        return visitor.visitLiteralExpr(this);
    }
    
}
