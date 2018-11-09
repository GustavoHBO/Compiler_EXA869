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

import exception.FileNotSavedException;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import model.Token;
import util.LexicalAnalyzer;

/**
 *
 * @author gustavo
 */
public class Controller {
    
    LexicalAnalyzer lexAnalyzer;
    
    /* Design Pattern Singleton */
    
    private static Controller controller; // Statement controller.
    
    /**
     * The constructor is private for use the singleton
     */
    private Controller(){
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
                                    
                                      /*--------------Lexical Methods------------------------*/
    
    /**
     * Using the file path for read all archives on folder and analyze these files.
     * @param file_path - File path.
     * @param file_name - File name.
     * @throws java.io.IOException - If the file not be read.
     * @throws java.io.FileNotFoundException - If the file not exist.
     * @return ArrayList Token - List with the tokens of the file.
     */
    public LexicalAnalyzer getLexicalAnalyzer(String file_path, String file_name) throws FileNotFoundException, IOException{
        lexAnalyzer = new LexicalAnalyzer(file_path, file_name);
        lexAnalyzer.analyzeFile();
        return new LexicalAnalyzer(file_path, file_name);
    }
    
    /**
     * Save the list of tokens in a file with extension .lex
     * @throws exception.FileNotSavedException - If the file not be saved.
     */
    public void saveTokensFile() throws FileNotSavedException {
        this.lexAnalyzer.saveFile();
    }
}
