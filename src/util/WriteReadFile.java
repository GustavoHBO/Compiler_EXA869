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

import exception.FileNotSavedException;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import model.Token;

/**
 * Class to handle with read and write files from compiler, the methods are creates to write output from layers of the compiler.
 * @author Gustavo Henrique.
 */
public class WriteReadFile implements IWriteReadFile{

    private final File file;
    private final FileReader fileReader;
    private final BufferedReader bufferedReader;
    private final String filePath;
    private final String fileName;
    
    /**
     * Constructor of the class, use the file path and file name to read and write on file.
     * @param filePath - File path.
     * @param fileName - File name.
     * @throws FileNotFoundException - Case the file not exists.
     */
    public WriteReadFile(String filePath, String fileName) throws FileNotFoundException{
        this.filePath = filePath;
        this.fileName = fileName;
        this.file = new File(filePath+fileName);
        this.fileReader = new FileReader(this.file);
        this.bufferedReader = new BufferedReader(this.fileReader);

    }
    
    /**
     * Constructor of the class, use the file path and file name to read and write on file.
     * @param url - URL from the file.
     * @throws FileNotFoundException - Case the file not exists.
     */
    public WriteReadFile(String url) throws FileNotFoundException{
        this.filePath = "";
        this.fileName = url;
        this.file = new File(filePath+fileName);
        this.fileReader = new FileReader(this.file);
        this.bufferedReader = new BufferedReader(this.fileReader);
    }
    
    /**
     * Read one line from file.
     * @return lineRead - Line read from file.
     * @throws FileNotFoundException - Case the file not exists.
     */
    @Override
    public String readLine() throws FileNotFoundException {
        try {
            return bufferedReader.readLine();
        } catch (IOException ex) {
            throw new FileNotFoundException();
        }
    }

    /**
     * Verify is the file is ready to operations.
     * @return true - Case the file is ready, false - Case the file isn't read.
     * @throws FileNotFoundException - Case the file not exists.
     */
    @Override
    public boolean ready() throws FileNotFoundException {
        try {
            return bufferedReader.ready();
        } catch (IOException ex) {
            throw new FileNotFoundException();
        }
    }

    /**
     * Close the file and save the editions.
     * @throws FileNotFoundException
     */
    @Override
    public void close() throws FileNotFoundException {
        try {
            bufferedReader.close();
            fileReader.close();
        } catch (IOException ex) {
            throw new FileNotFoundException();
        }
    }

    /**
     * Save the list of tokens in a file with extension .lex
     * @param list_tokens - List of tokens that will be save.
     * @throws exception.FileNotSavedException - If the file not be saved.
     */
    @Override
    public void saveFile(ArrayList<Token> list_tokens) throws FileNotSavedException {
        String name = "default";
        if(fileName.lastIndexOf(".") != -1){// Look for last . for remove the extension .*
            name = fileName.substring(0, fileName.lastIndexOf("."));
        }
        
        File fileOutput = new File(filePath + name + ".lex");
        if(fileOutput.exists()){
            fileOutput.delete();
        }
        
        try (FileWriter fileWriter = new FileWriter(fileOutput); PrintWriter filePrinter = new PrintWriter(fileWriter)) {//Write the file.
            list_tokens.forEach((list_token) -> {
                filePrinter.println(list_token.toString());
            });
            filePrinter.close();
            fileWriter.close();
        } catch (IOException ex) {
            throw new FileNotSavedException(name);
        }
    }

    public void putMessage(String msg) {
        String name = "default";
        if (fileName.lastIndexOf(".") != -1) {// Look for last . for remove the extension .*
            name = fileName.substring(0, fileName.lastIndexOf("."));
        }

        File fileOutput = new File(filePath + name + ".lex");
        if (fileOutput.exists()) {
            fileOutput.delete();
        }

        try (FileWriter fileWriter = new FileWriter(fileOutput); PrintWriter filePrinter = new PrintWriter(fileWriter)) {//Write the file.
            filePrinter.println(msg);
            filePrinter.close();
            fileWriter.close();
        } catch (IOException ex) {

        }
    }

    public void saveMsgErro(ArrayList<String> list_tokens) throws FileNotSavedException {
        String name = "default";
        if(fileName.lastIndexOf(".") != -1){// Look for last . for remove the extension .*
            name = fileName.substring(0, fileName.lastIndexOf("."));
        }

        File fileOutput = new File(filePath + name + ".lex");
        if(fileOutput.exists()){
            fileOutput.delete();
        }

        try (FileWriter fileWriter = new FileWriter(fileOutput); PrintWriter filePrinter = new PrintWriter(fileWriter)) {//Write the file.
            list_tokens.forEach((list_token) -> {
                filePrinter.println(list_token);
            });
            filePrinter.close();
            fileWriter.close();
        } catch (IOException ex) {
            throw new FileNotSavedException(name);
        }
    }
    
    
}
