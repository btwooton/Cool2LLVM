package compiler.ast;

import compiler.visitors.ASTVisitor;

public class NoExprNode extends ExprNode {
    public NoExprNode() {
        super(-1);
    }

    @Override
    public <T> T accept(ASTVisitor<T> visitor) {
        return visitor.visitNoExpr(this);
    } 

}
