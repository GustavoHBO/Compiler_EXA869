package util;

import model.Token;

import java.util.List;

public class SyntheticAnalyzerRestart {
    private List<Token> tokenList;
    private Token currentToken;
    private Token previousToken;
    int valueIndex = -1;

    public void start(List tokens){
        this.tokenList = tokens;

    }

    public void nextToken(){
    }

}
