package compiler.semantics;
import java.util.ArrayList;
import java.util.List;

public class SemanticErrorLogger {
    private ArrayList<SemanticError> errors;

    public SemanticErrorLogger() {
        errors = new ArrayList<>();
    }

    public void log(SemanticError error) {
        errors.add(error);
    }

    public void log(int line, String message) {
        errors.add(new SemanticError(line, message));
    }

    public boolean hasErrors() {
        return !errors.isEmpty();
    }

    public List<SemanticError> getErrors() {
        return errors;
    }

    public void printErrors() {
        for (SemanticError e : errors) {
            System.err.println(e);
        }
    }

    public void throwIfAny() {
        if (hasErrors()) {
            throw new RuntimeException("Semantic errors: \n" + formatErrors());
        }
    }

    public String formatErrors() {
        StringBuilder sb = new StringBuilder();
        for (SemanticError e : errors) {
            sb.append(e).append("\n");
        }
        return sb.toString();
    }
    
}
