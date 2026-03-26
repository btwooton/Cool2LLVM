package compiler;

import compiler.ast.*;
import compiler.utils.CoolTestUtils;
import grammar.CoolParser;
import compiler.visitors.CoolParserToASTVisitor;
import compiler.visitors.InheritanceGraphASTVisitor;
import compiler.semantics.InheritanceGraph;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class InheritanceGraphASTVisitorTest {

    @Test
    public void testSingleClassProgram() {
       // Given: You have the following program string with class declaration
        String programString = (
            "class MyClass {\n" +
            "   myAttr : Int <- 42;\n" +
            "   myMethod(x : Int, y : Bool) : String {\n" +
            "       if y then \"yes\" else \"no\" fi\n" +
            "   };\n" +
            "};"
        );
        // When: You parse the program to an AST and visit it with the InheritanceGraphASTVisitor
        CoolParser.ProgramContext ctx = CoolTestUtils.parseProgram(programString);
        CoolParserToASTVisitor visitor = new CoolParserToASTVisitor();
        ASTNode node = visitor.visit(ctx);
        InheritanceGraphASTVisitor astToIGVisitor = new InheritanceGraphASTVisitor();
        node.accept(astToIGVisitor);

        // Then: The inheritance graph should have exactly 1 node and no edges
        InheritanceGraph ig = astToIGVisitor.inheritanceGraph;
        assertEquals(ig.numEdges(), 0);
        assertEquals(ig.numNodes(), 1);
        
        // Then: The inheritance graph should have the node MyClass
        assertTrue(ig.hasNode("MyClass"));
    }

    @Test
    public void testSingleClassWithParentProgram() {
        // Given: You have the following program string with class declaration
        String programString = (
            "class MyClass inherits MyParent {\n" +
            "   myAttr : Int <- 42;\n" +
            "   myMethod(x : Int, y : Bool) : String {\n" +
            "       if y then \"yes\" else \"no\" fi\n" +
            "   };\n" +
            "};"
        );
        // When: You parse the program to an AST and visit it with the InheritanceGraphASTVisitor
        CoolParser.ProgramContext ctx = CoolTestUtils.parseProgram(programString);
        CoolParserToASTVisitor visitor = new CoolParserToASTVisitor();
        ASTNode node = visitor.visit(ctx);
        InheritanceGraphASTVisitor astToIGVisitor = new InheritanceGraphASTVisitor();
        node.accept(astToIGVisitor);

        // Then: The inheritance graph should have exactly one edge and two nodes
        InheritanceGraph ig = astToIGVisitor.inheritanceGraph;
        assertEquals(ig.numEdges(), 1);
        assertEquals(ig.numNodes(), 2);
        
        // Then: The inheritance graph should have an edge from MyClass to MyParent
        assertTrue(ig.hasEdge("MyClass", "MyParent"));
        assertTrue(ig.hasNode("MyClass"));
        assertTrue(ig.hasNode("MyParent"));
    }

    @Test
    public void testMultipleClassesChainedInheritance() {
        // Given: You have the following program string with class declaration
        String programString = (
            "class MyClass inherits MyParent {\n" +
            "   myAttr : Int <- 42;\n" +
            "   myMethod(x : Int, y : Bool) : String {\n" +
            "       if y then \"yes\" else \"no\" fi\n" +
            "   };\n" +
            "};\n" +
            "class MyParent inherits MyGrandParent {};\n" +
            "class MyGrandParent {};\n"
        );
        // When: You parse the program to an AST and visit it with the InheritanceGraphASTVisitor
        CoolParser.ProgramContext ctx = CoolTestUtils.parseProgram(programString);
        CoolParserToASTVisitor visitor = new CoolParserToASTVisitor();
        ASTNode node = visitor.visit(ctx);
        InheritanceGraphASTVisitor astToIGVisitor = new InheritanceGraphASTVisitor();
        node.accept(astToIGVisitor);

        // Then: The inheritance graph should have exactly two edges and three nodes
        InheritanceGraph ig = astToIGVisitor.inheritanceGraph;
        assertEquals(ig.numEdges(), 2);
        assertEquals(ig.numNodes(), 3);
        
        // Then: The inheritance graph should have the expected edges 
        assertTrue(ig.hasEdge("MyClass", "MyParent"));
        assertTrue(ig.hasEdge("MyParent", "MyGrandParent"));
    }

    @Test
    public void testMultipleClassesSharedInheritance() {
        // Given: You have the following program string with class declaration
        String programString = (
            "class MyClass inherits MyParent {\n" +
            "   myAttr : Int <- 42;\n" +
            "   myMethod(x : Int, y : Bool) : String {\n" +
            "       if y then \"yes\" else \"no\" fi\n" +
            "   };\n" +
            "};\n" +
            "class MyOtherClass inherits MyParent {};\n" +
            "class MyParent inherits MyGrandParent {};\n" +
            "class MyGrandParent {};\n"
        );
        // When: You parse the program to an AST and visit it with the InheritanceGraphASTVisitor
        CoolParser.ProgramContext ctx = CoolTestUtils.parseProgram(programString);
        CoolParserToASTVisitor visitor = new CoolParserToASTVisitor();
        ASTNode node = visitor.visit(ctx);
        InheritanceGraphASTVisitor astToIGVisitor = new InheritanceGraphASTVisitor();
        node.accept(astToIGVisitor);

        // Then: The inheritance graph should have exactly three edges and four nodes
        InheritanceGraph ig = astToIGVisitor.inheritanceGraph;
        assertEquals(ig.numEdges(), 3);
        assertEquals(ig.numNodes(), 4);
        
        // Then: The inheritance graph should have the expected edges 
        assertTrue(ig.hasEdge("MyClass", "MyParent"));
        assertTrue(ig.hasEdge("MyParent", "MyGrandParent"));
        assertTrue(ig.hasEdge("MyOtherClass", "MyParent"));
    }
}
