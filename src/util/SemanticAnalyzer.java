package util;

import model.Token;
import model.semantic.*;
import model.semantic.Class;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.PatternSyntaxException;

public class SemanticAnalyzer {

    private SemanticAnalyzerParse2 semanticAnalyzerParse2;

    private Global g;
    private Generic currentGeneric, previousGeneric;
    private Class currentClass;
    private Method currentMethod;
    private TypesRunTime typesRunTime;

    private List<Token> tokenList;
    private Token currentToken;
    private Token previousToken;
    int valueIndex = -1;

    private String lastIdentifier;
    private String lastType;

    public SemanticAnalyzer(){
        this.tokenList = null;
        this.currentToken = null;
        this.previousToken = null;
        this.g = new Global();
        this.typesRunTime = new TypesRunTime();
        this.semanticAnalyzerParse2 = new SemanticAnalyzerParse2();
    }

    public void start(List tokens) {
        this.tokenList = tokens;
        nextToken();
        variableOrConstant();
        classes();
        semanticAnalyzerParse2.start(tokens, g);
    }

    public void nextToken(){
        if(valueIndex + 1 < tokenList.size()){
            valueIndex++;
            previousToken = currentToken;
            currentToken = tokenList.get(valueIndex);
        }
    }

    private Boolean match(String type){
        try {
            if (currentToken.getString().equals(type) || currentToken.getType().getNAME().equals(type) || currentToken.getString().matches(type) || currentToken.getType().getNAME().matches(type)) {
                //if (currentToken.getType().getNAME().equals(type) && !currentToken.getString().equals(type)) return false;
                if (type.equals("Identifier")) {
                    lastIdentifier = currentToken.getString();
                }
                nextToken();
                return true;
            }
        } catch (PatternSyntaxException e){
            return false;
        }
        return false;
    }

    private Boolean matchWithoutNext(String type){
        if(currentToken.getType().getNAME().matches(type) || currentToken.getString().matches(type)){
            return true;
        }
        return false;
    }

    public void classes(){
        if (match("class") && match("Identifier")){
            Class c = new Class();
            c.setName(lastIdentifier);
            if (c.getName().equals("aluno")){
                System.out.println();
            }
            currentClass = c;
            changeGeneric(c);
            typesRunTime.addType(c.getName());
            g.addClass(c);
            if (match("extends") && match("Identifier")){
                Class temp = g.findClass(previousToken.getString());
                c.setExtended(temp);
                checkClass();
            } else {
                checkClass();
            }
        }
    }

    private void checkClass(){
        if (match("{")){
            variableOrConstant();
            method();
            if(!match("}")){
                panicMode();
            }
            classes();
        }

    }

    private void method() {
        if (match("method")) {
            Method m = new Method();
            if (match(TypeEnum.getRegex()) || match(typesRunTime.getRegex())) {
                m.setReturnType(previousToken.getString());
                if (match("Identifier")) {
                    m.setName(lastIdentifier);
                    currentMethod = m;
                    currentClass.addMethods(m);
                   // changeGeneric(m);
                    if (match("(")) {
                        checkParametersVariableMethod();
                        if (!match(")")) {
                            panicMode();
                        }
                        if (match("{")) {
                            variableForMethod();
                            int pilha = 1;
                            while(true){
                                if (match("{")) pilha++;
                                if (match("}")) pilha--;
                                if (pilha == 0) break;
                                nextToken();
                            }
                            //program();
                            method();
                        }
                    } else {
                        panicMode();
                    }
                } else {
                    panicMode();
                }
            } else {
                panicMode();
            }
        }
    }

    private void program() {
        System.out.println("Into in Program");
        while (!matchWithoutNext("}")) {
            checkWhile();
            variableAssignment();
        }
    }
    
    private boolean checkWhile(){
        if (match("while") && match("(")){
            checkConditionCallMethod();
            if (match(")")){
                
            }
        } else {
            return false;
        }
        return false;
    }


    //--------------------------------------------------------------------------------------------------------------
    private void checkConditionCallMethod(){
        relationalOperation();
    }

    private void variableAssignment(){
        if (match("Identifier") && match("=")){
            arithmeticOperation();
            if (match(";")){
                variableAssignment();
            }
        }
    }

    private Boolean arithmeticOperation(){
        if (match("(")){
            relationalOperation();
            if (match(")"));
        }
        if ((match("Identifier") || match("Number")) && match("Arithmetic Operator") && (match("Identifier") || match("Number"))){
            if (match("(")){
                relationalOperation();
                if (match(")"));
            }
            if (match(".") && match("Identifier")){
                return true;
            }
            if (match(".") && match("Identifier") && match("(") && match(")")){
                return true;
            }
        } else if ((match("Identifier")) && match("[") && (match("Identifier") || match("Number")) && match("]")) { // Vetor
            if (match("(")){
                relationalOperation();
                if (match(")"));
            }
            if (match(".") && match("Identifier")){
                return true;
            }
            if (match(".") && match("Identifier") && match("(") && match(")")){
                return true;
            }
        } else if(match("Number") || match("Identifier")){
            return true;
        }
        return false;
    }

    private Boolean relationalOperation(){
        if (match("(")){
            relationalOperation();
            if (match(")"));
        }
        if ((match("Identifier") || match("Number")) && match("Relational Operator") && (match("Identifier") || match("Number"))){
            if (match("(")){
                relationalOperation();
                if (match(")"));
            }
            if (match(".") && match("Identifier")){
                return true;
            }
            if (match(".") && match("Identifier") && match("(") && match(")")){
                return true;
            }
        } else if ((match("Identifier")) && match("[") && (match("Identifier") || match("Number")) && match("]")) { // Vetor
            if (match("(")){
                relationalOperation();
                if (match(")"));
            }
            if (match(".") && match("Identifier")){
                return true;
            }
            if (match(".") && match("Identifier") && match("(") && match(")")){
                return true;
            }
        }
        return false;
    }
    //--------------------------------------------------------------------------------------------------------------
    private void checkParametersCallMethod(){
        
    }

    public void checkParametersMethods(){
        if (match(TypeEnum.getRegex()) || match(typesRunTime.getRegex())){
            Variable v = new Variable();
            v.setType(previousToken.getString());
            if (match("Identifier")) {
                v.setNome(lastIdentifier);
                currentGeneric.addVariable(v);
                while (match(",")){
                    if (match(TypeEnum.getRegex())){
                        v = new Variable();
                        v.setType(previousToken.getString());
                        if (match("Identifier")) {
                            v.setNome(lastIdentifier);
                            currentGeneric.addVariable(v);
                        }
                    }

                }
            }
        }
    }

    private void checkParametersVariableMethod(){
        Variable v = new Variable();
        if (match(typesRunTime.getRegex()) || match(TypeEnum.getRegex())){ //check types
            v.setType(previousToken.getString());
            if (match("Identifier")){
                v.setNome(lastIdentifier);
                if (match("[") && (match("Identifier") || match("Number")) && match("]")){
                   currentMethod.addVariable(v);
                   currentMethod.addParameters(v);
                } else {
                   currentMethod.addVariable(v);
                   currentMethod.addParameters(v);
                }
                if (match(",")){
                    checkParametersVariableMethod();
                }
            } else {
                panicMode();
            }

        }
    }

    public void Program(){
        //callMethod();
    }

    private void variableOrConstant(){
        if (match("const")){
            if (match("{")) {
                checkConstant();
                if (!match("}")){
                    panicMode();
                }
            }
        } else if(match("variables")) {
            if (match("{")) {
                checkVariable();
                if (!match("}")){
                    panicMode();
                }
            }
        }
    }

    private void variableForMethod(){
        if(match("variables")) {
            if (match("{")) {
                checkVariableForMethod();
                if (!match("}")){
                    panicMode();
                }
            }
        }
    }
    private void checkVariableForMethod() {
        Variable v = new Variable();
        if (match("int|float|string|bool")){
            v.setType(previousToken.getString());
            if (match("Identifier")) {
                v.setNome(previousToken.getString());
                currentMethod.addVariable(v);
                if (match("=")){
                    Variable temp = new Variable();
                    if (match(TypeEnum.getRegex())){
                        lastType = previousToken.getString();
                        temp.setType(lastType);
                        if(temp.getType().equals(v.getType())){ //Checks equals type
                            while (match(",")){
                                if (match("Identifier")){
                                    currentMethod.addVariable(new Variable(lastIdentifier, lastType, true));
                                    nextToken();
                                    if (match("=") && match("Identifier")){
                                        //i still do not know
                                    } else {
                                        panicMode();
                                    }
                                }
                            }
                            if (match(";") && matchWithoutNext("int|float|string|bool")){
                                checkVariable();
                            } else if (!matchWithoutNext(";")){

                            }
                        } else {
                            panicMode();
                        }
                    }

                } else {
                    while (match(",")){
                        if (match("Identifier")){
                            currentMethod.addVariable(new Variable(lastIdentifier, lastType, false));
                            if (match("=") && match(TypeEnum.getRegex())) nextToken();
                            if (match(";")) checkVariable();
                        }
                    }
                    match(";");
                    checkVariable();
                }
            }
        }
    }

    private void checkVariable() {
        Variable v = new Variable();
        if (match("int|float|string|bool")){
            v.setType(previousToken.getString());
            if (match("Identifier")) {
                v.setNome(previousToken.getString());
                currentClass.addVariable(v);
                if (match("=")){
                    Variable temp = new Variable();
                    if (match(TypeEnum.getRegex())){
                        lastType = previousToken.getString();
                        temp.setType(lastType);
                        if(temp.getType().equals(v.getType())){ //Checks equals type
                            while (match(",")){
                                if (match("Identifier")){
                                    currentClass.addVariable(new Variable(lastIdentifier, lastType, true));
                                    nextToken();
                                    if (match("=") && match("Identifier")){
                                        //i still do not know
                                    } else {
                                        panicMode();
                                    }
                                }
                            }
                            if (match(";") && matchWithoutNext("int|float|string|bool")){
                                checkVariable();
                            } else if (!matchWithoutNext(";")){

                            }
                        } else {
                            panicMode();
                        }
                    }

                } else {
                    while (match(",")){
                        if (match("Identifier")){
                            currentClass.addVariable(new Variable(lastIdentifier, lastType, false));
                            if (match("=") && match(TypeEnum.getRegex())) nextToken();
                            if (match(";")) checkVariable();
                        }
                    }
                    match(";");
                    checkVariable();
                }
            }
        }
    }

    private void checkConstant(){
        Variable v = new Variable();
        v.setConstant(true);

        if (match("int|float|string|bool")){
            v.setType(previousToken.getString());
            lastType = previousToken.getString();
            if (match("Identifier")){
                v.setNome(previousToken.getString());
                g.addVariable(v);
                if (match("=")){
                    if (match("Digit|Number|String")){
                            while (match(",")){
                                if (match("Identifier") && match("=")){
                                    g.addVariable(new Variable(lastIdentifier, lastType, true));
                                    nextToken();
                                }
                            }
                            if (match(";") && matchWithoutNext("int|float|string|bool")){
                                checkConstant();
                            } else if (!matchWithoutNext(";")){

                            }
                    }
                }
            }
        }

    }

    private Boolean typeExistRegex(){
        return null;
    }

    private Boolean checkAssignment(Variable v, Variable t){
        return v.getType().equals(t.getType());
    }

    private void changeGeneric(Generic generic){
        this.previousGeneric = this.currentGeneric;
        this.currentGeneric = generic;

    }

    private void saveVariable(){

    }

    private void semanticErro(Token t) {
        System.out.println("Erro " + t);
    }

    private void panicMode(){

    }
}

class SemanticAnalyzerParse2 {
    private int valueIndex;
    private List<Token> tokenList;

    private ArrayList<String> tokenListErro;

    private Token currentToken;
    private Token previousToken;
    private String lastIdentifier;
    private String lastType;
    private Class currentClass;

    private Global global;

    public void start(List<Token> l, Global global) {
        valueIndex = -1; //Started tokens beginning
        this.tokenList = l;
        this.tokenListErro = new ArrayList<>();
        this.global = global;
        nextToken();
        checkConstant();
        checkClass();
        //output files
        saveFileErro();
    }

    private void saveFileErro() {
        try {
            WriteReadFile a = new WriteReadFile("files/semantic_output/", "output");
            if (tokenListErro.isEmpty()){
                a.putMessage("Sucesso");
            }else {
                a.saveMsgErro(tokenListErro);
            }
        } catch (Exception e){
            System.out.println(e);

        }
    }

    private void checkClass() {
        int pilha = 1;
        match("class");
        match("Identifier");
        currentClass = global.findClass(previousToken.getString());
        while (!match("{")){
            nextToken();
            if (previousToken.getString().equals("}") && currentToken.getString().equals("}")) return;
        }
        while (true){

            if (match("variables")){
                checkVariables();
            }
            if (match("method")){
                checkMethods();
            }


            if (match("{")) pilha++;
            if (match("}")) pilha--;
            if (pilha == 0) break;
            nextToken();
        }
        checkClass();
    }

    private void checkMethods() {
        if (match("int|float|string|bool|void") || match(global.getClassRegex())) {
            if (match("Identifier")) {
                Method m = findMethod(previousToken.getString());
                if (m.getName().equals("imprimir")){
                    System.out.println();
                }
                if (m != null) {
                    int pilha = 1;
                    while (!match("{")) nextToken();
                    if (match("variables")) while (!match("}")) nextToken();
                    while (pilha != 0) {
                        if (match("Identifier")) {
                            Variable b = findVariables(previousToken.getString());
                            if (b == null) b = m.findVariable(previousToken.getString());
                            if (b != null) {
                                if (match("=")) {
                                    if (match("Identifier")) {
                                        Method m1 = findMethod(previousToken.getString());
                                        if (m1 != null) {
                                            if (match("(")) {
                                                checkParameters(m1);
                                                if (!b.getType().equals(m1.getReturnType())) {
                                                    erro(currentToken, "Tipos incompativeis");
                                                }
                                            } else if (match("[")) {

                                            } else {
                                                Variable v1 = findVariables(previousToken.getString());
                                                if (v1 != null) {
                                                    if (!b.getType().equals(v1.getType())) {
                                                        erro(previousToken, "Tipos de variaveis incompativeis");
                                                    }
                                                } else {
                                                    erro(previousToken, "Variavel nao declarada");
                                                }
                                            }
                                        } else {
                                            erro(previousToken, "Não foi declarada");
                                        }
                                    }
                                }
                            } else {
                                erro(previousToken, "A variavel não foi declarada");
                            }
                        }

                        if (match("while")) {
                            checkWhile(m);
                        }
                        if (match("if")) {
                            checkMethods();
                        }

                        nextToken();


                        if (match("{")) pilha++;
                        if (match("}")) pilha--;
                    }
                } else {
                    erro(previousToken, "Não declarado");
                }
            }
        }

    }

    private void checkWhile(Method m){
        int pilha = 1;
        while (!match("{")) nextToken();
        if (match("variables")) while (!match("}")) nextToken();
        while (pilha != 0){
            if (match("Identifier")){
                Variable b = findVariables(previousToken.getString());
                if (b == null) b = m.findVariable(previousToken.getString());
                if (b != null){
                    if (match("=")){
                        if (match("Identifier")){
                            Method m1 = findMethod(previousToken.getString());
                            if (match("(")){
                                checkParameters(m1);
                                if (!b.getType().equals(m1.getReturnType())){
                                    erro(currentToken, "Tipos incompativeis");
                                }
                            } else if (match("[")){

                            } else {
                                Variable v1 = findVariables(previousToken.getString());
                                if (v1 != null){
                                    if (!b.getType().equals(v1.getType())){
                                        erro(previousToken, "Tipos de variaveis incompativeis");
                                    }
                                } else {
                                    erro(previousToken, "Variavel nao declarada");
                                }
                            }
                        }
                    }
                } else {
                    erro(previousToken, "A variavel não foi declarada");
                }
            }

            if (match("while")){
                checkMethods();
            }
            if (match("if")){
                checkMethods();
            }

            nextToken();


            if (match("{")) pilha++;
            if (match("}")) pilha--;
        }
    }

    private void checkVariables() {
        while (!match("}")){
            if (match("int|float|string|bool") || match(global.getClassRegex())) {
                lastType = previousToken.getString();
                if (match("Identifier") && match("=")) {
                    if (match("Identifier")){
                        if (match("(")){
                            Method m = findMethod(previousToken.getString());
                            if (m != null) { //Verifica se existe o metodo na classe
                                checkParameters(m);

                            } else {
                                erro(currentToken, "Método não declarado na classe");
                            }

                        }
                        String foundType = TypeEnum.getTypeLexema(previousToken.getType().getNAME(), previousToken.getString()).getName();
                        if(!lastType.equals(foundType)){
                            erro(currentToken, lastType, foundType);
                        }
                    }

                    if (match("new")){
                        if (match("Identifer")){
                            if (!lastType.equals(previousToken.getString())){
                                erro(currentToken, lastType, previousToken.getString());
                            }
                        }
                    }

                    if (match("Number")){
                        String foundType = TypeEnum.getTypeLexema(previousToken.getType().getNAME(), previousToken.getString()).getName();
                        if (!lastType.equals(foundType)){
                            erro(currentToken, lastType, foundType);
                        }
                    }
                }
            } else {
                nextToken();
            }
        }
        //nextToken();
    }

    private void checkParameters(Method m) {
        int indexParameter = 0;
        while (!match(")")){ //Até encontrar o fechamento dos parametros
            if (match("Identifier")){
                if (match("[")){
                    //Ainda para fazer
                } else if (match("(")){
                    checkParameters(m);
                } else {
                    Variable tempV = findVariables(currentToken.getString());
                    if (tempV != null){
                        if(!tempV.getType().equals(m.getParameters().get(indexParameter))){
                            erro(currentToken, "Tipos incompativeis");
                        }
                    } else {
                        erro(currentToken, "Variavel não foi declarada na classe");
                    }
                }
            }
            if (match("Number")){

            }
            nextToken();
            indexParameter++;
        }
    }

    public void nextToken(){
        if((valueIndex + 1) < tokenList.size()){
            valueIndex++;
            previousToken = currentToken;
            currentToken = tokenList.get(valueIndex);
        }
    }

    private void checkConstant(){
        while(true) {
            if (match("int|float|string")){
                lastType = previousToken.getString();
                if (match("Identifier")) {
                    if (match("=")) {
                        if (!lastType.equals(TypeEnum.getTypeLexema(currentToken.getType().getNAME(), currentToken.getString()).getName())){
                            erro(tokenList.get(valueIndex), lastType, TypeEnum.getTypeLexema(currentToken.getType().getNAME(), currentToken.getString()).getName());
                        }
                    }
                }
            }
            if (match("Identifier")) {
                if (match("=")) {
                    if (!lastType.equals(TypeEnum.getTypeLexema(currentToken.getType().getNAME(), currentToken.getString()).getName())){
                        erro(previousToken, lastType, TypeEnum.getTypeLexema(currentToken.getType().getNAME(), currentToken.getString()).getName());
                    }
                }
            }
            if (match("}")) break;
            nextToken();
        }
    }

    private void erro(Token currentToken, String expectedType, String foundType) {
        String msg = "Erro na palavra \'" + currentToken.getString() + "\' na linha " + currentToken.getLine() + " tipo não compativel. Esperava um \'" + expectedType + "\'" +
                " encontrou \'" + foundType + "\'";
        System.err.println(msg);
        tokenListErro.add(msg);
    }

    private void erro(Token currentToken, String erro) {
        String msg = "Erro na palavra \'" + currentToken.getString() + "\' na linha " + currentToken.getLine() + ". Erro: "+erro;
        System.err.println(msg);
        tokenListErro.add(msg);
    }


    private Method findMethod(String name) {
        try {
            return currentClass.findMethod(name);
        } catch (NullPointerException e){
            return null;
        }
    }

    private Variable findVariables(String name){
        try {
            return currentClass.findVariable(name);
        } catch (NullPointerException e){
            return null;
        }
    }

    private Boolean match(String type){
        try {
            if (currentToken.getString().equals(type) || currentToken.getType().getNAME().equals(type) || currentToken.getString().matches(type) || currentToken.getType().getNAME().matches(type)) {
                //if (currentToken.getType().getNAME().equals(type) && !currentToken.getString().equals(type)) return false;
                if (type.equals("Identifier")) {
                    lastIdentifier = currentToken.getString();
                }
                nextToken();
                return true;
            }
        } catch (PatternSyntaxException e){
            return false;
        }
        return false;
    }

    private Boolean matchWithoutNext(String type){
        if(currentToken.getType().getNAME().matches(type) || currentToken.getString().matches(type)){
            return true;
        }
        return false;
    }


}



