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

import exception.FileNotLexicalAnalyzerException;
import exception.FileNotSavedException;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import model.Token;

/**
 * The layer from the lexical analysis. This class allow get the list of tokens
 * from actual file and save them.
 *
 * @author Gustavo Henrique.
 */
public final class LexicalAnalyzer {

    private final String fileName;
    private ArrayList<Token> listTokens = null;
    private final WriteReadFile wrFile;

    /**
     * Constructor of the class, need the file path and file name from archive.
     *
     * @param filePath - The file path.
     * @param fileName - The file name.
     * @throws FileNotFoundException - Case the file not exists.
     */
    public LexicalAnalyzer(String filePath, String fileName) throws FileNotFoundException {
        this.fileName = fileName;
        this.wrFile = new WriteReadFile(filePath, fileName);
    }

    /**
     * Using the file path for read all archives on folder and analyze these
     * files.
     *
     * @throws java.io.FileNotFoundException - If the file not exist.
     */
    public void analyzeFile() throws FileNotFoundException {
        int countLine = 1;
        int countColumn;
        StringBuilder fileOutput = new StringBuilder("");
        String tokenFound = "";
        String fileBuffer;
        Token token = null;
        this.listTokens = new ArrayList<>();

        while (wrFile.ready()) {
            fileBuffer = wrFile.readLine();
            countColumn = 0;
            for (int i = 0; i < fileBuffer.length(); i++) { // The loop will get chat-by-char to search the match in the following combinations.
                tokenFound += fileBuffer.toCharArray()[i];
                if (tokenFound.matches(TokenEnum.KEYWORD.getREGEX())) {// Verify is the token found is a keyword.
                    token = new Token(TokenEnum.KEYWORD, countLine, countColumn, tokenFound);
                } else if (tokenFound.matches(TokenEnum.IDENTIFIER.getREGEX())) {// Verify is the token found is an identifier.
                    token = new Token(TokenEnum.IDENTIFIER, countLine, countColumn, tokenFound);
                } else if (tokenFound.matches(TokenEnum.DIGIT.getREGEX())) {// Verify is the token found is a digit.
                    token = new Token(TokenEnum.DIGIT, countLine, countColumn, tokenFound);
                } else if (tokenFound.matches(TokenEnum.NUMBER.getREGEX())) {// Verify is the token found is a number.
                    token = new Token(TokenEnum.NUMBER, countLine, countColumn, tokenFound.trim());
                } else if (tokenFound.matches(TokenEnum.LETTER.getREGEX())) {// Verify is the token found is a letter.
                    token = new Token(TokenEnum.LETTER, countLine, countColumn, tokenFound);
                } else if (tokenFound.matches(TokenEnum.ARITHMETIC_OPERATOR.getREGEX())) {// Verify is the token found is an arithmetic operator.
                    token = new Token(TokenEnum.ARITHMETIC_OPERATOR, countLine, countColumn, tokenFound);
                } else if (tokenFound.matches(TokenEnum.RELATIONAL_OPERATOR.getREGEX())) {// Verify is the token found is a relational operator.
                    token = new Token(TokenEnum.RELATIONAL_OPERATOR, countLine, countColumn, tokenFound);
                } else if (tokenFound.matches(TokenEnum.LOGICAL_OPERATOR.getREGEX())) {// Verify is the token found is a logical operator.
                    token = new Token(TokenEnum.LOGICAL_OPERATOR, countLine, countColumn, tokenFound);
                } else if (tokenFound.matches(TokenEnum.LINE_COMMENT.getREGEX())) {// Verify is the token found is a line comment.
                    token = new Token(TokenEnum.LINE_COMMENT, countLine, countColumn, tokenFound);
                } else if (tokenFound.matches(TokenEnum.BLOCK_COMMENT.getREGEX())) {// Verify is the token found is a block comment.
                    token = new Token(TokenEnum.BLOCK_COMMENT, countLine, countColumn, tokenFound);
                } else if (tokenFound.matches(TokenEnum.DELIMITER.getREGEX())) {// Verify is the token found is a delimiter.
                    token = new Token(TokenEnum.DELIMITER, countLine, countColumn, tokenFound);
                } else if (tokenFound.matches(TokenEnum.STRING.getREGEX()) && token != null && !token.getType().equals(TokenEnum.STRING)) {// Verify is the token found is a string.
                    token = new Token(TokenEnum.STRING, countLine, countColumn, tokenFound);
                } else if (tokenFound.matches(TokenEnum.SYMBOL.getREGEX())) {// Verify is the token found is a symbol.
                    token = new Token(TokenEnum.SYMBOL, countLine, countColumn, tokenFound);
                } else if (tokenFound.matches(TokenEnum.SPACE.getREGEX())) {// Verify is the token found is a space.
                    token = new Token(TokenEnum.SPACE, countLine, countColumn, tokenFound);
                } /* The next section identifies possible errors of bad token formation */
                else if (tokenFound.matches(TokenEnum.ERROR_NUMBER.getREGEX())) {// Verify is the token found is a number wrong.
                    token = new Token(TokenEnum.ERROR_NUMBER, countLine, countColumn, tokenFound);
                } else if (tokenFound.matches(TokenEnum.ERROR_NUMBER_FLOAT.getREGEX())) {// Verify is the token found is a float number wrong.
                    token = new Token(TokenEnum.ERROR_NUMBER_FLOAT, countLine, countColumn, tokenFound);
                } else if (tokenFound.matches(TokenEnum.ERROR_BLOCK_COMMENT.getREGEX())) {// Verify is the token found is a comment block wrong.
                    token = new Token(TokenEnum.ERROR_BLOCK_COMMENT, countLine, countColumn, tokenFound);
                } else if (tokenFound.matches(TokenEnum.ERROR_STRING.getREGEX())) {// Verify is the token found is a string wrong.
                    token = new Token(TokenEnum.ERROR_STRING, countLine, countColumn, tokenFound);
                } else {//If the token not match with any previous combinations the analyze change:
                    if (token != null) {// If the token match with some combination, this token is saved.
                        listTokens.add(token);
                        fileOutput.append(token.toString()).append("\n");
                        countColumn += tokenFound.length() - 1; // Calculates the actual column.
                        tokenFound = "";
                        token = null;
                        i--;// Ignore the actual charactere, will read any they in next loop.
                    } else {// Case the token not have any match, this token is unknown, it is add in next loop(the previous 'if')
                        token = new Token(TokenEnum.ERROR_TOKEN_UNKNOWN, countLine, countColumn, tokenFound);
                    }
                }
                // Verify if the actual line is the last line of the file, save the actual file. The errors on comment block are save on previous 'else'.
                if (i + 1 >= fileBuffer.length() && token != null && token.getType().getVALUE() != TokenEnum.ERROR_BLOCK_COMMENT.getVALUE()) {
                    listTokens.add(token);
                    fileOutput.append(token.toString()).append("\n");
                    countColumn += tokenFound.length() - 1; // Calculates the actual column.
                    tokenFound = "";
                    token = null;
                }
            }
            countLine++;
        }
        if (token != null) {
            listTokens.add(token);
        }
        wrFile.close();
    }

    /**
     * Return the list of tokens
     * @return listTokens - The actual list of tokens.
     */
    public ArrayList<Token> getTokens() {
        return this.listTokens;
    }

    /**
     * Save the output from lexical analyzer.
     *
     * @throws FileNotSavedException - Case the file not be saved.
     * @throws FileNotLexicalAnalyzerException - If the list of tokens not
     * exists.
     */
    public void saveFile() throws FileNotSavedException, FileNotLexicalAnalyzerException {
        if (this.listTokens != null) {
            wrFile.saveFile(this.listTokens);
        } else {
            throw new FileNotLexicalAnalyzerException(fileName);
        }
    }
}