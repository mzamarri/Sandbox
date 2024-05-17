

public class Follower implements Observer {
    private String followerName;

    public Follower(String name) {
        this.followerName = name;
    }

    public void update(String status) {
        System.out.println("New Update for " + this.followerName);
        System.out.println("New status:\n" + status);
    }
}