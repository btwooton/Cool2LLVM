package compiler.visitors;
import compiler.ast.*;
import compiler.semantics.SemanticErrorLogger;
import compiler.semantics.ClassTable;
import compiler.semantics.ClassSymbolTable;
import compiler.semantics.Scope;
import compiler.semantics.MethodInfo;
import java.util.Map;
import java.util.List;

public class TypeCheckingASTVisitor extends BaseASTVisitor<Void> {
    public static final String BUILT_IN_METHOD_BODY_TYPE = "built in method body";
    public static final String ERROR_TYPE = "error type";
    private ClassTable classTable;
    private Map<String, ClassSymbolTable> classSymbols;

    private ClassSymbolTable currentClass;
    private Scope currentScope; 
    private SemanticErrorLogger logger;

    private void logBinaryOpExprTypeError(int lineNumber, String operator, String leftType, String rightType) {
        StringBuilder errorBuilder = new StringBuilder();
        errorBuilder.append("Type Error: Invalid operand types for binary operator ");
        errorBuilder.append(operator);
        errorBuilder.append(", operands have types ");
        errorBuilder.append(leftType + " ");
        errorBuilder.append(" and " + rightType);
        logger.log(lineNumber, errorBuilder.toString());
    }

    private String operatorTypeToString(BinaryOpExprNode.Op operator) {
        switch (operator) {
            case LT:
                return "<";
            case LE:
                return "<=";
            case EQ:
                return "=";
            case ADD:
                return "+";
            case SUB:
                return "=";
            case MUL:
                return "*";
            case DIV:
                return "/";
            default: 
                return "";
        }
    }

    private String getNormalizedType(String classType) {
        if (classType != null && classType.equals("SELF_TYPE")) {
            return currentClass.getClassName();
        }
        return classType;
    }

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
        // handle SELF_TYPE correctly in the attribute's declared type
        String attributeType = getNormalizedType(node.featureType);
        // check if we have a child expression, if so, we need to type check it
        if (node.initExpr != null) {
            // create a new scope that binds "self" to the current class type
            currentScope = new Scope(currentScope);
            currentScope.getEnvironment().put("self", currentClass.getClassName());
            node.initExpr.accept(this);
            // pop the inner scope
            currentScope = currentScope.getParent();
            // get the inferred type of the initialier
            String inferredInitType = node.initExpr.getInferredType();
            // check for type mismatch between the attribute and its initializer
            // log any errors if needed
            if (!classTable.isAncestorOf(inferredInitType, attributeType)) {
                StringBuilder errorBuilder = new StringBuilder();
                errorBuilder.append("Type Error: Incompatible initializer for attribute ");
                errorBuilder.append(node.featureName);
                errorBuilder.append(" of type ");
                errorBuilder.append(attributeType);
                errorBuilder.append("; initializer has type ");
                errorBuilder.append(inferredInitType);
                errorBuilder.append(" inside of class " + currentClass.getClassName());
                logger.log( 
                    node.lineNumber,
                    errorBuilder.toString()
                );
                environment.put(node.featureName, ERROR_TYPE);
                // also annotate the node itself
                node.annotate(ERROR_TYPE);
                return null;
            }
        }
        // assuming nothing went wrong, the type of the attribute is the declared type
        environment.put(node.featureName, attributeType);
        // also annotate the node itself
        node.annotate(node.featureType);
        
        return null;
    }

    @Override
    public Void visitMethodFeature(MethodFeatureNode node) {
        // When visiting a method, create a new scope
        currentScope = new Scope(currentScope);

        // TODO: check to see if we are redefining a method that already exists in this class

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
        } else if (node instanceof UnaryOpExprNode) {
            ((UnaryOpExprNode) node).accept(this);
        } else if (node instanceof BinaryOpExprNode) {
            ((BinaryOpExprNode) node).accept(this);
        } else if (node instanceof NewExprNode) {
            ((NewExprNode) node).accept(this);
        }
        return null;
    }

    @Override
    public Void visitIdentifierExpr(IdentifierExprNode node) {
        // check if the identifier is defined in the current scope
        String definedType = getNormalizedType(currentScope.getDefinedType(node.name));
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
        // get its inferred type
        String inferredExprType = node.expr.getInferredType();
        // then check which unary operator we are performing
        // make sure the inferred type matches the expected type
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
                } else { 
                    node.annotate("Bool");
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
                } else {
                    node.annotate("Int");
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
        String inferredType = getNormalizedType(node.rhs.getInferredType());
        String boundType = getNormalizedType(currentScope.getEnvironment().get(node.varName));
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

    @Override
    public Void visitNewExpr(NewExprNode node) {
        // Set the type of the expression to the normalized type
        node.annotate(getNormalizedType(node.typeName));
        return null;
    }

    @Override
    public Void visitBinaryOpExpr(BinaryOpExprNode node) {
        // to visit a binary operation, visit both of its sub expressions
        node.left.accept(this);
        node.right.accept(this);

        // get their inferred types
        String leftType = getNormalizedType(node.left.getInferredType());
        String rightType = getNormalizedType(node.right.getInferredType());
        boolean typeMismatch;
        // now switch on the operator
        switch (node.operator) {
            case EQ: // equal is the oddity case, so we handle it first
                typeMismatch = ( 
                    (List.of("Int", "String", "Bool").contains(leftType) ||
                    List.of("Int", "String", "Bool").contains(rightType)) && 
                    leftType != rightType
                );
                if (typeMismatch) {
                    // This is a type error, so log it and infer error type
                    logBinaryOpExprTypeError(node.lineNumber, "=", leftType, rightType);
                    node.annotate(ERROR_TYPE);
                } else {
                    // then the inferred type should be Bool
                    node.annotate("Bool");
                }
                break;
            case LT:
            case LE: // both require int operands and infer Bool
                typeMismatch = (
                    !leftType.equals("Int") ||
                    !rightType.equals("Int")
                );
                if (typeMismatch) {
                    // log error and infer error type
                    logBinaryOpExprTypeError(
                        node.lineNumber,
                        operatorTypeToString(node.operator),
                        leftType,  rightType
                    );
                    node.annotate(ERROR_TYPE);
                } else {
                    node.annotate("Bool");
                }
                break;
            case ADD:
            case SUB:
            case MUL:
            case DIV:
                typeMismatch = (
                    !leftType.equals("Int") ||
                    !rightType.equals("Int")
                );
                if (typeMismatch) {
                    // log error and infer error type
                    logBinaryOpExprTypeError(
                        node.lineNumber,
                        operatorTypeToString(node.operator),
                        leftType,  rightType
                    );
                    node.annotate(ERROR_TYPE);
                } else {
                    node.annotate("Int");
                }
                break;
            default: 
                node.annotate(ERROR_TYPE);
        }
        return null;
    }

    @Override
    public Void visitBlockExpr(BlockExprNode node) {
        // Then evaluate all of its sub expressions
        for (ExprNode subExpr : node.expressions) {
            subExpr.accept(this);
        }
        String inferredType = getNormalizedType(
            node.expressions.get(node.expressions.size() - 1).getInferredType()
        );
        // Then infer the type of the overall block as that of the last expression
        node.annotate(inferredType);
        return null;
    }

    @Override
    public Void visitConditionalExpr(ConditionalExprNode node) {
        // to visit a conditional, visit its condition and sub expressions
        node.condition.accept(this);
        node.thenExpr.accept(this);
        node.elseExpr.accept(this);

        // check that the condition has inferred type Bool
        if (!node.condition.getInferredType().equals("Bool")) {
            // this is a type error; log it and set type to error type
            StringBuilder errorBuilder = new StringBuilder();
            errorBuilder.append(
                "Type Error: If conditional must have static type 'Bool'"
            );
            errorBuilder.append( 
                " but has inferred type '" + node.condition.getInferredType() + "'"
            );
            logger.log( 
                node.lineNumber,
                errorBuilder.toString()
            );
            node.annotate(ERROR_TYPE);
        } else {
            // then compute the type join of the types of the branches
            String typeJoin = classTable.computeTypeJoin(
                getNormalizedType(node.thenExpr.getInferredType()),
                getNormalizedType(node.elseExpr.getInferredType())
            );
            // infer the type of the overall conditional as that typeJoin
            node.annotate(typeJoin);
        }
        return null;
    }

    @Override
    public Void visitLoopExpr(LoopExprNode node) {
        // To visit a loop, visit its conditional and body
        node.condition.accept(this);
        node.body.accept(this);
        // Get there inferred types
        String conditionType = getNormalizedType(node.condition.getInferredType());
        // make sure the type of the conditional is Bool
        if (!conditionType.equals("Bool")) {
            // This is a type error; log it and infer ERROR_TYPE
            StringBuilder errorBuilder = new StringBuilder();
            errorBuilder.append("Type Error: Loop conditional must have type 'Bool' ");
            errorBuilder.append("but has inferred type '" + conditionType + "'");
            logger.log( 
                node.lineNumber,
                errorBuilder.toString()
            );
            node.annotate(ERROR_TYPE);
        } else {
            node.annotate("Object");
        }
        return null;
    }
}
