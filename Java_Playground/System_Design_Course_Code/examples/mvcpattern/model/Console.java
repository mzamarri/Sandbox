package model;

public class Console extends GameObject {
    private static String type = "Console";

    public Console(String name, double price) {
        super(name);
    }
    
    public String getType() {
        return super.getType(type);
    }
}