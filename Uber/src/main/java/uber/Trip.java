package uber;

import java.util.UUID;

public class Trip {
    private final String id;
    private final Passenger passenger;
    private Driver driver;
    private final Location source;
    private final Location destination;
    private TripStatus status;
    private double fare;
    PaymentStatus paymentStatus;
    public Trip( Passenger passenger, Location source, Location destination) {
        this.id = UUID.randomUUID().toString();
        this.passenger = passenger;
        this.source = source;
        this.destination = destination;
        this.status = TripStatus.REQUESTED;
        this.paymentStatus = PaymentStatus.PENDING;
    }

    public void complete() {
        this.status = TripStatus.COMPLETED;
    }

    public void markPayment() {
        this.paymentStatus = PaymentStatus.COMPLETED;
    }

    public void assignDriver(Driver driver) {
        this.driver = driver;
    }

    public void setStatus(TripStatus status) {
        this.status = status;
    }

    public void setFare(double fare) {
        this.fare = fare;
    }

    public String getId() {
        return id;
    }

    public Passenger getPassenger() {
        return passenger;
    }

    public Driver getDriver() {
        return driver;
    }

    public Location getSource() {
        return source;
    }

    public Location getDestination() {
        return destination;
    }

    public TripStatus getStatus() {
        return status;
    }

    public double getFare() {
        return fare;
    }
}
