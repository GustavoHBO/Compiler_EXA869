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

import controller.Controller;
import java.io.IOException;
import java.util.ArrayList;
import junit.framework.TestCase;
import model.Token;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import util.TokenEnum;

/**
 *
 * @author gustavo
 */
public class ControllerUnitTest extends TestCase{
    
    Controller controller = Controller.getInstance();
    ArrayList<Token> list_tokens_keywords_test;
    ArrayList<Token> list_tokens_identifiers_test;
    
    public ControllerUnitTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        list_tokens_keywords_test = new ArrayList<>();
        int i = 1;
        Token token = new Token(TokenEnum.KEYWORD, i++, 0, "class");
        list_tokens_keywords_test.add(token);
        token = new Token(TokenEnum.KEYWORD, i++, 0, "const");
        list_tokens_keywords_test.add(token);
        token = new Token(TokenEnum.KEYWORD, i++, 0, "variables");
        list_tokens_keywords_test.add(token);
        token = new Token(TokenEnum.KEYWORD, i++, 0, "method");
        list_tokens_keywords_test.add(token);
        token = new Token(TokenEnum.KEYWORD, i++, 0, "return");
        list_tokens_keywords_test.add(token);
        token = new Token(TokenEnum.KEYWORD, i++, 0, "main");
        list_tokens_keywords_test.add(token);
        token = new Token(TokenEnum.KEYWORD, i++, 0, "if");
        list_tokens_keywords_test.add(token);
        token = new Token(TokenEnum.KEYWORD, i++, 0, "then");
        list_tokens_keywords_test.add(token);
        token = new Token(TokenEnum.KEYWORD, i++, 0, "else");
        list_tokens_keywords_test.add(token);
        token = new Token(TokenEnum.KEYWORD, i++, 0, "while");
        list_tokens_keywords_test.add(token);
        token = new Token(TokenEnum.KEYWORD, i++, 0, "read");
        list_tokens_keywords_test.add(token);
        token = new Token(TokenEnum.KEYWORD, i++, 0, "write");
        list_tokens_keywords_test.add(token);
        token = new Token(TokenEnum.KEYWORD, i++, 0, "void");
        list_tokens_keywords_test.add(token);
        token = new Token(TokenEnum.KEYWORD, i++, 0, "int");
        list_tokens_keywords_test.add(token);
        token = new Token(TokenEnum.KEYWORD, i++, 0, "float");
        list_tokens_keywords_test.add(token);
        token = new Token(TokenEnum.KEYWORD, i++, 0, "bool");
        list_tokens_keywords_test.add(token);
        token = new Token(TokenEnum.KEYWORD, i++, 0, "string");
        list_tokens_keywords_test.add(token);
        token = new Token(TokenEnum.KEYWORD, i++, 0, "true");
        list_tokens_keywords_test.add(token);
        token = new Token(TokenEnum.KEYWORD, i++, 0, "false");
        list_tokens_keywords_test.add(token);
        token = new Token(TokenEnum.KEYWORD, i++, 0, "extends");
        list_tokens_keywords_test.add(token);
        
        i = 1;
        list_tokens_identifiers_test = new ArrayList<>();
        token = new Token(TokenEnum.IDENTIFIER, i, 0, "camisa");
        list_tokens_identifiers_test.add(token);
        token = new Token(TokenEnum.SYMBOL, i, 6, " ");
        list_tokens_identifiers_test.add(token);
        token = new Token(TokenEnum.IDENTIFIER, i, 7, "sapato");
        list_tokens_identifiers_test.add(token);
        token = new Token(TokenEnum.IDENTIFIER, ++i, 0, "aqui");
        list_tokens_identifiers_test.add(token);
        token = new Token(TokenEnum.SYMBOL, i, 4, " ");
        list_tokens_identifiers_test.add(token);
        token = new Token(TokenEnum.IDENTIFIER, i, 5, "nao");
        list_tokens_identifiers_test.add(token);
        token = new Token(TokenEnum.SYMBOL, i, 8, " ");
        list_tokens_identifiers_test.add(token);
        token = new Token(TokenEnum.IDENTIFIER, i, 9, "tem");
        list_tokens_identifiers_test.add(token);
        token = new Token(TokenEnum.SYMBOL, i, 12, " ");
        list_tokens_identifiers_test.add(token);
        token = new Token(TokenEnum.IDENTIFIER, i, 13, "hash");
        list_tokens_identifiers_test.add(token);
        token = new Token(TokenEnum.IDENTIFIER, ++i, 0, "oo");
        list_tokens_identifiers_test.add(token);
        token = new Token(TokenEnum.SYMBOL, i, 2, " ");
        list_tokens_identifiers_test.add(token);
        token = new Token(TokenEnum.IDENTIFIER, i, 3, "bruxo");
        list_tokens_identifiers_test.add(token);
        token = new Token(TokenEnum.SYMBOL, i, 8, " ");
        list_tokens_identifiers_test.add(token);
        token = new Token(TokenEnum.IDENTIFIER, i, 9, "esta");
        list_tokens_identifiers_test.add(token);
        token = new Token(TokenEnum.SYMBOL, i, 13, " ");
        list_tokens_identifiers_test.add(token);
        token = new Token(TokenEnum.IDENTIFIER, i, 14, "presente");
        list_tokens_identifiers_test.add(token);
        token = new Token(TokenEnum.IDENTIFIER, ++i, 0, "rapaz");
        list_tokens_identifiers_test.add(token);
        token = new Token(TokenEnum.SYMBOL, i, 5, " ");
        list_tokens_identifiers_test.add(token);
        token = new Token(TokenEnum.IDENTIFIER, i, 6, "que");
        list_tokens_identifiers_test.add(token);
        token = new Token(TokenEnum.SYMBOL, i, 9, " ");
        list_tokens_identifiers_test.add(token);
        token = new Token(TokenEnum.IDENTIFIER, i, 10, "coisa");
        list_tokens_identifiers_test.add(token);
        token = new Token(TokenEnum.IDENTIFIER, ++i, 0, "chata");
        list_tokens_identifiers_test.add(token);
        token = new Token(TokenEnum.IDENTIFIER, ++i, 0, "criar");
        list_tokens_identifiers_test.add(token);
        token = new Token(TokenEnum.IDENTIFIER, ++i, 0, "teste");
        list_tokens_identifiers_test.add(token);
        token = new Token(TokenEnum.SYMBOL, i, 5, " ");
        list_tokens_identifiers_test.add(token);
        token = new Token(TokenEnum.IDENTIFIER, i, 6, "de");
        list_tokens_identifiers_test.add(token);
        token = new Token(TokenEnum.IDENTIFIER, ++i, 0, "unidade");
        list_tokens_identifiers_test.add(token);
    }

    // TODO add test methods here.
    // The methods must be annotated with annotation @Test. For example:
    //
    @Test
    public void testAnalyzeFileKeywords() {
        String file_path = "./test/";
        Token token_test, token;
        try {
            ArrayList<Token> list_tokens = controller.analyzeFile(file_path, "ControllerUnitTestKeywords.txt");
            assertEquals(list_tokens_keywords_test.size(), list_tokens.size());
            for (int i = 0; i < list_tokens_keywords_test.size(); i++) {
                token_test = list_tokens_keywords_test.get(i);
                token = list_tokens.get(i);
                assertEquals(token_test.getType(), token.getType());
                assertEquals(token_test.getLine(), token.getLine());
                assertEquals(token_test.getColumn(), token.getColumn());
                assertEquals(token_test.getString(), token.getString());
            }
        } catch (IOException ex) {
            assertTrue(false);
        }
    }
    
    @Test
    public void testAnalyzeFileIndentifiers() {
        String file_path = "./test/";
        Token token_test, token;
        try {
            ArrayList<Token> list_tokens = controller.analyzeFile(file_path, "ControllerUnitTestIdentifiers.txt");
            assertEquals(list_tokens_identifiers_test.size(), list_tokens.size());
            for (int i = 0; i < list_tokens_identifiers_test.size(); i++) {
                System.out.println(i);
                token_test = list_tokens_identifiers_test.get(i);
                token = list_tokens.get(i);
                assertEquals(token_test.getType(), token.getType());
                assertEquals(token_test.getLine(), token.getLine());
                assertEquals(token_test.getColumn(), token.getColumn());
                assertEquals(token_test.getString(), token.getString());
            }
        } catch (IOException ex) {
            assertTrue(false);
        }
    }
}
