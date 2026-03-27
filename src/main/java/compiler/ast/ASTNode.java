package compiler.ast;
import compiler.visitors.ASTVisitor;

public abstract class ASTNode {
    protected String inferredType;
    public int lineNumber;

    public ASTNode(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public abstract <T> T accept (ASTVisitor<T> visitor);
    
    public void annotate(String inferredType) {
        this.inferredType = inferredType;
    }

    public String getInferredType() {
        return inferredType;
    }
}
