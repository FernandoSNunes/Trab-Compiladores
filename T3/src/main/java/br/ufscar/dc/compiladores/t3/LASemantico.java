package br.ufscar.dc.compiladores.t3;

import br.ufscar.dc.compiladores.t3.LAParser.ExpressaoContext;
import br.ufscar.dc.compiladores.t3.LAParser.ParcelaContext;
import static br.ufscar.dc.compiladores.t3.LASemanticoUtils.verificarTipo;
import br.ufscar.dc.compiladores.t3.TabelaDeSimbolos.TipoLA;
import java.util.List;
import java.util.Vector;

public class LASemantico extends LABaseVisitor<Void> {

    TabelaDeSimbolos tabela;

    @Override
    public Void visitPrograma(LAParser.ProgramaContext ctx) {
        tabela = new TabelaDeSimbolos();
        return super.visitPrograma(ctx);
    }

    // criar o escopo local da main
    @Override
    public Void visitCorpo(LAParser.CorpoContext ctx2) {
        tabela.novo_local();
        for (LAParser.Declaracao_localContext ctx : ctx2.declaracao_local()) {
            if (ctx.variavel() != null) {
                int erro_tipo = 0;
                for (LAParser.IdentificadorContext ident : ctx.variavel().identificador()) {

                    // verifica se a variavel ja existe
                    if (tabela.existe(ident.getText())) {
                        // arrumar texto
                        LASemanticoUtils.adicionarErroSemantico(ident.start,
                                "identificador " + ident.getText() + " ja declarado anteriormente");
                    } else {
                        // verificacao do tipo
                        String strTipoVar = ctx.variavel().tipo().getText();
                        TipoLA tipoVar;
                        switch (strTipoVar) {
                            case "inteiro":
                                tipoVar = TipoLA.INTEIRO;
                                tabela.adicionar_local(ident.getText(), tipoVar);
                                break;
                            case "literal":
                                tipoVar = TipoLA.LITERAL;
                                tabela.adicionar_local(ident.getText(), tipoVar);
                                break;
                            case "real":
                                tipoVar = TipoLA.REAL;
                                tabela.adicionar_local(ident.getText(), tipoVar);
                                break;
                            case "logico":
                                tipoVar = TipoLA.LOGICO;
                                tabela.adicionar_local(ident.getText(), tipoVar);
                                break;
                            default:
                                if ('^' == strTipoVar.charAt(0)) {
                                    tipoVar = TipoLA.ENDERECO;
                                    tabela.adicionar_local(ident.getText(), tipoVar);
                                } else {
                                    // se for um tipo declarado anteriormente
                                    if (tabela.existe(strTipoVar)) {
                                        TipoLA tipo_variavel_encontrada = tabela.verificar(strTipoVar);
                                        tipoVar = TipoLA.CUSTOMIZADO;
                                        if (tipo_variavel_encontrada == TipoLA.TIPO) {
                                            tabela.adicionar_local(ident.getText(), tipoVar, strTipoVar);
                                        } else {
                                            tabela.adicionar_local(ident.getText(), tipoVar, strTipoVar);
                                            LASemanticoUtils.adicionarErroSemantico(ident.start,
                                                    "tipo " + strTipoVar + " nao declarado");
                                        }
                                    } else {
                                        if (erro_tipo == 0) {
                                            tabela.adicionar(ident.getText(), TipoLA.INVALIDO);
                                            LASemanticoUtils.adicionarErroSemantico(ident.start,
                                                    "tipo " + strTipoVar + " nao declarado");
                                            erro_tipo++;
                                        }
                                    }
                                }
                                break;
                        }
                    }
                }
            }
        }

        // tabela.fim_local();
        return super.visitCorpo(ctx2);
    }

    @Override
    public Void visitDecl_local_global(LAParser.Decl_local_globalContext ctx) {

        if (ctx.declaracao_local() != null) {

            if (ctx.declaracao_local().variavel() != null) {
                //System.out.println(ctx.declaracao_local().variavel());
                int erro_tipo = 0;

                for (LAParser.IdentificadorContext ident : ctx.declaracao_local().variavel().identificador()) {

                    // verifica se a variavel ja existe
                    if (tabela.existe(ident.getText())) {
                        // arrumar texto
                        LASemanticoUtils.adicionarErroSemantico(ident.start,
                                "identificador " + ident.getText() + " ja declarado anteriormente");
                    } else {
                        // verificacao do tipo
                        String strTipoVar = ctx.declaracao_local().variavel().tipo().getText();
                        TipoLA tipoVar;
                        switch (strTipoVar) {
                            case "inteiro":
                                tipoVar = TipoLA.INTEIRO;
                                tabela.adicionar(ident.getText(), tipoVar);
                                break;
                            case "literal":
                                tipoVar = TipoLA.LITERAL;
                                tabela.adicionar(ident.getText(), tipoVar);
                                break;
                            case "real":
                                tipoVar = TipoLA.REAL;
                                tabela.adicionar(ident.getText(), tipoVar);
                                break;
                            case "logico":
                                tipoVar = TipoLA.LOGICO;
                                tabela.adicionar(ident.getText(), tipoVar);
                                break;
                            default:
                                if ('^' == strTipoVar.charAt(0)) {
                                    tipoVar = TipoLA.ENDERECO;
                                    tabela.adicionar(ident.getText(), tipoVar);
                                } else {
                                    // se for um tipo declarado anteriormente (tipo customizado)
                                    if (tabela.existe(strTipoVar)) {
                                        TipoLA tipo_variavel_encontrada = tabela.verificar(strTipoVar);
                                        tipoVar = TipoLA.CUSTOMIZADO;
                                        if (tipo_variavel_encontrada == TipoLA.TIPO) {
                                            tabela.adicionar(ident.getText(), tipoVar, strTipoVar);
                                        } else {
                                            tabela.adicionar(ident.getText(), tipoVar, strTipoVar);
                                            LASemanticoUtils.adicionarErroSemantico(ident.start,
                                                    "tipo " + strTipoVar + " nao declarado");
                                        }
                                    } else {
                                        if (erro_tipo == 0) {
                                            tabela.adicionar(ident.getText(), TipoLA.INVALIDO);
                                            LASemanticoUtils.adicionarErroSemantico(ident.start,
                                                    "tipo " + strTipoVar + " nao declarado");
                                            erro_tipo++;
                                        }
                                    }
                                }
                                break;
                        }
                    }
                }
            } else {
                //declaracao de constante global
                if (ctx.declaracao_local().valor_constante() != null) {

                    LAParser.Declaracao_localContext dl = ctx.declaracao_local();
                    String nome_const = dl.IDENT().getText();
                    // verificacao do tipo
                    String strTipoVar = dl.tipo_basico().getText();
                    TipoLA tipoVar;
                    switch (strTipoVar) {
                        case "inteiro":
                            tipoVar = TipoLA.INTEIRO;
                            tabela.adicionar(nome_const, TipoLA.CONSTANTE, tipoVar.toString());
                            break;
                        case "literal":
                            tipoVar = TipoLA.LITERAL;
                            tabela.adicionar(nome_const, TipoLA.CONSTANTE, tipoVar.toString());
                            break;
                        case "real":
                            tipoVar = TipoLA.REAL;
                            tabela.adicionar(nome_const, TipoLA.CONSTANTE, tipoVar.toString());
                            break;
                        case "logico":
                            tipoVar = TipoLA.LOGICO;
                            tabela.adicionar(nome_const, TipoLA.CONSTANTE, tipoVar.toString());
                            break;
                        default:
                            if ('^' == strTipoVar.charAt(0)) {
                                tipoVar = TipoLA.ENDERECO;
                                tabela.adicionar(nome_const, TipoLA.CONSTANTE, tipoVar.toString());
                            } else {
                                // se for um tipo declarado anteriormente (tipo customizado)
                                if (tabela.existe(strTipoVar)) {
                                    TipoLA tipo_variavel_encontrada = tabela.verificar(strTipoVar);
                                    //tipoVar = TipoLA.CUSTOMIZADO;     //deixa de ser usado pois o tipo constante aproveita o campo
                                    if (tipo_variavel_encontrada == TipoLA.TIPO) {
                                        tabela.adicionar(nome_const, TipoLA.CONSTANTE, strTipoVar);
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
                }
            }
        }
        return super.visitDecl_local_global(ctx); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Void visitDeclaracao_local(LAParser.Declaracao_localContext ctx) {
        // cobrindo declare apenas, por hora

        return super.visitDeclaracao_local(ctx);
    }

    @Override
    public Void visitCmdLeia(LAParser.CmdLeiaContext ctx) {

        // talvez precise verificar se e ou nao ponteiro
        for (LAParser.IdentificadorContext ident : ctx.identificador()) {
            if (!(tabela.existe(ident.getText()))) {
                // arrumar texto
                LASemanticoUtils.adicionarErroSemantico(ident.start,
                        "identificador " + ident.getText() + " nao declarado");
            }
        }

        return super.visitCmdLeia(ctx);
    }

    @Override
    public Void visitCmdEscreva(LAParser.CmdEscrevaContext ctx) {
        for (LAParser.ExpressaoContext ident : ctx.expressao()) {
            for (LAParser.Termo_logicoContext tl : ident.termo_logico()) {
                for (LAParser.Fator_logicoContext fl : tl.fator_logico()) {
                    for (LAParser.Exp_aritmeticaContext ea : fl.parcela_logica().exp_relacional().exp_aritmetica()) {
                        for (LAParser.TermoContext te : ea.termo()) {
                            for (LAParser.FatorContext fa : te.fator()) {
                                for (LAParser.ParcelaContext pa : fa.parcela()) {
 
                                    if (pa.parcela_unario() != null) {
                                        //verificando apenas primeiro caso
                                        if (pa.parcela_unario().identificador() != null) {
                                            //System.out.println(pa.parcela_unario().identificador().getText());
                                            if (!tabela.existe(pa.parcela_unario().identificador().getText())) {
                                                LASemanticoUtils.adicionarErroSemantico(pa.start,
                                                        "identificador " + pa.parcela_unario().identificador().getText() + " nao declarado");
                                            }
                                        }
                                    } else {      //pa.parcela_nao_unario()

                                    }

                                }
                            }
                        }
                    }
                }
            }
        }
        return super.visitCmdEscreva(ctx); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Void visitDeclaracao_global(LAParser.Declaracao_globalContext ctx) {

        TipoLA novotipo;
        if (ctx.FUNCAO() != null) {
            novotipo = TipoLA.FUNCAO;
            tabela.novo_local();
        } else {
            novotipo = TipoLA.PROCEDIMENTO;
            tabela.novo_local();
        }
        List<TipoLA> parametros = new Vector<>();
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
                            TipoLA tipo_variavel_encontrada = tabela.verificar(strTipoRetornoFuncao);
                            retornoFuncao = TipoLA.CUSTOMIZADO;
                        } else {
                            LASemanticoUtils.adicionarErroSemantico(ctx.start,
                                    "tipo " + strTipoRetornoFuncao + " nao declarado");
                        }
                    }
                    break;
            }
        }

        int erro_tipo = 0;
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
                        } else {
                            if (erro_tipo == 0) {
                                LASemanticoUtils.adicionarErroSemantico(pa.start,
                                        "tipo " + strTipoVar + " nao declarado");
                                erro_tipo++;
                            }
                        }
                    }
                    break;
            }
            if (tipoVar != TipoLA.INVALIDO) {
                tabela.adicionar_local(nomeVar, tipoVar);
                parametros.add(tipoVar);

            }

        }
        // checar tipo dos parametros
        // System.out.println(parametros);

        tabela.adicionar_funcproc(ctx.IDENT().getText(), novotipo, parametros, retornoFuncao);

        return super.visitDeclaracao_global(ctx);
    }

    @Override
    public Void visitParcela_unario(LAParser.Parcela_unarioContext ctx) {

        // verificacao de identificadores
        if (ctx.IDENT() != null) {
            List<TipoLA> parametros = tabela.get_parametros_funcprop(ctx.IDENT().getText());

            System.out.println("\n\n" + parametros);
            System.out.println(ctx.IDENT().getText() + " " + parametros);
            // System.out.println("Tipo " + tabela.verificar(ctx.IDENT().getText()));

            if (ctx.expressao().size() != parametros.size()) {
                System.out.println("Incompatibilidade entre tamanho da expressão e argumentos");
                LASemanticoUtils.adicionarErroSemantico(ctx.start,
                        "incompatibilidade de parametros na chamada de " + ctx.IDENT().getText());
            } else {
                for (int i = 0; i < ctx.expressao().size(); i++) {

                    ParcelaContext expressao = ctx.expressao().get(i).termo_logico(0).fator_logico(0).parcela_logica()
                            .exp_relacional().exp_aritmetica(0).termo(0).fator(0).parcela(0);

                    // System.out.println("Parcela context: " + expressao);
                    // System.out.println("Expressão: " + ctx.expressao().get(i).getText());
                    TipoLA parcela;

                    if (expressao.getText().indexOf('(') > -1) {
                        parcela = tabela.getRetornoFuncao(expressao.getText().substring(0, expressao.getText().indexOf('(')));
                    } else {
                        parcela = tabela.verificar(expressao.getText());
                    }

                    if (parcela == null) {
                        System.out.println("Parcela nula");
                    } else if (!parametros.get(i).equals(parcela)) {
                        System.out.println(parametros.get(i) + " != " + parcela);
                        LASemanticoUtils.adicionarErroSemantico(expressao.start, "incompatibilidade de parametros na chamada de " + ctx.IDENT().getText());
                    }

                    // verificarTipo(tabela, ctx);
                }
            }

        }

        return super.visitParcela_unario(ctx);
    }

    @Override
    public Void visitCmdAtribuicao(LAParser.CmdAtribuicaoContext ctx) {

        TipoLA tipoAlvo = null;
        if (ctx.INICIO_ENDERECO() != null) {
            tipoAlvo = TipoLA.ENDERECO;
        } else {
            tipoAlvo = tabela.verificar(ctx.identificador().getText());
        }
        if (tipoAlvo == null) {
            LASemanticoUtils.adicionarErroSemantico(ctx.start,
                    "identificador " + ctx.identificador().getText() + " nao declarado");
        }
        TipoLA tipoRecebido = verificarTipo(tabela, ctx.expressao());

        if (tipoAlvo != tipoRecebido) {

            System.out.println(tipoAlvo + "    " + tipoRecebido);
            LASemanticoUtils.adicionarErroSemantico(ctx.start,
                    "atribuicao nao compativel para " + ctx.identificador().getText());
        }

        return super.visitCmdAtribuicao(ctx); //To change body of generated methods, choose Tools | Templates.
    }

    
}
