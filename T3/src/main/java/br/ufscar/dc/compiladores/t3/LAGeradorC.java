package br.ufscar.dc.compiladores.t3;

import com.ibm.icu.number.Rounder;
import org.antlr.v4.runtime.tree.TerminalNode;
import static br.ufscar.dc.compiladores.t3.LAGeradorCUtils.imprimirConteudoFormatado;
import java.util.List;
import java.util.Vector;

public class LAGeradorC extends LABaseVisitor<Void> {

    StringBuilder saida;
    TabelaDeSimbolos tabela;

    public LAGeradorC() {
        saida = new StringBuilder();
        this.tabela = new TabelaDeSimbolos();
    }

    @Override
    public Void visitPrograma(LAParser.ProgramaContext ctx) {
        saida.append("#include <stdio.h>\n");
        saida.append("#include <stdlib.h>\n");

        ctx.declaracoes().decl_local_global().forEach(dec -> visitDecl_local_global(dec));

        saida.append("int main(){\n");

        if (ctx.corpo().declaracao_local() != null) {
            ctx.corpo().declaracao_local().forEach(decl -> visitDeclaracao_local(decl));
        }
        if (ctx.corpo().cmd() != null) {
            ctx.corpo().cmd().forEach(cmd -> visitCmd(cmd));
        }

        saida.append("return 0;\n}");
        return null;
    }

    @Override
    public Void visitDecl_local_global(LAParser.Decl_local_globalContext ctx) {
        String nomeVar = ctx.declaracao_local().variavel().identificador(0).IDENT(0).getText();
        String tipoVar = ctx.declaracao_local().variavel().tipo().getText();
        switch (tipoVar) {
            case "inteiro":
                tipoVar = "int";
                break;
            case "real":
                tipoVar = "float";
                break;
            case "literal":
                tipoVar = "char[500]";
                break;
        }

        saida.append(tipoVar + " " + nomeVar + ";\n");
        return null;
    }

    @Override
    public Void visitDeclaracao_local(LAParser.Declaracao_localContext ctx) {
        if (ctx.variavel() != null) {
            ////System.out.println(ctx.declaracao_local().variavel());
            int erro_tipo = 0;

            int cont = 0;
            for (LAParser.IdentificadorContext ident : ctx.variavel().identificador()) {
                if (ctx.variavel().tipo().registro() != null) {
                    saida.append("typedef struct {\n");
                    //adicionar registro
                    tabela.adicionar(ident.getText(), TabelaDeSimbolos.TipoLA.REGISTRO, false);
                    TabelaDeSimbolos tabelaInterna = tabela.get_tabela_interna(ident.getText());

                    for (LAParser.VariavelContext varInterna : ctx.variavel().tipo().registro().variavel()) {
                        int cont2 = 0;

                        for (LAParser.IdentificadorContext identificadorLocal : varInterna.identificador()) {

                            String nomeVarInterna = identificadorLocal.getText();
                            String strTipoVarInterna = varInterna.tipo().getText();
                            TabelaDeSimbolos.TipoLA tipoVarInterna = TabelaDeSimbolos.TipoLA.INVALIDO;
                            boolean vetor = (identificadorLocal.dimensao() != null);

                            switch (strTipoVarInterna) {
                                case "inteiro":
                                    tipoVarInterna = TabelaDeSimbolos.TipoLA.INTEIRO;
                                    tabelaInterna.adicionar(nomeVarInterna, tipoVarInterna, vetor);
                                    if (cont2 == 0) {
                                        saida.append("int " + nomeVarInterna);
                                        cont2++;
                                    } else {
                                        saida.append(", " + nomeVarInterna);
                                    }
                                    break;
                                case "literal":
                                    tipoVarInterna = TabelaDeSimbolos.TipoLA.LITERAL;
                                    tabelaInterna.adicionar(nomeVarInterna, tipoVarInterna, vetor);
                                    if (cont2 == 0) {
                                        saida.append("char[500] " + nomeVarInterna);
                                        cont2++;
                                    } else {
                                        saida.append(", " + nomeVarInterna);
                                    }
                                    break;
                                case "real":
                                    tipoVarInterna = TabelaDeSimbolos.TipoLA.REAL;
                                    tabelaInterna.adicionar(nomeVarInterna, tipoVarInterna, vetor);
                                    if (cont2 == 0) {
                                        saida.append("float " + nomeVarInterna);
                                        cont2++;
                                    } else {
                                        saida.append(", " + nomeVarInterna);
                                    }
                                    break;
                                case "logico":
                                    tipoVarInterna = TabelaDeSimbolos.TipoLA.LOGICO;
                                    tabelaInterna.adicionar(nomeVarInterna, tipoVarInterna, vetor);
                                    if (cont2 == 0) {
                                        saida.append("bool " + nomeVarInterna);
                                        cont2++;
                                    } else {
                                        saida.append(", " + nomeVarInterna);
                                    }
                                    break;
                                default:
                                    if ('^' == strTipoVarInterna.charAt(0)) {
                                        tipoVarInterna = TabelaDeSimbolos.TipoLA.ENDERECO;
                                        tabelaInterna.adicionar(nomeVarInterna, tipoVarInterna, vetor);
                                    } else {
                                        // se for um tipo declarado anteriormente
                                        if (tabelaInterna.existe(strTipoVarInterna)) {
                                            TabelaDeSimbolos.TipoLA tipo_variavel_encontrada = tabela.verificar(strTipoVarInterna);
                                            tipoVarInterna = TabelaDeSimbolos.TipoLA.CUSTOMIZADO;
                                            if (tipo_variavel_encontrada == TabelaDeSimbolos.TipoLA.TIPO) {
                                                tabelaInterna.adicionar(nomeVarInterna, tipoVarInterna, strTipoVarInterna, vetor);
                                                if (cont == 0) {
                                                    saida.append(strTipoVarInterna + " " + nomeVarInterna);
                                                    cont++;
                                                } else {
                                                    saida.append(", " + nomeVarInterna);
                                                }
                                            } else {
                                                tabelaInterna.adicionar(nomeVarInterna, tipoVarInterna, strTipoVarInterna, vetor);
                                                LASemanticoUtils.adicionarErroSemantico(ident.start,
                                                        "tipo " + strTipoVarInterna + " nao declarado");
                                            }
                                        } else {
                                            if (erro_tipo == 0) {
                                                tabelaInterna.adicionar(nomeVarInterna, TabelaDeSimbolos.TipoLA.INVALIDO);
                                                LASemanticoUtils.adicionarErroSemantico(ident.start,
                                                        "tipo " + strTipoVarInterna + " nao declarado");
                                                erro_tipo++;
                                            }
                                        }
                                    }
                                    break;
                            }

                        }
                        saida.append(";\n");
                    }
                    saida.append("};");

                } else {
                    // verificacao do tipo
                    String strTipoVar = ctx.variavel().tipo().getText();
                    TabelaDeSimbolos.TipoLA tipoVar;
                    boolean vetor = (ctx.variavel().identificador(0).dimensao() != null);

                    switch (strTipoVar) {
                        case "inteiro":
                            tipoVar = TabelaDeSimbolos.TipoLA.INTEIRO;
                            tabela.adicionar(ident.IDENT(0).getText(), tipoVar, vetor);
                            if (cont == 0) {
                                saida.append("int " + ident.getText());
                                cont++;
                            } else {
                                saida.append(", " + ident.getText());
                            }
                            break;
                        case "literal":
                            tipoVar = TabelaDeSimbolos.TipoLA.LITERAL;
                            tabela.adicionar(ident.IDENT(0).getText(), tipoVar, vetor);
                            if (cont == 0) {
                                saida.append("char " + ident.getText() +  "[500]");
                                cont++;
                            } else {
                                saida.append(", " + ident.getText() +  "[500]");
                            }
                            break;
                        case "real":
                            tipoVar = TabelaDeSimbolos.TipoLA.REAL;
                            tabela.adicionar(ident.IDENT(0).getText(), tipoVar, vetor);
                            if (cont == 0) {
                                saida.append("float " + ident.getText());
                                cont++;
                            } else {
                                saida.append(", " + ident.getText());
                            }
                            break;
                        case "logico":
                            tipoVar = TabelaDeSimbolos.TipoLA.LOGICO;
                            tabela.adicionar(ident.IDENT(0).getText(), tipoVar, vetor);
                            if (cont == 0) {
                                saida.append("bool " + ident.getText());
                                cont++;
                            } else {
                                saida.append(", " + ident.getText());
                            }
                            break;
                        default:
                            if ('^' == strTipoVar.charAt(0)) {
                                tipoVar = TabelaDeSimbolos.TipoLA.ENDERECO;
                                tabela.adicionar(ident.getText(), tipoVar, vetor);
                            } else {
                                // se for um tipo declarado anteriormente (tipo customizado)
                                tipoVar = TabelaDeSimbolos.TipoLA.CUSTOMIZADO;
                                tabela.adicionar(ident.IDENT(0).getText(), tipoVar, strTipoVar, vetor);
                                if (cont == 0) {
                                    saida.append(strTipoVar + " " + ident.getText());
                                    cont++;
                                } else {
                                    saida.append(", " + ident.getText());
                                }

                            }
                            break;
                    }
                }

            }
            
                    saida.append(";\n");
        } else {
            //declaracao de constante global
            if (ctx.valor_constante() != null) {

                LAParser.Declaracao_localContext dl = ctx;
                String nome_const = dl.IDENT().getText();
                // verificacao do tipo
                String strTipoVar = dl.tipo_basico().getText();
                TabelaDeSimbolos.TipoLA tipoVar;
                switch (strTipoVar) {
                    case "inteiro":
                        tipoVar = TabelaDeSimbolos.TipoLA.INTEIRO;
                        tabela.adicionar(nome_const, TabelaDeSimbolos.TipoLA.CONSTANTE, tipoVar.toString());
                        break;
                    case "literal":
                        tipoVar = TabelaDeSimbolos.TipoLA.LITERAL;
                        tabela.adicionar(nome_const, TabelaDeSimbolos.TipoLA.CONSTANTE, tipoVar.toString());
                        break;
                    case "real":
                        tipoVar = TabelaDeSimbolos.TipoLA.REAL;
                        tabela.adicionar(nome_const, TabelaDeSimbolos.TipoLA.CONSTANTE, tipoVar.toString());
                        break;
                    case "logico":
                        tipoVar = TabelaDeSimbolos.TipoLA.LOGICO;
                        tabela.adicionar(nome_const, TabelaDeSimbolos.TipoLA.CONSTANTE, tipoVar.toString());
                        break;
                    default:
                        if ('^' == strTipoVar.charAt(0)) {
                            tipoVar = TabelaDeSimbolos.TipoLA.ENDERECO;
                            tabela.adicionar(nome_const, TabelaDeSimbolos.TipoLA.CONSTANTE, tipoVar.toString());
                        } else {
                            // se for um tipo declarado anteriormente (tipo customizado)
                            if (tabela.existe(strTipoVar)) {
                                TabelaDeSimbolos.TipoLA tipo_variavel_encontrada = tabela.verificar(strTipoVar);
                                //tipoVar = TipoLA.CUSTOMIZADO;     //deixa de ser usado pois o tipo constante aproveita o campo
                                if (tipo_variavel_encontrada == TabelaDeSimbolos.TipoLA.TIPO) {
                                    tabela.adicionar(nome_const, TabelaDeSimbolos.TipoLA.CONSTANTE, strTipoVar);
                                } else {
                                    LASemanticoUtils.adicionarErroSemantico(dl.start,
                                            "tipo " + strTipoVar + " nao declarado");
                                }
                            } else {
                                LASemanticoUtils.adicionarErroSemantico(dl.start,
                                        "tipo " + strTipoVar + " nao declarado");
                            }
                        }
                        break;
                }
            } else {          //declaracao de tipo

                if (ctx.tipo().registro() != null) {
                    //novo tipo registro
                    saida.append("typedef struct {\n");

                    tabela.adicionar(ctx.IDENT().getText(), TabelaDeSimbolos.TipoLA.TIPO, false);
                    TabelaDeSimbolos tabelaInterna = tabela.get_tabela_interna(ctx.IDENT().getText());
                    for (LAParser.VariavelContext varInterna : ctx.tipo().registro().variavel()) {
                        int cont2 = 0;
                        for (LAParser.IdentificadorContext identificadorLocal : varInterna.identificador()) {
                            String nomeVarInterna = identificadorLocal.getText();
                            String strTipoVarInterna = varInterna.tipo().getText();
                            TabelaDeSimbolos.TipoLA tipoVarInterna = TabelaDeSimbolos.TipoLA.INVALIDO;
                            boolean vetor = (identificadorLocal.dimensao() != null);

                            switch (strTipoVarInterna) {
                                case "inteiro":
                                    tipoVarInterna = TabelaDeSimbolos.TipoLA.INTEIRO;
                                    tabelaInterna.adicionar(nomeVarInterna, tipoVarInterna, vetor);
                                    if (cont2 == 0) {
                                        saida.append("int " + nomeVarInterna);
                                        cont2++;
                                    } else {
                                        saida.append(", " + nomeVarInterna);
                                    }
                                    break;
                                case "literal":
                                    tipoVarInterna = TabelaDeSimbolos.TipoLA.LITERAL;
                                    tabelaInterna.adicionar(nomeVarInterna, tipoVarInterna, vetor);
                                    if (cont2 == 0) {
                                        saida.append("char[500] " + nomeVarInterna);
                                        cont2++;
                                    } else {
                                        saida.append(", " + nomeVarInterna);
                                    }
                                    break;
                                case "real":
                                    tipoVarInterna = TabelaDeSimbolos.TipoLA.REAL;
                                    tabelaInterna.adicionar(nomeVarInterna, tipoVarInterna, vetor);
                                    if (cont2 == 0) {
                                        saida.append("float " + nomeVarInterna);
                                        cont2++;
                                    } else {
                                        saida.append(", " + nomeVarInterna);
                                    }
                                    break;
                                case "logico":
                                    tipoVarInterna = TabelaDeSimbolos.TipoLA.LOGICO;
                                    tabelaInterna.adicionar(nomeVarInterna, tipoVarInterna, vetor);
                                    if (cont2 == 0) {
                                        saida.append("bool " + nomeVarInterna);
                                        cont2++;
                                    } else {
                                        saida.append(", " + nomeVarInterna);
                                    }
                                    break;
                                default:
                                    if ('^' == strTipoVarInterna.charAt(0)) {
                                        tipoVarInterna = TabelaDeSimbolos.TipoLA.ENDERECO;
                                        tabelaInterna.adicionar(nomeVarInterna, tipoVarInterna, vetor);
                                    } else {
                                        // se for um tipo declarado anteriormente
                                        if (tabelaInterna.existe(strTipoVarInterna)) {
                                            TabelaDeSimbolos.TipoLA tipo_variavel_encontrada = tabela.verificar(strTipoVarInterna);
                                            tipoVarInterna = TabelaDeSimbolos.TipoLA.CUSTOMIZADO;
                                            if (tipo_variavel_encontrada == TabelaDeSimbolos.TipoLA.TIPO) {
                                                tabelaInterna.adicionar(nomeVarInterna, tipoVarInterna, strTipoVarInterna, vetor);
                                                if (cont2 == 0) {
                                                    saida.append(strTipoVarInterna + " " + nomeVarInterna);
                                                    cont2++;
                                                } else {
                                                    saida.append(", " + nomeVarInterna);
                                                }
                                            } else {
                                                tabelaInterna.adicionar(nomeVarInterna, tipoVarInterna, strTipoVarInterna, vetor);
                                                LASemanticoUtils.adicionarErroSemantico(ctx.start,
                                                        "tipo " + strTipoVarInterna + " nao declarado");
                                            }
                                        }
                                    }
                                    break;
                            }

                        }
                        saida.append(";\n");
                    }
                    saida.append("}" + ctx.IDENT().getText() + ";\n");

                }
            }
        }

        return null;
    }

    @Override
    public Void visitCmdSe(LAParser.CmdSeContext ctx) {
        saida.append("if (");
        saida.append(LAGeradorCUtils.imprimirConteudo(ctx.expressao()) + "){\n");

        for (LAParser.CmdContext cmd : ctx.faca) {
            visitCmd(cmd);
        }
        saida.append("}\n");
        if (!ctx.senao.isEmpty()) {      //else
            saida.append("else{\n");
            for (LAParser.CmdContext cmd : ctx.senao) {
                visitCmd(cmd);
            }
            saida.append("}\n");
        }

        return null;
    }

    @Override
    public Void visitCmd(LAParser.CmdContext ctx) {
        if (ctx.cmdLeia() != null) {
            visitCmdLeia(ctx.cmdLeia());
        } else if (ctx.cmdEscreva() != null) {
            visitCmdEscreva(ctx.cmdEscreva());
        } else if (ctx.cmdEnquanto() != null) {
            visitCmdEnquanto(ctx.cmdEnquanto());
        } else if (ctx.cmdAtribuicao() != null) {
            visitCmdAtribuicao(ctx.cmdAtribuicao());
        } else if (ctx.cmdSe() != null) {
            visitCmdSe(ctx.cmdSe());
        } else if (ctx.cmdFaca() != null) {
            visitCmdFaca(ctx.cmdFaca());
        } else if (ctx.cmdRetorne() != null) {
            visitCmdRetorne(ctx.cmdRetorne());
        } else if (ctx.cmdCaso() != null) {
            visitCmdCaso(ctx.cmdCaso());
        } else {
            System.out.println("FALTA CHAMAR OU IMPLEMENTAR ESSE METODO SEU VAGABUNDO");
        }
        return null;
    }

    @Override
    public Void visitCmdLeia(LAParser.CmdLeiaContext ctx) {
        String nomeVar = ctx.identificador(0).IDENT(0).getText();
        TabelaDeSimbolos.TipoLA tipoVar = LASemanticoUtils.verificarTipo(tabela, nomeVar);
        String aux = "";

        switch (tipoVar) {
            case INTEIRO:
                aux = "%d";
                break;
            case REAL:
                aux = "%f";
                break;
        }

        if (tipoVar != TabelaDeSimbolos.TipoLA.LITERAL) {
            saida.append("scanf(\"" + aux + "\", &" + nomeVar + ");\n");
        } else {
            saida.append("gets(" + nomeVar + ");\n");
        }
        return null;
    }

    @Override
    public Void visitCmdEscreva(LAParser.CmdEscrevaContext ctx) {
        saida.append("printf(\"");
        List<String> variaveis = new Vector();

        for (LAParser.ExpressaoContext ex : ctx.expressao()) {
            saida.append(imprimirConteudoFormatado(tabela, variaveis, ex));
        }
        saida.append("\"");
        if (variaveis.size() > 0) {      //acho que nem precisa de if
            for (String aux : variaveis) {
                saida.append(", " + aux);
            }
        }
        saida.append(");\n");

        return null;
    }

    @Override
    public Void visitCmdAtribuicao(LAParser.CmdAtribuicaoContext ctx) {
        saida.append(ctx.identificador().getText() + " = ");
        saida.append(LAGeradorCUtils.imprimirConteudo(ctx.expressao()) + ";\n");
        return null;
    }

    @Override
    public Void visitCmdFaca(LAParser.CmdFacaContext ctx) {
        saida.append("do{\n");
        for (LAParser.CmdContext ident : ctx.cmd()) {
            visitCmd(ident);
        }
        saida.append("while(");
        saida.append(LAGeradorCUtils.imprimirConteudo(ctx.expressao()));
        saida.append(");\n");

        return null;
    }

    @Override
    public Void visitCmdCaso(LAParser.CmdCasoContext ctx) {
        saida.append("switch(" + LAGeradorCUtils.imprimirConteudo(ctx.exp_aritmetica()) + "){");

        for (LAParser.Item_selecaoContext ident : ctx.selecao().item_selecao()) {
            visitSelecao(ident);
            for (LAParser.CmdContext ident2 : ident.cmd()) {
                visitCmd(ident2);
            }
            saida.append("break;\n");
        }

        if (ctx.getText().contains("senao")) {
            saida.append("defaut:\n");
            for (LAParser.CmdContext ident : ctx.cmd()) {
                visitCmd(ident);
            }
        }

        saida.append("}");
        return null;
    }

    public Void visitSelecao(LAParser.Item_selecaoContext ctx) {

        for (LAParser.Numero_intervaloContext ident : ctx.constantes().numero_intervalo()) {
            int i = Integer.parseInt(ident.NUM_INT(0).getText());
            int fim = 0;
            //System.out.println("AAAA" + i + "AAAA");
            if (ident.NUM_INT(1) != null) {
                fim = Integer.parseInt(ident.NUM_INT(1).getText());

                while (i < fim) {
                    saida.append("case " + i + ":\n");
                    i++;
                }
            } else {
                saida.append("case " + i + ":\n");
            }

        }

        TerminalNode aux = ctx.constantes().numero_intervalo(0).NUM_INT(1);
        if (aux != null) {
            saida.append("case " + ctx.constantes().numero_intervalo(0).NUM_INT(1) + ":\n");
        }

        return null;
    }

}
