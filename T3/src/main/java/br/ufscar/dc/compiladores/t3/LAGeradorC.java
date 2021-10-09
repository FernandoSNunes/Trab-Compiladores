package br.ufscar.dc.compiladores.t3;

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
                    //adicionar registro
                    tabela.adicionar(ident.getText(), TabelaDeSimbolos.TipoLA.REGISTRO, false);
                    TabelaDeSimbolos tabelaInterna = tabela.get_tabela_interna(ident.getText());

                    for (LAParser.VariavelContext varInterna : ctx.variavel().tipo().registro().variavel()) {
                        for (LAParser.IdentificadorContext identificadorLocal : varInterna.identificador()) {
                            String nomeVarInterna = identificadorLocal.getText();
                            String strTipoVarInterna = varInterna.tipo().getText();
                            TabelaDeSimbolos.TipoLA tipoVarInterna = TabelaDeSimbolos.TipoLA.INVALIDO;
                            boolean vetor = (identificadorLocal.dimensao() != null);

                            switch (strTipoVarInterna) {
                                case "inteiro":
                                    tipoVarInterna = TabelaDeSimbolos.TipoLA.INTEIRO;
                                    tabelaInterna.adicionar(nomeVarInterna, tipoVarInterna, vetor);
                                    break;
                                case "literal":
                                    tipoVarInterna = TabelaDeSimbolos.TipoLA.LITERAL;
                                    tabelaInterna.adicionar(nomeVarInterna, tipoVarInterna, vetor);
                                    break;
                                case "real":
                                    tipoVarInterna = TabelaDeSimbolos.TipoLA.REAL;
                                    tabelaInterna.adicionar(nomeVarInterna, tipoVarInterna, vetor);
                                    break;
                                case "logico":
                                    tipoVarInterna = TabelaDeSimbolos.TipoLA.LOGICO;
                                    tabelaInterna.adicionar(nomeVarInterna, tipoVarInterna, vetor);
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
                    }

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
                                saida.append("char[500] " + ident.getText());
                                cont++;
                            } else {
                                saida.append(", " + ident.getText());
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
                                    saida.append(strTipoVar + ident.getText());
                                    cont++;
                                } else {
                                    saida.append(", " + ident.getText());
                                }

                            }
                            break;
                    }
                }

            }
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
                    tabela.adicionar(ctx.IDENT().getText(), TabelaDeSimbolos.TipoLA.TIPO, false);
                    TabelaDeSimbolos tabelaInterna = tabela.get_tabela_interna(ctx.IDENT().getText());
                    for (LAParser.VariavelContext varInterna : ctx.tipo().registro().variavel()) {
                        for (LAParser.IdentificadorContext identificadorLocal : varInterna.identificador()) {
                            String nomeVarInterna = identificadorLocal.getText();
                            String strTipoVarInterna = varInterna.tipo().getText();
                            TabelaDeSimbolos.TipoLA tipoVarInterna = TabelaDeSimbolos.TipoLA.INVALIDO;
                            boolean vetor = (identificadorLocal.dimensao() != null);

                            switch (strTipoVarInterna) {
                                case "inteiro":
                                    tipoVarInterna = TabelaDeSimbolos.TipoLA.INTEIRO;
                                    tabelaInterna.adicionar(nomeVarInterna, tipoVarInterna, vetor);
                                    break;
                                case "literal":
                                    tipoVarInterna = TabelaDeSimbolos.TipoLA.LITERAL;
                                    tabelaInterna.adicionar(nomeVarInterna, tipoVarInterna, vetor);
                                    break;
                                case "real":
                                    tipoVarInterna = TabelaDeSimbolos.TipoLA.REAL;
                                    tabelaInterna.adicionar(nomeVarInterna, tipoVarInterna, vetor);
                                    break;
                                case "logico":
                                    tipoVarInterna = TabelaDeSimbolos.TipoLA.LOGICO;
                                    tabelaInterna.adicionar(nomeVarInterna, tipoVarInterna, vetor);
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
                    }
                }
            }
        }

        saida.append(";\n");

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
        } else/*cmdRetorne*/ {
            visitCmdRetorne(ctx.cmdRetorne());
        }
        return null;
    }

    @Override
    public Void visitCmdLeia(LAParser.CmdLeiaContext ctx) {
        String nomeVar = ctx.identificador(0).IDENT(0).getText();
        TabelaDeSimbolos.TipoLA tipoVar = LASemanticoUtils.verificarTipo(tabela, nomeVar);
        String aux = "";
        System.out.println("tipo var  = " + tipoVar);

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
        /*if(ctx.getText().contains("\"")){
            String aux = ctx.getText();
            String auxFinal = "";
            int index = aux.indexOf('"') + 1;
            while(aux.charAt(index) != '"'){
                auxFinal += aux.charAt(index);
                index++;
            }
            System.out.println(auxFinal);
        }*/
        int qtdVariaveis = 0;
        String Variaveis = "";
        saida.append("printf(");
        for (LAParser.ExpressaoContext ident : ctx.expressao()) {
            if (ident.getText().contains("\"")) {
                if (ctx.expressao(1) == null) {
                    saida.append(ident.getText());
                } else {
                    System.out.println(ident.getText().substring(0, ident.getText().length() - 1));
                    String aux = ident.getText().substring(0, ident.getText().length() - 1);
                    saida.append(aux);
                }
            } else {
                String nomeVar = ident.getText();
                TabelaDeSimbolos.TipoLA tipoVar = LASemanticoUtils.verificarTipo(tabela, ident);
                String aux = "";
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
                if (ctx.expressao(1) == null) {
                    saida.append("\"" + aux + "\"");
                    Variaveis += "," + nomeVar;
                } else {
                    qtdVariaveis++;
                    Variaveis += "," + nomeVar;
                    saida.append(aux);
                }

            }
        }
        if (qtdVariaveis > 0) {
            saida.append("\"" + Variaveis + ");\n");
        } else {
            saida.append(Variaveis + ");\n");
        }
        return null;
    }

    @Override
    public Void visitCmdAtribuicao(LAParser.CmdAtribuicaoContext ctx) {

        return null;
    }

}
