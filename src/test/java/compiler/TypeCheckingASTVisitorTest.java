package compiler;

import compiler.ast.*;
import compiler.utils.CoolTestUtils;
import grammar.CoolParser;
import compiler.visitors.CoolParserToASTVisitor;
import compiler.visitors.TypeCheckingASTVisitor;
import compiler.semantics.Scope;
import compiler.semantics.ClassTable;
import compiler.semantics.ClassSymbolTable;
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

}