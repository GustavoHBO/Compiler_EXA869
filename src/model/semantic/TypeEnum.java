package model.semantic;

public enum TypeEnum {

    FLOAT("float"),
    STRING("string"),
    INT("int"),
    BOOL("bool");


    private final String name;

    TypeEnum(String name){
        this.name = name;
    }

    public static TypeEnum getTypeLexema(String type, String value) {
        if (type.matches("String")) return STRING;
        if (type.matches("Digit")) return INT;
        if (type.matches("Number") && value.contains(".")) return FLOAT;
        if (type.matches("Number")) return INT;
        return null;
    }

    public String getName() {
        return name;
    }

    public static TypeEnum getType(String type){
        for(TypeEnum e : TypeEnum.values()){
            if (e.name.equals(type)) return e;
        }
        return null;
    }

    public static String getRegex(){
        return "Digit|Number|String|void|bool|float|string|int";
    }

    @Override
    public String toString() {
        return name;
    }
}
