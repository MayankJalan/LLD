package uber;

import uber.payment.CreditCardPayment;

public class RideServiceDemo {
    public static void main(String[] args) {
        RideService rideSharingService = RideService.getInstance();

        Passenger passenger1 = rideSharingService.registerPassenger("John Doe", "1234567890");
        Passenger passenger2 = rideSharingService.registerPassenger("Jane Smith", "9876543210");

        Driver driver1 = rideSharingService.registerDriver("Alice Johnson", "4567890123", "ABC123", new Location(37.7749, -122.4194));
        Driver driver2 = rideSharingService.registerDriver("Bob Williams", "7890123456", "XYZ789", new Location(37.7860, -122.4070));

        // Passenger 1 requests a ride
        Trip trip1 = rideSharingService.requestRide(passenger1.getId(), new Location(32.7887, -112.4098), new Location(37.7887, -122.4098));

        // Driver 1 accepts the ride
        rideSharingService.acceptRide(driver1.getId(), trip1.getId());

        // Start the ride
        rideSharingService.startRide(trip1.getId());

        // Complete the ride
        rideSharingService.completeRide(trip1.getId());

        // Make payment
        rideSharingService.makePayment(trip1.getId(), new CreditCardPayment());

        // Passenger 2 requests a ride
        Trip trip2 = rideSharingService.requestRide(passenger1.getId(), new Location(27.7749, -102.6124), new Location(37.7749, -122.4194));

        // Driver 2 accepts the ride
        rideSharingService.acceptRide(driver2.getId(), trip2.getId());

        // Passenger 2 cancels the ride
        rideSharingService.cancelRide(trip2.getId());



    }
}
