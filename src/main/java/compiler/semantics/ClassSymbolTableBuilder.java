package compiler.semantics;
import java.util.Map;
import java.util.HashMap;
import java.util.ArrayList;
import compiler.ast.ClassNode;
import compiler.ast.MethodFeatureNode;
import compiler.ast.FormalNode;

public class ClassSymbolTableBuilder {
    Map<String, ClassSymbolTable> tables;

    public ClassSymbolTableBuilder() {
        tables = new HashMap<>(); 
    }
    
    public void build(ClassTable ct) {
        // Create all tables
        for (ClassNode node : ct.getAllClasses()) {
            tables.put(node.className, new ClassSymbolTable(node.className, null));
        }

        // Link parents
        for (ClassNode node : ct.getAllClasses()) {
            ClassSymbolTable cst = tables.get(node.className);
            String parentName = ct.getEffectiveParent(node);
            if (parentName != null) {
                cst.parentClassTable = tables.get(node.parentName);
            }
        }

        // Populate the tables with features
        for (ClassNode classNode : ct.getAllClasses()) {
            ClassSymbolTable cst = tables.get(classNode.className);
            for (MethodFeatureNode method : classNode.methods) {
                // build the method info
                MethodInfo mi = new MethodInfo(method.featureName, method.featureType);
                // build the parameter types list
                ArrayList<String> parameterTypes = new ArrayList<>();
                for (FormalNode formal : method.formals) {
                    parameterTypes.add(formal.formalType);
                }
                mi.paramTypes = parameterTypes;
                // set the defining class
                mi.definingClass = classNode.className;
                // add it to the class symbol table
                cst.methods.put(method.featureName, mi);
            }
        }
    }

    public Map<String, ClassSymbolTable> getClassSymbolTables() {
        return tables;
    }

}
