package model.semantic;

import exception.semantic.OperationNotSupportedException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Method implements Generic{
    private List<Variable> variables;
    private List<Variable> parameters;
    private String name;
    private String returnType;

    public Method() {
        this.variables = new ArrayList<>();
        this.parameters = new ArrayList<>();
    }

    public void addParameters(Variable v){
        parameters.add(v);
    }

    public void setReturnType(String type){
        this.returnType = type;
    }

    @Override
    public void addVariable(Variable v) {
        variables.add(v);
    }

    @Override
    public void addMethods(Method m) throws OperationNotSupportedException {
        throw new OperationNotSupportedException();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Variable> getParameters() {
        return parameters;
    }

    public String getReturnType() {
        return returnType;
    }

    public Variable findVariable(String name) {
        try {
            return variables.stream().filter(variable -> variable.getNome().equals(name)).collect(Collectors.toList()).get(0);
        } catch (Exception e){
            return null;
        }
    }
}
