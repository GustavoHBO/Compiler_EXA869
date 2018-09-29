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
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import model.Token;
import util.TokenEnum;

/**
 *
 * @author gustavo
 */
public class Controller {
    
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
    
    /**
     * Using the file path for read all archives on folder and analyze these files.
     * @param file_name - Folder file path.
     * @throws java.io.IOException - If the file not be read.
     * @throws java.io.FileNotFoundException - If the file not exist.
     */
    public void analyzeFile(String file_name) throws FileNotFoundException, IOException{
        int count_line;
        int count_column;
        StringBuilder file_output;
        String token_found;
        String file_buffer;
        String strAux;
        Token token;
        File file = new File(file_name);
        file_output = new StringBuilder("");

        if(file.isDirectory()){
            FileReader fileReader;
            BufferedReader bufferedReader;
            for (String list : file.list()) {
                fileReader = new FileReader(file_name + list);
                bufferedReader = new BufferedReader(fileReader);
                count_line = 1;
                token_found = "";
                token = null;
                strAux = "";
                list_tokens = new ArrayList<>();
                if(!list.substring(list.length()-4).equals(".lex")){
                    while(bufferedReader.ready()){
                        file_buffer = bufferedReader.readLine();
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
                            } else if(token_found.matches(TokenEnum.STRING.getREGEX())){
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
                                    strAux += file_buffer.toCharArray()[i];
                                    list_tokens.add(token);
                                    file_output.append(token.toString()).append("\n");
                                    count_column += token_found.length()-1; // Calculates the actual column.
                                    token_found = "";
                                    token = null;
                                    strAux = "";
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
                                strAux = "";
                            }
                        }
                        count_line++;
                    }
                    if(token != null){
                        list_tokens.add(token);
                    }
                    bufferedReader.close();
                    fileReader.close();
                    saveFileOutput(list_tokens, file_name, list);
                }
            }   
        } else{
            throw new FileNotFoundException();
        }
    }
    
    /**
     * Save the list of tokens in a file with extension .lex
     * @param list_tokens - List of tokens that will be save.
     * @param filePath - The file path.
     * @param fileName - The file name.
     * @throws IOException - If the file not be save.
     */
    private void saveFileOutput(ArrayList<Token> list_tokens, String filePath, String fileName) throws IOException{
        System.out.println(fileName);
        
        if(fileName.lastIndexOf(".") != -1){// Look for last . for remove the extension .*
            fileName = fileName.substring(0, fileName.lastIndexOf("."));
        }
        
        File file = new File(filePath + fileName + ".lex");
        if(file.exists()){
            file.delete();
        }
        //file.createNewFile();
        
        try (FileWriter fileWriter = new FileWriter(file); PrintWriter filePrinter = new PrintWriter(fileWriter)) {//Write the file.
            list_tokens.forEach((list_token) -> {
                filePrinter.println(list_token.toString());
            });
            filePrinter.close();
            fileWriter.close();
        }
    }
}
