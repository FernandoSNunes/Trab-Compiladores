package br.ufscar.dc.compiladores.t3;

import org.antlr.v4.runtime.tree.TerminalNode;
import static br.ufscar.dc.compiladores.t3.LAGeradorCUtils.imprimirConteudoFormatado;
import static br.ufscar.dc.compiladores.t3.LASemanticoUtils.verificarTipo;
import br.ufscar.dc.compiladores.t3.TabelaDeSimbolos.TipoLA;
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
        if (ctx.declaracao_global() != null) {
            visitDeclaracao_global(ctx.declaracao_global());
        } else {
            visitDeclaracao_local(ctx.declaracao_local());
        }
        return null;
    }

    @Override
    public Void visitDeclaracao_local(LAParser.Declaracao_localContext ctx) {
        if (ctx.variavel() != null) {
            int erro_tipo = 0;

            int cont = 0;
            List<String> variaveis = new Vector();
            if (ctx.variavel().tipo().registro() != null) {
                saida.append("struct {\n");
            }
            for (LAParser.IdentificadorContext ident : ctx.variavel().identificador()) {
                if (ctx.variavel().tipo().registro() != null) {
                    // adicionar registro
                    variaveis.add(ident.getText());
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
                                        saida.append("char " + nomeVarInterna + "[500]");
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
                                            TabelaDeSimbolos.TipoLA tipo_variavel_encontrada = tabela
                                                    .verificar(strTipoVarInterna);
                                            tipoVarInterna = TabelaDeSimbolos.TipoLA.CUSTOMIZADO;
                                            if (tipo_variavel_encontrada == TabelaDeSimbolos.TipoLA.TIPO) {
                                                tabelaInterna.adicionar(nomeVarInterna, tipoVarInterna,
                                                        strTipoVarInterna, vetor);
                                                if (cont == 0) {
                                                    saida.append(strTipoVarInterna + " " + nomeVarInterna);
                                                    cont++;
                                                } else {
                                                    saida.append(", " + nomeVarInterna);
                                                }
                                            } else {
                                                tabelaInterna.adicionar(nomeVarInterna, tipoVarInterna,
                                                        strTipoVarInterna, vetor);
                                                LASemanticoUtils.adicionarErroSemantico(ident.start,
                                                        "tipo " + strTipoVarInterna + " nao declarado");
                                            }
                                        } else {
                                            if (erro_tipo == 0) {
                                                tabelaInterna.adicionar(nomeVarInterna,
                                                        TabelaDeSimbolos.TipoLA.INVALIDO);
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
                    if (ctx.variavel().tipo().registro() == null) {
                        saida.append("};");
                    }

                } else {
                    // verificacao do tipo
                    String strTipoVar = ctx.variavel().tipo().getText();
                    TabelaDeSimbolos.TipoLA tipoVar;
                    boolean vetor = (ctx.variavel().identificador(0).dimensao() != null);
                    String ponteiro = "";
                    if (strTipoVar.charAt(0) == '^') {
                        ponteiro = "*";
                        strTipoVar = strTipoVar.substring(1);
                    }

                    switch (strTipoVar) {
                        case "inteiro":
                            tipoVar = TabelaDeSimbolos.TipoLA.INTEIRO;
                            tabela.adicionar(ident.IDENT(0).getText(), tipoVar, vetor);
                            if (cont == 0) {
                                saida.append("int" + ponteiro + " " + ident.getText());
                                cont++;
                            } else {
                                saida.append(", " + ident.getText());
                            }
                            break;
                        case "literal":
                            tipoVar = TabelaDeSimbolos.TipoLA.LITERAL;
                            tabela.adicionar(ident.IDENT(0).getText(), tipoVar, vetor);
                            if (cont == 0) {
                                saida.append("char " + ident.getText() + "[500]");
                                cont++;
                            } else {
                                saida.append(", " + ident.getText() + "[500]");
                            }
                            break;
                        case "real":
                            tipoVar = TabelaDeSimbolos.TipoLA.REAL;
                            tabela.adicionar(ident.IDENT(0).getText(), tipoVar, vetor);
                            if (cont == 0) {
                                saida.append("float" + ponteiro + " " + ident.getText());
                                cont++;
                            } else {
                                saida.append(", " + ident.getText());
                            }
                            break;
                        case "logico":
                            tipoVar = TabelaDeSimbolos.TipoLA.LOGICO;
                            tabela.adicionar(ident.IDENT(0).getText(), tipoVar, vetor);
                            if (cont == 0) {
                                saida.append("bool" + ponteiro + " " + ident.getText());
                                cont++;
                            } else {
                                saida.append(", " + ident.getText());
                            }
                            break;
                        default:

                            // se for um tipo declarado anteriormente (tipo customizado)
                            tipoVar = TabelaDeSimbolos.TipoLA.CUSTOMIZADO;
                            tabela.adicionar(ident.IDENT(0).getText(), tipoVar, strTipoVar, vetor);
                            if (cont == 0) {
                                saida.append(strTipoVar + " " + ident.getText());
                                cont++;
                            } else {
                                saida.append(", " + ident.getText());
                            }

                            break;
                    }
                }
            }
            if (ctx.variavel().tipo().registro() != null) {
                saida.append("}" + variaveis.get(0));
                if (variaveis.size() > 1) {
                    for (int i = 1; i < variaveis.size(); i++) {
                        saida.append(", " + variaveis.get(i));
                    }

                }
            }

            saida.append(";\n");
        } else {
            // declaracao de constante global
            if (ctx.valor_constante() != null) {
                saida.append("#define ");
                LAParser.Declaracao_localContext dl = ctx;
                String nome_const = dl.IDENT().getText();
                saida.append(nome_const + " " + dl.valor_constante().getText() + "\n");

            } else { // declaracao de tipo

                if (ctx.tipo().registro() != null) {
                    // novo tipo registro
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
                                        saida.append("char " + nomeVarInterna + "[500]");
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
                                            TabelaDeSimbolos.TipoLA tipo_variavel_encontrada = tabela
                                                    .verificar(strTipoVarInterna);
                                            tipoVarInterna = TabelaDeSimbolos.TipoLA.CUSTOMIZADO;
                                            if (tipo_variavel_encontrada == TabelaDeSimbolos.TipoLA.TIPO) {
                                                tabelaInterna.adicionar(nomeVarInterna, tipoVarInterna,
                                                        strTipoVarInterna, vetor);
                                                if (cont2 == 0) {
                                                    saida.append(strTipoVarInterna + " " + nomeVarInterna);
                                                    cont2++;
                                                } else {
                                                    saida.append(", " + nomeVarInterna);
                                                }
                                            } else {
                                                tabelaInterna.adicionar(nomeVarInterna, tipoVarInterna,
                                                        strTipoVarInterna, vetor);
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

        if (!ctx.senao.isEmpty()) { // else
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
        } else if (ctx.cmdPara() != null) {
            visitCmdPara(ctx.cmdPara());
        } else {
            System.out.println(ctx.getText());
            saida.append(ctx.getText() + ";\n");
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
            if (ex.getText().charAt(0) == '\"') {
                saida.append(imprimirConteudoFormatado(tabela, variaveis, ex));
            } else {
                variaveis.add(ex.getText());
                TipoLA tipo = verificarTipo(tabela, ex);
                if (tipo == TipoLA.INTEIRO) {
                    saida.append("%d");
                }
                if (tipo == TipoLA.REAL) {
                    saida.append("%f");
                }
                if (tipo == TipoLA.LITERAL) {
                    saida.append("%s");
                }
            }

        }
        saida.append("\"");
        if (variaveis.size() > 0) {
            for (String aux : variaveis) {
                saida.append(", " + aux);
            }
        }
        saida.append(");\n");

        return null;
    }

    @Override
    public Void visitCmdAtribuicao(LAParser.CmdAtribuicaoContext ctx) {

        if (tabela.verificar(ctx.identificador().getText()) != TabelaDeSimbolos.TipoLA.LITERAL) {
            if (ctx.getText().charAt(0) == '^') {
                saida.append("*");
            }
            saida.append(ctx.identificador().getText() + " = ");
            saida.append(LAGeradorCUtils.imprimirConteudo(ctx.expressao()) + ";\n");
        } else {
            saida.append("strcpy(" + ctx.identificador().getText() + ", "
                    + LAGeradorCUtils.imprimirConteudo(ctx.expressao()) + ");\n");

        }
        return null;
    }

    @Override
    public Void visitCmdFaca(LAParser.CmdFacaContext ctx) {
        saida.append("do{\n");
        for (LAParser.CmdContext ident : ctx.cmd()) {
            visitCmd(ident);
        }
        saida.append("}while(");
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

    @Override
    public Void visitCmdPara(LAParser.CmdParaContext ctx) {

        saida.append("for(" + ctx.IDENT() + " = " + LAGeradorCUtils.imprimirConteudo(ctx.exp1) + ";");
        if (Integer.parseInt(ctx.exp1.getText()) <= Integer.parseInt(ctx.exp2.getText())) {
            saida.append(ctx.IDENT() + " <= " + ctx.exp2.getText() + ";");
            saida.append(ctx.IDENT() + "++){\n");
        } else if (Integer.parseInt(ctx.exp1.getText()) > Integer.parseInt(ctx.exp2.getText())) {
            saida.append(ctx.IDENT() + " > " + ctx.exp2.getText() + ";");
            saida.append(ctx.IDENT() + "--){\n");
        }

        for (LAParser.CmdContext ident : ctx.cmd()) {
            visitCmd(ident);
        }

        saida.append("}");
        System.out.println(ctx.IDENT());

        return null;
    }

    @Override
    public Void visitCmdEnquanto(LAParser.CmdEnquantoContext ctx) {
        saida.append("while(" + LAGeradorCUtils.imprimirConteudo(ctx.expressao()) + "){");
        for (LAParser.CmdContext ident : ctx.cmd()) {
            visitCmd(ident);
        }
        saida.append("}");

        return null;
    }

    @Override
    public Void visitDeclaracao_global(LAParser.Declaracao_globalContext ctx) {
        TipoLA novotipo;
        if (ctx.FUNCAO() != null) {
            novotipo = TipoLA.FUNCAO;
            tabela.novo_local(false);
        } else {
            novotipo = TipoLA.PROCEDIMENTO;
            tabela.novo_local(true);
        }
        List<String> parametros = new Vector<>();
        List<TipoLA> tipoDosParametros = new Vector<>();
        TipoLA retornoFuncao = TipoLA.INVALIDO;

        if (novotipo == TipoLA.FUNCAO) {
            String strTipoRetornoFuncao = ctx.tipo_estendido().getText();

            switch (strTipoRetornoFuncao) {
                case "inteiro":
                    retornoFuncao = TipoLA.INTEIRO;
                    break;
                case "literal":
                    retornoFuncao = TipoLA.LITERAL;
                    break;
                case "real":
                    retornoFuncao = TipoLA.REAL;
                    break;
                case "logico":
                    retornoFuncao = TipoLA.LOGICO;
                    break;
                default:
                    if ('^' == strTipoRetornoFuncao.charAt(0)) {
                        retornoFuncao = TipoLA.ENDERECO;
                    } else {
                        // se for um tipo declarado anteriormente
                        if (tabela.existe(strTipoRetornoFuncao)) {
                            retornoFuncao = TipoLA.CUSTOMIZADO;
                        } else {
                            LASemanticoUtils.adicionarErroSemantico(ctx.start,
                                    "tipo " + strTipoRetornoFuncao + " nao declarado");
                        }
                    }
                    break;
            }
        } else {
            retornoFuncao = TipoLA.INVALIDO;
        }

        for (LAParser.ParametroContext pa : ctx.parametros().parametro()) {

            String strTipoVar = pa.tipo_estendido().getText();
            String nomeVar = pa.identificador(0).getText();
            TipoLA tipoVar = TipoLA.INVALIDO;
            switch (strTipoVar) {
                case "inteiro":
                    tipoVar = TipoLA.INTEIRO;
                    break;
                case "literal":
                    tipoVar = TipoLA.LITERAL;
                    break;
                case "real":
                    tipoVar = TipoLA.REAL;
                    break;
                case "logico":
                    tipoVar = TipoLA.LOGICO;
                    break;
                default:
                    if ('^' == strTipoVar.charAt(0)) {
                        tipoVar = TipoLA.ENDERECO;
                    } else {
                        // se for um tipo declarado anteriormente
                        if (tabela.existe(strTipoVar)) {
                            TipoLA tipo_variavel_encontrada = tabela.verificar(strTipoVar);
                            tipoVar = TipoLA.CUSTOMIZADO;
                            if (tipo_variavel_encontrada == TipoLA.TIPO) {
                                tabela.adicionar(nomeVar, tipoVar, strTipoVar, false);
                            } else {
                                tabela.adicionar(nomeVar, tipoVar, strTipoVar, false);
                                LASemanticoUtils.adicionarErroSemantico(ctx.start,
                                        "tipo " + strTipoVar + " nao declarado");
                            }
                        }
                    }
                    break;
            }
            if (tipoVar != TipoLA.INVALIDO) {
                if (tipoVar != TipoLA.CUSTOMIZADO) {
                    tabela.adicionar(nomeVar, tipoVar);
                }
                tipoDosParametros.add(tipoVar);
                parametros.add(nomeVar);

            }

        }

        tabela.adicionar_funcproc(ctx.IDENT().getText(), novotipo, tipoDosParametros, retornoFuncao);
        System.out.println("nome da " + novotipo + ": " + ctx.IDENT().getText() + "\nParametros: " + parametros
                + "\nTipos dos parametros: " + tipoDosParametros + "\nRetorno: " + retornoFuncao);

        // procedimento
        System.out.println(ctx.getText());
        if (retornoFuncao == TipoLA.INVALIDO) {
            saida.append("void " + ctx.IDENT().getText() + "(");
            int i = 0;
            for (String parametro : parametros) {
                TipoLA aux = tabela.verificar(parametro);
                if (aux == TipoLA.LITERAL) {
                    saida.append("char* " + parametro);
                } else if (aux == TipoLA.INTEIRO) {
                    saida.append("int " + parametro);
                } else if (aux == TipoLA.REAL) {
                    saida.append("float " + parametro);
                }
                if (i != parametros.size() - 1) {
                    saida.append(",");
                }
                i++;
            }
            saida.append("){\n");
            for (LAParser.Declaracao_localContext ident : ctx.declaracao_local()) {
                visitDeclaracao_local(ident);
            }
            for (LAParser.CmdContext ident : ctx.cmd()) {
                visitCmd(ident);
            }
            saida.append("}\n");

        }
        // funcao
        else {
            if (retornoFuncao == TipoLA.LITERAL) {
                saida.append("char* ");
            } else if (retornoFuncao == TipoLA.INTEIRO) {
                saida.append("int ");
            } else if (retornoFuncao == TipoLA.REAL) {
                saida.append("float ");
            }
            saida.append(ctx.IDENT().getText() + "(");
            int i = 0;
            for (String parametro : parametros) {
                TipoLA aux = tabela.verificar(parametro);
                if (aux == TipoLA.LITERAL) {
                    saida.append("char* " + parametro);
                } else if (aux == TipoLA.INTEIRO) {
                    saida.append("int " + parametro);
                } else if (aux == TipoLA.REAL) {
                    saida.append("float " + parametro);
                }
                if (i != parametros.size() - 1) {
                    saida.append(",");
                }
                i++;
            }
            saida.append("){\n");
            for (LAParser.Declaracao_localContext ident : ctx.declaracao_local()) {
                visitDeclaracao_local(ident);
            }
            for (LAParser.CmdContext ident : ctx.cmd()) {
                visitCmd(ident);
            }
            saida.append("}\n");
        }

        return null;
    }

    @Override
    public Void visitCmdRetorne(LAParser.CmdRetorneContext ctx) {
        saida.append("return " + LAGeradorCUtils.imprimirConteudo(ctx.expressao()) + ";\n");

        return null;
    }

}
