public class Subscriber {
    private final String name;

    public Subscriber(String name) {
        this.name = name;
    }

    public void onMessage(Message message) {
        System.out.println("Subscriber " + name + " received message: " + message.getContent());
    }
}
