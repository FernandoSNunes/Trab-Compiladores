
package br.ufscar.dc.compiladores.t3;

import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.Token;

public class LASemanticoUtils {
    public static List<String> errosSemanticos = new ArrayList<>();
    
    public static void adicionarErroSemantico(Token t, String mensagem) {
        int linha = t.getLine();
        int coluna = t.getCharPositionInLine();
        errosSemanticos.add(String.format("Erro %d:%d - %s", linha, coluna, mensagem));
    }
    
    
    //exp_aritmetica:     termo (op1 termo)*;
    public static TabelaDeSimbolos.TipoLA verificarTipo(TabelaDeSimbolos tabela, LAParser.Exp_aritmeticaContext ctx) {
        TabelaDeSimbolos.TipoLA ret = null;
        for (LAParser.TermoContext ta : ctx.termo()) {
            TabelaDeSimbolos.TipoLA aux = verificarTipo(tabela, ta);
            if (ret == null) {
                ret = aux;
            } else if (ret != aux && aux != TabelaDeSimbolos.TipoLA.INVALIDO) {
                adicionarErroSemantico(ctx.start, "Expressão " + ctx.getText() + " contém tipos incompatíveis");
                ret = TabelaDeSimbolos.TipoLA.INVALIDO;
            }
        }

        return ret;
    }

    //Fazer cmdAtribuicao--------------------------------
    //Fazer cmdAtribuicao--------------------------------
    //Fazer cmdAtribuicao--------------------------------
    
    //termo:      fator (op2 fator)*;
    public static TabelaDeSimbolos.TipoLA verificarTipo(TabelaDeSimbolos tabela, LAParser.TermoContext ctx) {
        TabelaDeSimbolos.TipoLA ret = null;

        for (LAParser.FatorContext fa : ctx.fator()) {
            TabelaDeSimbolos.TipoLA aux = verificarTipo(tabela, fa);
            if (ret == null) {
                ret = aux;
            } else if (ret != aux && aux != TabelaDeSimbolos.TipoLA.INVALIDO) {
                adicionarErroSemantico(ctx.start, "Termo " + ctx.getText() + " contém tipos incompatíveis");
                ret = TabelaDeSimbolos.TipoLA.INVALIDO;
            }
        }
        return ret;
    }
    
    //fator:      parcela (op3 parcela)*;
    public static TabelaDeSimbolos.TipoLA verificarTipo(TabelaDeSimbolos tabela, LAParser.FatorContext ctx) {
        TabelaDeSimbolos.TipoLA ret = null;

        for (LAParser.ParcelaContext pa : ctx.parcela()) {
            TabelaDeSimbolos.TipoLA aux = verificarTipo(tabela, pa);
            if (ret == null) {
                ret = aux;
            } else if (ret != aux && aux != TabelaDeSimbolos.TipoLA.INVALIDO) {
                adicionarErroSemantico(ctx.start, "Parcela " + ctx.getText() + " contém tipos incompatíveis");
                ret = TabelaDeSimbolos.TipoLA.INVALIDO;
            }
        }
        return ret;
    }
    
    //parcela:                (op_unario)? parcela_unario | parcela_nao_unario;
    public static TabelaDeSimbolos.TipoLA verificarTipo(TabelaDeSimbolos tabela, LAParser.ParcelaContext ctx) {
        
        if (ctx.parcela_unario()!= null) {
            TabelaDeSimbolos.TipoLA pu  = verificarTipo(tabela, ctx.parcela_unario());
            return pu;
        }
        else /*(ctx.parcela_nao_unario()!= null) */{
            TabelaDeSimbolos.TipoLA nu = verificarTipo(tabela, ctx.parcela_nao_unario());
            return nu;
        }
        
    }
    
    /*
    parcela_unario:         ('^')? identificador
                        | IDENT '(' expressao (',' expressao)* ')'
                        | NUM_INT
                        | NUM_REAL
                        | '(' expressao ')';
    */
    public static TabelaDeSimbolos.TipoLA verificarTipo(TabelaDeSimbolos tabela, LAParser.Parcela_unarioContext ctx) {
        if (ctx.NUM_INT() != null) {
            return TabelaDeSimbolos.TipoLA.INTEIRO;
        }
        if (ctx.NUM_REAL() != null) {
            return TabelaDeSimbolos.TipoLA.REAL;
        }
        //funcao???
                //vou deixar comentado, acredito que nao e usado
        /*
        if (ctx.IDENT() != null) {
            String nomeVar = ctx.VARIAVEL().getText();
            if (!tabela.existe(nomeVar)) {
                adicionarErroSemantico(ctx.VARIAVEL().getSymbol(), "Variável " + nomeVar + " não foi declarada antes do uso");
                return TabelaDeSimbolos.TipoLA.INVALIDO;
            }
            return verificarTipo(tabela, nomeVar);
        }
        */
        //ponteiro
        if (ctx.identificador() != null) {
            return TabelaDeSimbolos.TipoLA.PONTEIRO;
        }
        // se não for nenhum dos tipos acima, só pode ser uma expressão
        // entre parêntesis
        //---------------------probelma aqui, a expressao nao esta aceitando fora de vetor------------------------
        return verificarTipo(tabela, ctx.expressao(0));
    }
    
    /*
    parcela_nao_unario:     '&' identificador
                        | CADEIA;
    */
    public static TabelaDeSimbolos.TipoLA verificarTipo(TabelaDeSimbolos tabela, LAParser.Parcela_nao_unarioContext ctx) {
        if (ctx.identificador()!= null) {
            return TabelaDeSimbolos.TipoLA.PONTEIRO;
        }
        
        //String
        //if (ctx.CADEIA() != null) {   
            return TabelaDeSimbolos.TipoLA.LITERAL;
        //}
        
    }
    
    
    /*
        expressao:      termo_logico (op_logico_1 termo_logico)*;
    */
    
    public static TabelaDeSimbolos.TipoLA verificarTipo(TabelaDeSimbolos tabela, LAParser.ExpressaoContext ctx) {
        TabelaDeSimbolos.TipoLA ret = null;

        for (LAParser.Termo_logicoContext pa : ctx.termo_logico()) {
            TabelaDeSimbolos.TipoLA aux = verificarTipo(tabela, pa);
            if (ret == null) {
                ret = aux;
            } else if (ret != aux && aux != TabelaDeSimbolos.TipoLA.INVALIDO) {
                adicionarErroSemantico(ctx.start, "Expressao logica invalida para \"ou\":" + ctx.getText());
                ret = TabelaDeSimbolos.TipoLA.INVALIDO;
            }
        }
        return ret;
    }
    
    //termo_logico:       fator_logico (op_logico_2 fator_logico)*;
    public static TabelaDeSimbolos.TipoLA verificarTipo(TabelaDeSimbolos tabela, LAParser.Termo_logicoContext ctx) {
        TabelaDeSimbolos.TipoLA ret = null;

        for (LAParser.Fator_logicoContext pa : ctx.fator_logico()) {
            TabelaDeSimbolos.TipoLA aux = verificarTipo(tabela, pa);
            if (ret == null) {
                ret = aux;
            } else if (ret != aux && aux != TabelaDeSimbolos.TipoLA.INVALIDO) {
                adicionarErroSemantico(ctx.start, "Expressao logica invalida para \"e\":" + ctx.getText());
                ret = TabelaDeSimbolos.TipoLA.INVALIDO;
            }
        }
        return ret;
    }
    
    //fator_logico:       ('nao')? parcela_logica;
    public static TabelaDeSimbolos.TipoLA verificarTipo(TabelaDeSimbolos tabela, LAParser.Fator_logicoContext ctx) {
        
            TabelaDeSimbolos.TipoLA nu = verificarTipo(tabela, ctx.parcela_logica());
            return nu;
    }
    
    /*
    parcela_logica:     ('verdadeiro' | 'falso')
                    | exp_relacional;
    */
    public static TabelaDeSimbolos.TipoLA verificarTipo(TabelaDeSimbolos tabela, LAParser.Parcela_logicaContext ctx) {
        TabelaDeSimbolos.TipoLA ret = null;

          if (ctx.exp_relacional()!= null) {
            TabelaDeSimbolos.TipoLA pu  = verificarTipo(tabela, ctx.exp_relacional());
            return pu;
        }
            
            return TabelaDeSimbolos.TipoLA.LOGICO;
        
    }
    
    //exp_relacional:     exp_aritmetica (op_relacional exp_aritmetica)?;
    public static TabelaDeSimbolos.TipoLA verificarTipo(TabelaDeSimbolos tabela, LAParser.Exp_relacionalContext ctx) {
        TabelaDeSimbolos.TipoLA ret = null;

        for (LAParser.Exp_aritmeticaContext fa : ctx.exp_aritmetica()) {
            TabelaDeSimbolos.TipoLA aux = verificarTipo(tabela, fa);
            if (ret == null) {
                ret = aux;
            } else if (ret != aux && aux != TabelaDeSimbolos.TipoLA.INVALIDO) {
                adicionarErroSemantico(ctx.start, "Exp aritimetica " + ctx.getText() + " contém tipos incompatíveis");
                ret = TabelaDeSimbolos.TipoLA.INVALIDO;
            }
        }
        return ret;
    }
    
    
    
    
    
    public static TabelaDeSimbolos.TipoLA verificarTipo(TabelaDeSimbolos tabela, String nomeVar) {
        return tabela.verificar(nomeVar);
    }
}