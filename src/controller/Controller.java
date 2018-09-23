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
package controller;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import model.Token;
import util.TokenEnum;

/**
 *
 * @author gustavo
 */
public class Controller {
    
    private final String STRING_SPLIT = "\\s";
    private ArrayList<Token> list_tokens;
    
    /* Design Pattern Singleton */
    
    private static Controller controller; // Statement controller.
    
    /**
     * The constructor is private for use the singleton
     */
    private Controller(){
        list_tokens = new ArrayList<>();
    }
    
    /**
     * Return the instance of controller.
     * @return controller - An instance.
     */
    public static Controller getInstance(){
        if(controller == null){
            controller = new Controller();
        }
        return controller;
    }
    
    /**
     * Reset the controller.
     */
    public static void resetController(){
        controller = null;
    }

    /*-------------------------------------------------Public Methods-----------------------------------------------------------------*/
    
    public void analyseFile(String file_name){
        int count_line = 0;
        int count_column = 0;
        StringBuilder file_output;
        String token_found = "";
        String file_buffer;
        String strAux = "";
        Token token = null;
        File file = new File(file_name);
        file_output = new StringBuilder("");
        file_buffer = "";
        
        if(file.isDirectory()){
            FileReader fileReader = null;
            BufferedReader bufferedReader;
            try {
                System.out.println("The files on this folders is:");
                for (String list : file.list()) {
                    System.out.println(list);
                    fileReader = new FileReader(file_name + list);
                    bufferedReader = new BufferedReader(fileReader);
                    count_line = 1;
                    while(bufferedReader.ready()){
                        file_buffer = bufferedReader.readLine();
                        System.out.println(file_buffer);
                        count_column = 0;
                        for(int i = 0; i < file_buffer.length(); i++){
                            token_found += file_buffer.toCharArray()[i];
                            if(token_found.matches(TokenEnum.KEYWORD.getREGEX())){
                                token = new Token(TokenEnum.KEYWORD, count_line, count_column, token_found);
                            } else if (token_found.matches(TokenEnum.IDENTIFIER.getREGEX())){
                                token = new Token(TokenEnum.IDENTIFIER, count_line, count_column, token_found);
                            } else if(token_found.matches(TokenEnum.NUMBER.getREGEX())){
                                token = new Token(TokenEnum.NUMBER, count_line, count_column, token_found);
                            } else if(token_found.matches(TokenEnum.DIGIT.getREGEX())){
                                token = new Token(TokenEnum.DIGIT, count_line, count_column, token_found);
                            } else if(token_found.matches(TokenEnum.LETTER.getREGEX())){
                                token = new Token(TokenEnum.LETTER, count_line, count_column, token_found);
                            } else if(token_found.matches(TokenEnum.ARITHMETIC_OPERATOR.getREGEX())){
                                token = new Token(TokenEnum.ARITHMETIC_OPERATOR, count_line, count_column, token_found);
                            } else if(token_found.matches(TokenEnum.RELATIONAL_OPERATOR.getREGEX())){
                                token = new Token(TokenEnum.RELATIONAL_OPERATOR, count_line, count_column, token_found);
                            } else if(token_found.matches(TokenEnum.LOGICAL_OPERATOR.getREGEX())){
                                token = new Token(TokenEnum.LOGICAL_OPERATOR, count_line, count_column, token_found);
                            } else if(token_found.matches(TokenEnum.BLOCK_COMMENT.getREGEX())){
                                token = new Token(TokenEnum.BLOCK_COMMENT, count_line, count_column, token_found);
                            } else if(token_found.matches(TokenEnum.DELIMITER.getREGEX())){
                                token = new Token(TokenEnum.DELIMITER, count_line, count_column, token_found);
                            } else if(token_found.matches(TokenEnum.STRING.getREGEX())){
                                token = new Token(TokenEnum.STRING, count_line, count_column, token_found);
                            } else if(token_found.matches(TokenEnum.SYMBOL.getREGEX())){
                                token = new Token(TokenEnum.SYMBOL, count_line, count_column, token_found);
                            } else if(token_found.matches(TokenEnum.SPACE.getREGEX())){
                                token = new Token(TokenEnum.SPACE, count_line, count_column, token_found);
                            } else {
                                if(token != null){
                                    strAux += file_buffer.toCharArray()[i];
                                    if((token.getType().getVALUE() == TokenEnum.NUMBER.getVALUE() || token.getType().getVALUE() == TokenEnum.DIGIT.getVALUE())){
                                        if(file_buffer.toCharArray()[i] != ' '){
                                            if(!strAux.matches(TokenEnum.ARITHMETIC_OPERATOR.getREGEX())){
                                                if(!strAux.matches("\\(")){
                                                    token.setType(TokenEnum.ERROR_NUMBER);
                                                    token.setString(token_found);
                                                }
                                            }
                                        }
                                    } else if (token.getType().getVALUE() == TokenEnum.ERROR_NUMBER.getVALUE() && file_buffer.toCharArray()[i] != ' '){
                                        
                                        token.setString(token_found);
                                    } else {
                                        list_tokens.add(token);
                                        file_output.append(token.toString() + "\n");
                                        count_column += token_found.length()-1; // Calculates the actual column.
                                        token_found = "";
                                        token = null;
                                        i--;
                                    }
                                }
                            }
                        }
                        count_line++;
                    }
                    if(token != null){
                        list_tokens.add(token);
                    }
                    System.out.println(list_tokens.toString());
                }   
                System.out.println("Terminei");
            } catch (FileNotFoundException ex) {
                Logger.getLogger(view.Compiler.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(view.Compiler.class.getName()).log(Level.SEVERE, null, ex);
            } finally {
                try {
                    fileReader.close();
                } catch (IOException ex) {
                    Logger.getLogger(view.Compiler.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        } else{
            System.out.println("The name choose isn't a folder!");
            System.out.println(file.getAbsoluteFile());
        }
    }
}
