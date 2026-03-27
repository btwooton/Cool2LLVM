package compiler.semantics;
import java.util.Map;
import java.util.HashMap;

public class Scope {
    private Scope parentScope;
    private Map<String, String> objectEnvironment;

    public Scope(Scope parentScope) {
        this.parentScope = parentScope;
        this.objectEnvironment = new HashMap<String, String>();
    }

    public Scope getParent() {
        return parentScope;
    }

    public Map<String, String> getEnvironment() {
        return objectEnvironment;
    }

    public boolean isDefined(String identifier) {
        // check the current environment
        if (!objectEnvironment.containsKey(identifier)) {
            // if the parent scope exists, check if defined in parent
            if (parentScope != null) {
                return parentScope.isDefined(identifier);
            } else { // otherwise, return false
                return false;
            }
        }
        return true;
    }

    public String getDefinedType(String identifier) {
        // if we don't have a binding, check the parent
        if (!objectEnvironment.containsKey(identifier)) {
            if (parentScope != null) {
                return parentScope.getDefinedType(identifier);
            } else {
                return null;
            }
        }
        // otherwise, return the current binding
        return objectEnvironment.get(identifier);
    }
}
