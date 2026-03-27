package compiler.semantics;

public class SemanticError {

    private int lineNumber;
    private String errorString;

    public SemanticError(int lineNumber, String errorString) {
        this.lineNumber = lineNumber;
        this.errorString = errorString;
    }

    public String getErrorString() {
        return errorString;
    }

    public int getLineNumber() {
        return lineNumber;
    }

    @Override
    public String toString() {
        return "Line " + lineNumber + ": " + errorString;
    }
}
