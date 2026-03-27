package compiler.semantics;
import java.util.List;

public class MethodInfo {

    String methodName;
    String returnType;
    List<String> paramTypes;
    String definingClass;

    MethodInfo(String methodName, String returnType) {
        this.methodName = methodName;
        this.returnType = returnType;
    }

    public String getReturnType() {
        return returnType;
    }

    public List<String> getParamTypes() {
        return paramTypes;
    }

    public String getMethodName() {
        return methodName;
    }

    public boolean matches(MethodInfo other) {
        // confirm same number of parameters
        if (paramTypes.size() != other.paramTypes.size()) {
            return false;
        }
        // confirm parameter types match
        for (int i = 0; i < paramTypes.size(); i++) {
            if (paramTypes.get(i) != other.paramTypes.get(i)) {
                return false;
            }
        }
        // confirm method names and return types match
        return (
            methodName.equals(other.methodName) &&
            returnType.equals(other.returnType)
        );
    }
    
}
