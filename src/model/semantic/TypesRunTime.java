package model.semantic;

import java.util.ArrayList;
import java.util.List;

public class TypesRunTime {
    private List<String> types;

    public TypesRunTime(){
        types = new ArrayList<>();
    }

    public void addType(String type){
        types.add(type);
    }

    public String getRegex(){
        StringBuilder regex = new StringBuilder();
        types.forEach(s -> {
             regex.append("|"+s);
        });
        return regex.toString().substring(1);
    }
}
