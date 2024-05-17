

public class ChannelExample {
    public static void main(String[] args) {
        Observer obs1 = new Follower("obs 1");
        Observer obs2 = new Follower("obs 2");

        Channel channel = new Channel("Mikes Awesome Videos");

        channel.registerObserver(obs1);
        channel.registerObserver(obs2);

        channel.setStatus("Live Now! Streaming L4D2!!!");
        
    }
}