package br.ufscar.dc.compiladores.t4;

import br.ufscar.dc.compiladores.t4.CFParser.ProgramaContext;

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
        CFLexer lexer = new CFLexer(cs);

        Token t = null;
        boolean hasError = false;

        while ((t = lexer.nextToken()).getType() != Token.EOF && !hasError) {
            if ("ERRO".equals(CFLexer.VOCABULARY.getDisplayName(t.getType()))) {
                pw.println("Linha " + t.getLine() + ": " + t.getText() + " - simbolo nao identificado");
                hasError = true;
            }
        }

        return hasError;
    }

    public static boolean hasErrosSintaticos(String args[], PrintWriter pw) throws IOException {
        CharStream cs = CharStreams.fromFileName(args[0]);
        CFLexer lexer = new CFLexer(cs);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CFParser parser = new CFParser(tokens);

        boolean hasError = false;

        parser.removeErrorListeners();
        CFSintatico mcel = new CFSintatico(pw);
        parser.addErrorListener(mcel);

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
        CFLexer lexer = new CFLexer(cs);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        CFParser parser = new CFParser(tokens);
        ProgramaContext arvore = parser.programa();
        CFSemantico as = new CFSemantico();
        as.visitPrograma(arvore);
        CFSemantico.errosSemanticos.forEach((s) -> pw.println(s));

        return !CFSemantico.errosSemanticos.isEmpty();
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
                       CharStream cs = CharStreams.fromFileName(args[0]);
                       CFLexer lexer = new CFLexer(cs);
                       CommonTokenStream tokens = new CommonTokenStream(lexer);
                       CFParser parser = new CFParser(tokens);
                       ProgramaContext arvore = parser.programa();
                       CFSemantico as = new CFSemantico();
                       as.visitPrograma(arvore);
                       CFGerador gerador = new CFGerador();
                       gerador.visitPrograma(arvore);
                       try{
                           pw.print(gerador.saida.toString());
                       }
                       catch(Exception e){
                           e.printStackTrace();
                       }
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