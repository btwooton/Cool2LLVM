package compiler.ast;

import compiler.visitors.ASTVisitor;

public class AttributeFeatureNode extends ASTNode {
    public String featureName;
    public String featureType;
    public ExprNode initExpr; // null if no initialization expression

    public AttributeFeatureNode(int lineNumber, String featureName, String featureType, ExprNode initExpr) {
        super(lineNumber);
        this.featureName = featureName;
        this.featureType = featureType;
        this.initExpr = initExpr;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitAttributeFeature(this);
    } 
}
