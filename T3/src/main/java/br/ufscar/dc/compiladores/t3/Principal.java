package br.ufscar.dc.compiladores.t3;

import br.ufscar.dc.compiladores.t3.LAParser.ProgramaContext;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;

public class Principal {
    public static boolean hasErrosLexicos(String args[], PrintWriter pw) throws IOException {
        CharStream cs = CharStreams.fromFileName(args[0]);
        LALexer lexer = new LALexer(cs);

        Token t = null;
        boolean hasError = false;

        while ((t = lexer.nextToken()).getType() != Token.EOF && !hasError) {
            if ("ERRO".equals(LALexer.VOCABULARY.getDisplayName(t.getType()))) {
                pw.println("Linha " + t.getLine() + ": " + t.getText() + " - simbolo nao identificado");
                hasError = true;
            } else if ("ERRO_CADEIA".equals(LALexer.VOCABULARY.getDisplayName(t.getType()))) {
                pw.println("Linha " + t.getLine() + ": cadeia literal nao fechada");
                hasError = true;
            } else if ("ERRO_COMENTARIO".equals(LALexer.VOCABULARY.getDisplayName(t.getType()))) {
                pw.println("Linha " + t.getLine() + ": comentario nao fechado");
                hasError = true;
            } else {
                // para rodar os casos de testes léxicos,
                // descomentar essa linha e a linha de "Fim da compilacao" na função principal
                // pw.println("<'" + t.getText() + "'," + LALexer.VOCABULARY.getDisplayName(t.getType()) + ">");
            }
        }

        return hasError;
    }

    public static boolean hasErrosSintaticos(String args[], PrintWriter pw) throws IOException {
        CharStream cs = CharStreams.fromFileName(args[0]);
        LALexer lexer = new LALexer(cs);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LAParser parser = new LAParser(tokens);

        boolean hasError = false;

        parser.removeErrorListeners();
        LASintatico mcel = new LASintatico(pw);
        parser.addErrorListener(mcel);

        // tratamento de erros sintáticos agora é feito com exceções,
        // dessa forma é mais prático para o erro "subir" de instância
        try {
            parser.programa();
        } catch (Exception e) {
            pw.println(e.getMessage());
            hasError = true;
        }

        return hasError;
    }

    public static boolean hasErrosSemanticos(String args[], PrintWriter pw) throws IOException {
        CharStream cs = CharStreams.fromFileName(args[0]);
        LALexer lexer = new LALexer(cs);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LAParser parser = new LAParser(tokens);
        ProgramaContext arvore = parser.programa();
        LASemantico as = new LASemantico();
        as.visitPrograma(arvore);
        LASemanticoUtils.errosSemanticos.forEach((s) -> pw.println(s));

        // retorna false se a lista de erros semanticos estiver vazia
        // ou seja, false = não há erros
        return !LASemanticoUtils.errosSemanticos.isEmpty();
    }

    public static void main(String args[]) throws IOException {
        try (PrintWriter pw = new PrintWriter(new File(args[1]))) {
            boolean hasErrosLexicos = hasErrosLexicos(args, pw);
            boolean hasErrosSintaticos = false;
            boolean hasErrosSemanticos = false;

            if (!hasErrosLexicos) {
                hasErrosSintaticos = hasErrosSintaticos(args, pw);

                if (!hasErrosSintaticos) {
                    hasErrosSemanticos = hasErrosSemanticos(args, pw);

                    if (!hasErrosSemanticos) {
                        // gerar código C
                    }
                }
            }

            if (hasErrosLexicos || hasErrosSintaticos || hasErrosSemanticos) {
                pw.println("Fim da compilacao");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}