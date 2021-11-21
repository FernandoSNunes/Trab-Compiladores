package br.ufscar.dc.compiladores.t4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

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
        double imposto;
        
        
        private EntradaTabelaDeSimbolos(String nome, TipoCF tipo) {
            this.nome = nome;
            this.tipo = tipo;
            this.imposto = -1;
        }
        
        private void EntradaImposto(double imposto){
            this.imposto = imposto;
        }
        
        private double getImposto(){
                return this.imposto;
        }
    }

    private final Map<String, EntradaTabelaDeSimbolos> tabela;

    public TabelaDeSimbolos() {
        this.tabela = new HashMap<>();
    }
    
    public Object[] teste(){
        return this.tabela.keySet().toArray();
        //System.out.println(this.tabela.keySet());
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
        for (Map.Entry<String, EntradaTabelaDeSimbolos> obj : tabela.entrySet()) {
            System.out.println(obj.getKey() + " - " + obj.getValue().tipo);
        }
    }
    
    public int adicionarImposto(double imposto, String nome){
        EntradaTabelaDeSimbolos aux = tabela.get(nome);
        if (aux != null) {
            aux.EntradaImposto(imposto);
            return 1;
        }
        return -1;

    }
    
    
    
    public boolean impostoRegistrado(String nome){
        EntradaTabelaDeSimbolos aux = tabela.get(nome);
        if (aux != null) {
            if (aux.getImposto() != -1)
                return true;
        }
        return false;
        
    } 
}
