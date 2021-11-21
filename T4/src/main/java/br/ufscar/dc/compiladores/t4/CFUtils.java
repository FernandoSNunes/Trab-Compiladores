package br.ufscar.dc.compiladores.t4;

import br.ufscar.dc.compiladores.t4.CFParser.PorcentagensContext;
import br.ufscar.dc.compiladores.t4.CFParser.PrecosContext;

public class CFUtils {
    static Double getValorPorcentagem(PorcentagensContext porcentagem) {
        Double valor = -1.0;

        if (porcentagem.NUM_INT() != null && porcentagem.NUM_INT().getText() != "") {
            valor = Double.parseDouble(porcentagem.NUM_INT().getText());
        } else if (porcentagem.NUM_REAL() != null && porcentagem.NUM_REAL().getText() != "") {
            valor = Double.parseDouble(porcentagem.NUM_REAL().getText());
        }

        if (isPorcentagemNegativa(porcentagem)) {
            valor = -valor;
        }

        return valor;
    }

    static Double getValorPreco(PrecosContext preco) {
        Double valor = -1.0;

        if (preco.NUM_INT() != null && preco.NUM_INT().getText() != "") {
            valor = Double.parseDouble(preco.NUM_INT().getText());
        } else if (preco.NUM_REAL() != null && preco.NUM_REAL().getText() != "") {
            valor = Double.parseDouble(preco.NUM_REAL().getText());
        }

        return valor;
    }

    static boolean isPorcentagemNegativa(PorcentagensContext porcentagem) {
        return (porcentagem.NEGATIVO() != null && porcentagem.NEGATIVO().getText().equals("-"));
    }

    static String getNome(CFParser.NomeContext vetorNome) {
        String nome = "";

        for (int i = 0; i < vetorNome.IDENT().size(); i++) {
            if (i > 0)
                nome += " ";
            nome += vetorNome.IDENT(i).getText();
        }

        return nome;
    }
}
