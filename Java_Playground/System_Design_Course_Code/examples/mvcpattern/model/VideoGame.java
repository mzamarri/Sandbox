package model;

public class VideoGame extends GameObject {
    private static String type = "VideoGame";
    private String consoleType;

    public VideoGame(String name, Console console) {
        super(name);
        this.consoleType = console.getName();
    }

    public String getType() {
        return super.getType(type);
    }

    public String getConsoleType() {
        return this.consoleType;
    }
}