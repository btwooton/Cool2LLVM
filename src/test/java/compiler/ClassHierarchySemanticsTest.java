package compiler;

import compiler.ast.*;
import compiler.utils.CoolTestUtils;
import grammar.CoolParser;
import compiler.visitors.CoolParserToASTVisitor;
import compiler.visitors.ClassCollectorASTVisitor;
import compiler.semantics.ClassTable;
import compiler.semantics.SemanticErrorLogger;
import compiler.semantics.InheritanceGraphBuilder;
import compiler.semantics.InheritanceGraph;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class ClassHierarchySemanticsTest {

    @Test
    public void testSingleClassProgramClassTable() {
       // Given: You have the following program string with class declaration
        String programString = (
            "class MyClass {\n" +
            "   myAttr : Int <- 42;\n" +
            "   myMethod(x : Int, y : Bool) : String {\n" +
            "       if y then \"yes\" else \"no\" fi\n" +
            "   };\n" +
            "};"
        );
        // When: You parse the program to an AST and visit it with the ClassCollectorASTVisitor
        CoolParser.ProgramContext ctx = CoolTestUtils.parseProgram(programString);
        CoolParserToASTVisitor visitor = new CoolParserToASTVisitor();
        ASTNode node = visitor.visit(ctx);
        SemanticErrorLogger logger = new SemanticErrorLogger();
        ClassTable ct = new ClassTable(logger);
        ClassCollectorASTVisitor classCollector = new ClassCollectorASTVisitor(ct);
        node.accept(classCollector);

        // Then: The class table should have a mapping for "MyClass"
        assertTrue(ct.lookup("MyClass") != null);
        // Then: The stored class node should have one attribute, and one method
        assertTrue(ct.lookup("MyClass").attributes.size() == 1);
        assertTrue(ct.lookup("MyClass").methods.size() == 1);
        // Then: The logger should not have logged any errors
        assertFalse(logger.hasErrors());
        
    }

    @Test
    public void testCyclicInheritanceCycle() {
       // Given: You have the following program string with cyclic class hierarchy
        String programString = (
            "class MyClass inherits MyParent {\n" +
            "   myAttr : Int <- 42;\n" +
            "   myMethod(x : Int, y : Bool) : String {\n" +
            "       if y then \"yes\" else \"no\" fi\n" +
            "   };\n" +
            "};\n" +
            "class MyParent inherits MyGrandParent {};\n" +
            "class MyGrandParent inherits MyClass {};\n"
        );
        // When: You parse the program to an AST and visit it with the ClassCollectorASTVisitor
        CoolParser.ProgramContext ctx = CoolTestUtils.parseProgram(programString);
        CoolParserToASTVisitor visitor = new CoolParserToASTVisitor();
        ASTNode node = visitor.visit(ctx);
        SemanticErrorLogger logger = new SemanticErrorLogger();
        ClassTable ct = new ClassTable(logger);
        ClassCollectorASTVisitor classCollector = new ClassCollectorASTVisitor(ct);
        node.accept(classCollector);
        
        // When: You then build an inheritance graph from the ClassTable
        InheritanceGraphBuilder igb = new InheritanceGraphBuilder();
        igb.buildFromClassTable(ct);
        InheritanceGraph ig = igb.getInheritanceGraph();

        // Then: The underlying inheritance graph should fail the well formedness check
        assertFalse(ig.isWellFormed(ct, logger));

        // Then : The logger should have errors
        assertTrue(logger.hasErrors());

        logger.printErrors();

    }

    @Test
    public void testNonCyclicInheritanceCycle() {
       // Given: You have the following program string with a valid class hierarchy
        String programString = (
            "class MyClass inherits MyParent {\n" +
            "   myAttr : Int <- 42;\n" +
            "   myMethod(x : Int, y : Bool) : String {\n" +
            "       if y then \"yes\" else \"no\" fi\n" +
            "   };\n" +
            "};\n" +
            "class MyParent inherits MyGrandParent {};\n" +
            "class MyGrandParent {};"
        );
        // When: You parse the program to an AST and visit it with the ClassCollectorASTVisitor
        CoolParser.ProgramContext ctx = CoolTestUtils.parseProgram(programString);
        CoolParserToASTVisitor visitor = new CoolParserToASTVisitor();
        ASTNode node = visitor.visit(ctx);
        SemanticErrorLogger logger = new SemanticErrorLogger();
        ClassTable ct = new ClassTable(logger);
        ClassCollectorASTVisitor classCollector = new ClassCollectorASTVisitor(ct);
        node.accept(classCollector);
        
        // When: You then build an inheritance graph from the ClassTable
        InheritanceGraphBuilder igb = new InheritanceGraphBuilder();
        igb.buildFromClassTable(ct);
        InheritanceGraph ig = igb.getInheritanceGraph();

        // Then: The underlying inheritance graph should pass the wellFormedness check
        assertTrue(ig.isWellFormed(ct, logger));

        // Then : The logger should not have any errors
        assertFalse(logger.hasErrors());

    }

    @Test
    public void testClassInheritsFromUndefinedClass() {
       // Given: You have the following program string with a class inheriting from undefined parent
        String programString = (
            "class MyClass inherits MyParent {\n" +
            "   myAttr : Int <- 42;\n" +
            "   myMethod(x : Int, y : Bool) : String {\n" +
            "       if y then \"yes\" else \"no\" fi\n" +
            "   };\n" +
            "};"
        );
        // When: You parse the program to an AST and visit it with the ClassCollectorASTVisitor
        CoolParser.ProgramContext ctx = CoolTestUtils.parseProgram(programString);
        CoolParserToASTVisitor visitor = new CoolParserToASTVisitor();
        ASTNode node = visitor.visit(ctx);
        SemanticErrorLogger logger = new SemanticErrorLogger();
        ClassTable ct = new ClassTable(logger);
        ClassCollectorASTVisitor classCollector = new ClassCollectorASTVisitor(ct);
        node.accept(classCollector);
        
        // When: You then build an inheritance graph from the ClassTable
        InheritanceGraphBuilder igb = new InheritanceGraphBuilder();
        igb.buildFromClassTable(ct);
        InheritanceGraph ig = igb.getInheritanceGraph();

        // Then: The underlying inheritance graph should fail the well formedness check
        assertFalse(ig.isWellFormed(ct, logger));

        // Then : The logger should have errors
        assertTrue(logger.hasErrors());

        logger.printErrors();

    }

    @Test
    public void testClassRedefinesBasicClass() {
       // Given: You have the following program string with a definition for class Object
        String programString = (
            "class Object {\n" +
            "   myAttr : Int <- 42;\n" +
            "   myMethod(x : Int, y : Bool) : String {\n" +
            "       if y then \"yes\" else \"no\" fi\n" +
            "   };\n" +
            "};"
        );
        // When: You parse the program to an AST and visit it with the ClassCollectorASTVisitor
        CoolParser.ProgramContext ctx = CoolTestUtils.parseProgram(programString);
        CoolParserToASTVisitor visitor = new CoolParserToASTVisitor();
        ASTNode node = visitor.visit(ctx);
        SemanticErrorLogger logger = new SemanticErrorLogger();
        ClassTable ct = new ClassTable(logger);
        ClassCollectorASTVisitor classCollector = new ClassCollectorASTVisitor(ct);
        node.accept(classCollector);
        

        // Then : The logger should have errors
        assertTrue(logger.hasErrors());

        logger.printErrors();

    }

    @Test
    public void testClassRedefinesExistingClass() {
       // Given: You have the following program string with a class that is defined twice
        String programString = (
            "class MyClass {\n" +
            "   myAttr : Int <- 42;\n" +
            "   myMethod(x : Int, y : Bool) : String {\n" +
            "       if y then \"yes\" else \"no\" fi\n" +
            "   };\n" +
            "};\n" +
            "class MyClass {};"
        );
        // When: You parse the program to an AST and visit it with the ClassCollectorASTVisitor
        CoolParser.ProgramContext ctx = CoolTestUtils.parseProgram(programString);
        CoolParserToASTVisitor visitor = new CoolParserToASTVisitor();
        ASTNode node = visitor.visit(ctx);
        SemanticErrorLogger logger = new SemanticErrorLogger();
        ClassTable ct = new ClassTable(logger);
        ClassCollectorASTVisitor classCollector = new ClassCollectorASTVisitor(ct);
        node.accept(classCollector);
        
        // Then : The logger should have errors
        assertTrue(logger.hasErrors());

        logger.printErrors();

    }

    @Test
    public void testClassInheritFromForbiddenBase() {
       // Given: You have the following program string with a class that inherits from Int
        String programString = (
            "class MyClass inherits Int {\n" +
            "   myAttr : Int <- 42;\n" +
            "   myMethod(x : Int, y : Bool) : String {\n" +
            "       if y then \"yes\" else \"no\" fi\n" +
            "   };\n" +
            "};\n" +
            "class MyClass {};"
        );
        // When: You parse the program to an AST and visit it with the ClassCollectorASTVisitor
        CoolParser.ProgramContext ctx = CoolTestUtils.parseProgram(programString);
        CoolParserToASTVisitor visitor = new CoolParserToASTVisitor();
        ASTNode node = visitor.visit(ctx);
        SemanticErrorLogger logger = new SemanticErrorLogger();
        ClassTable ct = new ClassTable(logger);
        ClassCollectorASTVisitor classCollector = new ClassCollectorASTVisitor(ct);
        node.accept(classCollector);
        
        // When: You then build an inheritance graph from the ClassTable
        InheritanceGraphBuilder igb = new InheritanceGraphBuilder();
        igb.buildFromClassTable(ct);
        InheritanceGraph ig = igb.getInheritanceGraph();

        // Then: The inheritance graph should not be well formed
        assertFalse(ig.isWellFormed(ct, logger));
        // Then : The logger should have errors
        assertTrue(logger.hasErrors());

        logger.printErrors();

    }

    @Test
    public void testClassTypeJoinOperation() {
       // Given: You have the following program string with a class that inherits from Int
        String programString = (
            "class MyClass inherits MyParent {\n" +
            "   myAttr : Int <- 42;\n" +
            "   myMethod(x : Int, y : Bool) : String {\n" +
            "       if y then \"yes\" else \"no\" fi\n" +
            "   };\n" +
            "};\n" +
            "class MyParent inherits MyGrandParent {};\n" +
            "class MyGrandParent {};\n" +
            "class MyOtherClass inherits MyOtherParent {};\n" +
            "class MyOtherParent inherits MyGrandParent {};\n" +
            "class MySibling inherits MyParent {};\n" +
            "class MyStranger {};\n"
        );
        // When: You parse the program to an AST and visit it with the ClassCollectorASTVisitor
        CoolParser.ProgramContext ctx = CoolTestUtils.parseProgram(programString);
        CoolParserToASTVisitor visitor = new CoolParserToASTVisitor();
        ASTNode node = visitor.visit(ctx);
        SemanticErrorLogger logger = new SemanticErrorLogger();
        ClassTable ct = new ClassTable(logger);
        ClassCollectorASTVisitor classCollector = new ClassCollectorASTVisitor(ct);
        node.accept(classCollector);
        
        // When: You then build an inheritance graph from the ClassTable
        InheritanceGraphBuilder igb = new InheritanceGraphBuilder();
        igb.buildFromClassTable(ct);
        InheritanceGraph ig = igb.getInheritanceGraph();

        // Then: The inheritance graph should be well formed
        assertTrue(ig.isWellFormed(ct, logger));
        // Then : The logger should not have errors
        assertFalse(logger.hasErrors());
        String typeJoin = ct.computeTypeJoin("MyParent", "MyOtherClass");
        String typeJoin2 = ct.computeTypeJoin("MyClass", "MyOtherClass");
        // Then: The type joins should be as expected
        assertEquals(typeJoin, "MyGrandParent");
        assertEquals(typeJoin2, "MyGrandParent");
        assertEquals(ct.computeTypeJoin("MyClass", "MySibling"), "MyParent");
        assertEquals(ct.computeTypeJoin("MyClass", "MyStranger"), "Object");

    }

}
