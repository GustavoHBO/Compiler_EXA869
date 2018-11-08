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

/**
 *
 * @author Gustavo Henrique
 * @author Marcos Vinícius
 */
public class Node {
    
    private HashMap<String, Node> childrens;
    private String value;
    
    public Node(String value){
        this.childrens = new HashMap<>();
        this.value = value;
    }
    
    public HashMap<String, Node> getChildrens(){
        return childrens;
    }
    
    public String getValue() {
        return value;
    }
    
    public void add(String key, Node node){
        this.childrens.put(key, node);
    }
}
