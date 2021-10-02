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
        CONSTANTE,
        CUSTOMIZADO
    }

    class EntradaTabelaDeSimbolos {

        String nome;
        TipoLA tipo;
        String tipo_customizado;        //usado para tipos declarados no codigo e constantes
        List<TipoLA> parametros;        //usado para funcoes e procedimentos
        TipoLA retornoFuncao;
        

        private EntradaTabelaDeSimbolos(String nome, TipoLA tipo, String tipo_customizado) {    
            this.nome = nome;
            this.tipo = tipo;
            this.tipo_customizado = tipo_customizado;
            this.parametros = null;
            this.retornoFuncao = TipoLA.INVALIDO;
        }

        private EntradaTabelaDeSimbolos(String nome, TipoLA tipo, List<TipoLA> tipos, TipoLA retornoFuncao) {
            this.nome = nome;
            this.tipo = tipo;
            this.tipo_customizado = "";
            this.parametros = tipos;
            this.retornoFuncao = retornoFuncao;
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
    public void adicionar_funcproc(String nome, TipoLA tipo, List<TipoLA> tipos, TipoLA retornoFuncao) {
        tabela.put(nome, new EntradaTabelaDeSimbolos(nome, tipo, tipos, retornoFuncao));
    }

    public List<TipoLA> get_parametros_funcprop(String nome) {
        EntradaTabelaDeSimbolos ret = tabela.get(nome);
        if (ret != null) {
            return ret.parametros;
        }
        return null;
    }

    public TipoLA getRetornoFuncao(String nome) {
        EntradaTabelaDeSimbolos ret = tabela.get(nome);
        if (ret != null) {
            return ret.retornoFuncao;
        }
        return null;
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
        if (tabela_local != null){
            //System.out.println(nome);
            return (tabela.containsKey(nome) || tabela_local.containsKey(nome));
        }
        else return tabela.containsKey(nome);
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
