package compiler;

import compiler.ast.*;
import compiler.utils.CoolTestUtils;
import grammar.CoolParser;
import compiler.visitors.CoolParserToASTVisitor;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;


public class CoolParserToASTVisitorTest {

    @Test
    public void testIntegerLiteral() {
        CoolParser.ExprContext ctx = CoolTestUtils.parseExpr("42");
        CoolParserToASTVisitor visitor = new CoolParserToASTVisitor();
        ASTNode node = visitor.visit(ctx);

        assertTrue(node instanceof LiteralExprNode);
        LiteralExprNode lit = (LiteralExprNode) node;
        assertEquals(LiteralExprNode.LiteralType.INT, lit.type);
        assertEquals(42, lit.value);
    }

    @Test
    public void testStringLiteral() {
        CoolParser.ExprContext ctx = CoolTestUtils.parseExpr("\"hello\"");
        CoolParserToASTVisitor visitor = new CoolParserToASTVisitor();
        ASTNode node = visitor.visit(ctx);

        assertTrue(node instanceof LiteralExprNode);
        LiteralExprNode lit = (LiteralExprNode) node;
        assertEquals(LiteralExprNode.LiteralType.STRING, lit.type);
        assertEquals("hello", lit.value);
    }

    @Test
    public void testBooleanLiteral() {
        CoolParser.ExprContext ctx = CoolTestUtils.parseExpr("true");
        CoolParserToASTVisitor visitor = new CoolParserToASTVisitor();
        ASTNode node = visitor.visit(ctx);

        assertTrue(node instanceof LiteralExprNode);
        LiteralExprNode lit = (LiteralExprNode) node;
        assertEquals(LiteralExprNode.LiteralType.BOOL, lit.type);
        assertEquals(true, lit.value);

        ctx = CoolTestUtils.parseExpr("false");
        node = visitor.visit(ctx);
        assertTrue(node instanceof LiteralExprNode);
        lit = (LiteralExprNode) node;
        assertEquals(LiteralExprNode.LiteralType.BOOL, lit.type);
        assertEquals(false, lit.value);
    }

    @Test
    public void testBinaryOp() {
        // Given: You parse the following Cool expression
        CoolParser.ExprContext ctx = CoolTestUtils.parseExpr("1 + 2");
        // When: You visit the parse tree with your CoolParserToASTVisitor
        CoolParserToASTVisitor visitor = new CoolParserToASTVisitor();
        ASTNode node = visitor.visit(ctx);
        // Then: You get a BinaryOpExprNode with Op.ADD
        assertTrue(node instanceof BinaryOpExprNode);
        BinaryOpExprNode bin = (BinaryOpExprNode) node;
        assertEquals(BinaryOpExprNode.Op.ADD, bin.operator);
        // Then: The left and right children are LiteralExprNodes with integer values 1 and 2
        assertTrue(bin.left instanceof LiteralExprNode);
        assertTrue(bin.right instanceof LiteralExprNode);
        assertEquals(LiteralExprNode.LiteralType.INT, ((LiteralExprNode) bin.left).type);
        assertEquals(LiteralExprNode.LiteralType.INT, ((LiteralExprNode) bin.right).type);
        assertEquals(1, ((LiteralExprNode) bin.left).value);
        assertEquals(2, ((LiteralExprNode) bin.right).value);
    }

    @Test
    public void testParenExpr() {
        // Given: You parse the following Cool expression
        CoolParser.ExprContext ctx = CoolTestUtils.parseExpr("(3 + 4 * 5)");
        // When: You visit the parse tree with your CoolParserToASTVisitor
        CoolParserToASTVisitor visitor = new CoolParserToASTVisitor();
        ASTNode node = visitor.visit(ctx);
        // Then: You get a ParenExprNode
        assertTrue(node instanceof ParenExprNode);
        ParenExprNode paren = (ParenExprNode) node;
        // Then: The inner expression is a BinaryOpExprNode with Op. ADD
        assertTrue(paren.expr instanceof BinaryOpExprNode);
        BinaryOpExprNode bin = (BinaryOpExprNode) paren.expr;
        assertEquals(BinaryOpExprNode.Op.ADD, bin.operator);
        // Then: The left child is a LiteralExprNode with integer value 3
        assertTrue(bin.left instanceof LiteralExprNode);
        assertEquals(LiteralExprNode.LiteralType.INT, ((LiteralExprNode) bin.left).type);
        assertEquals(3, ((LiteralExprNode) bin.left).value);
        // Then: The right child is a BinaryOpExprNode with Op. MUL
        assertTrue(bin.right instanceof BinaryOpExprNode);
        BinaryOpExprNode rightBin = (BinaryOpExprNode) bin.right;
        assertEquals(BinaryOpExprNode.Op.MUL, rightBin.operator);
        // Then: The left and right children of the right binary op are LiteralExprNodes with integer values 4 and 5
        assertTrue(rightBin.left instanceof LiteralExprNode);
        assertTrue(rightBin.right instanceof LiteralExprNode);
        assertEquals(LiteralExprNode.LiteralType.INT, ((LiteralExprNode) rightBin.left).type);
        assertEquals(LiteralExprNode.LiteralType.INT, ((LiteralExprNode) rightBin.right).type);
        assertEquals(4, ((LiteralExprNode) rightBin.left).value);
        assertEquals(5, ((LiteralExprNode) rightBin.right).value);
    }

    @Test
    public void testUnaryOpExprs() {
        // Given: You parse the following Cool expression
        CoolParser.ExprContext ctx = CoolTestUtils.parseExpr("not false");
        // When: You visit the parse tree with your CoolParserToASTVisitor
        CoolParserToASTVisitor visitor = new CoolParserToASTVisitor();
        ASTNode node = visitor.visit(ctx);
        // Then: You get a UnaryOpExprNode with Op.NOT
        assertTrue(node instanceof UnaryOpExprNode);
        UnaryOpExprNode unary = (UnaryOpExprNode) node;
        assertEquals(UnaryOpExprNode.Op.NOT, unary.operator);
        // Then: The inner expression is a LiteralExprNode with boolean value false
        assertTrue(unary.expr instanceof LiteralExprNode);
        LiteralExprNode lit = (LiteralExprNode) unary.expr;
        assertEquals(LiteralExprNode.LiteralType.BOOL, lit.type);
        assertEquals(false, lit.value);

        // Given: You parse the following Cool expression
        ctx = CoolTestUtils.parseExpr("~10075");
        // When: You visit the parse tree with your CoolParserToASTVisitor
        node = visitor.visit(ctx);
        // Then: You get a UnaryOpExprNode with Op.COMP
        assertTrue(node instanceof UnaryOpExprNode);
        unary = (UnaryOpExprNode) node;
        assertEquals(UnaryOpExprNode.Op.COMP, unary.operator);
        // Then: The inner expression is a LiteralExprNode with integer value 10075
        assertTrue(unary.expr instanceof LiteralExprNode);
        lit = (LiteralExprNode) unary.expr;
        assertEquals(LiteralExprNode.LiteralType.INT, lit.type);
        assertEquals(10075, lit.value);

    }

    @Test
    public void testIdentifierExpr() {
        // Given: You parse the following Cool expression containing an identifier
        CoolParser.ExprContext ctx = CoolTestUtils.parseExpr("myVar");
        // When: You visit the parse tree with your CoolParserToASTVisitor
        CoolParserToASTVisitor visitor = new CoolParserToASTVisitor();
        ASTNode node = visitor.visit(ctx);
        // Then: You get an IdentifierExprNode with the correct name
        assertTrue(node instanceof IdentifierExprNode);
        IdentifierExprNode id = (IdentifierExprNode) node;
        assertEquals("myVar", id.name);
    }

    @Test
    public void testIsVoidExpr() {
        // Given: You parse the following Cool expression containing an isvoid operation
        CoolParser.ExprContext ctx = CoolTestUtils.parseExpr("isvoid myVar");
        // When: You visit the parse tree with your CoolParserToASTVisitor
        CoolParserToASTVisitor visitor = new CoolParserToASTVisitor();
        ASTNode node = visitor.visit(ctx);
        // Then: You get an IsVoidExprNode
        assertTrue(node instanceof IsVoidExprNode);
        IsVoidExprNode isvoid = (IsVoidExprNode) node;
        // Then: The inner expression is an IdentifierExprNode with the correct name
        assertTrue(isvoid.expr instanceof IdentifierExprNode);
        IdentifierExprNode id = (IdentifierExprNode) isvoid.expr;
        assertEquals("myVar", id.name);
    }

    @Test
    public void testNewExpr() {
        // Given: You parse the following Cool expression containing a new operation
        CoolParser.ExprContext ctx = CoolTestUtils.parseExpr("new MyClass");
        // When: You visit the parse tree with your CoolParserToASTVisitor
        CoolParserToASTVisitor visitor = new CoolParserToASTVisitor();
        ASTNode node = visitor.visit(ctx);
        // Then: You get a NewExprNode
        assertTrue(node instanceof NewExprNode);
        NewExprNode newExpr = (NewExprNode) node;
        // Then: The typeName is correct
        assertEquals("MyClass", newExpr.typeName);
    }

    @Test
    public void testCaseExpr() {
        // Given: You parse the following Cool expression containing a case statement
        CoolParser.ExprContext ctx = CoolTestUtils.parseExpr("case myVar of x: Int => x + 1; y: Bool => not y; esac");
        // When: You visit the parse tree with your CoolParserToASTVisitor
        CoolParserToASTVisitor visitor = new CoolParserToASTVisitor();
        ASTNode node = visitor.visit(ctx);
        // Then: You get a CaseExprNode at the top level
        assertTrue(node instanceof CaseExprNode);
        CaseExprNode caseExpr = (CaseExprNode) node;
        // Then: The expression being switched on is an IdentifierExprNode with the correct name
        assertTrue(caseExpr.expr instanceof IdentifierExprNode);
        IdentifierExprNode id = (IdentifierExprNode) caseExpr.expr;
        assertEquals("myVar", id.name);
        // Then: There are two branches
        assertEquals(2, caseExpr.branches.size());
        // Then: The first branch has the correct variable name, type, and expression
        CaseBranch branch1 = caseExpr.branches.get(0);
        assertEquals("x", branch1.varName);
        assertEquals("Int", branch1.varType);
        assertTrue(branch1.expr instanceof BinaryOpExprNode);
        BinaryOpExprNode bin = (BinaryOpExprNode) branch1.expr;
        assertEquals(BinaryOpExprNode.Op.ADD, bin.operator);
        assertTrue(bin.left instanceof IdentifierExprNode);
        assertEquals("x", ((IdentifierExprNode) bin.left).name);
        assertTrue(bin.right instanceof LiteralExprNode);
        assertEquals(LiteralExprNode.LiteralType.INT, ((LiteralExprNode) bin.right).type);
        assertEquals(1, ((LiteralExprNode) bin.right).value);
        // Then: The second branch has the correct variable name, type, and expression
        CaseBranch branch2 = caseExpr.branches.get(1);
        assertEquals("y", branch2.varName);
        assertEquals("Bool", branch2.varType);
        assertTrue(branch2.expr instanceof UnaryOpExprNode);
        UnaryOpExprNode unary = (UnaryOpExprNode) branch2.expr;
        assertEquals(UnaryOpExprNode.Op.NOT, unary.operator);
        assertTrue(unary.expr instanceof IdentifierExprNode);
        assertEquals("y", ((IdentifierExprNode) unary.expr).name);
    }
    
    @Test
    public void testLetExpr() {
        // Given: You parse the following Cool expression containing a let
        String letExprStr = (
            "let x : Int <- 5 + 10, y : Int <- 10 + 10, z : Int in x + y + z"
        );
        CoolParser.ExprContext ctx = CoolTestUtils.parseExpr(letExprStr);
        // When: You visit the parse tree with your CoolParserToASTVisitor
        CoolParserToASTVisitor visitor = new CoolParserToASTVisitor();
        ASTNode node = visitor.visit(ctx);
        System.out.println(node.getClass().getName());
        // Then: You get a LetExprNode at the top level
        assertTrue(node instanceof LetExprNode);
        // Then: The LetExprNode has three bindings
        LetExprNode letExpr = (LetExprNode) node;
        assertEquals(3, letExpr.letBindings.size());
        // Then: The body is a BinaryOpExprNode representing x + y + z
        assertTrue(letExpr.body instanceof BinaryOpExprNode);
        BinaryOpExprNode bodyBin = (BinaryOpExprNode) letExpr.body;
        assertEquals(BinaryOpExprNode.Op.ADD, bodyBin.operator);
        // Then: The left child of the body is a BinaryOpExprNode representing x + y
        assertTrue(bodyBin.left instanceof BinaryOpExprNode);
        BinaryOpExprNode leftBin = (BinaryOpExprNode) bodyBin.left;
        assertEquals(BinaryOpExprNode.Op.ADD, leftBin.operator);
        // Then: The left child of the left binary op is an IdentifierExprNode for x
        assertTrue(leftBin.left instanceof IdentifierExprNode);
        assertEquals("x", ((IdentifierExprNode) leftBin.left).name);
        // Then: The right child of the left binary op is an IdentifierExprNode for y
        assertTrue(leftBin.right instanceof IdentifierExprNode);
        assertEquals("y", ((IdentifierExprNode) leftBin.right).name);
        // Then: The right child of the body binary op is an IdentifierExprNode for z
        assertTrue(bodyBin.right instanceof IdentifierExprNode);
        assertEquals("z", ((IdentifierExprNode) bodyBin.right).name);
    }

    @Test
    public void testBlockExpr() {
        // Given: You parse the following Cool expression containing a block
        CoolParser.ExprContext ctx = CoolTestUtils.parseExpr("{ 1 + 2; isvoid myVar; \"hello\"; }");
        // When: You visit the parse tree with your CoolParserToASTVisitor
        CoolParserToASTVisitor visitor = new CoolParserToASTVisitor();
        ASTNode node = visitor.visit(ctx);
        // Then: You get a BlockExprNode at the top level
        assertTrue(node instanceof BlockExprNode);
        BlockExprNode block = (BlockExprNode) node;
        // Then: The block has three expressions
        assertEquals(3, block.expressions.size());
        // Then: The first expression is a BinaryOpExprNode for 1 + 2
        assertTrue(block.expressions.get(0) instanceof BinaryOpExprNode);
        BinaryOpExprNode bin = (BinaryOpExprNode) block.expressions.get(0);
        assertEquals(BinaryOpExprNode.Op.ADD, bin.operator);
        assertTrue(bin.left instanceof LiteralExprNode);
        assertEquals(1, ((LiteralExprNode) bin.left).value);
        assertTrue(bin.right instanceof LiteralExprNode);
        assertEquals(2, ((LiteralExprNode) bin.right).value);
        // Then: The second expression is an IsVoidExprNode for isvoid myVar
        assertTrue(block.expressions.get(1) instanceof IsVoidExprNode);
        IsVoidExprNode isvoid = (IsVoidExprNode) block.expressions.get(1);
        assertTrue(isvoid.expr instanceof IdentifierExprNode);
        assertEquals("myVar", ((IdentifierExprNode) isvoid.expr).name);
        // Then: The third expression is a LiteralExprNode for the string "hello"
        assertTrue(block.expressions.get(2) instanceof LiteralExprNode);
        LiteralExprNode lit = (LiteralExprNode) block.expressions.get(2);
        assertEquals(LiteralExprNode.LiteralType.STRING, lit.type);
        assertEquals("hello", lit.value);
    }
}