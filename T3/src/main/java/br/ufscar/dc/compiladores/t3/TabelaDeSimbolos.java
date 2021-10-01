package br.ufscar.dc.compiladores.t3;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TabelaDeSimbolos {

    public enum TipoLA {
        INTEIRO,
        REAL,
        PONTEIRO,
        ENDERECO,
        LOGICO,
        REGISTRO,
        INVALIDO,
        LITERAL,
        FUNCAO,
        PROCEDIMENTO,
        TIPO,
        CUSTOMIZADO
    }

    class EntradaTabelaDeSimbolos {

        String nome;
        TipoLA tipo;
        String tipo_customizado;
        List<TipoLA> parametros;        //usado para funcoes e procedimentos

        private EntradaTabelaDeSimbolos(String nome, TipoLA tipo, String tipo_customizado) {
            this.nome = nome;
            this.tipo = tipo;
            this.tipo_customizado = tipo_customizado;
            this.parametros = null;
        }

        private EntradaTabelaDeSimbolos(String nome, TipoLA tipo, List<TipoLA> tipos) {
            this.nome = nome;
            this.tipo = tipo;
            this.tipo_customizado = "";
            this.parametros = tipos;
        }
    }

    private final Map<String, EntradaTabelaDeSimbolos> tabela;
    private Map<String, EntradaTabelaDeSimbolos> tabela_local;

    public TabelaDeSimbolos() {
        this.tabela = new HashMap<>();
        this.tabela_local = null;
    }

    public void adicionar(String nome, TipoLA tipo) {
        tabela.put(nome, new EntradaTabelaDeSimbolos(nome, tipo, ""));
    }

    /*
    public boolean existe(String nome) {
        return (tabela.containsKey(nome));
    }
     */
 /*public TipoLA verificar(String nome) {
        return tabela.get(nome).tipo;
    }*/
    //teste------------------------------------------------------------------------
    public void adicionar_funcproc(String nome, TipoLA tipo, List<TipoLA> tipos) {
        tabela.put(nome, new EntradaTabelaDeSimbolos(nome, tipo, tipos));
    }

    //Cuidado, use corretamente ou vai crashar
    public List<TipoLA> get_parametros_funcprop(String nome) {
        return tabela.get(nome).parametros;
    }

    public void adicionar(String nome, TipoLA tipo, String tipo_customizado) {
        tabela.put(nome, new EntradaTabelaDeSimbolos(nome, tipo, tipo_customizado));
    }

    public void novo_local() {
        this.tabela_local = new HashMap<>();
    }

    public TipoLA verificar(String nome) {
        EntradaTabelaDeSimbolos aux = tabela.get(nome);
        if (aux == null) {
            aux = tabela_local.get(nome);
        }
        if (aux != null) {
            return aux.tipo;
        }
        return null;

    }

    public boolean existe(String nome) {
        return (tabela.containsKey(nome) || tabela_local.containsKey(nome));
    }

    public void adicionar_local(String nome, TipoLA tipo) {
        tabela_local.put(nome, new EntradaTabelaDeSimbolos(nome, tipo, ""));
    }

    public void adicionar_local(String nome, TipoLA tipo, String tipo_customizado) {
        tabela_local.put(nome, new EntradaTabelaDeSimbolos(nome, tipo, tipo_customizado));
    }

    public void fim_local() {
        tabela_local = null;
    }

}