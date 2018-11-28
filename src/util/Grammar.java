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
import java.util.Map;

/**
 *
 * @author gustavo
 */
public class Grammar implements IGrammar {

    private String file;
    private final HashMap<String, Node> grammar;
    private String startSymbol;

    public Grammar() {
        this.grammar = new HashMap<>();
    }

    @Override
    public HashMap<String, Node> getGrammar() {
        String[][] productions;
        if (grammar.isEmpty()) {
            startSymbol = "<Program>";
            addProductor("StringLiteral", "<Value>");
            addProductor("NumberTerminal", "<Value>");
            productions = new String[][]{{"true"}, {"false"}, {"StringLiteral"}, {"NumberTerminal"}};
            addProduction("<Value>", productions);
            addProductor("Identifier", "<Extends>");
            productions = new String[][]{{"extends", "Identifier"}, {""}};
            addProduction("<Extends>", productions);
            addProductor("Identifier", "<Type>");
            productions = new String[][]{{"string"}, {"int"}, {"float"}, {"bool"}, {"void"}, {"Identifier"}};
            addProduction("<Type>", productions);
            addProductor("<If-Block>", "<Code Statements>");
            addProductor("<Looping-Block>", "<Code Statements>");
            addProductor("<Line Code>", "<Code Statements>");
            addProductor("<Variables>", "<Code Statements>");
            productions = new String[][]{{"<If-Block>", "<Code Statements>"}, {"<Looping-Block>", "<Code Statements>"}, {"<Line Code>", ";", "<Code Statements>"}, {"<Variables>", "<Code Statements>"}, {""}};
            addProduction("<Code Statements>", productions);
            addProductor("<Initial Value>", "<Argument>");
            productions = new String[][]{{",", "<Initial Value>", "<Argument>"}, {""}};
            addProduction("<Argument>", productions);
            addProductor("<Initial Value>", "<Arguments>");
            addProductor("<Argument>", "<Arguments>");
            productions = new String[][]{{"<Initial Value>", "<Argument>"}, {""}};
            addProduction("<Arguments>", productions);
            addProductor("<Negate Exp>", "<Mult Exp>");
            addProductor("<Expression>", "<Mult Exp>");
            addProductor("<Negate Exp>", "<Mult Exp>");
            addProductor("<Expression>", "<Mult Exp>");
            addProductor("<Negate Exp>", "<Mult Exp>");
            productions = new String[][]{{"<Negate Exp>", "*", "<Expression>"}, {"<Negate Exp>", "/", "<Expression>"}, {"<Negate Exp>"}};
            addProduction("<Mult Exp>", productions);
            addProductor("<Expr Arit>", "<Expression>");
            addProductor("<Expr Arit>", "<Expression>");
            productions = new String[][]{{"(", "<Expr Arit>", ")"}, {"<Expr Arit>"}};
            addProduction("<Expression>", productions);
            addProductor("<Declaration>", "<Parameters>");
            addProductor("<Parameter>", "<Parameters>");
            productions = new String[][]{{"<Declaration>", "<Parameter>"}, {""}};
            addProduction("<Parameters>", productions);
            addProductor("<Type>", "<Declaration>");
            addProductor("<Valid Identifier>", "<Declaration>");
            productions = new String[][]{{"<Type>", "<Valid Identifier>"}};
            addProduction("<Declaration>", productions);
            addProductor("<Valid Values>", "<Read>");
            addProductor("<Read Parameters>", "<Read>");
            productions = new String[][]{{"read", "(", "<Valid Values>", "<Read Parameters>", ")"}};
            addProduction("<Read>", productions);
            addProductor("<Negate>", "<Condition>");
            addProductor("<Expression>", "<Condition>");
            addProductor("<Relational Logic>", "<Condition>");
            addProductor("<Relational Logic>", "<Condition>");
            productions = new String[][]{{"<Negate>", "<Expression>", "<Relational Logic>"}, {"(", "<Condition>", ")", "<Relational Logic>"}};
            addProduction("<Condition>", productions);
            addProductor("<Return Statement>", "<Line Code>");
            addProductor("<Method Call>", "<Line Code>");
            addProductor("<Read>", "<Line Code>");
            addProductor("<Write>", "<Line Code>");
            addProductor("<Valid Identifier>", "<Line Code>");
            addProductor("<Attribute Access>", "<Line Code>");
            addProductor("<Attributition>", "<Line Code>");
            productions = new String[][]{{"return", "<Return Statement>"}, {"<Method Call>"}, {"<Read>"}, {"<Write>"}, {"<Valid Identifier>", "<Attribute Access>", "<Attributition>"}};
            addProduction("<Line Code>", productions);
            addProductor("<Valid Values>", "<To-Write>");
            addProductor("<Method Call>", "<To-Write>");
            productions = new String[][]{{",", "<Valid Values>", "<To-Write>"}, {",", "<Method Call>", "<To-Write>"}, {""}};
            addProduction("<To-Write>", productions);
            addProductor("<Expression>", "<Attributition>");
            addProductor("<Increment-Decrement>", "<Attributition>");
            productions = new String[][]{{"=", "<Expression>"}, {"<Increment-Decrement>"}};
            addProduction("<Attributition>", productions);
            addProductor("<Expression>", "<Array Position>");
            productions = new String[][]{{"<Expression>"}, {""}};
            addProduction("<Array Position>", productions);
            addProductor("<Parameters>", "<Parameter>");
            productions = new String[][]{{",", "<Parameters>"}, {""}};
            addProduction("<Parameter>", productions);
            addProductor("<Valid Values>", "<Read Parameters>");
            productions = new String[][]{{",", "<Valid Values>", "<Read Parameters>"}, {""}};
            addProduction("<Read Parameters>", productions);
            addProductor("<Valid Values>", "<Write Parameters>");
            addProductor("<To-Write>", "<Write Parameters>");
            addProductor("<Method Call>", "<Write Parameters>");
            addProductor("<To-Write>", "<Write Parameters>");
            productions = new String[][]{{"<Valid Values>", "<To-Write>"}, {"<Method Call>", "<To-Write>"}};
            addProduction("<Write Parameters>", productions);
            addProductor("<Mult Exp>", "<Expr Arit>");
            addProductor("<Expression>", "<Expr Arit>");
            addProductor("<Mult Exp>", "<Expr Arit>");
            addProductor("<Expression>", "<Expr Arit>");
            addProductor("<Mult Exp>", "<Expr Arit>");
            productions = new String[][]{{"<Mult Exp>", "+", "<Expression>"}, {"<Mult Exp>", "-", "<Expression>"}, {"<Mult Exp>"}};
            addProduction("<Expr Arit>", productions);
            addProductor("<Condition>", "<Looping-Block>");
            addProductor("<Code Block>", "<Looping-Block>");
            productions = new String[][]{{"while", "(", "<Condition>", ")", "<Code Block>"}};
            addProduction("<Looping-Block>", productions);
            addProductor("<Array Position>", "<Array>");
            productions = new String[][]{{"[", "<Array Position>", "]", "<Array>"}, {""}};
            addProduction("<Array>", productions);
            addProductor("<If-Block>", "<Post-Else-Block>");
            addProductor("<Code Block>", "<Post-Else-Block>");
            productions = new String[][]{{"<If-Block>"}, {"<Code Block>"}, {""}};
            addProduction("<Post-Else-Block>", productions);
            addProductor("<Condition>", "<Return Statement>");
            productions = new String[][]{{"<Condition>"}, {"(", "<Return Statement>", ")"}, {""}};
            addProduction("<Return Statement>", productions);
            addProductor("<Constant Assignment>", "<Constants>");
            productions = new String[][]{{"const", "{", "<Constant Assignment>", "}"}};
            addProduction("<Constants>", productions);
            addProductor("<Variables>", "<Class Code>");
            addProductor("<Methods>", "<Class Code>");
            productions = new String[][]{{"<Variables>", "<Class Code>"}, {"<Methods>", "<Class Code>"}, {""}};
            addProduction("<Class Code>", productions);
            addProductor("<Value>", "<Valid Values>");
            addProductor("<Valid Identifier>", "<Valid Values>");
            addProductor("<Attribute Access>", "<Valid Values>");
            productions = new String[][]{{"<Value>"}, {"<Valid Identifier>", "<Attribute Access>"}};
            addProduction("<Valid Values>", productions);
            addProductor("Identifier", "<Valid Identifier>");
            addProductor("<Array>", "<Valid Identifier>");
            productions = new String[][]{{"Identifier", "<Array>"}};
            addProduction("<Valid Identifier>", productions);
            addProductor("<Multiple Identifier>", "<Initialize Constant>");
            addProductor("<Expression>", "<Initialize Constant>");
            productions = new String[][]{{"<Multiple Identifier>", "=", "<Expression>", "<Initialize Constant>"}, {""}};
            addProduction("<Initialize Constant>", productions);
            addProductor("<Class>", "<Program>");
            addProductor("<Constants>", "<Program>");
            productions = new String[][]{{"<Class>", "<Program>"}, {"<Constants>", "<Program>"}, {""}};
            addProduction("<Program>", productions);
            productions = new String[][]{{"&&"}, {"||"}};
            addProduction("<Logic Operator>", productions);
            addProductor("<Code Statements>", "<Code Block>");
            productions = new String[][]{{"{", "<Code Statements>", "}"}};
            addProduction("<Code Block>", productions);
            addProductor("<Init Array_2>", "<Init Array>");
            productions = new String[][]{{"{", "<Init Array_2>", "}"}};
            addProduction("<Init Array>", productions);
            addProductor("<Declaration>", "<Constant Assignment>");
            addProductor("<Expression>", "<Constant Assignment>");
            addProductor("<Initialize Constant>", "<Constant Assignment>");
            productions = new String[][]{{"<Declaration>", "=", "<Expression>", "<Initialize Constant>", ";", "<Constant Assignment>"}, {""}};
            addProduction("<Constant Assignment>", productions);
            addProductor("<Post-Else-Block>", "<Else-Block>");
            productions = new String[][]{{"else", "<Post-Else-Block>"}, {""}};
            addProduction("<Else-Block>", productions);
            addProductor("<Condition>", "<If-Block>");
            addProductor("<Code Block>", "<If-Block>");
            addProductor("<Else-Block>", "<If-Block>");
            productions = new String[][]{{"if", "(", "<Condition>", ")", "then", "<Code Block>", "<Else-Block>"}};
            addProduction("<If-Block>", productions);
            addProductor("<Write Parameters>", "<Write>");
            productions = new String[][]{{"write", "(", "<Write Parameters>", ")"}};
            addProduction("<Write>", productions);
            addProductor("Identifier", "<Class>");
            addProductor("<Extends>", "<Class>");
            addProductor("<Class Code>", "<Class>");
            productions = new String[][]{{"class", "Identifier", "<Extends>", "{", "<Class Code>", "}"}};
            addProduction("<Class>", productions);
            addProductor("<Variable Assignment>", "<Variables>");
            productions = new String[][]{{"variables", "{", "<Variable Assignment>", "}"}};
            addProduction("<Variables>", productions);
            addProductor("<Initial Value>", "<Init Array_3>");
            addProductor("<Initial Value>", "<Init Array_3>");
            productions = new String[][]{{"<Initial Value>"}, {"<Initial Value>", ",", "<Init Array_3>"}};
            addProduction("<Init Array_3>", productions);
            addProductor("<Multiple Identifier>", "<Initialize Variable>");
            addProductor("<Initialize>", "<Initialize Variable>");
            productions = new String[][]{{"<Multiple Identifier>", "<Initialize>"}};
            addProduction("<Initialize Variable>", productions);
            addProductor("<Relational Operator>", "<Relational Logic>");
            addProductor("<Condition>", "<Relational Logic>");
            addProductor("<Logic Operator>", "<Relational Logic>");
            addProductor("<Condition>", "<Relational Logic>");
            productions = new String[][]{{"<Relational Operator>", "<Condition>"}, {"<Logic Operator>", "<Condition>"}, {""}};
            addProduction("<Relational Logic>", productions);
            addProductor("<Init Array_3>", "<Init Array_2>");
            addProductor("<Init Array_3>", "<Init Array_2>");
            productions = new String[][]{{"(", "<Init Array_3>", ")"}, {"(", "<Init Array_3>", ")", "<Init Array_2>"}};
            addProduction("<Init Array_2>", productions);
            productions = new String[][]{{"!", "<Negate>"}, {""}};
            addProduction("<Negate>", productions);
            productions = new String[][]{{"++", "<Increment-Decrement>"}, {"--", "<Increment-Decrement>"}, {""}};
            addProduction("<Increment-Decrement>", productions);
            addProductor("<Declaration>", "<Methods>");
            addProductor("<Parameters>", "<Methods>");
            addProductor("<Code Block>", "<Methods>");
            productions = new String[][]{{"method", "<Declaration>", "(", "<Parameters>", ")", "<Code Block>"}};
            addProduction("<Methods>", productions);
            addProductor("<Valid Identifier>", "<Multiple Identifier>");
            productions = new String[][]{{",", "<Valid Identifier>", "<Multiple Identifier>"}, {""}};
            addProduction("<Multiple Identifier>", productions);
            addProductor("<Valid Identifier>", "<Method Call>");
            addProductor("<Attribute Access>", "<Method Call>");
            addProductor("<Arguments>", "<Method Call>");
            productions = new String[][]{{"<Valid Identifier>", "<Attribute Access>", "(", "<Arguments>", ")"}};
            addProduction("<Method Call>", productions);
            productions = new String[][]{{"!="}, {"=="}, {"<"}, {"<="}, {">"}, {">="}};
            addProduction("<Relational Operator>", productions);
            addProductor("<Initial Value>", "<Negate Exp>");
            addProductor("<Initial Value>", "<Negate Exp>");
            productions = new String[][]{{"-", "<Initial Value>"}, {"<Initial Value>"}};
            addProduction("<Negate Exp>", productions);
            addProductor("<Valid Identifier>", "<Attribute Access>");
            productions = new String[][]{{".", "<Valid Identifier>", "<Attribute Access>"}, {""}};
            addProduction("<Attribute Access>", productions);
            addProductor("<Expression>", "<Initialize>");
            productions = new String[][]{{"=", "<Expression>"}, {""}};
            addProduction("<Initialize>", productions);
            addProductor("<Declaration>", "<Variable Assignment>");
            addProductor("<Initialize>", "<Variable Assignment>");
            addProductor("<Initialize Variable>", "<Variable Assignment>");
            productions = new String[][]{{"<Declaration>", "<Initialize>", "<Initialize Variable>", ";", "<Variable Assignment>"}, {""}};
            addProduction("<Variable Assignment>", productions);
            addProductor("<Init Array>", "<Initial Value>");
            addProductor("<Valid Values>", "<Initial Value>");
            addProductor("<Increment-Decrement>", "<Initial Value>");
            addProductor("<Method Call>", "<Initial Value>");
            productions = new String[][]{{"<Init Array>"}, {"<Valid Values>", "<Increment-Decrement>"}, {"<Method Call>"}};
            addProduction("<Initial Value>", productions);
        }
        return grammar;
    }

    public void addProduction(String value, String[][] production) {
        Node node = this.grammar.get(value);
        if (node == null) {
            node = new Node(value);
            this.grammar.put(value, node);
        }
        node.setProductions(production);
    }

    public void addProductor(String production, String productor) {
        Node node = this.grammar.get(production);
        if (node == null) {
            this.grammar.put(production, new Node(production));
            node = this.grammar.get(production);
        }
    }

    public HashMap<String, String> getFirst(String p, HashMap<String, String> hashMapVisitors) {
        HashMap<String, String> hashMapProductor = new HashMap<>();
        HashMap<String, String> hashMapProduction;
        HashMap<String, String> hashMapAux;
        if (hashMapVisitors == null) {
            hashMapVisitors = new HashMap<>();
        }
        System.out.println("Primeiro de " + p);
        if (isTerminal(p)) {
            hashMapProductor.put(p, p);
            return hashMapProductor;
        } else if (p.equals(startSymbol)) {
            hashMapProductor.put("$", "$");
            return hashMapProductor;
        }
        Node productor = this.grammar.get(p);
        if (productor == null) {
            return null;
        }
        for (String[] production : productor.getProductions()) {
            hashMapAux = new HashMap<>();
            for (String string : production) {
                if (hashMapVisitors.containsKey(string)) {
                    break;
                } else if (isTerminal(string)) {// Find a terminal.
                    hashMapAux.put(string, string);
                    hashMapAux.remove(""); // Case have 'empy'.
                    break;// Break, the search finish.
                } else {
                    if (p.equals(string)) {// Case the search found yourself.
                        break;
                    }
                    System.out.println("First: " + string);
                    hashMapVisitors.put(string, string);
                    hashMapProduction = getFirst(string, hashMapVisitors);
                    System.out.println("Encontrei\nProcurando de :" + string);
                    if (hashMapProduction == null) {
                        return hashMapProductor;
                    }
                    hashMapAux.putAll(hashMapProduction);
                }
            }
            hashMapProductor.putAll(hashMapAux);
        }
        return hashMapProductor;
    }

    public HashMap<String, String> getFollow(String production) {
        HashMap<String, String> hashMapFollow = new HashMap<>();
        HashMap<String, String> hashAux;

        if (production == null) {
            return null;
        }
        System.out.println("Entrei");
        if (production.equals(startSymbol)) {
            hashMapFollow.put("$", "$");
        } else {
            for (Map.Entry<String, Node> p : grammar.entrySet()) {
                Node productor = p.getValue();
                if (!isTerminal(p.getKey())) {
                    for (String[] prodAux : productor.getProductions()) {
                        hashAux = new HashMap<>();
                        for (int i = 0; i < prodAux.length; i++) {
                            if (prodAux[i].equals(production)) {
                                if (i - 1 >= 0 && i + 1 >= prodAux.length) {
                                    hashAux.putAll(getFollow(productor.getValue()));
                                } else if (i + 1 < prodAux.length) {
                                    System.out.println("Seguinte de :" + prodAux[i + 1]);
                                    hashAux.putAll(getFirst(prodAux[i + 1], null));
                                    if (hashAux.containsKey("")) {
                                        hashAux.putAll(getFollow(prodAux[i + 1]));
                                    }
                                }
                            }
                        }
                        hashMapFollow.putAll(hashAux);
                    }
                }
            }
        }
        return hashMapFollow;
    }

    private boolean isTerminal(String token) {
        if (token.equals("")) {
            return true;
        }
        return !(token.charAt(0) == '<' && token.charAt(token.length() - 1) == '>');
    }
}
