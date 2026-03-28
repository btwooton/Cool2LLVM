package compiler;

import compiler.ast.*;
import compiler.utils.CoolTestUtils;
import grammar.CoolParser;
import compiler.visitors.ClassCollectorASTVisitor;
import compiler.visitors.CoolParserToASTVisitor;
import compiler.visitors.TypeCheckingASTVisitor;
import compiler.semantics.Scope;
import compiler.semantics.ClassTable;
import compiler.semantics.ClassSymbolTable;
import compiler.semantics.ClassSymbolTableBuilder;
import compiler.semantics.SemanticErrorLogger;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TypeCheckingASTVisitorTest {

    private TypeCheckingASTVisitor initializeVisitorInDummyEnvironment(String dummyClassName) {
        Scope dummyScope = new Scope(null); // initialize dummy scope
        ClassSymbolTable cst = new ClassSymbolTable(dummyClassName, null); // add dummy class
        SemanticErrorLogger logger = new SemanticErrorLogger();
        ClassTable ct = new ClassTable(logger);
        ct.addClass(new ClassNode(1, dummyClassName, null, null, null));
        Map<String, ClassSymbolTable> classSymbols = new HashMap<>();
        classSymbols.put(dummyClassName, cst);
        TypeCheckingASTVisitor typeCheckingVisitor = new TypeCheckingASTVisitor( 
            ct, classSymbols, logger
        );
        typeCheckingVisitor.setCurrentScope(dummyScope);
        typeCheckingVisitor.setCurrentClass(cst);
        return typeCheckingVisitor;
    }

    private TypeCheckingASTVisitor initializeVisitorWithDummyScope(String dummyClassName, Scope dummyScope) {
        ClassSymbolTable cst = new ClassSymbolTable(dummyClassName, null); // add dummy class
        SemanticErrorLogger logger = new SemanticErrorLogger();
        ClassTable ct = new ClassTable(logger);
        ct.addClass(new ClassNode(1, dummyClassName, null, null, null));
        Map<String, ClassSymbolTable> classSymbols = new HashMap<>();
        classSymbols.put(dummyClassName, cst);
        TypeCheckingASTVisitor typeCheckingVisitor = new TypeCheckingASTVisitor( 
            ct, classSymbols, logger
        );
        typeCheckingVisitor.setCurrentScope(dummyScope);
        typeCheckingVisitor.setCurrentClass(cst);
        return typeCheckingVisitor;
    }

    @Test
    public void testIntegerLiteral() {
        // When: You parse the following literal expression to a node
        CoolParser.ExprContext ctx = CoolTestUtils.parseExpr("42");
        CoolParserToASTVisitor visitor = new CoolParserToASTVisitor();
        ExprNode node = (ExprNode) visitor.visit(ctx);

        // When: You then visit that node using the TypeCheckingASTVisitor
        TypeCheckingASTVisitor typeChecker = initializeVisitorInDummyEnvironment("Dummy");
        node.accept(typeChecker);
        
        // Then: The inferred type of teh node should be "Int"
        assertEquals(node.getInferredType(), "Int");
    }

    @Test
    public void testStringLiteral() {
        // When: You parse the following literal expression to a node
        CoolParser.ExprContext ctx = CoolTestUtils.parseExpr("\"hello\"");
        CoolParserToASTVisitor visitor = new CoolParserToASTVisitor();
        ExprNode node = (ExprNode) visitor.visit(ctx);

        // When: You then visit that node using the TypeCheckingASTVisitor
        TypeCheckingASTVisitor typeChecker = initializeVisitorInDummyEnvironment("Dummy");
        node.accept(typeChecker);
        
        // Then: The inferred type of the node should be "String"
        assertEquals(node.getInferredType(), "String"); 
    }

    @Test
    public void testTrueLiteral() {
       // When: You parse the following literal expression to a node
        CoolParser.ExprContext ctx = CoolTestUtils.parseExpr("true");
        CoolParserToASTVisitor visitor = new CoolParserToASTVisitor();
        ExprNode node = (ExprNode) visitor.visit(ctx);

        // When: You then visit that node using the TypeCheckingASTVisitor
        TypeCheckingASTVisitor typeChecker = initializeVisitorInDummyEnvironment("Dummy");
        node.accept(typeChecker);
        
        // Then: The inferred type of the node should be Bool
        assertEquals(node.getInferredType(), "Bool");  
    }

    @Test
    public void testFalseLiteral() {
       // When: You parse the following literal expression to a node
        CoolParser.ExprContext ctx = CoolTestUtils.parseExpr("false");
        CoolParserToASTVisitor visitor = new CoolParserToASTVisitor();
        ExprNode node = (ExprNode) visitor.visit(ctx);

        // When: You then visit that node using the TypeCheckingASTVisitor
        TypeCheckingASTVisitor typeChecker = initializeVisitorInDummyEnvironment("Dummy");
        node.accept(typeChecker);
        
        // Then: The inferred type of the node should be Bool
        assertEquals(node.getInferredType(), "Bool");   
    }

    @Test
    public void testDefinedIdentifierLiteral() {
        // When: You parse the following literal expression to a node
        CoolParser.ExprContext ctx = CoolTestUtils.parseExpr("x");
        CoolParserToASTVisitor visitor = new CoolParserToASTVisitor();
        ExprNode node = (ExprNode) visitor.visit(ctx);

        // When: You then visit that node using the TypeCheckingASTVisitor
        Scope dummyScope = new Scope(null);
        dummyScope.getEnvironment().put("x", "Int");
        TypeCheckingASTVisitor typeChecker = initializeVisitorWithDummyScope("Dummy", dummyScope);
        node.accept(typeChecker);
        
        // Then: The inferred type of the node should be Int
        assertEquals(node.getInferredType(), "Int");   
    }

    @Test
    public void testUndefinedLiteralExpr() {
        // When: You parse the following undefinedliteral expression to a node
        CoolParser.ExprContext ctx = CoolTestUtils.parseExpr("x");
        CoolParserToASTVisitor visitor = new CoolParserToASTVisitor();
        ExprNode node = (ExprNode) visitor.visit(ctx);

        // When: You then visit that node using the TypeCheckingASTVisitor
        TypeCheckingASTVisitor typeChecker = initializeVisitorInDummyEnvironment("Dummy");
        node.accept(typeChecker);

        // Then: The logger should have errors
        assertTrue(typeChecker.getLogger().hasErrors());
        
        // Then: The inferred type of the node should be "error type"
        assertEquals(node.getInferredType(), "error type");

        typeChecker.getLogger().printErrors();
    }

    @Test
    public void testUnaryNotExpr() {
        // When: You parse the following undefinedliteral expression to a node
        CoolParser.ExprContext ctx = CoolTestUtils.parseExpr("not true");
        CoolParserToASTVisitor visitor = new CoolParserToASTVisitor();
        ExprNode node = (ExprNode) visitor.visit(ctx);

        // When: You then visit that node using the TypeCheckingASTVisitor
        TypeCheckingASTVisitor typeChecker = initializeVisitorInDummyEnvironment("Dummy");
        node.accept(typeChecker);

        // Then: The logger should not have errors
        assertFalse(typeChecker.getLogger().hasErrors());
        
        // Then: The inferred type of the node should be Bool
        assertEquals(node.getInferredType(), "Bool");

    }

    @Test
    public void testUnaryCompExper() {
        // When: You parse the following undefinedliteral expression to a node
        CoolParser.ExprContext ctx = CoolTestUtils.parseExpr("~5");
        CoolParserToASTVisitor visitor = new CoolParserToASTVisitor();
        ExprNode node = (ExprNode) visitor.visit(ctx);

        // When: You then visit that node using the TypeCheckingASTVisitor
        TypeCheckingASTVisitor typeChecker = initializeVisitorInDummyEnvironment("Dummy");
        node.accept(typeChecker);

        // Then: The logger should not have errors
        assertFalse(typeChecker.getLogger().hasErrors());
        
        // Then: The inferred type of the node should be Int
        assertEquals(node.getInferredType(), "Int");

    }
    @Test
    public void testUnaryExprInvalidOperand() {
        // When: You parse the following undefinedliteral expression to a node
        CoolParser.ExprContext ctx = CoolTestUtils.parseExpr("not 0");
        CoolParserToASTVisitor visitor = new CoolParserToASTVisitor();
        ExprNode node = (ExprNode) visitor.visit(ctx);

        // When: You then visit that node using the TypeCheckingASTVisitor
        TypeCheckingASTVisitor typeChecker = initializeVisitorInDummyEnvironment("Dummy");
        node.accept(typeChecker);

        // Then: The logger should have errors
        assertTrue(typeChecker.getLogger().hasErrors());
        
        // Then: The inferred type of the node should be "error type"
        assertEquals(node.getInferredType(), "error type");

        typeChecker.getLogger().printErrors();
    }

    @Test
    public void testValidAssignmentExpression() {
        // When: You parse the following assigment expression
        CoolParser.ExprContext ctx = CoolTestUtils.parseExpr("x <- 5");
        CoolParserToASTVisitor visitor = new CoolParserToASTVisitor();
        ExprNode node = (ExprNode) visitor.visit(ctx);

        // When: You then visit that node using the TypeCheckingASTVisitor
        Scope dummyScope = new Scope(null);
        dummyScope.getEnvironment().put("x", "Object");
        TypeCheckingASTVisitor typeChecker = initializeVisitorWithDummyScope("Dummy", dummyScope);
        node.accept(typeChecker);

        // Then: The logger should not have errors
        assertFalse(typeChecker.getLogger().hasErrors());
        
        // Then: The inferred type of the node should be "Int"
        assertEquals(node.getInferredType(), "Int");
    }

    @Test
    public void testInvalidAssignmentExpression() {
        // When: You parse the following assigment expression
        CoolParser.ExprContext ctx = CoolTestUtils.parseExpr("x <- 5");
        CoolParserToASTVisitor visitor = new CoolParserToASTVisitor();
        ExprNode node = (ExprNode) visitor.visit(ctx);

        // When: You then visit that node using the TypeCheckingASTVisitor
        Scope dummyScope = new Scope(null);
        dummyScope.getEnvironment().put("x", "String");
        TypeCheckingASTVisitor typeChecker = initializeVisitorWithDummyScope("Dummy", dummyScope);
        node.accept(typeChecker);

        // Then: The logger should have errors
        assertTrue(typeChecker.getLogger().hasErrors());
        
        // Then: The inferred type of the node should be "error type"
        assertEquals(node.getInferredType(), "error type"); 

        typeChecker.getLogger().printErrors();
    }

    @Test
    public void testNewExpressionWithSelfType() {
       // When: You parse the following new expression
        CoolParser.ExprContext ctx = CoolTestUtils.parseExpr("new SELF_TYPE");
        CoolParserToASTVisitor visitor = new CoolParserToASTVisitor();
        ExprNode node = (ExprNode) visitor.visit(ctx);

        // When: You then visit that node using the TypeCheckingASTVisitor
        TypeCheckingASTVisitor typeChecker = initializeVisitorInDummyEnvironment("MyClass");
        node.accept(typeChecker);

        // Then: The logger should not have errors
        assertFalse(typeChecker.getLogger().hasErrors());
        
        // Then: The inferred type of the node should be "MyClass"
        assertEquals(node.getInferredType(), "MyClass"); 
    }

    @Test
    public void testNewExpressionWithClassType() {
       // When: You parse the following new expression
        CoolParser.ExprContext ctx = CoolTestUtils.parseExpr("new Int");
        CoolParserToASTVisitor visitor = new CoolParserToASTVisitor();
        ExprNode node = (ExprNode) visitor.visit(ctx);

        // When: You then visit that node using the TypeCheckingASTVisitor
        TypeCheckingASTVisitor typeChecker = initializeVisitorInDummyEnvironment("MyClass");
        node.accept(typeChecker);

        // Then: The logger should not have errors
        assertFalse(typeChecker.getLogger().hasErrors());
        
        // Then: The inferred type of the node should be "Int"
        assertEquals(node.getInferredType(), "Int"); 
    }

    @Test
    public void testArithmeticOpExpression() {
        // When: You parse the following add expression 
        CoolParser.ExprContext ctx = CoolTestUtils.parseExpr("10 + 5");
        CoolParserToASTVisitor visitor = new CoolParserToASTVisitor();
        ExprNode node = (ExprNode) visitor.visit(ctx);

        // When: You then visit that node using the TypeCheckingASTVisitor
        TypeCheckingASTVisitor typeChecker = initializeVisitorInDummyEnvironment("MyClass");
        node.accept(typeChecker);

        // Then: The logger should not have errors
        assertFalse(typeChecker.getLogger().hasErrors());
        
        // Then: The inferred type of the node should be "Int"
        assertEquals(node.getInferredType(), "Int"); 
    }

    @Test
    public void testCompareOpExpression() {
        // When: You parse the following add expression 
        CoolParser.ExprContext ctx = CoolTestUtils.parseExpr("10 < 5");
        CoolParserToASTVisitor visitor = new CoolParserToASTVisitor();
        ExprNode node = (ExprNode) visitor.visit(ctx);

        // When: You then visit that node using the TypeCheckingASTVisitor
        TypeCheckingASTVisitor typeChecker = initializeVisitorInDummyEnvironment("MyClass");
        node.accept(typeChecker);

        // Then: The logger should not have errors
        assertFalse(typeChecker.getLogger().hasErrors());
        
        // Then: The inferred type of the node should be "Int"
        assertEquals(node.getInferredType(), "Bool"); 
    }

    @Test
    public void testBinaryOpInvalid() {
       // When: You parse the following add expression 
        CoolParser.ExprContext ctx = CoolTestUtils.parseExpr("true < 5");
        CoolParserToASTVisitor visitor = new CoolParserToASTVisitor();
        ExprNode node = (ExprNode) visitor.visit(ctx);

        // When: You then visit that node using the TypeCheckingASTVisitor
        TypeCheckingASTVisitor typeChecker = initializeVisitorInDummyEnvironment("MyClass");
        node.accept(typeChecker);

        // Then: The logger should have errors
        assertTrue(typeChecker.getLogger().hasErrors());
        
        // Then: The inferred type of the node should be ERROR_TYPE
        assertEquals(node.getInferredType(), TypeCheckingASTVisitor.ERROR_TYPE);

        typeChecker.getLogger().printErrors();
    }

    @Test
    public void testBlockExpr() {
        // Given: You parse the following block expression
        CoolParser.ExprContext ctx = CoolTestUtils.parseExpr("{ 5 + 5; a <- new MyClass; 5 < 10; }");
        CoolParserToASTVisitor visitor = new CoolParserToASTVisitor();
        ExprNode node = (ExprNode) visitor.visit(ctx);

        // When: You then visit that node using the TypeCheckingASTVisitor
        Scope dummyScope = new Scope(null);
        dummyScope.getEnvironment().put("a", "MyClass");
        TypeCheckingASTVisitor typeChecker = initializeVisitorWithDummyScope("MyClass", dummyScope);
        node.accept(typeChecker);

        // Then: The logger should have no errors
        assertFalse(typeChecker.getLogger().hasErrors());
        
        // Then: The inferred type of the node should be Bool
        assertEquals(node.getInferredType(), "Bool");
    }

    @Test
    public void testConditional() {
        // Given: You parse the following conditional expression
        CoolParser.ExprContext ctx = CoolTestUtils.parseExpr("if 5 < 10 then \"Hello\" else new MyClass fi");
        CoolParserToASTVisitor visitor = new CoolParserToASTVisitor();
        ExprNode node = (ExprNode) visitor.visit(ctx);

        // When: You then visit that node using the TypeCheckingASTVisitor
        TypeCheckingASTVisitor typeChecker = initializeVisitorInDummyEnvironment("MyClass");
        node.accept(typeChecker);

        // Then: The logger should have no errors
        assertFalse(typeChecker.getLogger().hasErrors());
        
        // Then: The inferred type of the node should be Object
        assertEquals(node.getInferredType(), "Object");
    }

    @Test
    public void testConditionalWithBadCondition() {
        // Given: You parse the following conditional expression
        CoolParser.ExprContext ctx = CoolTestUtils.parseExpr("if 5 + 10 then \"Hello\" else new MyClass fi");
        CoolParserToASTVisitor visitor = new CoolParserToASTVisitor();
        ExprNode node = (ExprNode) visitor.visit(ctx);

        // When: You then visit that node using the TypeCheckingASTVisitor
        TypeCheckingASTVisitor typeChecker = initializeVisitorInDummyEnvironment("MyClass");
        node.accept(typeChecker);

        // Then: The logger should have errors
        assertTrue(typeChecker.getLogger().hasErrors());
        
        // Then: The inferred type of the node should be ERROR_TYPE
        assertEquals(node.getInferredType(), TypeCheckingASTVisitor.ERROR_TYPE); 

        typeChecker.getLogger().printErrors();
    }

    @Test 
    public void testLoopExpression() {
       // Given: You parse the following loop expression
        CoolParser.ExprContext ctx = CoolTestUtils.parseExpr("while x < 10 loop x <- x + 1 pool\"");
        CoolParserToASTVisitor visitor = new CoolParserToASTVisitor();
        ExprNode node = (ExprNode) visitor.visit(ctx);

        // When: You then visit that node using the TypeCheckingASTVisitor
        Scope dummyScope = new Scope(null);
        dummyScope.getEnvironment().put("x", "Int");
        TypeCheckingASTVisitor typeChecker = initializeVisitorWithDummyScope("MyClass", dummyScope);
        node.accept(typeChecker);

        // Then: The logger should not have errors
        assertFalse(typeChecker.getLogger().hasErrors());
        
        // Then: The inferred type of the node should be Object
        assertEquals(node.getInferredType(), "Object"); 
    }

    @Test
    public void testClassWithAttributeInit() {
        // Given: You have the following COOL program
        String programString = (
            "class MyClass inherits MyParent {\n" +
            "   myAttr : MyParent <- self;\n" +
            "};\n" +
            "class MyParent {};"
        );

        // When: You parse it to an AST and then type check it
        CoolParser.ProgramContext ctx = CoolTestUtils.parseProgram(programString);
        CoolParserToASTVisitor visitor = new CoolParserToASTVisitor();
        ASTNode node = visitor.visit(ctx);
        SemanticErrorLogger logger = new SemanticErrorLogger();
        ClassTable ct = new ClassTable(logger);
        ClassCollectorASTVisitor classCollector = new ClassCollectorASTVisitor(ct);
        node.accept(classCollector);
        // build the Class Symbol tables
        ClassSymbolTableBuilder ctb = new ClassSymbolTableBuilder();
        ctb.build(ct); // build from the class table
        // Now initialize the TypeChecker
        TypeCheckingASTVisitor typeChecker = new TypeCheckingASTVisitor( 
            ct,
            ctb.getClassSymbolTables(),
            logger
        );
        node.accept(typeChecker);

        // Then: The logger should have no errors
        assertFalse(logger.hasErrors());
    }

}