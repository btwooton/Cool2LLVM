package compiler.semantics;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.Collection;
import compiler.ast.ClassNode;
import compiler.ast.MethodFeatureNode;
import compiler.ast.NoExprNode;
import compiler.ast.FormalNode;

import java.util.List;

public class ClassTable {
    private final Map<String, ClassNode> classes;
    private static final Set<String> BASIC_CLASSES = Set.of( 
        "Object", "IO", "Int", "Bool", "String"
    );
    private SemanticErrorLogger logger;

    public ClassTable(SemanticErrorLogger logger) {
        classes = new HashMap<>();
        this.logger = logger;
        // install the base classes on construction
        installBaseClasses();
    }

    private void installBaseClasses() {
        // Install the Object class
        classes.put(
            "Object",
            new ClassNode(
                -1, 
                "Object", 
                null, 
                List.of(), 
                List.of(
                    new MethodFeatureNode(
                        -1, "abort", "Object", List.of(), new NoExprNode()
                    ),
                    new MethodFeatureNode(
                        -1, "type_name", "String", List.of(), new NoExprNode()
                    ),
                    new MethodFeatureNode(
                        -1, "copy", "SELF_TYPE", List.of(), new NoExprNode()
                    )
                )
            )
        );

        // install the IO class
        classes.put(
            "IO",
            new ClassNode(
                -1, "IO", "Object", List.of(),
                List.of(
                    new MethodFeatureNode(
                        -1, "out_string", "SELF_TYPE", 
                        List.of(new FormalNode(-1, "x", "String")),
                        new NoExprNode()
                    ),
                    new MethodFeatureNode( 
                        -1, "out_int", "SELF_TYPE",
                        List.of(new FormalNode(-1, "x", "Int")),
                        new NoExprNode()
                    ),
                    new MethodFeatureNode( 
                        -1, "in_string", "String",
                        List.of(), new NoExprNode()
                    ),
                    new MethodFeatureNode( 
                        -1, "in_int", "Int",
                        List.of(), new NoExprNode()
                    )
                )
            )
        );

        // install the Int class
        classes.put( 
            "Int",
            new ClassNode(-1, "Int", "Object", List.of(), List.of())
        );

        // install the String class
        classes.put( 
            "String",
            new ClassNode(
                -1, "String", "Object", List.of(),
                List.of( 
                    new MethodFeatureNode(
                        -1, "length", "Int", 
                        List.of(), new NoExprNode()
                    ),
                    new MethodFeatureNode( 
                        -1, "concat", "String",
                        List.of(new FormalNode(-1, "s", "String")),
                        new NoExprNode()
                    ),
                    new MethodFeatureNode( 
                        -1, "substr", "String",
                        List.of(
                            new FormalNode(-1, "i", "Int"),
                            new FormalNode(-1, "l", "Int")
                        ),
                        new NoExprNode()
                    )
                )
            )
        );

        // Install the Bool class
        classes.put(
            "Bool", 
            new ClassNode(-1, "Bool", "Object", List.of(), List.of())
        );
    }

    public boolean contains(String name) {
        return classes.containsKey(name);
    }

    public void addClass(ClassNode cls) {
        if (BASIC_CLASSES.contains(cls.className)) {
            logger.log( 
                cls.lineNumber,
                "Redefinition of basic class " + cls.className
            );
            return;
        } else if (classes.containsKey(cls.className)) {
            logger.log(cls.lineNumber, "Class " + cls.className + " already defined");
            return;
        }
        classes.put(cls.className, cls);
    }

    public ClassNode lookup(String name) {
        if (name == null) {
            return null;
        }
        return classes.get(name);
    }

    public Collection<ClassNode> getAllClasses() {
        return classes.values();
    }

    public boolean isBasicClass(String name) {
        return BASIC_CLASSES.contains(name);
    }

    public String getEffectiveParent(ClassNode cls) {
        if (cls.className.equals("Object")) return null;
        return cls.parentName != null ? cls.parentName : "Object";
    }

    public boolean isAncestorOf(String className, String ancestorName) {
        if (className == null) {
            return false;
        }
        if (className.equals(ancestorName)) {
            // a class is its own ancestor
            return true;
        }
        // get the node associated with the className
        ClassNode node = classes.get(className);
        if (node == null) {
            return false;
        }
        if (ancestorName == null) {
            return className.equals("Object");
        }

        String parentName = node.parentName;

        if (parentName != null && parentName.equals(ancestorName)) {
            return true;
        } else {
            // recursively check if the ancestor is ancestor of parent
            return isAncestorOf(parentName, ancestorName);
        }
    }
    
}
