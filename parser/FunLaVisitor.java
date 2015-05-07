// Generated from FunLa.g4 by ANTLR 4.5

package parser;
import ast.*;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link FunLaParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface FunLaVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link FunLaParser#program}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProgram(FunLaParser.ProgramContext ctx);
	/**
	 * Visit a parse tree produced by {@link FunLaParser#primaryExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrimaryExp(FunLaParser.PrimaryExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link FunLaParser#consExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitConsExp(FunLaParser.ConsExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link FunLaParser#nilExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNilExp(FunLaParser.NilExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link FunLaParser#sdataExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitSdataExp(FunLaParser.SdataExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link FunLaParser#funcAppExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncAppExp(FunLaParser.FuncAppExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link FunLaParser#unaryExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitUnaryExp(FunLaParser.UnaryExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link FunLaParser#multiplicativeExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitMultiplicativeExp(FunLaParser.MultiplicativeExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link FunLaParser#additiveExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitAdditiveExp(FunLaParser.AdditiveExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link FunLaParser#relationalExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitRelationalExp(FunLaParser.RelationalExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link FunLaParser#equalityExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitEqualityExp(FunLaParser.EqualityExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link FunLaParser#logicalAndExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalAndExp(FunLaParser.LogicalAndExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link FunLaParser#logicalOrExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLogicalOrExp(FunLaParser.LogicalOrExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link FunLaParser#binaryExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitBinaryExp(FunLaParser.BinaryExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link FunLaParser#funcExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitFuncExp(FunLaParser.FuncExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link FunLaParser#ifExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitIfExp(FunLaParser.IfExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link FunLaParser#letExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLetExp(FunLaParser.LetExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link FunLaParser#patternConsExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPatternConsExp(FunLaParser.PatternConsExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link FunLaParser#caseExp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitCaseExp(FunLaParser.CaseExpContext ctx);
	/**
	 * Visit a parse tree produced by {@link FunLaParser#exp}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitExp(FunLaParser.ExpContext ctx);
}