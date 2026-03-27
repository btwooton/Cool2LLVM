package compiler.ast;
import compiler.visitors.ASTVisitor;

public abstract class ExprNode extends ASTNode {
    protected String inferredType;
    // This class can be extended to represent different kinds of expressions
    public ExprNode(int lineNumber) {
        super(lineNumber);
    }

    @Override
    public abstract <T> T accept(ASTVisitor<T> visitor);

    public void annotate(String inferredType) {
        this.inferredType = inferredType;
    }

    public String getInferredType() {
        return inferredType;
    }
}

