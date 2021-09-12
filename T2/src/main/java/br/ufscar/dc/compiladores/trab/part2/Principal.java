package br.ufscar.dc.compiladores.trab.part2;

import java.io.File;    
import java.io.IOException;
import java.io.PrintWriter;     //biblioteca 
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;

public class Principal {

    public static void main(String args[]) throws IOException {
        CharStream cs = CharStreams.fromFileName(args[0]);
        LALexer lexer = new LALexer(cs);

        //parte do trabalho 1, verifica erros lexicos
        Token t = null;
        try (
                // arquivo de saida
            PrintWriter pw = new PrintWriter(new File(args[1]))) {
            boolean continuar = true;
            while ((t = lexer.nextToken()).getType() != Token.EOF && continuar) {
                //erros na parte do trab 1
                if ("ERRO".equals(LALexer.VOCABULARY.getDisplayName(t.getType()))) {
                    pw.println("Linha " + t.getLine() + ": " + t.getText() + " - simbolo nao identificado");
                    continuar = false;
                } else if ("ERRO_CADEIA".equals(LALexer.VOCABULARY.getDisplayName(t.getType()))) {
                    pw.println("Linha " + t.getLine() + ": cadeia literal nao fechada");
                    continuar = false;
                } else if ("ERRO_COMENTARIO".equals(LALexer.VOCABULARY.getDisplayName(t.getType()))) {
                    pw.println("Linha " + t.getLine() + ": comentario nao fechado");
                    continuar = false;
                }

                //pw.println("<" + LALexer.VOCABULARY.getDisplayName(t.getType()) + "," + t.getText() + ">");
            }
            
            //para caso ja tenha encontrado erros lexicos
            if (continuar) {
                //reinicia a lista de tokens para analisar a sintaxe
                lexer.reset();
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                LAParser parser = new LAParser(tokens);

                //remover os erros padroes do terminal
                parser.removeErrorListeners();
                // Registrar o error lister personalizado aqui
                MyCustomErrorListener mcel = new MyCustomErrorListener(pw);
                parser.addErrorListener(mcel);

                parser.programa();
            }
            //necessario pros testes disponibilizados pelo professor
        pw.println("Fim da compilacao");
        }
    }
}
