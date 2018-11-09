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
 *
 * @author gustavo
 */
public class WriteReadFile implements WriteReadFileInterface{

    private final File file;
    private final FileReader fileReader;
    private final BufferedReader bufferedReader;
    private final String file_path;
    private final String file_name;
    
    public WriteReadFile(String file_path, String file_name) throws FileNotFoundException{
        this.file_path = file_path;
        this.file_name = file_name;
        this.file = new File(file_path+file_name);
        fileReader = new FileReader(this.file);
        bufferedReader = new BufferedReader(fileReader);
    }
    
    @Override
    public String readLine() throws IOException {
        return bufferedReader.readLine();
    }

    @Override
    public boolean ready() throws IOException {
        return bufferedReader.ready();
    }

    @Override
    public void close() throws IOException {
        bufferedReader.close();
        fileReader.close();
    }

    /**
     * Save the list of tokens in a file with extension .lex
     * @param list_tokens - List of tokens that will be save.
     * @throws exception.FileNotSavedException - If the file not be saved.
     */
    @Override
    public void saveFile(ArrayList<Token> list_tokens) throws FileNotSavedException {
        String name = "default";
        if(file_name.lastIndexOf(".") != -1){// Look for last . for remove the extension .*
            name = file_name.substring(0, file_name.lastIndexOf("."));
        }
        
        File file = new File(file_path + name + ".lex");
        if(file.exists()){
            file.delete();
        }
        
        try (FileWriter fileWriter = new FileWriter(file); PrintWriter filePrinter = new PrintWriter(fileWriter)) {//Write the file.
            list_tokens.forEach((list_token) -> {
                filePrinter.println(list_token.toString());
            });
            filePrinter.close();
            fileWriter.close();
        } catch (IOException ex) {
            throw new FileNotSavedException(name);
        }
    }
    
    
}
