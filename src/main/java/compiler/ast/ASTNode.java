package compiler.ast;
import compiler.visitors.ASTVisitor;

public abstract class ASTNode {
    public int lineNumber;

    public ASTNode(int lineNumber) {
        this.lineNumber = lineNumber;
    }

    public abstract <T> T accept (ASTVisitor<T> visitor);
}
