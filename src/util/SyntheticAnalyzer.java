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
package util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import model.Token;

/**
 *
 * @author Gustavo Henrique.
 */
public class SyntheticAnalyzer {

    private HashMap<String, Node> hashMap;
    private HashMap<String, Node> hashMapProduction;
    private final ArrayList<Token> listTokens;
    private Grammar grammar;
    private int indexToken;
    private Token currentToken;
    private int amountErro;

    public SyntheticAnalyzer(ArrayList<Token> listTokens, Grammar grammar) {
        this.hashMap = new HashMap<>();
        this.listTokens = listTokens;
        this.grammar = grammar;
        this.indexToken = -1;
        this.amountErro = 0;
    }

    public void analyzerTokens() {
        System.out.println("Iniciando a Análise");
        if (listTokens != null) {
//            for (Iterator<Token> iterator = listTokens.iterator(); iterator.hasNext();) {
//                Token next = iterator.next();
//                Node node = grammar.getNode(next.getString());
//            }
            Node node = grammar.getStartNode();
//            for (Iterator<Token> iterator = listTokens.iterator(); iterator.hasNext();) {
//                Token next = iterator.next();
//                for (String[] production : node.getProductions()) {
//                    for (String string : production) {
//                        if (grammar.getNode(string) != null) {
//
//                        }
//                    }
//                }
//            }
            s(node, listTokens, grammar, 0);
        }
    }

    public boolean s(Node node, ArrayList<Token> listTokens, Grammar grammar, int i) {
        System.out.println(node.getValue());
        Node nodeAux;
        for (String[] production : node.getProductions()) {
            for (String string : production) {
                nodeAux = grammar.getNode(string);
                System.out.println("Node AUX " + string);
                if (nodeAux == null) {
                    if (string.equals(listTokens.get(i).getString()));
                } else {
                    System.out.println("Produções de " + nodeAux.getValue());
                    for (String string1 : nodeAux.getFirst()) {
                        System.out.println(string1);
                    }
                    if (!nodeAux.firstContains("")) {
                        System.out.println("Não contém Vazio");
                        if (nodeAux.firstContains(string)) {
                            System.out.println("Contém");
                        }
                        break;
                    }
                    System.out.println("Contém Vazio");
                }
            }
            System.out.println("Próxima produção");
        }
        return false;
    }

    private void eat() {
        if (indexToken + 1 < listTokens.size()) {
            indexToken++;
            this.currentToken = listTokens.get(indexToken);
        }
    }

    private void metch() {

    }

    private void panicMode() {
        publishErro();
        while (!currentToken.getString().matches(";")) { // this should be changed later for characters synchronizer all
            eat(); // consuming the tokens up until find a character synchronizer
        }
    }

    private void publishErro() {
        amountErro++;
        //here will be the time write on file
        System.out.println("There an error in token " + currentToken.getString() + " type " + currentToken.getType());
    }
}
