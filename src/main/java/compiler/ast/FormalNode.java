package compiler.ast;
import compiler.visitors.ASTVisitor;

public class FormalNode extends ASTNode {
    public String formalName;
    public String formalType; 
    public FormalNode(int lineNumber, String formalName, String formalType) {
        super(lineNumber);
        this.formalName = formalName;
        this.formalType = formalType;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitFormal(this);
    }
}
