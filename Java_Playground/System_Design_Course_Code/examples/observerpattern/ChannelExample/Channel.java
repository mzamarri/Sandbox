

import java.util.ArrayList;

public class Channel implements Subject {
    private static ArrayList<Observer> observers = new ArrayList<>();
    private String channelName;
    private String status;

    public Channel(String channelName) {
        this.channelName = channelName;
    }

    public void registerObserver(Observer observer) {
        observers.add(observer);
    }

    public void unregisterObserver(Observer observer) {
        observers.remove(observer);
    }

    public void notifyObservers() {
        for (Observer o : observers) {
            o.update(this.status);
        }
    }

    public void setStatus(String newStatus) {
        this.status = newStatus;
        notifyObservers();
    }

    public String getStatus() {
        return this.status;
    }
}