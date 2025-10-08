package compiler.ast;
import java.util.List;
import compiler.visitors.ASTVisitor;

public class ProgramNode extends ASTNode {
    public List<ClassNode> classes;
    public ProgramNode(int lineNumber, List<ClassNode> classes) {
        super(lineNumber);
        this.classes = classes;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitProgram(this);
    }
}