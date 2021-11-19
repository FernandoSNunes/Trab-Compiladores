/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.compiladores.t4;

/**
 *
 * @author Fer_s
 */
import br.ufscar.dc.compiladores.t4.TabelaDeSimbolos.TipoCF;
import java.util.ArrayList;
import java.util.List;
import java.util.Vector;
import org.antlr.v4.runtime.Token;

public class CFSemantico extends CFBaseVisitor<Void> {

    public static List<String> errosSemanticos = new ArrayList<>();

    public static void adicionarErroSemantico(Token t, String mensagem) {
        int linha = t.getLine();
        errosSemanticos.add(String.format("Linha %d: %s", linha, mensagem));
    }

    TabelaDeSimbolos tabela;

    @Override
    public Void visitPrograma(CFParser.ProgramaContext ctx) {
        tabela = new TabelaDeSimbolos();
        return super.visitPrograma(ctx);
    }

    @Override
    public Void visitProdutos(CFParser.ProdutosContext ctx) {
        
        //for (CFParser.PrecosContext pr: ctx.precos()){
        for (int i = 0; i < ctx.precos().size(); i++) {

            TabelaDeSimbolos.TipoCF tipo = ctx.precos(i).UNIDADE_MEDIDA().getText().equals("un") ? TipoCF.UNIDADE : TipoCF.QUILO;

            for (CFParser.NomeContext no : ctx.precos(i).nome()) {
                String nome = "";
                for (int j = 0; j < no.IDENT().size(); j++){
                    if (j > 0)
                        nome += " ";
                    nome += no.IDENT(j).getText();
                }

                if (tabela.existe(nome)) {
                    adicionarErroSemantico(no.start, "Produto " + nome + " duplicado");
                } else {
                    tabela.adicionar(nome, tipo);
                }
            }
        }
        return super.visitProdutos(ctx); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Void visitImpostos(CFParser.ImpostosContext ctx) {
        
        //for (CFParser.PrecosContext pr: ctx.precos()){
        for (int i = 0; i < ctx.porcentagens().size(); i++) {

            for (CFParser.NomeContext po : ctx.porcentagens(i).nome()) {
                String nome = "";
                for (int j = 0; j < po.IDENT().size(); j++){
                    if (j > 0)
                        nome += " ";
                    nome += po.IDENT(j).getText();
                }
                if (!tabela.existe(nome)) {
                    adicionarErroSemantico(po.start, "Produto " + nome + " nao foi declarado em PRODUTOS");
                }
            }
            
                if (ctx.porcentagens(i).NEGATIVO() != null)
                    adicionarErroSemantico(ctx.porcentagens(i).start, "Imposto nao pode ser negativo" );

        }
        
        return super.visitImpostos(ctx); //To change body of generated methods, choose Tools | Templates.
    }

    
    
}
