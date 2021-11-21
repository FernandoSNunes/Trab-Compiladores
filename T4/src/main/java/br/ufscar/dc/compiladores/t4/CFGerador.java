package br.ufscar.dc.compiladores.t4;

import br.ufscar.dc.compiladores.t4.CFParser.ItensContext;

import java.util.Map;

import br.ufscar.dc.compiladores.t4.CFParser.ImpostosContext;
import br.ufscar.dc.compiladores.t4.CFParser.LojaContext;
import br.ufscar.dc.compiladores.t4.CFParser.NotaContext;
import br.ufscar.dc.compiladores.t4.CFParser.PorcentagensContext;
import br.ufscar.dc.compiladores.t4.CFParser.PrecosContext;
import br.ufscar.dc.compiladores.t4.CFParser.ProdutosContext;
import br.ufscar.dc.compiladores.t4.CFParser.ProgramaContext;
import br.ufscar.dc.compiladores.t4.CFParser.TaxasContext;

public class CFGerador extends CFBaseVisitor<Void> {
    StringBuilder saida;
    TabelaDeSimbolos tabela;
    CFGeradorUtils utils;

    @Override
    public Void visitPrograma(ProgramaContext ctx) {
        saida = new StringBuilder();
        tabela = new TabelaDeSimbolos();
        utils = new CFGeradorUtils();

        saida.append("---------- CUPOM FISCAL ----------\n\n");
        return super.visitPrograma(ctx);
    }

    @Override
    public Void visitProdutos(ProdutosContext ctx) {

        for (PrecosContext preco : ctx.precos()) {
            for (CFParser.NomeContext nome : preco.nome()) {

                String nomeProduto = CFUtils.getNome(nome);
                utils.adicionarProduto(nomeProduto, CFUtils.getValorPreco(preco));
            }
        }

        return super.visitProdutos(ctx);
    }

    @Override
    public Void visitImpostos(ImpostosContext ctx) {

        for (PorcentagensContext porcentagemCtx : ctx.porcentagens()) {
            for (CFParser.NomeContext porcentagem : porcentagemCtx.nome()) {

                String nome = CFUtils.getNome(porcentagem);
                utils.adicionarImposto(nome, porcentagemCtx);
            }
        }
        return super.visitImpostos(ctx);
    }

    @Override
    public Void visitTaxas(TaxasContext ctx) {

        for (PorcentagensContext porcentagemCtx : ctx.porcentagens()) {
            for (CFParser.NomeContext porcentagem : porcentagemCtx.nome()) {

                String nome = CFUtils.getNome(porcentagem);
                utils.adicionarTaxa(nome, porcentagemCtx);
            }
        }
        return super.visitTaxas(ctx);
    }

    @Override
    public Void visitLoja(LojaContext ctx) {
        String nomeLoja = CFUtils.getNome(ctx.nome());
        String cnpjLoja = ctx.CNPJ().getText();

        saida.append(nomeLoja + "\n");
        saida.append("CNPJ: " + cnpjLoja + "\n");
        saida.append("\n----------------------------------\n\n");

        return super.visitLoja(ctx);
    }

    @Override
    public Void visitNota(NotaContext ctx) {
        int codigo = 1;
        Double impostoAcumulado = 0.0;
        Double totalNota = 0.0;

        saida.append("ITEM | DESC | QTD | VL UNI | TOTAL\n");

        for (ItensContext item : ctx.itens()) {
            String codigoItem = String.format("%03d", codigo);
            String descItem = CFUtils.getNome(item.nome());
            String qtdItem = (item.NUM_INT() != null && item.NUM_INT().getText() != "")
                ? item.NUM_INT().getText()
                : item.NUM_REAL().getText();
            Double valorUnitario = utils.getValorProduto(descItem);
            Double total = valorUnitario * Double.parseDouble(qtdItem);

            // atualiza valor do imposto acumulado
            impostoAcumulado += total * utils.getValorImposto(descItem);

            // aplica imposto no valor unitario e total
            valorUnitario *= (1 + utils.getValorImposto(descItem));
            total *= (1 + utils.getValorImposto(descItem));

            // atualiza valor total da nota
            totalNota += total;
            

            saida.append(
                codigoItem + " | " +
                descItem + " | " +
                qtdItem + " | " +
                String.format("%.02f", valorUnitario) + " | " +
                "R$ " + String.format("%.02f",total)
                + "\n"
            );


            codigo++;
        }

        saida.append("\n----------------------------------\n\n");

        // verifica se ha taxas adicionais ou descontos
        if (utils.getTaxas().size() > 0) {
            saida.append("ADICIONAIS\n\n");

            Double totalTaxas = 0.0;

            for (Map.Entry<String, Double> taxa : utils.getTaxas().entrySet()) {
                totalTaxas += taxa.getValue();

                saida.append(taxa.getKey() + ": " + taxa.getValue()*100 + "%\n");
            }

            totalNota *= 1+totalTaxas;

            saida.append("\n----------------------------------\n\n");
        }

        saida.append("TOTAL: R$ " + String.format("%.02f", totalNota) + "\n");

        saida.append("\n----------------------------------\n\n");
        saida.append("IMPOSTOS TOTAIS: R$ " + String.format("%.02f", impostoAcumulado) + "\n");


        saida.append("\n----------------------------------\n\n");
        if (ctx.infos().CPF() != null && ctx.infos().CPF().getText() != "") {
            saida.append("CONSUMIDOR: " + ctx.infos().CPF().getText() + "\n");
        } else {
            saida.append("CONSUMIDOR NAO IDENTIFICADO\n");
        }


        saida.append("\n----------------------------------\n\n");
        saida.append("VOLTE SEMPRE!!");

        return super.visitNota(ctx);
    }
}
