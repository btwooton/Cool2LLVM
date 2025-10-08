package compiler.ast;
import compiler.visitors.ASTVisitor;

public abstract class ExprNode extends ASTNode {
    // This class can be extended to represent different kinds of expressions
    public ExprNode(int lineNumber) {
        super(lineNumber);
    }

    @Override
    public abstract <T> T accept(ASTVisitor<T> visitor);
}

