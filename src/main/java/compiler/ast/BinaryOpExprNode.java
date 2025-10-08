package compiler.ast;
import compiler.visitors.ASTVisitor;

public class BinaryOpExprNode extends ExprNode {
    public enum Op {
        ADD, SUB, MUL, DIV, LT, LE, EQ
    }
    public Op operator;
    public ExprNode left;
    public ExprNode right;

    public BinaryOpExprNode(int lineNumber, Op operator, ExprNode left, ExprNode right) {
        super(lineNumber);
        this.operator = operator;
        this.left = left;
        this.right = right;
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitBinaryOpExpr(this);
    }

}
