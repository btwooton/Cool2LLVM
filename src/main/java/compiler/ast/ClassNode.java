package compiler.ast;
import java.util.List;
import compiler.visitors.ASTVisitor;

public class ClassNode extends ASTNode {
    public String className;
    public String parentName; // null if no parent
    public List<FeatureNode> features;

    public ClassNode(int lineNumber, String className, String parentName, List<FeatureNode> features) {
        super(lineNumber);
        this.className = className;
        this.parentName = parentName;
        this.features = features;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitClass(this);
    }
}
