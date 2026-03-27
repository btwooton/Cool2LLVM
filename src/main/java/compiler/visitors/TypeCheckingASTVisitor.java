package compiler.visitors;
import compiler.ast.*;
import compiler.semantics.SemanticErrorLogger;
import compiler.semantics.ClassTable;
import compiler.semantics.ClassSymbolTable;
import compiler.semantics.Scope;
import compiler.semantics.MethodInfo;
import java.util.Map;

public class TypeCheckingASTVisitor extends BaseASTVisitor<Void> {
    public static final String BUILT_IN_METHOD_BODY_TYPE = "built in method body";
    public static final String ERROR_TYPE = "error type";
    private ClassTable classTable;
    private Map<String, ClassSymbolTable> classSymbols;

    private ClassSymbolTable currentClass;
    private Scope currentScope; 
    private SemanticErrorLogger logger;

    public TypeCheckingASTVisitor(ClassTable ct, Map<String, ClassSymbolTable> classSymbols, SemanticErrorLogger logger) {
        this.classTable = ct;
        this.classSymbols = classSymbols;
        this.logger = logger;
        currentScope = null;
    }

    public void setCurrentScope(Scope currentScope) {
        this.currentScope = currentScope;
    }

    public void setCurrentClass(ClassSymbolTable cst) {
        currentClass = cst;
    }

    public SemanticErrorLogger getLogger() {
        return logger;
    }

    @Override
    public Void visitProgram(ProgramNode node) {
        // to visit a program node, visit all of its classes
        for (ClassNode cnode : node.classes) {
            cnode.accept(this);
        }
        return null;
    }

    @Override
    public Void visitClass(ClassNode node) {
        // to visit a class, set the currentClass to its symbol table
        currentClass = classSymbols.get(node.className);
        // set the currentScope to a new Scope with current as parent
        currentScope = new Scope(currentScope);
        // now recursively visit all of its attributes
        for (AttributeFeatureNode attribute : node.attributes) {
            attribute.accept(this);
        }
        // then recursively visit all of its methods
        for (MethodFeatureNode method : node.methods) {
            method.accept(this);
        }
        // pop the current scope
        currentScope = currentScope.getParent();
        return null;
    }

    @Override
    public Void visitAttributeFeature(AttributeFeatureNode node) {
        // to visit an attribute feature, add it to the current scope
        Map<String, String> environment = currentScope.getEnvironment();
        // check if we have a child expression, if so, we need to type check it
        if (node.initExpr != null) {
            node.initExpr.accept(this);
            // get the inferred type of the initialier
            String inferredInitType = node.initExpr.getInferredType();
            // check for type mismatch between the attribute and its initializer
            // log any errors if needed
            if (!classTable.isAncestorOf(inferredInitType, node.featureType)) {
                StringBuilder errorBuilder = new StringBuilder();
                errorBuilder.append("Type Error: Incompatible initializer for type ");
                errorBuilder.append(inferredInitType);
                errorBuilder.append(" for attribute ");
                errorBuilder.append(node.featureName);
                errorBuilder.append(" of type " + node.featureType);
                errorBuilder.append(" inside of class " + currentClass.getClassName());
                logger.log( 
                    node.lineNumber,
                    errorBuilder.toString()
                );
            }
        }
        // add the type association to teh current environment
        environment.put(node.featureName, node.featureType);
        return null;
    }

    @Override
    public Void visitMethodFeature(MethodFeatureNode node) {
        // When visiting a method, create a new scope
        currentScope = new Scope(currentScope);

        // Check to see if the method declaration clashes with any ancestors
        ClassSymbolTable currentAncestor = currentClass.getParentTable();

        MethodInfo currentMethodInfo = currentClass.getMethodInfo(node.featureName);

        while (currentAncestor != null) {
            // see if the parent defines the same method
            MethodInfo parentMi = currentAncestor.getMethodInfo(node.featureName); 
            if (parentMi != null && !currentMethodInfo.matches(parentMi)) {
                // We have incorrectly overridden the parent method
                StringBuilder errorBuilder = new StringBuilder();
                errorBuilder.append("Invalid override for method ");
                errorBuilder.append(currentMethodInfo.getMethodName());
                errorBuilder.append(" in class ");
                errorBuilder.append(currentClass.getClassName());
                errorBuilder.append(" which does not conform to parent method ");
                errorBuilder.append(" in ancestral class ");
                errorBuilder.append(currentAncestor.getClassName());
                logger.log( 
                    node.lineNumber,
                    errorBuilder.toString()
                );
                break;
            }
            // otherwise, walk up the ancestry and check any prior ancestors
            currentAncestor = currentAncestor.getParentTable(); 
        }

        // add the formal parameters to the environment of the scope
        for (FormalNode formal : node.formals) {
            currentScope.getEnvironment().put(formal.formalName, formal.formalType);
        }

        // then visit the body expression
        visitExpr(node.bodyExpr);

        // then confirm that the inferred type of the body expression matches the return type
        String bodyType = node.bodyExpr.getInferredType();
        boolean bodyMatchesReturnType = (
            (currentMethodInfo.getReturnType().equals("SELF_TYPE") &&
            bodyType == currentClass.getClassName()) ||
            bodyType == BUILT_IN_METHOD_BODY_TYPE ||
            bodyType == currentMethodInfo.getReturnType()
        );
        if (!bodyMatchesReturnType) {
            // if the body type doesn't match the return type, this is a semantic error
            StringBuilder errorBuilder = new StringBuilder();
            errorBuilder.append("Type Error: Type mismatch in body expression of method ");
            errorBuilder.append(node.featureName);
            errorBuilder.append(" inside of class ");
            errorBuilder.append(currentClass.getClassName());
            errorBuilder.append(" with return type ");
            errorBuilder.append(node.featureType);
            errorBuilder.append(" body has inferred type ");
            errorBuilder.append(bodyType);
            logger.log( 
                node.lineNumber,
                errorBuilder.toString()
            );
        }

        // finally, release the currentScope
        currentScope = currentScope.getParent();
        return null;
    }

    @Override
    public Void visitExpr(ExprNode node) {
        // here we need to handle the specific cases
        if (node instanceof LiteralExprNode) {
            ((LiteralExprNode) node).accept(this);
        } else if (node instanceof IdentifierExprNode) {
            ((IdentifierExprNode) node).accept(this);
        } else if (node instanceof AssignExprNode) {
            ((AssignExprNode) node).accept(this);
        }
        return null;
    }

    @Override
    public Void visitIdentifierExpr(IdentifierExprNode node) {
        // check if the identifier is defined in the current scope
        String definedType = currentScope.getDefinedType(node.name);
        // if not, we should log an error
        if (definedType == null) {
            StringBuilder errorBuilder = new StringBuilder();
            errorBuilder.append("Ubound identifier ");
            errorBuilder.append(node.name);
            errorBuilder.append(" inside of class ");
            errorBuilder.append(currentClass.getClassName());
            logger.log( 
                node.lineNumber,
                errorBuilder.toString()
            );
            definedType = ERROR_TYPE;
        }
        node.annotate(definedType);
        return null;
    }

    @Override
    public Void visitLiteralExpr(LiteralExprNode node) {
        // literal nodes are just annotated with their respective types
        switch (node.type) {
            case INT:
                node.annotate("Int");
                break;
            case BOOL:
                node.annotate("Bool");
                break;
            case STRING:
                node.annotate("String");
                break;
        }
        return null;
    }

    @Override
    public Void visitUnaryOpExpr(UnaryOpExprNode node) {
        // virst visit the underling expr node
        node.expr.accept(this);
        String inferredExprType = node.expr.getInferredType();
        // then our type becomes the type of the underlying node
        node.annotate(inferredExprType);
        // then check which unary operator we are performing
        switch (node.operator) {
            case NOT:
                if (!inferredExprType.equals("Bool")) {
                    logger.log( 
                        node.lineNumber,
                        "Type Error: Invalid operand of type " +
                        inferredExprType +
                        " for unary operator not"
                    );
                    node.annotate(ERROR_TYPE);
                }
                break;
            case COMP:
                if (!inferredExprType.equals("Int")) {
                    logger.log( 
                        node.lineNumber,
                        "Type Error: Invalid operand of type " +
                        inferredExprType +
                        " for unary operator ~"
                    );
                    node.annotate(ERROR_TYPE);
                }
                break;
        }
        return null;
    }

    @Override
    public Void visitAssignExpr(AssignExprNode node) {
        // to visit an assignment expression, first visit the underlying expression
        node.rhs.accept(this);
        // then confirm that the inferred type is compatible with that of the identifer
        String inferredType = node.rhs.getInferredType();
        String boundType = currentScope.getEnvironment().get(node.varName);
        // if the variable being assigned to is unbound
        // or if the types are incompatible, then infer error type for the expression
        if (boundType == null) {
            node.annotate(ERROR_TYPE);
            logger.log(
                node.lineNumber, 
                "Invalid assignment to unbound variable " + node.varName +
                " in class " + currentClass.getClassName()
            );
            return null;
        } else if (!classTable.isAncestorOf(inferredType, boundType)) {
            node.annotate(ERROR_TYPE);
            StringBuilder errorBuilder = new StringBuilder();
            errorBuilder.append("Type Error: Incompatible expression type ");
            errorBuilder.append(inferredType);
            errorBuilder.append(" for identifier " + node.varName + " of bound type ");
            errorBuilder.append(boundType);
            errorBuilder.append(" in class " + currentClass.getClassName());
            logger.log( 
                node.lineNumber, 
                errorBuilder.toString()
            );
            return null;
        }

        // otherwise, the type of the assignment is the inferred type of its child
        node.annotate(inferredType);

        return null;
    }
}
