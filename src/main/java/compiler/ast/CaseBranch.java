package compiler.ast;

public class CaseBranch {
    public String varName;
    public String varType;
    public ExprNode expr;

    public CaseBranch(String varName, String varType, ExprNode expr) {
        this.varName = varName;
        this.varType = varType;
        this.expr = expr;
    }
}
