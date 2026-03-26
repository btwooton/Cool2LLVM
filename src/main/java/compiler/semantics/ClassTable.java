package compiler.semantics;
import java.util.Map;
import java.util.HashMap;
import compiler.ast.ClassNode;

public class ClassTable {
    public Map<String, ClassNode> classes;

    public ClassTable() {
        classes = new HashMap<>();
    }

    public void installBaseClasses() {

    }
    
}
