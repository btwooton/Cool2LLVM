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
}