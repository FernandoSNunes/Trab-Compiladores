package br.ufscar.dc.compiladores.constdecomptrab1;

import java.io.IOException;
import java.io.FileWriter;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.Token;

public class Principal {
    public static void main(String[] args) {
        try {
            CharStream cs = CharStreams.fromFileName(args[0]);
            
            try (
                // arquivo de saida
                FileWriter myWriter = new FileWriter(args[1])) {
                Lexers lex = new Lexers(cs);
                Token t = null;

                // interrompe o loop caso seja encontrado um erro
                boolean continuar = true;

                while ((t = lex.nextToken()).getType() != Token.EOF && continuar) {
                    
                    // compara o token recebido com os seguintes identificadores
                    // caso seja encontrado um erro, exibe uma mensagem e encerra o loop
                    if ("ERRO".equals(Lexers.VOCABULARY.getDisplayName(t.getType()))){
                        myWriter.write("Linha " + lex.getLine() + ": " + t.getText() + " - simbolo nao identificado\n");
                        continuar = false;
                    }
                    else if ("ERRO_CADEIA".equals(Lexers.VOCABULARY.getDisplayName(t.getType()))){
                        myWriter.write("Linha " + lex.getLine() + ": cadeia literal nao fechada\n");
                        continuar = false;
                    }
                    else if ("ERRO_COMENTARIO".equals(Lexers.VOCABULARY.getDisplayName(t.getType()))){
                        myWriter.write("Linha " + lex.getLine() + ": comentario nao fechado\n");
                        continuar = false;
                    }
                    else if ("PALAVRA_CHAVE".equals(Lexers.VOCABULARY.getDisplayName(t.getType()))){
                        myWriter.write("<'" + t.getText() + "','" + t.getText() + "'>\n");
                    }
                    else if ("OP_REL".equals(Lexers.VOCABULARY.getDisplayName(t.getType()))){
                        myWriter.write("<'" + t.getText() + "','" + t.getText() + "'>\n");
                    }
                    else if ("OP_COMPR".equals(Lexers.VOCABULARY.getDisplayName(t.getType()))){
                        myWriter.write("<'" + t.getText() + "','" + t.getText() + "'>\n");
                    }
                    else if ("OP_ARIT".equals(Lexers.VOCABULARY.getDisplayName(t.getType()))){
                        myWriter.write("<'" + t.getText() + "','" + t.getText() + "'>\n");
                    }
                    // caso o token não seja a própria palavra identificada
                    // ou possua um único identificador definido na gramática g4
                    else
                        myWriter.write("<'" + t.getText() + "'," + Lexers.VOCABULARY.getDisplayName(t.getType()) + ">\n");
                }
                myWriter.close();
            }
        } catch (IOException ex) { }
    }
}
