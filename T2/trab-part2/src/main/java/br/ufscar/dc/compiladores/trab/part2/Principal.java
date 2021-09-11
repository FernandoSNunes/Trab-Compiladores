package br.ufscar.dc.compiladores.trab.part2;

import java.io.IOException;
import java.io.FileWriter;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Token;

public class Principal {

    public static void main(String args[]) throws IOException {
        CharStream cs = CharStreams.fromFileName(args[0]);
        AlgumaLexer lexer = new AlgumaLexer(cs);

        // Descomentar para depurar o Léxico
        Token t = null;
        try (
                // arquivo de saida
                FileWriter myWriter = new FileWriter(args[1])) {
            while ((t = lexer.nextToken()).getType() != Token.EOF) {
                myWriter.write("<" + AlgumaLexer.VOCABULARY.getDisplayName(t.getType()) + "," + t.getText() + ">\n");
            }
            CommonTokenStream tokens = new CommonTokenStream(lexer);
            AlgumaParser parser = new AlgumaParser(tokens);
            parser.programa();
        myWriter.close();
        }
    }
}
