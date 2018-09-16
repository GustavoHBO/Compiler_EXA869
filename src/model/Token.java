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
 * 		Marcus Aldrey		-	marcusaldrey@gmail.com
 *
 * See the original project in: <https://github.com/GustavoHBO/Compiler_EXA869>.
 */
package model;

import util.TokenEnum;

/**
 *
 * @author gustavo
 */
public class Token {
    
    private TokenEnum type;
    private String string;
    private int line = 0;
    private int column = 0;
    
    public Token(TokenEnum TYPE, int line, int column, String string){
        this.type = TYPE;
        this.line = line;
        this.column = column;
        this.string = string;
    }
    
    public int getValue(){
        return type.getVALUE();
    }
    
    @Override
    public String toString(){
        return "Found a token! Line: " + line + " Column: " + column + " | Type: " + type.getNAME() + " : \"" + string + "\"\n";
    }
}
