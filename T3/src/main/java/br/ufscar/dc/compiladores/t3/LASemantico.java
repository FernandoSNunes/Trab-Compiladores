package br.ufscar.dc.compiladores.t3;

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

    //criar o escopo local da main
    @Override
    public Void visitCorpo(LAParser.CorpoContext ctx) {
        tabela.novo_local();

        //tabela.fim_local();
        return super.visitCorpo(ctx); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Void visitDeclaracao_local(LAParser.Declaracao_localContext ctx) {
        //cobrindo declare apenas, por hora
        if (ctx.variavel() != null) {
            int erro_tipo = 0;

            for (LAParser.IdentificadorContext ident : ctx.variavel().identificador()) {

                //verifica se a variavel ja existe
                if (tabela.existe(ident.getText())) {
                    //arrumar texto
                    LASemanticoUtils.adicionarErroSemantico(ident.start, "identificador " + ident.getText() + " ja declarado anteriormente");
                } else {
                    //verificacao do tipo
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
                                //se for um tipo declarado anteriormente
                                if (tabela.existe(strTipoVar)) {
                                    TipoLA tipo_variavel_encontrada = tabela.verificar(strTipoVar);
                                    tipoVar = TipoLA.CUSTOMIZADO;
                                    if (tipo_variavel_encontrada == TipoLA.TIPO) {
                                        tabela.adicionar_local(ident.getText(), tipoVar, strTipoVar);
                                    } else {
                                        LASemanticoUtils.adicionarErroSemantico(ident.start, "tipo " + strTipoVar + " nao declarado");
                                    }
                                } else {
                                    if (erro_tipo == 0) {
                                        LASemanticoUtils.adicionarErroSemantico(ident.start, "tipo " + strTipoVar + " nao declarado");
                                        erro_tipo++;
                                    }
                                }
                            }
                            break;
                    }
                }
            }
        }

        return super.visitDeclaracao_local(ctx); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Void visitCmdLeia(LAParser.CmdLeiaContext ctx) {
        //talvez precise verificar se e ou nao ponteiro
        for (LAParser.IdentificadorContext ident : ctx.identificador()) {
            if (!(tabela.existe(ident.getText()))) {
                //arrumar texto
                LASemanticoUtils.adicionarErroSemantico(ident.start, "identificador  " + ident.getText() + " nao declarado");
            }
        }

        return super.visitCmdLeia(ctx); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Void visitDeclaracao_global(LAParser.Declaracao_globalContext ctx) {

        TipoLA novotipo;

        if (ctx.FUNCAO() != null) {
            novotipo = TipoLA.FUNCAO;
        } else {      //procedimento
            novotipo = TipoLA.PROCEDIMENTO;
        }
        List<TipoLA> parametros = new Vector<>();

        int erro_tipo = 0;
        for (LAParser.ParametroContext pa : ctx.parametros().parametro()) {

            String strTipoVar = pa.tipo_estendido().getText();
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
                        //se for um tipo declarado anteriormente
                        if (tabela.existe(strTipoVar)) {
                            TipoLA tipo_variavel_encontrada = tabela.verificar(strTipoVar);
                            tipoVar = TipoLA.CUSTOMIZADO;
                        } else {
                            if (erro_tipo == 0) {
                                LASemanticoUtils.adicionarErroSemantico(pa.start, "tipo " + strTipoVar + " nao declarado");
                                erro_tipo++;
                            }
                        }
                    }
                    break;
            }
            if (tipoVar != TipoLA.INVALIDO) {

                parametros.add(tipoVar);

            }

        }
        //checar tipo dos parametros
        // System.out.println(parametros);

        tabela.adicionar_funcproc(ctx.IDENT().getText(), novotipo, parametros);

        return super.visitDeclaracao_global(ctx); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Void visitParcela_unario(LAParser.Parcela_unarioContext ctx) {

        //verificacao de funcoes
        if (ctx.IDENT() != null) {
            List<TipoLA> parametros = tabela.get_parametros_funcprop(ctx.IDENT().getText());
            System.out.println("\n\n " + ctx.IDENT().getText() + " " + parametros);
            System.out.println("Tipo " + tabela.verificar(ctx.IDENT().getText()));
            for (int i = 0; i < ctx.expressao().size(); i++) {

                String a = ctx.expressao(i).termo_logico(0).fator_logico(0).parcela_logica().exp_relacional().exp_aritmetica(0).termo(0).fator(0).parcela(0).getText();
                TipoLA b = tabela.verificar(a);
                String c;
                if (b == null) {
                    c = "";
                } else {
                    c = b.toString();
                }

                System.out.println(
                        a + " " + c
                );

//                verificarTipo(tabela, ctx);
            }

        }

        return super.visitParcela_unario(ctx); //To change body of generated methods, choose Tools | Templates.
    }

    /*
    @Override
    public Void visitDeclaracao(LAParser.DeclaracaoContext ctx) {
        String nomeVar = ctx.VARIAVEL().getText();
        String strTipoVar = ctx.TIPO_VAR().getText();
        TipoLA tipoVar = TipoLA.INVALIDO;
        switch (strTipoVar) {
            case "INTEIRO":
                tipoVar = TipoLA.INTEIRO;
                break;
            case "REAL":
                tipoVar = TipoLA.REAL;
                break;
            default:
                // Nunca irá acontecer, pois o analisador sintático
                // não permite
                break;
        }

        // Verificar se a variável já foi declarada
        if (tabela.existe(nomeVar)) {
            LASemanticoUtils.adicionarErroSemantico(ctx.VARIAVEL().getSymbol(), "Variável " + nomeVar + " já existe");
        } else {
            tabela.adicionar(nomeVar, tipoVar);
        }

        return super.visitDeclaracao(ctx);
    }

    @Override
    public Void visitComandoAtribuicao(LAParser.ComandoAtribuicaoContext ctx) {
        TipoLA tipoExpressao = LASemanticoUtils.verificarTipo(tabela, ctx.expressaoAritmetica());
        if (tipoExpressao != TipoLA.INVALIDO) {
            String nomeVar = ctx.VARIAVEL().getText();
            if (!tabela.existe(nomeVar)) {
                LASemanticoUtils.adicionarErroSemantico(ctx.VARIAVEL().getSymbol(), "Variável " + nomeVar + " não foi declarada antes do uso");
            } else {
                TipoLA tipoVariavel = LASemanticoUtils.verificarTipo(tabela, nomeVar);
                if (tipoVariavel != tipoExpressao) {
                    LASemanticoUtils.adicionarErroSemantico(ctx.VARIAVEL().getSymbol(), "Tipo da variável " + nomeVar + " não é compatível com o tipo da expressão");
                }
            }
        }
        return super.visitComandoAtribuicao(ctx);
    }

    @Override
    public Void visitComandoEntrada(LAParser.ComandoEntradaContext ctx) {
        String nomeVar = ctx.VARIAVEL().getText();
        if (!tabela.existe(nomeVar)) {
            LASemanticoUtils.adicionarErroSemantico(ctx.VARIAVEL().getSymbol(), "Variável " + nomeVar + " não foi declarada antes do uso");
        }
        return super.visitComandoEntrada(ctx);
    }

    @Override
    public Void visitExpressaoAritmetica(LAParser.ExpressaoAritmeticaContext ctx) {
        LASemanticoUtils.verificarTipo(tabela, ctx);
        return super.visitExpressaoAritmetica(ctx);
    }
     */
//    @Override
//    public Void visitCmd(LAParser.CmdContext ctx) {
//        return super.visitCmd(ctx); //To change body of generated methods, choose Tools | Templates.
//    }
//    
//    
//    
//
//    @Override
//    public Void visitParcela_nao_unario(LAParser.Parcela_nao_unarioContext ctx) {
//        
//        if (ctx.CADEIA()!= null)
//            System.out.println(ctx.CADEIA());
//        
//        return super.visitParcela_nao_unario(ctx); //To change body of generated methods, choose Tools | Templates.
//    }
}