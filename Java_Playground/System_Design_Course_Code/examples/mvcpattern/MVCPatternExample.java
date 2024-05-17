import model.*;

public class MVCPatternExample {
    public static void main(String[] args) {
        Console xbox = new Console("Xbox", 500.00);
        Console ps = new Console("Play Station", 400.00);

        VideoGame godOfWar = new VideoGame("God of War", ps);
        VideoGame halo = new VideoGame("Halo Reach",  xbox);

        System.out.println(xbox.getType());
        System.out.println(ps.getType());
        System.out.println(godOfWar.getConsoleType());
        System.out.println(halo.getConsoleType());
    }
}