// package observerpattern;

import java.util.ArrayList;

public abstract class Subject {
    private ArrayList<Observer> observers = new ArrayList<>();
    protected String state;

    public void registerObserver(Observer observer) {
        observers.add(observer);
        observer.update(state);
    }

    public void unregisterObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer o : this.observers) {
            o.update(state);
        }
    }
}