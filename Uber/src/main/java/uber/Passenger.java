package uber;

import java.util.UUID;

public class Passenger {
    private final String id;
    private final String name;
    private final String contact;
    private final Location location;
    private Trip currentTrip;

    public Passenger( String name, String contact ) {
        this.id = UUID.randomUUID().toString();
        this.name = name;
        this.contact = contact;
        this.location = null;
        this.currentTrip=null;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getContact() {
        return contact;
    }

    public Location getLocation() {
        return location;
    }
    public synchronized void assignTrip(Trip trip) {
        this.currentTrip = trip;
    }

    public synchronized void completeTrip() {
        this.currentTrip = null;
    }


}
