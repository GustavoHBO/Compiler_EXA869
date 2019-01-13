package model.semantic;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Global {
    private List<Variable> variables;
    private List<Class> classes;
    private List<Method> methods;

    public Global(){
        this.variables = new ArrayList();
        this.methods  = new ArrayList();
        this.classes  = new ArrayList();
    }

    public void addVariable(Variable v){
        variables.add(v);
    }

    public void addClass(Class c){
        classes.add(c);
    }

    public Class findClass(String s){
        try {
            return classes.stream().filter(aClass -> aClass.getName().equals(s)).collect(Collectors.toList()).get(0);
        } catch (IndexOutOfBoundsException e){
            return null;
        }
    }

    public List<Class> getClasses() {
        return classes;
    }

    public String getClassRegex(){
        StringBuilder s = new StringBuilder();
        classes.forEach(aClass -> s.append("|"+aClass.getName()));
        return s.toString().substring(1);
    }
}
