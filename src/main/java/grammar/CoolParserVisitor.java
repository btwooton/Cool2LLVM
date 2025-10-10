// Generated from CoolParser.g4 by ANTLR 4.13.2

package grammar;

import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CoolParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CoolParserVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CoolParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(CoolParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#class}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitClass(CoolParser.ClassContext ctx);
	/**
	 * Visit a parse tree produced by the {@code methodFeature}
	 * labeled alternative in {@link CoolParser#feature}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodFeature(CoolParser.MethodFeatureContext ctx);
	/**
	 * Visit a parse tree produced by the {@code attributeFeature}
	 * labeled alternative in {@link CoolParser#feature}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAttributeFeature(CoolParser.AttributeFeatureContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#formal}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFormal(CoolParser.FormalContext ctx);
	/**
	 * Visit a parse tree produced by {@link CoolParser#let_binding}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLet_binding(CoolParser.Let_bindingContext ctx);
	/**
	 * Visit a parse tree produced by the {@code newExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNewExpr(CoolParser.NewExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code complementExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitComplementExpr(CoolParser.ComplementExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code minusExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMinusExpr(CoolParser.MinusExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code letExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetExpr(CoolParser.LetExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code parenExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitParenExpr(CoolParser.ParenExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code methodCallExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMethodCallExpr(CoolParser.MethodCallExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code ltExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLtExpr(CoolParser.LtExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code isvoidExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIsvoidExpr(CoolParser.IsvoidExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code divExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitDivExpr(CoolParser.DivExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code blockExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBlockExpr(CoolParser.BlockExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code assignExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAssignExpr(CoolParser.AssignExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code conditionalExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConditionalExpr(CoolParser.ConditionalExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code falseLiteralExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFalseLiteralExpr(CoolParser.FalseLiteralExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code implicitMethodCallExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImplicitMethodCallExpr(CoolParser.ImplicitMethodCallExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code integerLiteralExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIntegerLiteralExpr(CoolParser.IntegerLiteralExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code stringLiteralExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitStringLiteralExpr(CoolParser.StringLiteralExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code multExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultExpr(CoolParser.MultExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code plusExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPlusExpr(CoolParser.PlusExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code loopExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoopExpr(CoolParser.LoopExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code eqExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqExpr(CoolParser.EqExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code notExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNotExpr(CoolParser.NotExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code trueLiteralExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTrueLiteralExpr(CoolParser.TrueLiteralExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code leExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLeExpr(CoolParser.LeExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code caseExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseExpr(CoolParser.CaseExprContext ctx);
	/**
	 * Visit a parse tree produced by the {@code identifierExpr}
	 * labeled alternative in {@link CoolParser#expr}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIdentifierExpr(CoolParser.IdentifierExprContext ctx);
}