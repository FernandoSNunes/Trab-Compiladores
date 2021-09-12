package br.ufscar.dc.compiladores.trab.part2;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;

public class Principal {

    public static void main(String args[]) throws IOException {
        CharStream cs = CharStreams.fromFileName(args[0]);
        LALexer lexer = new LALexer(cs);

        // Descomentar para depurar o Léxico
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

            if (continuar) {
                lexer.reset();
                CommonTokenStream tokens = new CommonTokenStream(lexer);
                LAParser parser = new LAParser(tokens);

                //remover os erros padroes
                parser.removeErrorListeners();
                // Registrar o error lister personalizado aqui
                MyCustomErrorListener mcel = new MyCustomErrorListener(pw);
                parser.addErrorListener(mcel);

                parser.programa();
            }
        pw.println("Fim da compilacao");
        }
    }
}
