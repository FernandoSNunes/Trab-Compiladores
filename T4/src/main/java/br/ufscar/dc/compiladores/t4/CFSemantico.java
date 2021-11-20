package br.ufscar.dc.compiladores.t4;

import br.ufscar.dc.compiladores.t4.CFParser.PorcentagensContext;
import br.ufscar.dc.compiladores.t4.CFParser.TaxasContext;
import br.ufscar.dc.compiladores.t4.TabelaDeSimbolos.TipoCF;
import java.util.ArrayList;
import java.util.List;
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

        // for (CFParser.PrecosContext pr: ctx.precos()){
        for (int i = 0; i < ctx.precos().size(); i++) {

            TabelaDeSimbolos.TipoCF tipo = ctx.precos(i).UNIDADE_MEDIDA().getText().equals("un") ? TipoCF.UNIDADE
                    : TipoCF.QUILO;

            for (CFParser.NomeContext no : ctx.precos(i).nome()) {
                String nome = "";
                for (int j = 0; j < no.IDENT().size(); j++) {
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
        return super.visitProdutos(ctx);
    }

    @Override
    public Void visitImpostos(CFParser.ImpostosContext ctx) {

        // for (CFParser.PrecosContext pr: ctx.precos()){
        for (PorcentagensContext porcentagemCtx : ctx.porcentagens()) {

            for (CFParser.NomeContext porcentagem : porcentagemCtx.nome()) {
                String nome = CFSemanticoUtils.getNomePorcentagem(porcentagem);

                if (!tabela.existe(nome)) {
                    adicionarErroSemantico(porcentagem.start, "Produto " + nome + " nao foi declarado em PRODUTOS");
                } else if (CFSemanticoUtils.isPorcentagemNegativa(porcentagemCtx)) {
                    adicionarErroSemantico(porcentagemCtx.start, "Imposto nao pode ser negativo");
                } else if (CFSemanticoUtils.getValorPorcentagem(porcentagemCtx) > 100) {
                    adicionarErroSemantico(porcentagemCtx.start, "Imposto nao pode ser maior que 100%");
                } else {
                    CFSemanticoUtils.adicionarImposto(nome, porcentagemCtx);
                }
            }
        }

        CFSemanticoUtils.printImpostos();

        return super.visitImpostos(ctx);
    }

    @Override
    public Void visitTaxas(TaxasContext ctx) {

        for (PorcentagensContext porcentagemCtx : ctx.porcentagens()) {

            for (CFParser.NomeContext porcentagem : porcentagemCtx.nome()) {
                String nome = CFSemanticoUtils.getNomePorcentagem(porcentagem);

                if (tabela.existe(nome)) {
                    adicionarErroSemantico(porcentagem.start, nome + " ja declarado anteriormente");
                } else {
                    CFSemanticoUtils.adicionarTaxa(nome, porcentagemCtx);
                }
            }
        }

        CFSemanticoUtils.printTaxas();

        return super.visitTaxas(ctx);
    }

}
