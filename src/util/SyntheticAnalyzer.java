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

    public SyntheticAnalyzer(ArrayList<Token> listTokens, Grammar grammar) {
        this.hashMap = new HashMap<>();
        this.listTokens = listTokens;
        this.grammar = grammar;
    }

    public void AnalyzerTokens() {
        if (listTokens != null) {
            
        }
    }
}
