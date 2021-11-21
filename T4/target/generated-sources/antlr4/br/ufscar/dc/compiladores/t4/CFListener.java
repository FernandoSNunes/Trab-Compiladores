// Generated from br\u005Cufscar\dc\compiladores\t4\CF.g4 by ANTLR 4.7.2
package br.ufscar.dc.compiladores.t4;
import org.antlr.v4.runtime.tree.ParseTreeListener;

/**
 * This interface defines a complete listener for a parse tree produced by
 * {@link CFParser}.
 */
public interface CFListener extends ParseTreeListener {
	/**
	 * Enter a parse tree produced by {@link CFParser#programa}.
	 * @param ctx the parse tree
	 */
	void enterPrograma(CFParser.ProgramaContext ctx);
	/**
	 * Exit a parse tree produced by {@link CFParser#programa}.
	 * @param ctx the parse tree
	 */
	void exitPrograma(CFParser.ProgramaContext ctx);
	/**
	 * Enter a parse tree produced by {@link CFParser#produtos}.
	 * @param ctx the parse tree
	 */
	void enterProdutos(CFParser.ProdutosContext ctx);
	/**
	 * Exit a parse tree produced by {@link CFParser#produtos}.
	 * @param ctx the parse tree
	 */
	void exitProdutos(CFParser.ProdutosContext ctx);
	/**
	 * Enter a parse tree produced by {@link CFParser#precos}.
	 * @param ctx the parse tree
	 */
	void enterPrecos(CFParser.PrecosContext ctx);
	/**
	 * Exit a parse tree produced by {@link CFParser#precos}.
	 * @param ctx the parse tree
	 */
	void exitPrecos(CFParser.PrecosContext ctx);
	/**
	 * Enter a parse tree produced by {@link CFParser#nome}.
	 * @param ctx the parse tree
	 */
	void enterNome(CFParser.NomeContext ctx);
	/**
	 * Exit a parse tree produced by {@link CFParser#nome}.
	 * @param ctx the parse tree
	 */
	void exitNome(CFParser.NomeContext ctx);
	/**
	 * Enter a parse tree produced by {@link CFParser#impostos}.
	 * @param ctx the parse tree
	 */
	void enterImpostos(CFParser.ImpostosContext ctx);
	/**
	 * Exit a parse tree produced by {@link CFParser#impostos}.
	 * @param ctx the parse tree
	 */
	void exitImpostos(CFParser.ImpostosContext ctx);
	/**
	 * Enter a parse tree produced by {@link CFParser#porcentagens}.
	 * @param ctx the parse tree
	 */
	void enterPorcentagens(CFParser.PorcentagensContext ctx);
	/**
	 * Exit a parse tree produced by {@link CFParser#porcentagens}.
	 * @param ctx the parse tree
	 */
	void exitPorcentagens(CFParser.PorcentagensContext ctx);
	/**
	 * Enter a parse tree produced by {@link CFParser#taxas}.
	 * @param ctx the parse tree
	 */
	void enterTaxas(CFParser.TaxasContext ctx);
	/**
	 * Exit a parse tree produced by {@link CFParser#taxas}.
	 * @param ctx the parse tree
	 */
	void exitTaxas(CFParser.TaxasContext ctx);
	/**
	 * Enter a parse tree produced by {@link CFParser#loja}.
	 * @param ctx the parse tree
	 */
	void enterLoja(CFParser.LojaContext ctx);
	/**
	 * Exit a parse tree produced by {@link CFParser#loja}.
	 * @param ctx the parse tree
	 */
	void exitLoja(CFParser.LojaContext ctx);
	/**
	 * Enter a parse tree produced by {@link CFParser#nota}.
	 * @param ctx the parse tree
	 */
	void enterNota(CFParser.NotaContext ctx);
	/**
	 * Exit a parse tree produced by {@link CFParser#nota}.
	 * @param ctx the parse tree
	 */
	void exitNota(CFParser.NotaContext ctx);
	/**
	 * Enter a parse tree produced by {@link CFParser#itens}.
	 * @param ctx the parse tree
	 */
	void enterItens(CFParser.ItensContext ctx);
	/**
	 * Exit a parse tree produced by {@link CFParser#itens}.
	 * @param ctx the parse tree
	 */
	void exitItens(CFParser.ItensContext ctx);
	/**
	 * Enter a parse tree produced by {@link CFParser#infos}.
	 * @param ctx the parse tree
	 */
	void enterInfos(CFParser.InfosContext ctx);
	/**
	 * Exit a parse tree produced by {@link CFParser#infos}.
	 * @param ctx the parse tree
	 */
	void exitInfos(CFParser.InfosContext ctx);
}