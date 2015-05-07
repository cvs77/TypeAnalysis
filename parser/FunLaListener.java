// Generated from FunLa.g4 by ANTLR 4.5

package parser;
import ast.*;

import org.antlr.v4.runtime.misc.NotNull;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link FunLaParser}.
 */
public interface FunLaListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link FunLaParser#program}.
	 * @param ctx the parse tree
	 */
	void enterProgram(FunLaParser.ProgramContext ctx);
	/**
	 * Exit a parse tree produced by {@link FunLaParser#program}.
	 * @param ctx the parse tree
	 */
	void exitProgram(FunLaParser.ProgramContext ctx);
	/**
	 * Enter a parse tree produced by {@link FunLaParser#primaryExp}.
	 * @param ctx the parse tree
	 */
	void enterPrimaryExp(FunLaParser.PrimaryExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link FunLaParser#primaryExp}.
	 * @param ctx the parse tree
	 */
	void exitPrimaryExp(FunLaParser.PrimaryExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link FunLaParser#consExp}.
	 * @param ctx the parse tree
	 */
	void enterConsExp(FunLaParser.ConsExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link FunLaParser#consExp}.
	 * @param ctx the parse tree
	 */
	void exitConsExp(FunLaParser.ConsExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link FunLaParser#nilExp}.
	 * @param ctx the parse tree
	 */
	void enterNilExp(FunLaParser.NilExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link FunLaParser#nilExp}.
	 * @param ctx the parse tree
	 */
	void exitNilExp(FunLaParser.NilExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link FunLaParser#sdataExp}.
	 * @param ctx the parse tree
	 */
	void enterSdataExp(FunLaParser.SdataExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link FunLaParser#sdataExp}.
	 * @param ctx the parse tree
	 */
	void exitSdataExp(FunLaParser.SdataExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link FunLaParser#funcAppExp}.
	 * @param ctx the parse tree
	 */
	void enterFuncAppExp(FunLaParser.FuncAppExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link FunLaParser#funcAppExp}.
	 * @param ctx the parse tree
	 */
	void exitFuncAppExp(FunLaParser.FuncAppExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link FunLaParser#unaryExp}.
	 * @param ctx the parse tree
	 */
	void enterUnaryExp(FunLaParser.UnaryExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link FunLaParser#unaryExp}.
	 * @param ctx the parse tree
	 */
	void exitUnaryExp(FunLaParser.UnaryExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link FunLaParser#multiplicativeExp}.
	 * @param ctx the parse tree
	 */
	void enterMultiplicativeExp(FunLaParser.MultiplicativeExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link FunLaParser#multiplicativeExp}.
	 * @param ctx the parse tree
	 */
	void exitMultiplicativeExp(FunLaParser.MultiplicativeExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link FunLaParser#additiveExp}.
	 * @param ctx the parse tree
	 */
	void enterAdditiveExp(FunLaParser.AdditiveExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link FunLaParser#additiveExp}.
	 * @param ctx the parse tree
	 */
	void exitAdditiveExp(FunLaParser.AdditiveExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link FunLaParser#relationalExp}.
	 * @param ctx the parse tree
	 */
	void enterRelationalExp(FunLaParser.RelationalExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link FunLaParser#relationalExp}.
	 * @param ctx the parse tree
	 */
	void exitRelationalExp(FunLaParser.RelationalExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link FunLaParser#equalityExp}.
	 * @param ctx the parse tree
	 */
	void enterEqualityExp(FunLaParser.EqualityExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link FunLaParser#equalityExp}.
	 * @param ctx the parse tree
	 */
	void exitEqualityExp(FunLaParser.EqualityExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link FunLaParser#logicalAndExp}.
	 * @param ctx the parse tree
	 */
	void enterLogicalAndExp(FunLaParser.LogicalAndExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link FunLaParser#logicalAndExp}.
	 * @param ctx the parse tree
	 */
	void exitLogicalAndExp(FunLaParser.LogicalAndExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link FunLaParser#logicalOrExp}.
	 * @param ctx the parse tree
	 */
	void enterLogicalOrExp(FunLaParser.LogicalOrExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link FunLaParser#logicalOrExp}.
	 * @param ctx the parse tree
	 */
	void exitLogicalOrExp(FunLaParser.LogicalOrExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link FunLaParser#binaryExp}.
	 * @param ctx the parse tree
	 */
	void enterBinaryExp(FunLaParser.BinaryExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link FunLaParser#binaryExp}.
	 * @param ctx the parse tree
	 */
	void exitBinaryExp(FunLaParser.BinaryExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link FunLaParser#funcExp}.
	 * @param ctx the parse tree
	 */
	void enterFuncExp(FunLaParser.FuncExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link FunLaParser#funcExp}.
	 * @param ctx the parse tree
	 */
	void exitFuncExp(FunLaParser.FuncExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link FunLaParser#ifExp}.
	 * @param ctx the parse tree
	 */
	void enterIfExp(FunLaParser.IfExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link FunLaParser#ifExp}.
	 * @param ctx the parse tree
	 */
	void exitIfExp(FunLaParser.IfExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link FunLaParser#letExp}.
	 * @param ctx the parse tree
	 */
	void enterLetExp(FunLaParser.LetExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link FunLaParser#letExp}.
	 * @param ctx the parse tree
	 */
	void exitLetExp(FunLaParser.LetExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link FunLaParser#patternConsExp}.
	 * @param ctx the parse tree
	 */
	void enterPatternConsExp(FunLaParser.PatternConsExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link FunLaParser#patternConsExp}.
	 * @param ctx the parse tree
	 */
	void exitPatternConsExp(FunLaParser.PatternConsExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link FunLaParser#caseExp}.
	 * @param ctx the parse tree
	 */
	void enterCaseExp(FunLaParser.CaseExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link FunLaParser#caseExp}.
	 * @param ctx the parse tree
	 */
	void exitCaseExp(FunLaParser.CaseExpContext ctx);
	/**
	 * Enter a parse tree produced by {@link FunLaParser#exp}.
	 * @param ctx the parse tree
	 */
	void enterExp(FunLaParser.ExpContext ctx);
	/**
	 * Exit a parse tree produced by {@link FunLaParser#exp}.
	 * @param ctx the parse tree
	 */
	void exitExp(FunLaParser.ExpContext ctx);
}