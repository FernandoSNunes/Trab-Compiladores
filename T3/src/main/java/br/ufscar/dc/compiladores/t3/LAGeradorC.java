package br.ufscar.dc.compiladores.t3;

public class LAGeradorC extends LABaseVisitor<Void>{
    StringBuilder saida;
    TabelaDeSimbolos tabela;
    
    public LAGeradorC(){
        saida = new StringBuilder();
        this.tabela = new TabelaDeSimbolos();
    }

    @Override
    public Void visitPrograma(LAParser.ProgramaContext ctx) {
        saida.append("#include <stdio.h>\n");
        saida.append("#include <stdlib.h>\n");
        
        ctx.declaracoes().decl_local_global().forEach(dec -> visitDecl_local_global(dec));
        
        saida.append("int main(){\n");
        
        if(ctx.corpo().declaracao_local() != null){
            ctx.corpo().declaracao_local().forEach(decl -> visitDeclaracao_local(decl));
        }
        if(ctx.corpo().cmd() != null){
            ctx.corpo().cmd().forEach(cmd -> visitCmd(cmd));
        }
        
        
        saida.append("return 0;\n}");
        return null;
    }

    @Override
    public Void visitDecl_local_global(LAParser.Decl_local_globalContext ctx) {
        String nomeVar = ctx.declaracao_local().variavel().identificador(0).IDENT(0).getText();
        String tipoVar = ctx.declaracao_local().variavel().tipo().getText();
        switch(tipoVar){
            case "inteiro": tipoVar = "int"; break;
            case "real": tipoVar = "float"; break;
            case "literal": tipoVar = "char[500]"; break;
        }
        
        saida.append(tipoVar + " " + nomeVar + ";\n");
        return null;
    }

    @Override
    public Void visitDeclaracao_local(LAParser.Declaracao_localContext ctx) {
        String nomeVar = ctx.variavel().identificador(0).IDENT(0).getText();
        String tipoVar = ctx.variavel().tipo().getText();
        TabelaDeSimbolos.TipoLA tipoVariavel = TabelaDeSimbolos.TipoLA.INVALIDO;
        switch(tipoVar){
            case "inteiro": 
                tipoVar = "int";
                tipoVariavel = TabelaDeSimbolos.TipoLA.INTEIRO;
                break;
            case "real":
                tipoVar = "float";
                tipoVariavel = TabelaDeSimbolos.TipoLA.REAL;
                break;
            case "literal":
                tipoVar = "char";
                tipoVariavel = TabelaDeSimbolos.TipoLA.LITERAL;
                break;
        }
        tabela.adicionar(nomeVar, tipoVariavel);
        
        if(tipoVariavel != TabelaDeSimbolos.TipoLA.LITERAL) 
            saida.append(tipoVar + " " + nomeVar + ";\n");
        else
            saida.append(tipoVar + " " + nomeVar + "[500];\n");
        return null;
    }

    @Override
    public Void visitCmd(LAParser.CmdContext ctx) {
        if(ctx.cmdLeia() != null) visitCmdLeia(ctx.cmdLeia());
        else if(ctx.cmdEscreva() != null) visitCmdEscreva(ctx.cmdEscreva());
        else if(ctx.cmdEnquanto() != null) visitCmdEnquanto(ctx.cmdEnquanto());
        else if(ctx.cmdAtribuicao() != null) visitCmdAtribuicao(ctx.cmdAtribuicao());
        else/*cmdRetorne*/ visitCmdRetorne(ctx.cmdRetorne());
        return null;
    }

    @Override
    public Void visitCmdLeia(LAParser.CmdLeiaContext ctx) {
        String nomeVar = ctx.identificador(0).IDENT(0).getText();
        TabelaDeSimbolos.TipoLA tipoVar = LASemanticoUtils.verificarTipo(tabela, nomeVar);
        String aux = "";
        switch(tipoVar){
                case INTEIRO: aux = "%d"; break;
                case REAL: aux = "%f"; break;
        }
        
        if(tipoVar != TabelaDeSimbolos.TipoLA.LITERAL)
            saida.append("scanf(\""+ aux +"\", &" + nomeVar+");\n");
        else
            saida.append("gets(" + nomeVar + ");\n");
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
            if(ident.getText().contains("\"")){
                if(ctx.expressao(1) == null){
                    saida.append(ident.getText());
                }
                else{
                    System.out.println(ident.getText().substring(0, ident.getText().length()-1));
                    String aux = ident.getText().substring(0, ident.getText().length()-1);
                    saida.append(aux);
                }
            }
            else{
                String nomeVar = ident.getText();
                TabelaDeSimbolos.TipoLA tipoVar = LASemanticoUtils.verificarTipo(tabela, nomeVar);
                String aux = "";
                switch(tipoVar){
                        case INTEIRO: aux = "%d"; break;
                        case REAL: aux = "%f"; break;
                        case LITERAL: aux = "%s"; break;
                }
                if(ctx.expressao(1) == null){
                    saida.append("\"" + aux + "\"");
                    Variaveis += "," + nomeVar;
                }
                else{
                    qtdVariaveis++;
                    Variaveis += "," + nomeVar;
                    saida.append(aux);
                }
                
            }
        }
        if(qtdVariaveis>0) saida.append("\"" + Variaveis + ");\n");
        else saida.append(Variaveis + ");\n");
        return null;
    }
    
    
    
    
    @Override
    public Void visitCmdAtribuicao(LAParser.CmdAtribuicaoContext ctx) {
        
        
        return null;
    }
    
    
    
    
    
    
    
    
    
    
}
