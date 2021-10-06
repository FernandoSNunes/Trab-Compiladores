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

    boolean isProcedimento;

    class EntradaTabelaDeSimbolos {

        String nome;
        TipoLA tipo;
        String tipo_customizado;        //usado para tipos declarados no codigo e constantes
        List<TipoLA> parametros;        //usado para funcoes e procedimentos
        TipoLA retornoFuncao;
        boolean vetor;
        public TabelaDeSimbolos tabela_interna;

        private EntradaTabelaDeSimbolos(String nome, TipoLA tipo, String tipo_customizado) {
            this.nome = nome;
            this.tipo = tipo;
            this.tipo_customizado = tipo_customizado;
            this.parametros = null;
            this.retornoFuncao = TipoLA.INVALIDO;
        }

        //devo remover
        private EntradaTabelaDeSimbolos(String nome, TipoLA tipo, String tipo_customizado, boolean vetor) {
            this.nome = nome;
            this.tipo = tipo;
            this.tipo_customizado = tipo_customizado;
            this.parametros = null;
            this.retornoFuncao = TipoLA.INVALIDO;
            this.vetor = vetor;
        }

        private EntradaTabelaDeSimbolos(String nome, TipoLA tipo, List<TipoLA> tipos, TipoLA retornoFuncao) {
            this.nome = nome;
            this.tipo = tipo;
            this.tipo_customizado = "";
            this.parametros = tipos;
            this.retornoFuncao = retornoFuncao;
        }

        private EntradaTabelaDeSimbolos(String nome, TipoLA tipo, boolean vetor) {
            this.nome = nome;
            this.tipo = tipo;
            this.tipo_customizado = "";
            this.vetor = vetor;
            if (tipo == TipoLA.REGISTRO || tipo == TipoLA.TIPO) {
                this.tabela_interna = new TabelaDeSimbolos();
            }
        }
    }

    private final Map<String, EntradaTabelaDeSimbolos> tabela;
    private Map<String, EntradaTabelaDeSimbolos> tabela_local;

    public TabelaDeSimbolos() {
        this.tabela = new HashMap<>();
        this.tabela_local = null;
    }

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



   

    public void novo_local(boolean isProcedimento) {
        this.tabela_local = new HashMap<>();
        this.isProcedimento = isProcedimento;
    }

    public boolean isProcedimento() {
        return isProcedimento;
    }

    public TipoLA verificar(String nome) {
       //// System.out.println("nome que chegou inicialmente foi " +nome);
        if (nome.contains("[")) {
            nome = nome.substring(0, nome.length() - 3);
        }

        if (nome.contains(".")) {
           //// System.out.println(" nome que chegou com . foi " +nome);
            String[] parts = nome.split("\\.", 2);
           //// System.out.println("Dividido em :" + parts[0] + " e " + parts[1]);

            TipoLA tipo = this.verificar(parts[0]);
            if (tipo == TipoLA.CUSTOMIZADO) {
           //// System.out.println(parts[0] + " e um tipo CUSTOMIZADO");
                
                EntradaTabelaDeSimbolos aux = tabela.get(parts[0]);
                if (aux == null && tabela_local != null) {
                    aux = tabela_local.get(parts[0]);
                }
                
                return this.verificar(aux.tipo_customizado + "."+ parts[1]);
            
                
            } else {
               //// System.out.println(parts[0] + " e um TIPO");
                
                TabelaDeSimbolos tabelaInterna = this.get_tabela_interna(parts[0]);

                ////System.out.println("retornou " + tabelaInterna);
                if (tabelaInterna == null) {
                    return null;
                }
                return tabelaInterna.verificar(parts[1]);
            }

        } else {
           //// System.out.println(" nome que chegou sem . foi " +nome);
             
            //System.out.println(nome);
            EntradaTabelaDeSimbolos aux = tabela.get(nome);
            if (aux == null && tabela_local != null) {
                aux = tabela_local.get(nome);
            }
            if (aux != null) {
                return aux.tipo;
            }
            return null;
        }

    }

    public boolean existe(String nome) {
        if (tabela_local != null) {
            ////System.out.println(nome);
            return (tabela.containsKey(nome) || tabela_local.containsKey(nome));
        } else {
            return tabela.containsKey(nome);
        }
    }
    
        public void adicionar(String nome, TipoLA tipo, String tipo_customizado) {
            if (tabela_local == null)
            tabela.put(nome, new EntradaTabelaDeSimbolos(nome, tipo, ""));
        else
        tabela.put(nome, new EntradaTabelaDeSimbolos(nome, tipo, tipo_customizado));
    }

    public void adicionar(String nome, TipoLA tipo) {
        if (tabela_local == null)
            tabela.put(nome, new EntradaTabelaDeSimbolos(nome, tipo, ""));
        else
            tabela_local.put(nome, new EntradaTabelaDeSimbolos(nome, tipo, ""));
    }

    public void adicionar(String nome, TipoLA tipo, String tipo_customizado, boolean vetor) {
        if (tabela_local == null)
            tabela.put(nome, new EntradaTabelaDeSimbolos(nome, tipo, tipo_customizado, vetor));
        else
        tabela_local.put(nome, new EntradaTabelaDeSimbolos(nome, tipo, tipo_customizado, vetor));
    }

    public void adicionar(String nome, TipoLA tipo, boolean vetor) {
        if (tabela_local == null)
            tabela.put(nome, new EntradaTabelaDeSimbolos(nome, tipo, vetor));
        else
        tabela_local.put(nome, new EntradaTabelaDeSimbolos(nome, tipo, vetor));
    }

    public TabelaDeSimbolos get_tabela_interna(String nome) {
        TipoLA tipo = this.verificar(nome);
        if (tipo == TipoLA.REGISTRO || tipo == TipoLA.TIPO) {
            EntradaTabelaDeSimbolos aux = tabela.get(nome);
            if (aux == null) {
                aux = tabela_local.get(nome);
            }
            if (aux != null) {
                return aux.tabela_interna;
            }
            return null;
        } else {
            return null;
        }

    }

    public void fim_local() {
        tabela_local = null;
    }

}
