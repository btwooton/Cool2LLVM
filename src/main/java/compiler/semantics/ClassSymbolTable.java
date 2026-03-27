package compiler.semantics;

import java.util.Map;
import java.util.HashMap;

public class ClassSymbolTable {
    String className;
    Map<String, MethodInfo> methods;
    ClassSymbolTable parentClassTable;

    public ClassSymbolTable(String className, ClassSymbolTable parentTable) {
        this.className = className;
        methods = new HashMap<>();
        this.parentClassTable = parentTable;
    }

    public String getClassName() {
        return className;
    }

    public Map<String, MethodInfo> getMethodsMap() {
        return methods;
    }

    public MethodInfo getMethodInfo(String methodName) {
        return methods.get(methodName);
    }

    public ClassSymbolTable getParentTable() {
        return parentClassTable;
    }
}
