// package observerpattern;

public class ObserverPatternExample {
    public static void main(String[] args) {
        Observer obs1 = new Subscriber("Obs 1");
        Observer obs2 = new Subscriber("Obs 2");

        Blog blog = new Blog("My first Post!");
        

        blog.registerObserver(obs1);
        blog.registerObserver(obs2);

        blog.setState("Post is now changed!");
    }
}