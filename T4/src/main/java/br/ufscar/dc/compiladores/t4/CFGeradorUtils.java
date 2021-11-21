package br.ufscar.dc.compiladores.t4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import br.ufscar.dc.compiladores.t4.CFParser.PorcentagensContext;

public class CFGeradorUtils {
    private Map<String, Double> produtos;
    private Map<String, Double> impostos;
    private Map<String, Double> taxas;

    CFGeradorUtils() {
        this.produtos = new HashMap<String, Double>();
        this.impostos = new HashMap<String, Double>();
        this.taxas = new HashMap<String, Double>();
    }

    void adicionarProduto(String nome, Double valor) {
        produtos.put(nome, valor);
    }

    Double getValorProduto(String nome) {
        return produtos.get(nome);
    }

    void adicionarImposto(String nome, PorcentagensContext porcentagem) {
        impostos.put(nome, CFUtils.getValorPorcentagem(porcentagem) / 100);
    }

    Double getValorImposto(String nome) {
        return impostos.get(nome);
    }

    void adicionarTaxa(String nome, PorcentagensContext porcentagem) {
        taxas.put(nome, CFUtils.getValorPorcentagem(porcentagem) / 100);
    }

    Map<String, Double> getTaxas() {
        return taxas;
    }

    List<Double> getValorTaxas() {
        List<Double> listaTaxas = new ArrayList<Double>();

        taxas.forEach((nome, valor) -> listaTaxas.add(valor));

        return listaTaxas;
    }

    void printProdutos() {
        System.out.println("PRODUTOS\n");
        produtos.forEach((k, v) -> System.out.println(k + ": " + v));
        System.out.println("\n\n");
    }

    void printImpostos() {
        System.out.println("IMPOSTOS\n");
        impostos.forEach((k, v) -> System.out.println(k + ": " + v));
        System.out.println("\n\n");
    }

    void printTaxas() {
        System.out.println("TAXAS\n");
        taxas.forEach((k, v) -> System.out.println(k + ": " + v));
    }
}
