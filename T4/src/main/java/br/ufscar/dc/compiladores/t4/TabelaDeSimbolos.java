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
import java.util.HashMap;
import java.util.Map;

public class TabelaDeSimbolos {

    public enum TipoCF {
        QUILO,
        PORCENTAGEM,
        UNIDADE
    }

    boolean isProcedimento;

    class EntradaTabelaDeSimbolos {

        String nome;
        TipoCF tipo;

        private EntradaTabelaDeSimbolos(String nome, TipoCF tipo) {
            this.nome = nome;
            this.tipo = tipo;

        }
    }

    private final Map<String, EntradaTabelaDeSimbolos> tabela;

    public TabelaDeSimbolos() {
        this.tabela = new HashMap<>();
    }

    public TipoCF verificar(String nome) {

        //System.out.println(nome);
        EntradaTabelaDeSimbolos aux = tabela.get(nome);
        if (aux != null) {
            return aux.tipo;
        }
        return null;

    }

    public boolean existe(String nome) {
        return tabela.containsKey(nome);

    }

    public void adicionar(String nome, TipoCF tipo) {
        tabela.put(nome, new EntradaTabelaDeSimbolos(nome, tipo));
    }

}
