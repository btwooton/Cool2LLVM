package compiler.ast;

public class LetExprNode extends ExprNode {

    public java.util.List<LetBindingNode> letBindings;
    public ExprNode body;

    public LetExprNode(int lineNumber, java.util.List<LetBindingNode> letBindings, ExprNode body) {
        super(lineNumber);
        this.letBindings = letBindings;
        this.body = body;
    }

    @Override
    public <T> T accept(compiler.visitors.ASTVisitor<T> visitor) {
        return visitor.visitLetExpr(this);
    }
    
}
