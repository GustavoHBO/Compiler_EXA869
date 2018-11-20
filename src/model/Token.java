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
    
    @Override
    public String toString(){
        return "Found a token! Line: " + getLine() + " Column: " + getColumn() + " | Type: " + getType().getNAME() + " : \"" + getString() + "\"\n";
    }
    
    /**
     * @return the string
     */
    public String getString() {
        return string;
    }

    /**
     * @param string the string to set
     */
    public void setString(String string) {
        this.string = string;
    }

    /**
     * @return the line
     */
    public int getLine() {
        return line;
    }

    /**
     * @param line the line to set
     */
    public void setLine(int line) {
        this.line = line;
    }

    /**
     * @return the column
     */
    public int getColumn() {
        return column;
    }

    /**
     * @param column the column to set
     */
    public void setColumn(int column) {
        this.column = column;
    }

    /**
     * @return the type
     */
    public TokenEnum getType() {
        return type;
    }

    /**
     * @param type the type to set
     */
    public void setType(TokenEnum type) {
        this.type = type;
    }
}
