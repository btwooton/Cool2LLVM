package compiler.ast;

public class LetBindingNode extends ASTNode {
    public String varName;
    public String varType;
    public ExprNode initExpr; // can be null if no initialization provided

    public LetBindingNode(int lineNumber, String varName, String varType, ExprNode initExpr) {
        super(lineNumber);
        this.varName = varName;
        this.varType = varType;
        this.initExpr = initExpr;
    }

    @Override
    public <T> T accept(compiler.visitors.ASTVisitor<T> visitor) {
        return visitor.visitLetBinding(this);
    }
}