// package observerpattern;

public class Subscriber implements Observer {
    private String blogState;
    private String name;

    public Subscriber(String name) {
        this.name = name;
    }

    public void update(String newState) {
        System.out.println(this.name + " - Updating...");
        this.blogState = newState;
        printNewestPost();
    }

    public void printNewestPost() {
        System.out.println("Post \n\n" + blogState + "\n");
    }
}