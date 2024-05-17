// package observerpattern;

public class Blog extends Subject {
    public Blog(String post) {
        this.state = post;
    }

    public String getState() {
        return this.state;
    }

    public void setState(String state) {
        this.state = state;
        notifyObservers();
    }
} 