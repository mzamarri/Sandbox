public class Main {
    public static String[] msg = {"Hello, World!"};
    public static void main(String[] arg) { 
        print(msg);
    }

    public static void print(String[] msg) {
        for (String m : msg) {
            System.out.println(m);
        }
    }
}

