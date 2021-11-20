package br.ufscar.dc.compiladores.t4;

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

    public void printTabela() {
        for(Map.Entry<String, EntradaTabelaDeSimbolos> obj : tabela.entrySet()) {
            System.out.println(obj.getKey() + " - " + obj.getValue().tipo);
        }
    }

}
