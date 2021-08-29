/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.ufscar.dc.compiladores.constdecomptrab1;

import java.io.IOException;
import java.io.FileWriter;   // Import the FileWriter class
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
                boolean continuar = true;
                while ((t = lex.nextToken()).getType() != Token.EOF && continuar) {
                    
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
                    else
                        myWriter.write("<'" + t.getText() + "'," + Lexers.VOCABULARY.getDisplayName(t.getType()) + ">\n");
                }
                myWriter.close();
            }
        } catch (IOException ex) {
        }
    }
}
