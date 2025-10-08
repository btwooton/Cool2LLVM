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
}