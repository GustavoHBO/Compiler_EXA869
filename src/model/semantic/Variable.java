package model.semantic;

import model.Token;
import util.TokenEnum;

public class Variable {
    private String name;
    private String tipo;
    private Boolean constant;


    public Variable(){this.constant = false;}

    public Variable(String name, String tipo, Boolean constant) {
        this.name = name;
        this.tipo = tipo;
        this.constant = constant;
    }

    public String getNome() {
        return name;
    }

    public void setNome(String nome) {
        this.name = nome;
    }

    public String getType() {
        return tipo;
    }

    public void setType(String tipo) {
        this.tipo = tipo;
    }

    public Boolean isConstant() {
        return constant;
    }

    public void setConstant(Boolean constant) {
        this.constant = constant;
    }
}
