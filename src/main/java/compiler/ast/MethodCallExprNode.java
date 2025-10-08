package compiler.ast;
import java.util.List;
import compiler.visitors.ASTVisitor;

public class MethodCallExprNode extends ExprNode {
    public ExprNode caller; // may be null fo implicit self calls
    public String referencedType; // null if no explicit type referenced
    public String methodName; // name of the method being called
    public List<ExprNode> parameters; // actual parameters provided in the call

    public MethodCallExprNode(int lineNumber, ExprNode caller, String referencedType, String methodName, List<ExprNode> parameters) {
        super(lineNumber);
        this.caller = caller;
        this.referencedType = referencedType;
        this.methodName = methodName;
        this.parameters = parameters;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitMethodCallExpr(this);
    }
}
