// Generated from br\u005Cufscar\dc\compiladores\t4\CF.g4 by ANTLR 4.7.2
package br.ufscar.dc.compiladores.t4;
import org.antlr.v4.runtime.tree.ParseTreeVisitor;

/**
 * This interface defines a complete generic visitor for a parse tree produced
 * by {@link CFParser}.
 *
 * @param <T> The return type of the visit operation. Use {@link Void} for
 * operations with no return type.
 */
public interface CFVisitor<T> extends ParseTreeVisitor<T> {
	/**
	 * Visit a parse tree produced by {@link CFParser#programa}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrograma(CFParser.ProgramaContext ctx);
	/**
	 * Visit a parse tree produced by {@link CFParser#produtos}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitProdutos(CFParser.ProdutosContext ctx);
	/**
	 * Visit a parse tree produced by {@link CFParser#precos}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPrecos(CFParser.PrecosContext ctx);
	/**
	 * Visit a parse tree produced by {@link CFParser#nome}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNome(CFParser.NomeContext ctx);
	/**
	 * Visit a parse tree produced by {@link CFParser#impostos}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitImpostos(CFParser.ImpostosContext ctx);
	/**
	 * Visit a parse tree produced by {@link CFParser#porcentagens}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitPorcentagens(CFParser.PorcentagensContext ctx);
	/**
	 * Visit a parse tree produced by {@link CFParser#taxas}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitTaxas(CFParser.TaxasContext ctx);
	/**
	 * Visit a parse tree produced by {@link CFParser#loja}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitLoja(CFParser.LojaContext ctx);
	/**
	 * Visit a parse tree produced by {@link CFParser#nota}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitNota(CFParser.NotaContext ctx);
	/**
	 * Visit a parse tree produced by {@link CFParser#itens}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitItens(CFParser.ItensContext ctx);
	/**
	 * Visit a parse tree produced by {@link CFParser#infos}.
	 * @param ctx the parse tree
	 * @return the visitor result
	 */
	T visitInfos(CFParser.InfosContext ctx);
}