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

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Observable;
import java.util.Observer;

/**
 *
 * @author gustavo
 */
public final class Grammar implements IGrammar, Observer {

    private HashMap<String, Node> grammar;
    private String startSymbol;

    public Grammar() {
        this.grammar = getGrammar();
    }

    @Override
    public HashMap<String, Node> getGrammar() {
        String[][] productions;
        if (grammar == null || grammar.isEmpty()) {
            grammar = new HashMap<>();
            startSymbol = "<Program>";
            productions = new String[][]{{"true"}, {"false"}, {"StringLiteral"}, {"NumberTerminal"}};
            addProduction("<Value>", productions);
            productions = new String[][]{{"extends", "Identifier"}, {""}};
            addProduction("<Extends>", productions);
            productions = new String[][]{{"string"}, {"int"}, {"float"}, {"bool"}, {"void"}, {"Identifier"}};
            addProduction("<TypesRunTime>", productions);
            productions = new String[][]{{"<If-Block>", "<Code Statements>"}, {"<Looping-Block>", "<Code Statements>"}, {"<Line Code>", ";", "<Code Statements>"}, {"<Variable>", "<Code Statements>"}, {""}};
            addProduction("<Code Statements>", productions);
            productions = new String[][]{{",", "<Initial Value>", "<Argument>"}, {""}};
            addProduction("<Argument>", productions);
            productions = new String[][]{{"<Initial Value>", "<Argument>"}, {""}};
            addProduction("<Arguments>", productions);
            productions = new String[][]{{"<Negate Exp>", "*", "<Expression>"}, {"<Negate Exp>", "/", "<Expression>"}, {"<Negate Exp>"}};
            addProduction("<Mult Exp>", productions);
            productions = new String[][]{{"(", "<Expr Arit>", ")"}, {"<Expr Arit>"}};
            addProduction("<Expression>", productions);
            productions = new String[][]{{"<Declaration>", "<Parameter>"}, {""}};
            addProduction("<Parameters>", productions);
            productions = new String[][]{{"<TypesRunTime>", "<Valid Identifier>"}};
            addProduction("<Declaration>", productions);
            productions = new String[][]{{"read", "(", "<Valid Values>", "<Read Parameters>", ")"}};
            addProduction("<Read>", productions);
            productions = new String[][]{{"<Negate>", "<Expression>", "<Relational Logic>"}, {"(", "<Condition>", ")", "<Relational Logic>"}};
            addProduction("<Condition>", productions);
            productions = new String[][]{{"return", "<Return Statement>"}, {"<Method Call>"}, {"<Read>"}, {"<Write>"}, {"<Valid Identifier>", "<Attribute Access>", "<Attributition>"}};
            addProduction("<Line Code>", productions);
            productions = new String[][]{{",", "<Valid Values>", "<To-Write>"}, {",", "<Method Call>", "<To-Write>"}, {""}};
            addProduction("<To-Write>", productions);
            productions = new String[][]{{"=", "<Expression>"}, {"<Increment-Decrement>"}};
            addProduction("<Attributition>", productions);
            productions = new String[][]{{"<Expression>"}, {""}};
            addProduction("<Array Position>", productions);
            productions = new String[][]{{",", "<Parameters>"}, {""}};
            addProduction("<Parameter>", productions);
            productions = new String[][]{{",", "<Valid Values>", "<Read Parameters>"}, {""}};
            addProduction("<Read Parameters>", productions);
            productions = new String[][]{{"<Valid Values>", "<To-Write>"}, {"<Method Call>", "<To-Write>"}};
            addProduction("<Write Parameters>", productions);
            productions = new String[][]{{"<Mult Exp>", "+", "<Expression>"}, {"<Mult Exp>", "-", "<Expression>"}, {"<Mult Exp>"}};
            addProduction("<Expr Arit>", productions);
            productions = new String[][]{{"while", "(", "<Condition>", ")", "<Code Block>"}};
            addProduction("<Looping-Block>", productions);
            productions = new String[][]{{"[", "<Array Position>", "]", "<Array>"}, {""}};
            addProduction("<Array>", productions);
            productions = new String[][]{{"<If-Block>"}, {"<Code Block>"}, {""}};
            addProduction("<Post-Else-Block>", productions);
            productions = new String[][]{{"<Condition>"}, {"(", "<Return Statement>", ")"}, {""}};
            addProduction("<Return Statement>", productions);
            productions = new String[][]{{"const", "{", "<Constant Assignment>", "}"}};
            addProduction("<Constants>", productions);
            productions = new String[][]{{"<Variable>", "<Class Code>"}, {"<Methods>", "<Class Code>"}, {""}};
            addProduction("<Class Code>", productions);
            productions = new String[][]{{"<Value>"}, {"<Valid Identifier>", "<Attribute Access>"}};
            addProduction("<Valid Values>", productions);
            productions = new String[][]{{"Identifier", "<Array>"}};
            addProduction("<Valid Identifier>", productions);
            productions = new String[][]{{"<Multiple Identifier>", "=", "<Expression>", "<Initialize Constant>"}, {""}};
            addProduction("<Initialize Constant>", productions);
            productions = new String[][]{{"<Class>", "<Program>"}, {"<Constants>", "<Program>"}, {""}};
            addProduction("<Program>", productions);
            productions = new String[][]{{"&&"}, {"||"}};
            addProduction("<Logic Operator>", productions);
            productions = new String[][]{{"{", "<Code Statements>", "}"}};
            addProduction("<Code Block>", productions);
            productions = new String[][]{{"{", "<Init Array_2>", "}"}};
            addProduction("<Init Array>", productions);
            productions = new String[][]{{"<Declaration>", "=", "<Expression>", "<Initialize Constant>", ";", "<Constant Assignment>"}, {""}};
            addProduction("<Constant Assignment>", productions);
            productions = new String[][]{{"else", "<Post-Else-Block>"}, {""}};
            addProduction("<Else-Block>", productions);
            productions = new String[][]{{"if", "(", "<Condition>", ")", "then", "<Code Block>", "<Else-Block>"}};
            addProduction("<If-Block>", productions);
            productions = new String[][]{{"write", "(", "<Write Parameters>", ")"}};
            addProduction("<Write>", productions);
            productions = new String[][]{{"class", "Identifier", "<Extends>", "{", "<Class Code>", "}"}};
            addProduction("<Class>", productions);
            productions = new String[][]{{"variables", "{", "<Variable Assignment>", "}"}};
            addProduction("<Variable>", productions);
            productions = new String[][]{{"<Initial Value>"}, {"<Initial Value>", ",", "<Init Array_3>"}};
            addProduction("<Init Array_3>", productions);
            productions = new String[][]{{"<Multiple Identifier>", "<Initialize>"}};
            addProduction("<Initialize Variable>", productions);
            productions = new String[][]{{"<Relational Operator>", "<Condition>"}, {"<Logic Operator>", "<Condition>"}, {""}};
            addProduction("<Relational Logic>", productions);
            productions = new String[][]{{"(", "<Init Array_3>", ")"}, {"(", "<Init Array_3>", ")", "<Init Array_2>"}};
            addProduction("<Init Array_2>", productions);
            productions = new String[][]{{"!", "<Negate>"}, {""}};
            addProduction("<Negate>", productions);
            productions = new String[][]{{"++", "<Increment-Decrement>"}, {"--", "<Increment-Decrement>"}, {""}};
            addProduction("<Increment-Decrement>", productions);
            productions = new String[][]{{"method", "<Declaration>", "(", "<Parameters>", ")", "<Code Block>"}};
            addProduction("<Methods>", productions);
            productions = new String[][]{{",", "<Valid Identifier>", "<Multiple Identifier>"}, {""}};
            addProduction("<Multiple Identifier>", productions);
            productions = new String[][]{{"<Valid Identifier>", "<Attribute Access>", "(", "<Arguments>", ")"}};
            addProduction("<Method Call>", productions);
            productions = new String[][]{{"!="}, {"=="}, {"<"}, {"<="}, {">"}, {">="}};
            addProduction("<Relational Operator>", productions);
            productions = new String[][]{{"-", "<Initial Value>"}, {"<Initial Value>"}};
            addProduction("<Negate Exp>", productions);
            productions = new String[][]{{".", "<Valid Identifier>", "<Attribute Access>"}, {""}};
            addProduction("<Attribute Access>", productions);
            productions = new String[][]{{"=", "<Expression>"}, {""}};
            addProduction("<Initialize>", productions);
            productions = new String[][]{{"<Declaration>", "<Initialize>", "<Initialize Variable>", ";", "<Variable Assignment>"}, {""}};
            addProduction("<Variable Assignment>", productions);
            productions = new String[][]{{"<Init Array>"}, {"<Valid Values>", "<Increment-Decrement>"}, {"<Method Call>"}};
            addProduction("<Initial Value>", productions);
        }
        return grammar;
    }

    public void addProduction(String value, String[][] production) {
        Node node = grammar.get(value);
        if (node == null) {
            node = new Node(value);
            node.addObserver(this);
            grammar.put(value, node);
        }
        node.setProductions(production);
    }

    public Node getNode(String p) {
        return grammar.get(p);
    }
    
    public Node getStartNode(){
        return grammar.get(startSymbol);
    }
    
    public void getFirst(Node node) {
        if (node != null) {
            for (String[] production : node.getProductions()) {
                for (String prod : production) {
                    if (!node.getValue().equals(prod)) {
                        if (isTerminal(prod)) {
                            node.addFirst(prod);
                            break;
                        } else {
                            node.addFirst(grammar.get(prod).getFirst());
                            if (!grammar.get(prod).firstContains("")) {
                                break;
                            }
                        }
                    } else {
                        break;
                    }
                }
            }
        }
    }

    public void getFollow(Node node) {
        getFollow(node, null);
    }

    private void getFollow(Node node, HashSet<String> vis) {
        if (vis == null) {
            vis = new HashSet<>();
        }
        if (node != null) {
            if (node.getValue().equals(startSymbol)) {
                node.addFollow("$");
            } else {
                for (Map.Entry<String, Node> p : grammar.entrySet()) {
                    for (String[] pAux : p.getValue().getProductions()) {
                        for (int i = 0; i < pAux.length; i++) {
                            if (pAux[i].equals(node.getValue())) {
                                if (i + 1 >= pAux.length) { // αB
                                    if (!vis.contains(p.getKey())) {
                                        vis.add(p.getKey());
                                        getFollow(p.getValue(), vis);
                                        node.addFollow(p.getValue().getFollow());
                                    }
                                    break;
                                } else { //αBβ
                                    if (isTerminal(pAux[i + 1])) {
                                        node.addFollow(pAux[i + 1]);
                                        break;
                                    }
                                    node.addFollow(grammar.get(pAux[i + 1]).getFirst());
                                    if (grammar.get(pAux[i + 1]).firstContains("")) {
                                        break;
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }

    private boolean isTerminal(String token) {
        if (token.equals("")) {
            return true;
        }
        return !(token.charAt(0) == '<' && token.charAt(token.length() - 1) == '>');
    }

    @Override
    public void update(Observable o, Object arg) {
        if (o instanceof Node) {
            if (arg instanceof String) {
                Node node = (Node) o;
                String string = (String) arg;
                if (string.equals("first")) {
                    getFirst(node);
                } else {
                    getFollow(node);
                }
            }
        }
    }
}
