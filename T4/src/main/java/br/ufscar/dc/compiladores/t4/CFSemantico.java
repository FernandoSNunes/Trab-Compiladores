package br.ufscar.dc.compiladores.t4;

import br.ufscar.dc.compiladores.t4.CFParser.ItensContext;
import br.ufscar.dc.compiladores.t4.CFParser.NotaContext;
import br.ufscar.dc.compiladores.t4.CFParser.PorcentagensContext;
import br.ufscar.dc.compiladores.t4.CFParser.PrecosContext;
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

        for (PrecosContext preco : ctx.precos()) {

            TabelaDeSimbolos.TipoCF tipo = preco.UNIDADE_MEDIDA().getText().equals("un")
                    ? TipoCF.UNIDADE
                    : TipoCF.QUILO;

            for (CFParser.NomeContext nome : preco.nome()) {
                String nomeProduto = CFUtils.getNome(nome);

                if (tabela.existe(nomeProduto)) {
                    adicionarErroSemantico(nome.start, "Produto " + nomeProduto + " duplicado");
                } else {
                    tabela.adicionar(nomeProduto, tipo);
                }
            }
        }
        return super.visitProdutos(ctx);
    }

    @Override
    public Void visitImpostos(CFParser.ImpostosContext ctx) {

        for (PorcentagensContext porcentagemCtx : ctx.porcentagens()) {
            for (CFParser.NomeContext porcentagem : porcentagemCtx.nome()) {

                String nome = CFUtils.getNome(porcentagem);

                if (!tabela.existe(nome)) {
                    adicionarErroSemantico(porcentagem.start, "Produto " + nome + " nao foi declarado em PRODUTOS");
                } else if (CFUtils.isPorcentagemNegativa(porcentagemCtx)) {
                    adicionarErroSemantico(porcentagemCtx.start, "Imposto nao pode ser negativo");
                } else if (CFUtils.getValorPorcentagem(porcentagemCtx) > 100) {
                    adicionarErroSemantico(porcentagemCtx.start, "Imposto nao pode ser maior que 100%");
                } else {
                    tabela.adicionarImposto(CFUtils.getValorPorcentagem(porcentagemCtx), nome);
                }
            }
        }

        Object[] produtos = tabela.teste();
        for (int i = 0; i < produtos.length; i++) {
            if (! tabela.impostoRegistrado(produtos[i].toString())) {
                adicionarErroSemantico(ctx.start, "Imposto de " + produtos[i].toString() + " nao declarado");
            }
        }
        //tabela.teste();

        return super.visitImpostos(ctx);
    }

    @Override
    public Void visitTaxas(TaxasContext ctx) {

        for (PorcentagensContext porcentagemCtx : ctx.porcentagens()) {
            for (CFParser.NomeContext porcentagem : porcentagemCtx.nome()) {
                String nome = CFUtils.getNome(porcentagem);

                if (tabela.existe(nome)) {
                    adicionarErroSemantico(porcentagem.start, nome + " ja declarado anteriormente");
                }
            }
        }

        return super.visitTaxas(ctx);
    }

    @Override
    public Void visitNota(NotaContext ctx) {
        for (ItensContext item : ctx.itens()) {
            String nome = CFUtils.getNome(item.nome());

            if (!tabela.existe(nome)) {
                adicionarErroSemantico(item.start, "Item " + nome + " nao foi declarado em PRODUTOS");
            } else if (item.NUM_REAL() != null && item.NUM_REAL().getText() != "" && tabela.verificar(nome) == TipoCF.UNIDADE) {
                adicionarErroSemantico(item.start, "Item " + nome + " foi declarado como UNIDADE mas possui quantidade real");
            }
        }

        return super.visitNota(ctx);
    }

}
