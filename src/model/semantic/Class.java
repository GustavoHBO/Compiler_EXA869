package model.semantic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Class implements Generic{
    private String name;
    private List<Variable> variables;
    private List<Method> methods;
    private Class extended;

    public Class(){
        this.variables = new ArrayList<>();
        this.methods = new ArrayList<>();
    }

    @Override
    public void addVariable(Variable v){
        variables.add(v);
    }

    @Override
    public void addMethods(Method m) {
        methods.add(m);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Method findMethod(String name){
        List<Method> temp = methods;
        if (extended != null){
            temp.addAll(extended.methods);
        }
        try {
            return temp.stream().filter(method -> method.getName().equals(name)).collect(Collectors.toList()).get(0);
        } catch (Exception e){
            return null;
        }

    }

    public Variable findVariable(String name) {
        List<Variable> temp = variables;

        if (extended != null) {
            temp.addAll(extended.variables);
        }
        try {
            return temp.stream().filter(variable -> variable.getNome().equals(name)).collect(Collectors.toList()).get(0);
        } catch (Exception e){
            return null;
        }
    }

    public void setExtended(Class extended) {
        this.extended = extended;
    }

    public Class getExtended() {
        return extended;
    }
}
