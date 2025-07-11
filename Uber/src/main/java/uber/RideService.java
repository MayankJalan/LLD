package uber;

import uber.payment.Payment;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class RideService {
    private static RideService instance;

    private final Map<String, Driver> drivers;
    private final Map<String, Passenger> passengers;
    private final Map<String, Trip> trips;

    private RideService() {
        drivers = new ConcurrentHashMap<>();
        passengers = new ConcurrentHashMap<>();
        trips = new ConcurrentHashMap<>();
    }

    public static synchronized RideService getInstance() {
        if (instance == null) {
            instance = new RideService();
        }
        return instance;
    }

    public Driver registerDriver(String name, String contact, String licensePlate, Location location) {
        Driver driver = new Driver(name, contact, licensePlate, location);
        drivers.put(driver.getId(), driver);
        return driver;
    }

    public void updateDriverLocation(String driverId, Location location) {
        Driver driver = drivers.get(driverId);
        if (driver != null) {
            driver.updateLocation(location);
        } else {
            throw new IllegalArgumentException("Driver not found");
        }
    }

    public Passenger registerPassenger(String name, String contact) {
        Passenger passenger = new Passenger(name, contact);
        passengers.put(passenger.getId(), passenger);
        return passenger;
    }

    public Trip requestRide(String passengerId, Location fromLocation, Location toLocation) {
        Passenger passenger = passengers.get(passengerId);
        if (passenger == null) {
            throw new IllegalStateException("Passenger Not Found");
        }
        Trip trip = new Trip(passenger, fromLocation, toLocation);
        notifyNearbyDrivers(trip);

        trips.put(trip.getId(), trip);
        return trip;

    }

    public void acceptRide(String driverId, String tripId) {
        Driver driver = drivers.get(driverId);
        Trip trip = trips.get(tripId);
        if (trip.getStatus() == TripStatus.REQUESTED) {
            Passenger passenger=trip.getPassenger();
            trip.assignDriver(driver);
            trip.setStatus(TripStatus.ACCEPTED);
            passenger.assignTrip(trip);
            driver.assignTrip(trip);
            notifyPassenger(trip);
            System.out.printf("Trip started: %s (Driver: %s -> Rider: %s)%n",
                    trip.getId(), driver.getName(), passenger.getName());
        }

    }

    public void startRide(String tripId) {
        Trip trip = trips.get(tripId);
        if (trip.getStatus() == TripStatus.ACCEPTED) {
            trip.setStatus(TripStatus.ONGOING);
            notifyPassenger(trip);

        }

    }

    public synchronized void completeRide(String tripId) {
        Trip trip = trips.get(tripId);
        Driver driver = trip.getDriver();
        Passenger passenger=trip.getPassenger();
        if (trip.getStatus() == TripStatus.ONGOING) {
            trip.complete();
            driver.completeTrip();
            passenger.completeTrip();

            double fare = calculateFare(trip);
            trip.setFare(fare);

            notifyPassenger(trip);
            notifyDriver(trip);
            System.out.printf("Trip %s completed%n", trip.getId());


        }

    }

    public void cancelRide(String tripId) {
        Trip trip = trips.get(tripId);
        if (trip.getStatus() == TripStatus.REQUESTED || trip.getStatus() == TripStatus.ACCEPTED) {
            trip.setStatus(TripStatus.CANCELLED);
            if (trip.getDriver() != null) {
                trip.getDriver().setStatus(DriverStatus.AVAILABLE);
            }
            notifyDriver(trip);
        }


    }

    public void makePayment(String tripId, Payment payment) {
        Trip trip = trips.get(tripId);
        double fare = trip.getFare();
        payment.processPayment(fare);
        trip.markPayment();
    }

    private void notifyNearbyDrivers(Trip trip) {
        double distance = trip.getDestination().distanceTo(trip.getSource());
        boolean foundNearbyAvailableDriver = false;
        for (Driver driver : drivers.values()) {
            if (driver.isAvailable() && driver.getLocation().distanceTo(trip.getSource()) < 5000.0) {  //5km

                System.out.println("Notifying driver: " + driver.getName() + " about ride request: " + trip.getId());
                foundNearbyAvailableDriver = true;

            }
        }
        if (!foundNearbyAvailableDriver) {
            throw new IllegalStateException("No available drivers");
        }
    }

    private void notifyDriver(Trip trip) {
        Driver driver = trip.getDriver();
        if (driver != null) {
            String message = switch (trip.getStatus()) {
                case COMPLETED -> "Ride completed. Fare: $" + trip.getFare();
                case CANCELLED -> "Ride cancelled by passenger";
                default -> "";
            };
            // Send notification to the driver
            System.out.println("Notifying driver: " + driver.getName() + " - " + message);
        }
    }


    private void notifyPassenger(Trip trip) {
        Passenger passenger = trip.getPassenger();
        String message = switch (trip.getStatus()) {
            case ACCEPTED -> "Your ride has been accepted by driver: " + trip.getDriver().getName();
            case ONGOING -> "Your ride is in progress";
            case COMPLETED -> "Your ride has been completed. Fare: $" + trip.getFare();
            case CANCELLED -> "Your ride has been cancelled";
            default -> "";
        };
        // Send notification to the passenger
        System.out.println("Notifying rider: " + passenger.getName() + " - " + message);

    }

    private double calculateFare(Trip trip) {
        double baseFare = 2.0;
        double perKmFare = 1.5;

        double distance = trip.getSource().distanceTo(trip.getDestination());

        double fare = baseFare + (distance * perKmFare);
        return Math.round(fare * 100.0) / 100.0; // Round to 2 decimal places
    }


}
