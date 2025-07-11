package uber;

import java.util.UUID;

public class Driver {
    String id;
    private String name;
    private String contact;

    private final String licensePlate;
    private Location location;
    private DriverStatus status;
    private Trip currentTrip;

    public Driver(String name, String contact, String licensePlate, Location location) {
        this.id=UUID.randomUUID().toString();
        this.name=name;
        this.contact=contact;
        this.licensePlate = licensePlate;
        this.location = location;
        this.status = DriverStatus.AVAILABLE;
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public synchronized void updateLocation(Location location) {
        this.location = location;
    }

    public synchronized boolean isAvailable() { return status == DriverStatus.AVAILABLE; }

    public synchronized void assignTrip(Trip trip) {
        this.currentTrip = trip;
        this.status = DriverStatus.BUSY;
    }

    public synchronized void completeTrip() {
        this.currentTrip = null;
        this.status = DriverStatus.AVAILABLE;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public void setStatus(DriverStatus status) {
        this.status = status;
    }

    public Trip getCurrentTrip() {
        return currentTrip;
    }

    public String getLicensePlate() {
        return licensePlate;
    }

    public Location getLocation() {
        return location;
    }

    public DriverStatus getStatus() {
        return status;
    }

}