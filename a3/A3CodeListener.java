// Generated from A3Code.g4 by ANTLR 4.5.3


import java.io.*;
import java.util.*;

import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link A3CodeParser}.
 */
public interface A3CodeListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link A3CodeParser#prog}.
	 * @param ctx the parse tree
	 */
	void enterProg(A3CodeParser.ProgContext ctx);
	/**
	 * Exit a parse tree produced by {@link A3CodeParser#prog}.
	 * @param ctx the parse tree
	 */
	void exitProg(A3CodeParser.ProgContext ctx);
	/**
	 * Enter a parse tree produced by {@link A3CodeParser#field_decls}.
	 * @param ctx the parse tree
	 */
	void enterField_decls(A3CodeParser.Field_declsContext ctx);
	/**
	 * Exit a parse tree produced by {@link A3CodeParser#field_decls}.
	 * @param ctx the parse tree
	 */
	void exitField_decls(A3CodeParser.Field_declsContext ctx);
	/**
	 * Enter a parse tree produced by {@link A3CodeParser#field_decl}.
	 * @param ctx the parse tree
	 */
	void enterField_decl(A3CodeParser.Field_declContext ctx);
	/**
	 * Exit a parse tree produced by {@link A3CodeParser#field_decl}.
	 * @param ctx the parse tree
	 */
	void exitField_decl(A3CodeParser.Field_declContext ctx);
	/**
	 * Enter a parse tree produced by {@link A3CodeParser#method_decl}.
	 * @param ctx the parse tree
	 */
	void enterMethod_decl(A3CodeParser.Method_declContext ctx);
	/**
	 * Exit a parse tree produced by {@link A3CodeParser#method_decl}.
	 * @param ctx the parse tree
	 */
	void exitMethod_decl(A3CodeParser.Method_declContext ctx);
	/**
	 * Enter a parse tree produced by {@link A3CodeParser#params}.
	 * @param ctx the parse tree
	 */
	void enterParams(A3CodeParser.ParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link A3CodeParser#params}.
	 * @param ctx the parse tree
	 */
	void exitParams(A3CodeParser.ParamsContext ctx);
	/**
	 * Enter a parse tree produced by {@link A3CodeParser#nextParams}.
	 * @param ctx the parse tree
	 */
	void enterNextParams(A3CodeParser.NextParamsContext ctx);
	/**
	 * Exit a parse tree produced by {@link A3CodeParser#nextParams}.
	 * @param ctx the parse tree
	 */
	void exitNextParams(A3CodeParser.NextParamsContext ctx);
	/**
	 * Enter a parse tree produced by {@link A3CodeParser#block}.
	 * @param ctx the parse tree
	 */
	void enterBlock(A3CodeParser.BlockContext ctx);
	/**
	 * Exit a parse tree produced by {@link A3CodeParser#block}.
	 * @param ctx the parse tree
	 */
	void exitBlock(A3CodeParser.BlockContext ctx);
	/**
	 * Enter a parse tree produced by {@link A3CodeParser#var_decls}.
	 * @param ctx the parse tree
	 */
	void enterVar_decls(A3CodeParser.Var_declsContext ctx);
	/**
	 * Exit a parse tree produced by {@link A3CodeParser#var_decls}.
	 * @param ctx the parse tree
	 */
	void exitVar_decls(A3CodeParser.Var_declsContext ctx);
	/**
	 * Enter a parse tree produced by {@link A3CodeParser#var_decl}.
	 * @param ctx the parse tree
	 */
	void enterVar_decl(A3CodeParser.Var_declContext ctx);
	/**
	 * Exit a parse tree produced by {@link A3CodeParser#var_decl}.
	 * @param ctx the parse tree
	 */
	void exitVar_decl(A3CodeParser.Var_declContext ctx);
	/**
	 * Enter a parse tree produced by {@link A3CodeParser#statements}.
	 * @param ctx the parse tree
	 */
	void enterStatements(A3CodeParser.StatementsContext ctx);
	/**
	 * Exit a parse tree produced by {@link A3CodeParser#statements}.
	 * @param ctx the parse tree
	 */
	void exitStatements(A3CodeParser.StatementsContext ctx);
	/**
	 * Enter a parse tree produced by {@link A3CodeParser#statement}.
	 * @param ctx the parse tree
	 */
	void enterStatement(A3CodeParser.StatementContext ctx);
	/**
	 * Exit a parse tree produced by {@link A3CodeParser#statement}.
	 * @param ctx the parse tree
	 */
	void exitStatement(A3CodeParser.StatementContext ctx);
	/**
	 * Enter a parse tree produced by {@link A3CodeParser#expr}.
	 * @param ctx the parse tree
	 */
	void enterExpr(A3CodeParser.ExprContext ctx);
	/**
	 * Exit a parse tree produced by {@link A3CodeParser#expr}.
	 * @param ctx the parse tree
	 */
	void exitExpr(A3CodeParser.ExprContext ctx);
	/**
	 * Enter a parse tree produced by {@link A3CodeParser#location}.
	 * @param ctx the parse tree
	 */
	void enterLocation(A3CodeParser.LocationContext ctx);
	/**
	 * Exit a parse tree produced by {@link A3CodeParser#location}.
	 * @param ctx the parse tree
	 */
	void exitLocation(A3CodeParser.LocationContext ctx);
	/**
	 * Enter a parse tree produced by {@link A3CodeParser#num}.
	 * @param ctx the parse tree
	 */
	void enterNum(A3CodeParser.NumContext ctx);
	/**
	 * Exit a parse tree produced by {@link A3CodeParser#num}.
	 * @param ctx the parse tree
	 */
	void exitNum(A3CodeParser.NumContext ctx);
	/**
	 * Enter a parse tree produced by {@link A3CodeParser#literal}.
	 * @param ctx the parse tree
	 */
	void enterLiteral(A3CodeParser.LiteralContext ctx);
	/**
	 * Exit a parse tree produced by {@link A3CodeParser#literal}.
	 * @param ctx the parse tree
	 */
	void exitLiteral(A3CodeParser.LiteralContext ctx);
	/**
	 * Enter a parse tree produced by {@link A3CodeParser#eqOp}.
	 * @param ctx the parse tree
	 */
	void enterEqOp(A3CodeParser.EqOpContext ctx);
	/**
	 * Exit a parse tree produced by {@link A3CodeParser#eqOp}.
	 * @param ctx the parse tree
	 */
	void exitEqOp(A3CodeParser.EqOpContext ctx);
}