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
package util;

/**
 * This enum determines the tokens of the lexical structure of the language specified on problem, see the document on the description folder.
 * @author Gustavo Henrique
 * @author Marcus Aldrey
 */
public enum TokenEnum {
    /*The structure of the enum has the next format:
        TOKEN(ID, "NAME", "REGEX");
    */
    KEYWORD(1, "Keyword", "bool|c(lass|onst)|e(lse|xtends)|f(loat|alse)|i(f|nt)|m(ethod|ain)|re(ad|turn)|string|t(hen|rue)|v(ariables|oid)|w(hil|rit)e"), 
    IDENTIFIER(2, "Identifier", "[a-zA-Z]([a-zA-Z]|\\d|_)*"), 
    NUMBER(3, "Number", "(-)?\\s*\\d(\\d)*(\\.\\d(\\d)*)?"), 
    DIGIT(4, "Digit", "\\d"), 
    LETTER(5, "Letter", "[a-zA-Z]"), 
    ARITHMETIC_OPERATOR(6, "Arithmetic Operator", "\\+|\\-|\\*|\\/|\\+\\+|\\-\\-"), 
    RELATIONAL_OPERATOR(7, "Relational Operator", "!=|==|<|<=|>|>=|="), 
    LOGICAL_OPERATOR(8, "Logical Operator", "!|&&|\\|\\|"), 
    LINE_COMMENT(10, "Line Comment", "\\/\\/.*"), 
    BLOCK_COMMENT(9, "Block Comment", "\\/\\*[\\s\\S]*\\*\\/"), 
    DELIMITER(11, "Delimiter", ";|,|\\(|\\)|\\[|\\]|\\{|\\}|\\."), 
    STRING(12, "String", "\"(\\x20|\\x21|[\\x23-\\x7E]|\\d|\\\")*\""), 
    SYMBOL(13, "Symbol", "\\x20|\\x21|[\\x23-\\x7E]"), 
    SPACE(14, "Space", "\\s"), 
    ERROR(15, "ERROR", "");
    
    private final int VALUE; // Can be used in comparing between values.
    private final String NAME; // Identify the Token, can be used on messagens.
    private final String REGEX; // Regex of the Token, used to identify the token in a string.
    
    /**
     * Constructor of the enum.
     * @param VALUE - VALUE determined to the token.
     * @param NAME - NAME determined to the token.
     * @param REGEX - REGEX determined to the token.
     */
    TokenEnum(int VALUE, String NAME, String REGEX){
        this.VALUE = VALUE;
        this.NAME = NAME;
        this.REGEX = REGEX;
    }
    
    /**
     * Returns the VALUE depending on the current token.
     * @return VALUE
     */
    public int getVALUE(){
        return this.VALUE;
    }
    
    /**
     * Returns the NAME depending on the current token.
     * @return NAME
     */
    public String getNAME(){
        return this.NAME;
    }
    
    /**
     * Returns the REGEX depending on the current token.
     * @return REGEX
     */
    public String getREGEX(){
        return this.REGEX;
    }
}