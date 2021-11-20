package br.ufscar.dc.compiladores.t4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ufscar.dc.compiladores.t4.CFParser.PorcentagensContext;

public class CFSemanticoUtils {
    static Map<String, Double> impostos = new HashMap<String, Double>();
    static Map<String, Double> taxas = new HashMap<String, Double>();

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

    static String getNome(CFParser.NomeContext vetorNome) {
        String nome = "";

        for (int i = 0; i < vetorNome.IDENT().size(); i++) {
            if (i > 0)
                nome += " ";
            nome += vetorNome.IDENT(i).getText();
        }

        return nome;
    }

    static boolean isPorcentagemNegativa(PorcentagensContext porcentagem) {
        return (porcentagem.NEGATIVO() != null && porcentagem.NEGATIVO().getText().equals("-"));
    }

    static void adicionarImposto(String nome, PorcentagensContext porcentagem) {
        impostos.put(nome, getValorPorcentagem(porcentagem) / 100);
    }

    static Map<String, Double> getImpostos() {
        return impostos;
    }

    static Double getValorImposto(String nome) {
        return impostos.get(nome);
    }

    static void adicionarTaxa(String nome, PorcentagensContext porcentagem) {
        taxas.put(nome, getValorPorcentagem(porcentagem) / 100);
    }

    static Map<String, Double> getTaxas() {
        return taxas;
    }

    static List<Double> getValorTaxas() {
        List<Double> listaTaxas = new ArrayList<Double>();

        taxas.forEach((nome, valor) -> listaTaxas.add(valor));

        return listaTaxas;
    }

    static void printImpostos() {
        System.out.println("IMPOSTOS\n");
        impostos.forEach((k, v) -> System.out.println(k + ": " + v));
        System.out.println("\n\n");
    }

    static void printTaxas() {
        System.out.println("TAXAS\n");
        taxas.forEach((k, v) -> System.out.println(k + ": " + v));
    }
}
