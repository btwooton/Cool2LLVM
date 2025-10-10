package compiler.ast;
import java.util.List;
import compiler.visitors.ASTVisitor;

public class ClassNode extends ASTNode {
    public String className;
    public String parentName; // null if no parent
    public List<AttributeFeatureNode> attributes;
    public List<MethodFeatureNode> methods;
    

    public ClassNode(int lineNumber, String className, String parentName, List<AttributeFeatureNode> attributes, List<MethodFeatureNode> methods) {
        super(lineNumber);
        this.className = className;
        this.parentName = parentName;
        this.attributes = attributes;
        this.methods = methods;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitClass(this);
    }
}
