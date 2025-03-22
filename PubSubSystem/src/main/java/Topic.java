import java.util.HashSet;
import java.util.Set;

public class Topic {
    private final String name;
    Set<Subscriber> subscribers;

    public Topic(String name) {
        this.name = name;
        subscribers=new HashSet<>();
    }

    public String getName() {
        return name;
    }
    public void addSubscriber(Subscriber subscriber) {
        subscribers.add(subscriber);
    }

    public void removeSubscriber(Subscriber subscriber) {
        subscribers.remove(subscriber);
    }

    public void publish(Message message) {
        for (Subscriber subscriber : subscribers) {
            subscriber.onMessage(message);
        }
    }
}
