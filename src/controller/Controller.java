/* 
 * Copyright (C) 2018 Gustavo Henrique and Marcus Aldrey
 *
 * This program is free software: you can redistribute it and/or modify
 * it under the terms of the GNU General Public License as published by
 * the Free Software Foundation, either version 3 of the License, or
 * (at your option) any later version.
 *
 * This program is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 * GNU General Public License for more details.
 *
 * You should have received a copy of the GNU General Public License
 * along with this program.  If not, see <http://www.gnu.org/licenses/>.
 *
 * Contact us:	Gustavo Henrique	-	ghboliveira@hotmail.com
 * 		Marcos Vinícius		-	marcosviniciuscl@outlook.com
 * 		Marcus Aldrey		-	marcusaldrey@gmail.com
 *
 * See the original project in: <https://github.com/GustavoHBO/Compiler_EXA869>.
 */
package controller;

import exception.FileNotLexicalAnalyzerException;
import exception.FileNotSavedException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import model.Token;
import util.Grammar;
import util.LexicalAnalyzer;
import util.Node;

/**
 * This controller manage the layers of analyzer from the compiler. Such file
 * will have yourself controller, as such analyzer not need be sync between
 * other files the analyze will run alone.
 *
 * @author Gustavo Henrique.
 */
public class Controller {

    private final String fileName;
    private final String filePath;
    private LexicalAnalyzer lexAnalyzer;

    /**
     * The controller for file, it will can running the analyzer the files.
     *
     * @param filePath - File path.
     * @param fileName - File name.
     * @throws java.io.FileNotFoundException - If the file not be founded.
     */
    public Controller(String filePath, String fileName) throws FileNotFoundException {
        this.filePath = filePath;
        this.fileName = fileName;
        this.lexAnalyzer = getLexicalAnalyzer();
    }

    /*-------------------------------------------------Public Methods-----------------------------------------------------------------*/
    /**
     * Run the analyzer from the file creating the list of tokens.
     *
     * @throws FileNotFoundException - Case the file not exists.
     */
    public void analyzeFile() throws FileNotFoundException {
        this.lexAnalyzer.analyzeFile();
    }

    /**
     * Get the list of tokens from actual file.
     *
     * @return listTokens - List of tokens generated from the lexical analyzer.
     * @throws java.io.FileNotFoundException - Case the file analyzer not
     * exists.
     */
    public ArrayList<Token> getTokens() throws FileNotFoundException {
        return lexAnalyzer.getTokens();
    }

    /**
     * Save the list of tokens from the actual file, case not exist will throw
     * the exception to run the analyzer lexical first. If the list of tokens
     * not be saved will throw the exception FileNotSavedException.
     *
     * @throws FileNotSavedException - Case the file not be saved.
     * @throws FileNotLexicalAnalyzerException - Case the list of tokens not
     * exists, run the method analyzeFile before.
     */
    public void saveTokens() throws FileNotSavedException, FileNotLexicalAnalyzerException {
        saveTokensFile();
    }

    /*-------------------------------------------------Private Methods-----------------------------------------------------------------*/
    /*--------------Lexical Methods------------------------*/
    /**
     * Using the file path for read all archives on folder and analyze these
     * files.
     *
     * @throws java.io.IOException - If the file not be read.
     * @throws java.io.FileNotFoundException - If the file not exist.
     * @return ArrayList Token - List with the tokens of the file.
     */
    private LexicalAnalyzer getLexicalAnalyzer() throws FileNotFoundException {
        lexAnalyzer = new LexicalAnalyzer(filePath, fileName);
        lexAnalyzer.analyzeFile();
        return new LexicalAnalyzer(filePath, fileName);
    }

    /**
     * Save the list of tokens in a file with extension .lex
     *
     * @throws exception.FileNotSavedException - If the file not be saved.
     */
    private void saveTokensFile() throws FileNotSavedException, FileNotLexicalAnalyzerException {
        this.lexAnalyzer.saveFile();
    }

    /*--------------Synthetic Analyzer Methods------------------------*/
    private HashMap<String, Node> getGrammar() {
        return null;
    }

    /*-------------- Methods for Debug ------------------------*/
    public void debugGrammarFirst(String string) {
        Grammar grammar = new Grammar();
        Node node = grammar.getNode(string);
        if (node == null) {
            System.out.println("Não existe alguma regra de produção para o produtor definido!");
        } else {
            System.out.println("Exibindo o conjunto first!");
            System.out.println("Tamanho :" + node.getFirst().length);
            for (String str : node.getFirst()) {
                System.out.println(str);
            }
            System.out.println("Finalizando a exibição!");
        }
    }

    public void debugPrintGrammar() {
        Grammar grammar = new Grammar();
        grammar.getGrammar().entrySet().forEach((entry) -> {
            System.out.println(entry.getKey());
        });
    }

    public void debugGetFollow(String p) {
        Grammar grammar = new Grammar();
        Node node = grammar.getNode(p);
        System.out.println("Exibindo o conjuto follow da produção!");
        for (String str : node.getFollow()) {
            System.out.println(str);
        }
    }
    
    public void debugAddFirst(String str){
        Grammar grammar = new Grammar();
        Node node, nodeAux;
        node = grammar.getNode(str);
        nodeAux = new Node(str);
        node.addFirst("a");
        node.addFirst("b");
        node.addFirst("c");
        nodeAux.addFirst(node.getFirst());
        for (String string : nodeAux.getFirst()) {
            System.out.println(string);
        }
    }
}
