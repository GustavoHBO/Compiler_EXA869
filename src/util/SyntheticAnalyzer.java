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
    private int i = 0;

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
            s(node);
        }
    }

    public void s(Node node) {
        System.out.println(node.getValue());
        Node nodeAux;
        if (i < listTokens.size()) {
            for (int k = 0; k < node.getProductions().length; k++) {
                String[] production = node.getProductions()[k];
                for (int j = 0; j < production.length; j++) {
                    nodeAux = grammar.getNode(production[j]);
                    System.out.println("\nNode AUX \"" + production[j] + "\"");
//                System.out.println("\nToken Atual : \"" + listTokens.get(i).getString() + "\"");
                    if (i >= listTokens.size()) {
                        return;
                    }
                    for (; i < listTokens.size(); i++) {
//                    System.out.println(listTokens.get(i).getString());
                        if (listTokens.get(i).getType() != TokenEnum.SPACE && listTokens.get(i).getType() != TokenEnum.BLOCK_COMMENT && listTokens.get(i).getType() != TokenEnum.LINE_COMMENT && listTokens.get(i).getType() != TokenEnum.SYMBOL) {
                            break;
                        }
                        System.out.println("Pulando Token");
                    }
                    if (i >= listTokens.size()) {
                        return;
                    }
                    System.out.println("Token Atual : \"" + listTokens.get(i).getString() + "\" " + listTokens.get(i).getType());
                    if (nodeAux == null) {
//                    System.out.println("Node Null");
                        if (production[j].equals(listTokens.get(i).getString())) {
                            System.out.println("Found: " + production[j]);
                            i++;
                            if (j + 1 >= production.length) {
                                k = node.getProductions().length;
                            }
                        } else if (listTokens.get(i).getType() == TokenEnum.IDENTIFIER && production[j].equals("Identifier")) {
                            System.out.println("Found: " + production[j]);
                            i++;
                            if (j + 1 >= production.length) {
                                k = node.getProductions().length;
                            }
                        } else if (listTokens.get(i).getType() == TokenEnum.NUMBER && production[j].equals("NumberTerminal")) {
                            System.out.println("Found: " + production[j]);
                            i++;
                            if (j + 1 >= production.length) {
                                k = node.getProductions().length;
                            }
                        } else if (listTokens.get(i).getType() == TokenEnum.STRING && production[j].equals("StringLiteral")) {
                            System.out.println("Found: " + production[j]);
                            i++;
                            if (j + 1 >= production.length) {
                                k = node.getProductions().length;
                            }
                        }
                    } else {
//                    System.out.println("Produções de " + nodeAux.getValue());
//                    for (String string1 : nodeAux.getFirst()) {
//                        System.out.println(string1);
//                    }

                        if (nodeAux.firstContains("NumberTerminal") && listTokens.get(i).getType() == TokenEnum.NUMBER) {
//                        System.out.println("Contém Numero");
                            s(nodeAux);
                            System.out.println("Retornando do loop");
                        } else if (nodeAux.firstContains("Identifier") && listTokens.get(i).getType() == TokenEnum.IDENTIFIER) {
//                        System.out.println("Contém Numero");
                            s(nodeAux);
                            System.out.println("Retornando do loop");
                        } else if (nodeAux.firstContains("StringLiteral") && listTokens.get(i).getType() == TokenEnum.STRING) {
//                        System.out.println("Contém Numero");
                            s(nodeAux);
                            System.out.println("Retornando do loop");
                        } else if (nodeAux.firstContains(listTokens.get(i).getString())) {
//                        System.out.println("Contém");
                            s(nodeAux);
                            System.out.println("Retornando do loop");
                        } else {
//                        System.out.println("Não Contém!");
                            if (!nodeAux.firstContains("")) {
//                            System.out.println("Não contém Vazio");
                                if (j > 0) {
                                    k = 100;
                                }
                                break;
                            }
                        }
                    }
                }
                System.out.println("Próxima produção");
            }
            if (i >= listTokens.size()) {
                System.out.println("Terminou");
            }
        }
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
