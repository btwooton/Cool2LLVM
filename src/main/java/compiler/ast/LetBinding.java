package compiler.ast;

public class LetBinding {
    public String varName;
    public String varType;
    public ExprNode initExpr; // can be null if no initialization provided

    public LetBinding(String varName, String varType, ExprNode initExpr) {
        this.varName = varName;
        this.varType = varType;
        this.initExpr = initExpr;
    }
}