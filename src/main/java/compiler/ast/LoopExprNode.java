package compiler.ast;

public class LoopExprNode extends ExprNode {
    public ExprNode condition;
    public ExprNode body;

    public LoopExprNode(int lineNumber, ExprNode condition, ExprNode body) {
        super(lineNumber);
        this.condition = condition;
        this.body = body;
    }

    @Override
    public <T> T accept(compiler.visitors.ASTVisitor<T> visitor) {
        return visitor.visitLoopExpr(this);
    }
}