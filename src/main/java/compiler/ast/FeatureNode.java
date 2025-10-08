package compiler.ast;
import java.util.List;
import compiler.visitors.ASTVisitor;

public class FeatureNode extends ASTNode {
    public String featureName;
    public String featureType;
    public ExprNode initExpr; // null if no initialization expression
    public List<FormalNode> formals; // null if attribute
    public ExprNode bodyExpr; // null if attribute, root of body if method

    public FeatureNode(int lineNumber, String featureName, String featureType, ExprNode initExpr, List<FormalNode> formals, ExprNode bodyExpr) {
        super(lineNumber);
        this.featureName = featureName;
        this.featureType = featureType;
        this.initExpr = initExpr;
        this.formals = formals;
        this.bodyExpr = bodyExpr;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitFeature(this);
    }


    
}
