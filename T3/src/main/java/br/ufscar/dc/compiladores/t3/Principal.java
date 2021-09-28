
package br.ufscar.dc.compiladores.t3;

import br.ufscar.dc.compiladores.t3.LAParser.ProgramaContext;
import java.io.IOException;
import java.io.PrintWriter;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;

public class Principal {
    public static void main(String args[]) throws IOException {
        CharStream cs = CharStreams.fromFileName(args[0]);
        LALexer lexer = new LALexer(cs);
        CommonTokenStream tokens = new CommonTokenStream(lexer);
        LAParser parser = new LAParser(tokens);
        ProgramaContext arvore = parser.programa();
        LASemantico as = new LASemantico();
        as.visitPrograma(arvore);
        LASemanticoUtils.errosSemanticos.forEach((s) -> System.out.println(s));
    }
}