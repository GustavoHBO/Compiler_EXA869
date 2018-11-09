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

import exception.FileNotSavedException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import model.Token;

/**
 *
 * @author gustavo
 */
public class LexicalAnalyzer {
    
    private final String file_path;
    private final String file_name;
    private ArrayList<Token> listTokens;
    private final WriteReadFile wrFile;

    
    public LexicalAnalyzer(String file_path, String file_name) throws FileNotFoundException{
        this.file_path = file_path;
        this.file_name = file_name;
        this.wrFile = new WriteReadFile(file_path, file_name);
    }
    
    /**
     * Using the file path for read all archives on folder and analyze these files.
     * @throws java.io.IOException - If the file not be read.
     * @throws java.io.FileNotFoundException - If the file not exist.
     */
    public void analyzeFile() throws FileNotFoundException, IOException{
        int count_line = 1;
        int count_column;
        StringBuilder file_output = new StringBuilder("");
        String token_found = "";
        String file_buffer;
        Token token = null;
        ArrayList<Token> list_tokens = new ArrayList<>();
        
        while(wrFile.ready()){
            file_buffer = wrFile.readLine();
            count_column = 0;
            for(int i = 0; i < file_buffer.length(); i++){
                token_found += file_buffer.toCharArray()[i];
                if(token_found.matches(TokenEnum.KEYWORD.getREGEX())){
                    token = new Token(TokenEnum.KEYWORD, count_line, count_column, token_found);
                } else if (token_found.matches(TokenEnum.IDENTIFIER.getREGEX())){
                    token = new Token(TokenEnum.IDENTIFIER, count_line, count_column, token_found);
                } else if(token_found.matches(TokenEnum.DIGIT.getREGEX())){
                    token = new Token(TokenEnum.DIGIT, count_line, count_column, token_found);
                } else if(token_found.matches(TokenEnum.NUMBER.getREGEX())){
                    token = new Token(TokenEnum.NUMBER, count_line, count_column, token_found);
                } else if(token_found.matches(TokenEnum.LETTER.getREGEX())){
                    token = new Token(TokenEnum.LETTER, count_line, count_column, token_found);
                } else if(token_found.matches(TokenEnum.ARITHMETIC_OPERATOR.getREGEX())){
                    token = new Token(TokenEnum.ARITHMETIC_OPERATOR, count_line, count_column, token_found);
                } else if(token_found.matches(TokenEnum.RELATIONAL_OPERATOR.getREGEX())){
                    token = new Token(TokenEnum.RELATIONAL_OPERATOR, count_line, count_column, token_found);
                } else if(token_found.matches(TokenEnum.LOGICAL_OPERATOR.getREGEX())){
                    token = new Token(TokenEnum.LOGICAL_OPERATOR, count_line, count_column, token_found);
                } else if(token_found.matches(TokenEnum.LINE_COMMENT.getREGEX())){
                    token = new Token(TokenEnum.LINE_COMMENT, count_line, count_column, token_found);
                } else if(token_found.matches(TokenEnum.BLOCK_COMMENT.getREGEX())){
                    token = new Token(TokenEnum.BLOCK_COMMENT, count_line, count_column, token_found);
                } else if(token_found.matches(TokenEnum.DELIMITER.getREGEX())){
                    token = new Token(TokenEnum.DELIMITER, count_line, count_column, token_found);
                } else if(token_found.matches(TokenEnum.STRING.getREGEX()) && token != null && !token.getType().equals(TokenEnum.STRING)){
                    token = new Token(TokenEnum.STRING, count_line, count_column, token_found);
                } else if(token_found.matches(TokenEnum.SYMBOL.getREGEX())){
                    token = new Token(TokenEnum.SYMBOL, count_line, count_column, token_found);
                } else if(token_found.matches(TokenEnum.SPACE.getREGEX())){
                    token = new Token(TokenEnum.SPACE, count_line, count_column, token_found);
                } 

                    /* The next section identifies possible errors of bad token formation */

                else if(token_found.matches(TokenEnum.ERROR_NUMBER.getREGEX())){
                    token = new Token(TokenEnum.ERROR_NUMBER, count_line, count_column, token_found);
                } else if(token_found.matches(TokenEnum.ERROR_NUMBER_FLOAT.getREGEX())){
                    token = new Token(TokenEnum.ERROR_NUMBER_FLOAT, count_line, count_column, token_found);
                } else if(token_found.matches(TokenEnum.ERROR_BLOCK_COMMENT.getREGEX())){
                    token = new Token(TokenEnum.ERROR_BLOCK_COMMENT, count_line, count_column, token_found);
                } else if(token_found.matches(TokenEnum.ERROR_STRING.getREGEX())){
                    token = new Token(TokenEnum.ERROR_STRING, count_line, count_column, token_found);
                } else {
                    if(token != null){
                        list_tokens.add(token);
                        file_output.append(token.toString()).append("\n");
                        count_column += token_found.length()-1; // Calculates the actual column.
                        token_found = "";
                        token = null;
                        i--;
                    } else {
                        token = new Token(TokenEnum.ERROR_TOKEN_UNKNOWN, count_line, count_column, token_found);
                    }
                }
                if(i+1 >= file_buffer.length() && token != null && token.getType().getVALUE() != TokenEnum.ERROR_BLOCK_COMMENT.getVALUE()){
                    list_tokens.add(token);
                    file_output.append(token.toString()).append("\n");
                    count_column += token_found.length()-1; // Calculates the actual column.
                    token_found = "";
                    token = null;
                }
            }
            count_line++;
        }
        if(token != null){
            list_tokens.add(token);
        }
        wrFile.close();
        this.listTokens = list_tokens;
    }
    
    public ArrayList<Token> getTokens(){
        return this.listTokens;
    }
    
    public void saveFile() throws FileNotSavedException{
        wrFile.saveFile(this.listTokens);
    }
}
