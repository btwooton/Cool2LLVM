package compiler.ast;
import java.util.List;
import compiler.visitors.ASTVisitor;

public class MethodFeatureNode extends ASTNode {
    public String featureName;
    public String featureType;
    public List<FormalNode> formals; // null if attribute
    public ExprNode bodyExpr; // null if attribute, root of body if method

    public MethodFeatureNode(int lineNumber, String featureName, String featureType, List<FormalNode> formals, ExprNode bodyExpr) {
        super(lineNumber);
        this.featureName = featureName;
        this.featureType = featureType;
        this.formals = formals;
        this.bodyExpr = bodyExpr;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitMethodFeature(this);
    }


    
}
