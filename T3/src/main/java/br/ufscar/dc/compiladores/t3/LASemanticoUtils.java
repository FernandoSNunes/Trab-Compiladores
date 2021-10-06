package br.ufscar.dc.compiladores.t3;

import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.Token;

public class LASemanticoUtils {

    public static List<String> errosSemanticos = new ArrayList<>();

    public static void adicionarErroSemantico(Token t, String mensagem) {
        int linha = t.getLine();
        errosSemanticos.add(String.format("Linha %d: %s", linha, mensagem));
    }

    // exp_aritmetica: termo (op1 termo)*;
    public static TabelaDeSimbolos.TipoLA verificarTipo(TabelaDeSimbolos tabela, LAParser.Exp_aritmeticaContext ctx) {
        TabelaDeSimbolos.TipoLA ret = null;
        for (LAParser.TermoContext ta : ctx.termo()) {
            TabelaDeSimbolos.TipoLA aux = verificarTipo(tabela, ta);
            if (ret == null) {
                if (aux == null) {
                    return aux;
                } else {
                    ret = aux;
                }
            } else if (aux == TabelaDeSimbolos.TipoLA.INTEIRO || aux == TabelaDeSimbolos.TipoLA.REAL) {
                //entra aqui caso tenha um segundo elemento, ou seja: ret + aux ou ret - aux, se aux nao for real ou inteiro exibe erro
                if (ret == TabelaDeSimbolos.TipoLA.INTEIRO || ret == TabelaDeSimbolos.TipoLA.REAL) {
                    //verifica se ret é valido
                    if (aux == TabelaDeSimbolos.TipoLA.REAL) {
                        ret = aux;
                    }
                } else {
                    //adicionarErroSemantico(ta.start, "Expressão " + ta.getText() + " contém tipos incompatíveis id : 1");
                    return TabelaDeSimbolos.TipoLA.INVALIDO;

                }

            } else {
                
                if (aux == TabelaDeSimbolos.TipoLA.LITERAL && ret == aux){
                    ret = aux;
                    
                }else {
                //adicionarErroSemantico(ctx.start, "Expressão " + ctx.getText() + " contém tipos incompatíveis  id : 2");
                return TabelaDeSimbolos.TipoLA.INVALIDO;
                }
            }
        }

        return ret;
    }

    // Fazer cmdAtribuicao--------------------------------
    // Fazer cmdAtribuicao--------------------------------
    // Fazer cmdAtribuicao--------------------------------
    // termo: fator (op2 fator)*;
    public static TabelaDeSimbolos.TipoLA verificarTipo(TabelaDeSimbolos tabela, LAParser.TermoContext ctx) {
        TabelaDeSimbolos.TipoLA ret = null;

        for (LAParser.FatorContext fa : ctx.fator()) {
            TabelaDeSimbolos.TipoLA aux = verificarTipo(tabela, fa);
            if (ret == null) {
                if (aux == null) {
                    return aux;
                } else {
                    ret = aux;
                }
            } else if (aux == TabelaDeSimbolos.TipoLA.INTEIRO || aux == TabelaDeSimbolos.TipoLA.REAL) {
                //System.out.println("linha 65 ");
                //entra aqui caso tenha um segundo elemento, ou seja: ret * aux ou ret / aux, se aux nao for real ou inteiro exibe erro
                if (ret == TabelaDeSimbolos.TipoLA.INTEIRO || ret == TabelaDeSimbolos.TipoLA.REAL) {
                    //verifica se ret é valido
                    if (aux == TabelaDeSimbolos.TipoLA.REAL) {
                        ret = aux;
                    }
                } else {
                    adicionarErroSemantico(fa.start, "Expressão " + fa.getText() + " contém tipos incompatíveis id : 3");
                    return TabelaDeSimbolos.TipoLA.INVALIDO;

                }

            } else {
                adicionarErroSemantico(ctx.start, "Expressão " + ctx.getText() + " contém tipos incompatíveis id : 4");
                return TabelaDeSimbolos.TipoLA.INVALIDO;
            }
        }
        return ret;
    }

    // fator: parcela (op3 parcela)*;
    public static TabelaDeSimbolos.TipoLA verificarTipo(TabelaDeSimbolos tabela, LAParser.FatorContext ctx) {
        TabelaDeSimbolos.TipoLA ret = null;

        for (LAParser.ParcelaContext pa : ctx.parcela()) {
            TabelaDeSimbolos.TipoLA aux = verificarTipo(tabela, pa);
            if (ret == null) {
                if (aux == null) {
                    return aux;
                } else {
                    ret = aux;
                }
            } else if (aux == TabelaDeSimbolos.TipoLA.INTEIRO) {
                //entra aqui caso tenha um segundo elemento, ou seja: ret % aux, se aux nao for inteiro exibe erro
                if (ret == TabelaDeSimbolos.TipoLA.INTEIRO) {
                    //verifica se ret é valido
                    ret = aux;

                } else {
                    adicionarErroSemantico(pa.start, "Expressão " + pa.getText() + " contém tipos incompatíveis id : 5");
                    return TabelaDeSimbolos.TipoLA.INVALIDO;

                }

            } else {
                adicionarErroSemantico(ctx.start, "Expressão " + ctx.getText() + " contém tipos incompatíveis id : 6");
                return TabelaDeSimbolos.TipoLA.INVALIDO;
            }
        }
        return ret;
    }

    // parcela: (op_unario)? parcela_unario | parcela_nao_unario;
    public static TabelaDeSimbolos.TipoLA verificarTipo(TabelaDeSimbolos tabela, LAParser.ParcelaContext ctx) {

        if (ctx.parcela_unario() != null) {
            //System.out.println("122 " + ctx.parcela_unario().getText());
            TabelaDeSimbolos.TipoLA pu = verificarTipo(tabela, ctx.parcela_unario());
            return pu;
        } else /* (ctx.parcela_nao_unario()!= null) */ {
            //System.out.println("126");
            TabelaDeSimbolos.TipoLA nu = verificarTipo(tabela, ctx.parcela_nao_unario());
            return nu;
        }

    }

    /*
     * parcela_unario: ('^')? identificador | IDENT '(' expressao (',' expressao)*
     * ')' | NUM_INT | NUM_REAL | '(' expressao ')';
     */
    public static TabelaDeSimbolos.TipoLA verificarTipo(TabelaDeSimbolos tabela, LAParser.Parcela_unarioContext ctx) {
        if (ctx.NUM_INT() != null) {
            return TabelaDeSimbolos.TipoLA.INTEIRO;
        }
        if (ctx.NUM_REAL() != null) {
            return TabelaDeSimbolos.TipoLA.REAL;
        }
        // funcao???
        // vou deixar comentado, acredito que nao e usado

        if (ctx.IDENT() != null) {
            String nomeFunc = ctx.IDENT().getText();
            if (tabela.verificar(nomeFunc) != TabelaDeSimbolos.TipoLA.FUNCAO) {
                //nao e funcao nem procedimento, erro
                LASemanticoUtils.adicionarErroSemantico(ctx.start,
                        "identificador " + nomeFunc + " nao declarado");
                return TabelaDeSimbolos.TipoLA.INVALIDO;
            }
            return tabela.getRetornoFuncao(nomeFunc);

        }

        // ponteiro
        if (ctx.identificador() != null) {
            //System.out.println("161 " + ctx.identificador().getText());
            if (ctx.INICIO_ENDERECO() != null) {
                //System.out.println("163 " + ctx.identificador().getText());
                return TabelaDeSimbolos.TipoLA.PONTEIRO;
            } else {
                String nomeVar = ctx.identificador().getText();

                System.out.println(ctx.getText());

                TabelaDeSimbolos.TipoLA tipoVar = tabela.verificar(nomeVar);
                //System.out.println("166 " + ctx.identificador().getText() + "tipoVar = " + tipoVar);
                if (tipoVar != TabelaDeSimbolos.TipoLA.FUNCAO) {
                    if (tipoVar == null) {
                        LASemanticoUtils.adicionarErroSemantico(ctx.start,
                                "identificador " + nomeVar + " nao declarado");
                        return TabelaDeSimbolos.TipoLA.INVALIDO;
                    }
                    //System.out.println(" parcela_unario identificador = " + nomeVar + " e tipo = " + tipoVar);
                    return tipoVar;

                }
                return tabela.getRetornoFuncao(nomeVar);
            }
        }
        // se não for nenhum dos tipos acima, só pode ser uma expressão
        // entre parêntesis
        // ---------------------probelma aqui, a expressao nao esta aceitando fora de
        // vetor------------------------
        return verificarTipo(tabela, ctx.expressao(0));
    }

    /*
     * parcela_nao_unario: '&' identificador | CADEIA;
     */
    public static TabelaDeSimbolos.TipoLA verificarTipo(TabelaDeSimbolos tabela,
            LAParser.Parcela_nao_unarioContext ctx) {
        if (ctx.identificador() != null) {
            return TabelaDeSimbolos.TipoLA.ENDERECO;
        }

        // String
        // if (ctx.CADEIA() != null) {
        return TabelaDeSimbolos.TipoLA.LITERAL;
        // }

    }

    /*
     * expressao: termo_logico (op_logico_1 termo_logico)*;
     */
    public static TabelaDeSimbolos.TipoLA verificarTipo(TabelaDeSimbolos tabela, LAParser.ExpressaoContext ctx) {
        TabelaDeSimbolos.TipoLA ret = null;

        for (LAParser.Termo_logicoContext pa : ctx.termo_logico()) {
            TabelaDeSimbolos.TipoLA aux = verificarTipo(tabela, pa);
            if (ret == null) {
                //System.out.println("208");
                if (aux == null) {
                    //System.out.println("210");
                    return aux;
                } else {
                    //System.out.println("213");
                    ret = aux;
                }
            } else if (aux == TabelaDeSimbolos.TipoLA.LOGICO) {
                //System.out.println("215");
                //entra aqui caso tenha um segundo elemento, ou seja: ret % aux, se aux nao for inteiro exibe erro
                if (ret == TabelaDeSimbolos.TipoLA.LOGICO) {
                    //System.out.println("220");
                    //verifica se ret é valido
                    ret = aux;

                } else {
                    //System.out.println("225");
                    adicionarErroSemantico(pa.start, "Expressão " + pa.getText() + " contém tipos incompatíveis id : 7");
                    return TabelaDeSimbolos.TipoLA.INVALIDO;

                }

            } else {
                adicionarErroSemantico(ctx.start, "Expressão " + ctx.getText() + " contém tipos incompatíveis id : 8");
                return TabelaDeSimbolos.TipoLA.INVALIDO;
            }
        }
        return ret;
    }

    // termo_logico: fator_logico (op_logico_2 fator_logico)*;
    public static TabelaDeSimbolos.TipoLA verificarTipo(TabelaDeSimbolos tabela, LAParser.Termo_logicoContext ctx) {
        TabelaDeSimbolos.TipoLA ret = null;

        for (LAParser.Fator_logicoContext pa : ctx.fator_logico()) {
            TabelaDeSimbolos.TipoLA aux = verificarTipo(tabela, pa);
            if (ret == null) {
                //System.out.println("244");
                if (aux == null) {
                    //System.out.println("248");
                    return aux;
                } else {
                    //System.out.println("252");
                    ret = aux;
                }
            } else if (aux == TabelaDeSimbolos.TipoLA.LOGICO) {
                //entra aqui caso tenha um segundo elemento, ou seja: ret % aux, se aux nao for inteiro exibe erro
                if (ret == TabelaDeSimbolos.TipoLA.LOGICO) {
                    //verifica se ret é valido
                    ret = aux;

                } else {
                    // adicionarErroSemantico(pa.start, "Expressão " + pa.getText() + " contém tipos incompatíveis id : 9");
                    return TabelaDeSimbolos.TipoLA.INVALIDO;

                }

            } else {
                adicionarErroSemantico(ctx.start, "Expressão " + ctx.getText() + " contém tipos incompatíveis id : 10");
                return TabelaDeSimbolos.TipoLA.INVALIDO;
            }
        }
        return ret;
    }

    // fator_logico: ('nao')? parcela_logica;
    public static TabelaDeSimbolos.TipoLA verificarTipo(TabelaDeSimbolos tabela, LAParser.Fator_logicoContext ctx) {

        TabelaDeSimbolos.TipoLA nu = verificarTipo(tabela, ctx.parcela_logica());
        return nu;
    }

    /*
     * parcela_logica: ('verdadeiro' | 'falso') | exp_relacional;
     */
    public static TabelaDeSimbolos.TipoLA verificarTipo(TabelaDeSimbolos tabela, LAParser.Parcela_logicaContext ctx) {

        if (ctx.exp_relacional() != null) {
            return verificarTipo(tabela, ctx.exp_relacional());
        }

        return TabelaDeSimbolos.TipoLA.LOGICO;

    }

    // exp_relacional: exp_aritmetica (op_relacional exp_aritmetica)?;
    public static TabelaDeSimbolos.TipoLA verificarTipo(TabelaDeSimbolos tabela, LAParser.Exp_relacionalContext ctx) {
        TabelaDeSimbolos.TipoLA tipo1 = verificarTipo(tabela, ctx.exp_aritmetica(0));

        // System.out.println("passou aqui");

        if (ctx.exp_aritmetica().size() == 1) {      //sem segunda operacao
            return tipo1;
        } else {    //
            TabelaDeSimbolos.TipoLA tipo2 = verificarTipo(tabela, ctx.exp_aritmetica(1));
            if (tipo2 == tipo1 && "=".equals(ctx.op_relacional().getText())) {  //operacao de igualdade
                return TabelaDeSimbolos.TipoLA.LOGICO;
            } else {
                if (tipo2 != tipo1) {
                    // adicionarErroSemantico(ctx.start, "Expressão " + ctx.getText() + " contém tipos incompatíveis id : 11");
                    return TabelaDeSimbolos.TipoLA.INVALIDO;
                }
                if ((tipo1 == TabelaDeSimbolos.TipoLA.INTEIRO || tipo1 == TabelaDeSimbolos.TipoLA.REAL)
                        && (tipo2 == TabelaDeSimbolos.TipoLA.INTEIRO || tipo2 == TabelaDeSimbolos.TipoLA.REAL)) {
                    return TabelaDeSimbolos.TipoLA.LOGICO;
                } else {
                    // adicionarErroSemantico(ctx.start, "Expressão " + ctx.getText() + " contém tipos incompatíveis id : 12");
                    return TabelaDeSimbolos.TipoLA.INVALIDO;
                }
            }
        }
    }

    // public static String getNomeFunc(TabelaDeSimbolos tabela,
    // LAParser.ExpressaoContext ctx) {
    // TabelaDeSimbolos.TipoLA ret = null;
    //
    // for (LAParser.Termo_logicoContext fa : ctx.termo_logico()) {
    // return
    // }
    // return ret;
    // }
    public static TabelaDeSimbolos.TipoLA verificarTipo(TabelaDeSimbolos tabela, String nomeVar) {
        return tabela.verificar(nomeVar);
    }
}
