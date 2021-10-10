package br.ufscar.dc.compiladores.t3;

import br.ufscar.dc.compiladores.t3.TabelaDeSimbolos.TipoLA;
import java.util.ArrayList;
import java.util.List;
import org.antlr.v4.runtime.Token;

public class LAGeradorCUtils {

    // exp_aritmetica: termo (op1 termo)*;
    public static String imprimirConteudoFormatado(TabelaDeSimbolos tabela, List <String> variaveis, LAParser.Exp_aritmeticaContext ctx) {
        String retorno = "";
        String ret = null;
        for (int i = 0; i < ctx.termo().size(); i++) {

            if (i != 0) {
                retorno += (" " + ctx.op1(i - 1).getText() + " ");
            }
            retorno += imprimirConteudoFormatado(tabela, variaveis, ctx.termo(i));

        }
        return retorno;
    }

    // termo: fator (op2 fator)*;
    public static String imprimirConteudoFormatado(TabelaDeSimbolos tabela, List <String> variaveis, LAParser.TermoContext ctx) {
        String retorno = "";
        String ret = null;
        for (int i = 0; i < ctx.fator().size(); i++) {

            if (i != 0) {
                retorno += (" " + ctx.op2(i - 1) + " ");
            }
            retorno += imprimirConteudoFormatado(tabela, variaveis, ctx.fator(i));

        }
        return retorno;
    }

    // fator: parcela (op3 parcela)*;
    public static String imprimirConteudoFormatado(TabelaDeSimbolos tabela, List <String> variaveis, LAParser.FatorContext ctx) {

        String retorno = "";
        String ret = null;
        for (int i = 0; i < ctx.parcela().size(); i++) {

            if (i != 0) {
                retorno += (" " + ctx.op3(i - 1) + " ");
            }
            retorno += imprimirConteudoFormatado(tabela, variaveis, ctx.parcela(i));

        }
        return retorno;
    }

    // parcela: (op_unario)? parcela_unario | parcela_nao_unario;
    public static String imprimirConteudoFormatado(TabelaDeSimbolos tabela, List <String> variaveis, LAParser.ParcelaContext ctx) {

        if (ctx.parcela_unario() != null) {
            //System.out.println("122 " + ctx.parcela_unario().getText());
            String pu = imprimirConteudoFormatado(tabela, variaveis, ctx.parcela_unario());
            return pu;
        } else /* (ctx.parcela_nao_unario()!= null) */ {
            //System.out.println("126");
//            String nu = imprimirConteudoFormatado(TabelaDeSimbolos tabela, List <String> variaveis, ctx.parcela_nao_unario());
            return imprimirConteudoFormatado(tabela, variaveis, ctx.parcela_nao_unario());
        }

    }

    /*
     * parcela_unario: ('^')? identificador | IDENT '(' expressao (',' expressao)*
     * ')' | NUM_INT | NUM_REAL | '(' expressao ')';
     */
    public static String imprimirConteudoFormatado(TabelaDeSimbolos tabela, List <String> variaveis, LAParser.Parcela_unarioContext ctx) {
        if (ctx.NUM_INT() != null) {
            return ctx.NUM_INT().getText() + " ";
        }
        if (ctx.NUM_REAL() != null) {
            return ctx.NUM_REAL().getText() + " ";
        }
        if (ctx.IDENT() != null) {
            //talvez tenha que fazer essa parte
            return ctx.IDENT().getText() + " ";
        }
        if (ctx.identificador() != null) {
            //System.out.println("161 " + ctx.identificador().getText());
            if (ctx.INICIO_ENDERECO() != null) {
                //System.out.println("163 " + ctx.identificador().getText());
                return "&" + ctx.identificador().getText();
            } else {
                TipoLA tipoVar = tabela.verificar(ctx.identificador().getText());
                variaveis.add(ctx.identificador().getText());
                String aux = " Faltou esse, linha 94 do LAGeradorCUtils";
                switch (tipoVar) {
                    case INTEIRO:
                        aux = "%d";
                        break;
                    case REAL:
                        aux = "%f";
                        break;
                    case LITERAL:
                        aux = "%s";
                        break;
                }
                return aux;
            }
        }
        // se não for nenhum dos tipos acima, só pode ser uma expressão
        // entre parêntesis
        // ---------------------probelma aqui, a expressao nao esta aceitando fora de
        // vetor------------------------
        return "(" + imprimirConteudoFormatado(tabela, variaveis, ctx.expressao(0)) + ")";
    }

    /*
     * parcela_nao_unario: '&' identificador | CADEIA;
     */
    public static String imprimirConteudoFormatado(TabelaDeSimbolos tabela, List <String> variaveis, LAParser.Parcela_nao_unarioContext ctx) {
        if (ctx.identificador() != null) {
            return ctx.getText();
        }

        // String
        // if (ctx.CADEIA() != null) {
        return ctx.CADEIA().getText().substring(1, ctx.CADEIA().getText().length()-1);
        // }

    }

    /*
     * expressao: termo_logico (op_logico_1 termo_logico)*;
     */
    public static String imprimirConteudoFormatado(TabelaDeSimbolos tabela, List <String> variaveis, LAParser.ExpressaoContext ctx) {
        String retorno = "";
        String ret = null;
        for (int i = 0; i < ctx.termo_logico().size(); i++) {

            if (i != 0) {
                retorno += " || ";
            }
            retorno += imprimirConteudoFormatado(tabela, variaveis, ctx.termo_logico(i));

        }
        return retorno;
    }

    // termo_logico: fator_logico (op_logico_2 fator_logico)*;
    public static String imprimirConteudoFormatado(TabelaDeSimbolos tabela, List <String> variaveis, LAParser.Termo_logicoContext ctx) {

        String retorno = "";
        for (int i = 0; i < ctx.fator_logico().size(); i++) {

            if (i != 0) {
                retorno += " && ";
            }
            retorno += imprimirConteudoFormatado(tabela, variaveis, ctx.fator_logico(i));

        }
        return retorno;
    }

    // fator_logico: (OP_NAO)? parcela_logica;
    public static String imprimirConteudoFormatado(TabelaDeSimbolos tabela, List <String> variaveis, LAParser.Fator_logicoContext ctx) {

        if (ctx.OP_NAO() != null) {
            return " !" + imprimirConteudoFormatado(tabela, variaveis, ctx.parcela_logica());
        }
        return imprimirConteudoFormatado(tabela, variaveis, ctx.parcela_logica());
    }

    /*
     * parcela_logica: OP_bool | exp_relacional;
     */
    public static String imprimirConteudoFormatado(TabelaDeSimbolos tabela, List <String> variaveis, LAParser.Parcela_logicaContext ctx) {

        if (ctx.exp_relacional() != null) {
            return imprimirConteudoFormatado(tabela, variaveis, ctx.exp_relacional());
        } else {
            if ("falso".equals(ctx.OP_bool().getText())) {
                return " 0 ";
            } else {
                return " 1 ";
            }

        }
    }

    // exp_relacional: exp_aritmetica (op_relacional exp_aritmetica)?;
    public static String imprimirConteudoFormatado(TabelaDeSimbolos tabela, List <String> variaveis, LAParser.Exp_relacionalContext ctx) {

        String retorno = imprimirConteudoFormatado(tabela, variaveis, ctx.exp_aritmetica(0));
        String op = "";
        if (ctx.exp_aritmetica().size() > 1) {
            op = ctx.op_relacional().getText();
            if (op.equals("=")) {
                op = "==";
            } else if (op.equals("<>")) {
                op = "!=";
            }

            retorno = retorno +" " + op + " " + imprimirConteudoFormatado(tabela, variaveis, ctx.exp_aritmetica(1));
        }
        return retorno;
    }

     // exp_aritmetica: termo (op1 termo)*;
    public static String imprimirConteudo(LAParser.Exp_aritmeticaContext ctx) {
        String retorno = "";
        String ret = null;
        for (int i = 0; i < ctx.termo().size(); i++) {

            if (i != 0) {
                retorno += (" " + ctx.op1(i - 1).getText() + " ");
            }
            retorno += imprimirConteudo(ctx.termo(i));

        }
        return retorno;
    }

    // termo: fator (op2 fator)*;
    public static String imprimirConteudo(LAParser.TermoContext ctx) {
        String retorno = "";
        String ret = null;
        for (int i = 0; i < ctx.fator().size(); i++) {

            if (i != 0) {
                retorno += (" " + ctx.op2(i - 1).getText() + " ");
            }
            retorno += imprimirConteudo(ctx.fator(i));

        }
        return retorno;
    }

    // fator: parcela (op3 parcela)*;
    public static String imprimirConteudo(LAParser.FatorContext ctx) {

        String retorno = "";
        String ret = null;
        for (int i = 0; i < ctx.parcela().size(); i++) {

            if (i != 0) {
                retorno += (" " + ctx.op3(i - 1) + " ");
            }
            retorno += imprimirConteudo(ctx.parcela(i));

        }
        return retorno;
    }

    // parcela: (op_unario)? parcela_unario | parcela_nao_unario;
    public static String imprimirConteudo(LAParser.ParcelaContext ctx) {

        if (ctx.parcela_unario() != null) {
            //System.out.println("122 " + ctx.parcela_unario().getText());
            String pu = imprimirConteudo(ctx.parcela_unario());
            return pu;
        } else /* (ctx.parcela_nao_unario()!= null) */ {
            //System.out.println("126");
//            String nu = imprimirConteudo(ctx.parcela_nao_unario());
            return ctx.parcela_nao_unario().getText();
        }

    }

    /*
     * parcela_unario: ('^')? identificador | IDENT '(' expressao (',' expressao)*
     * ')' | NUM_INT | NUM_REAL | '(' expressao ')';
     */
    public static String imprimirConteudo(LAParser.Parcela_unarioContext ctx) {
        if (ctx.NUM_INT() != null) {
            return ctx.NUM_INT().getText() + " ";
        }
        if (ctx.NUM_REAL() != null) {
            return ctx.NUM_REAL().getText() + " ";
        }
        if (ctx.IDENT() != null) {
            //talvez tenha que fazer essa parte
            return ctx.IDENT().getText() + " ";
        }
        if (ctx.identificador() != null) {
            //System.out.println("161 " + ctx.identificador().getText());
            if (ctx.INICIO_ENDERECO() != null) {
                //System.out.println("163 " + ctx.identificador().getText());
                return "&" + ctx.identificador().getText();
            } else {
                return ctx.identificador().getText();
            }
        }
        // se não for nenhum dos tipos acima, só pode ser uma expressão
        // entre parêntesis
        // ---------------------probelma aqui, a expressao nao esta aceitando fora de
        // vetor------------------------
        return "(" + imprimirConteudo(ctx.expressao(0)) + ")";
    }

    /*
     * parcela_nao_unario: '&' identificador | CADEIA;
     */
//    public static String imprimirConteudo(LAParser.Parcela_nao_unarioContext ctx) {
//        if (ctx.identificador() != null) {
//            return ctx.getText();
//        }
//
//        // String
//        // if (ctx.CADEIA() != null) {
//        return String.LITERAL;
//        // }
//
//    }

    /*
     * expressao: termo_logico (op_logico_1 termo_logico)*;
     */
    public static String imprimirConteudo(LAParser.ExpressaoContext ctx) {
        String retorno = "";
        String ret = null;
        for (int i = 0; i < ctx.termo_logico().size(); i++) {

            if (i != 0) {
                retorno += " || ";
            }
            retorno += imprimirConteudo(ctx.termo_logico(i));

        }
        return retorno;
    }

    // termo_logico: fator_logico (op_logico_2 fator_logico)*;
    public static String imprimirConteudo(LAParser.Termo_logicoContext ctx) {

        String retorno = "";
        for (int i = 0; i < ctx.fator_logico().size(); i++) {

            if (i != 0) {
                retorno += " && ";
            }
            retorno += imprimirConteudo(ctx.fator_logico(i));

        }
        return retorno;
    }

    // fator_logico: (OP_NAO)? parcela_logica;
    public static String imprimirConteudo(LAParser.Fator_logicoContext ctx) {

        if (ctx.OP_NAO() != null) {
            return " !" + imprimirConteudo(ctx.parcela_logica());
        }
        return imprimirConteudo(ctx.parcela_logica());
    }

    /*
     * parcela_logica: OP_bool | exp_relacional;
     */
    public static String imprimirConteudo(LAParser.Parcela_logicaContext ctx) {

        if (ctx.exp_relacional() != null) {
            return imprimirConteudo(ctx.exp_relacional());
        } else {
            if ("falso".equals(ctx.OP_bool().getText())) {
                return " 0 ";
            } else {
                return " 1 ";
            }

        }
    }

    // exp_relacional: exp_aritmetica (op_relacional exp_aritmetica)?;
    public static String imprimirConteudo(LAParser.Exp_relacionalContext ctx) {

        String retorno = imprimirConteudo(ctx.exp_aritmetica(0));
        String op = "";
        if (ctx.exp_aritmetica().size() > 1) {
            op = ctx.op_relacional().getText();
            if (op.equals("=")) {
                op = "==";
            } else if (op.equals("<>")) {
                op = "!=";
            }

            retorno = retorno +" " + op + " " + imprimirConteudo(ctx.exp_aritmetica(1));
        }
        return retorno;
    }
    
    
}
