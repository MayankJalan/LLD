package foodDeliveryService;

import java.util.UUID;

public class DeliveryAgent {
    private final String id;
    private final String name;
    private final String email;
    private final String phone;
    private boolean available;
    private Location currentLocation;
    public DeliveryAgent(String name, String email, String phone, Location location) {
        this.id= UUID.randomUUID().toString();
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.available = true;
        this.currentLocation = location;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    public synchronized boolean isAvailable() {
        return available;
    }

    public synchronized void assign() {
        if (!available) throw new IllegalStateException("Already assigned");
        available = false;
    }
    public synchronized void release() {
        available = true;
    }
    public String getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public Location getCurrentLocation() {
        return currentLocation;
    }
}
