package compiler.ast;

public class ConditionalExprNode extends ExprNode  {
    public ExprNode condition;
    public ExprNode thenExpr;
    public ExprNode elseExpr; 

    public ConditionalExprNode(int lineNumber, ExprNode condition, ExprNode thenExpr, ExprNode elseExpr) {
        super(lineNumber);
        this.condition = condition;
        this.thenExpr = thenExpr;
        this.elseExpr = elseExpr;
    }

    @Override
    public <T> T accept(compiler.visitors.ASTVisitor<T> visitor) {
        return visitor.visitConditionalExpr(this);
    }
}
