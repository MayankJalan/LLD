package airlinesManagementSystem;

import airlinesManagementSystem.booking.Booking;
import airlinesManagementSystem.booking.BookingManager;
import airlinesManagementSystem.flight.Flight;
import airlinesManagementSystem.flight.FlightSearch;
import airlinesManagementSystem.payment.Payment;
import airlinesManagementSystem.payment.PaymentProcessor;
import airlinesManagementSystem.seat.Seat;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class AirlineManagementSystem {
    private final List<Flight> flights;
    private final List<Aircraft> aircrafts;
    private final FlightSearch flightSearch;
    private final BookingManager bookingManager;
    private final PaymentProcessor paymentProcessor;

    public AirlineManagementSystem() {
        flights = new ArrayList<>();
        aircrafts = new ArrayList<>();
        flightSearch = new FlightSearch(flights);
        bookingManager = BookingManager.getInstance();
        paymentProcessor = PaymentProcessor.getInstance();
    }

    public void addFlight(Flight flight) {
        flights.add(flight);
    }

    public void addAircraft(Aircraft aircraft) {
        aircrafts.add(aircraft);
    }

    public List<Flight> searchFlights(String source, String destination, LocalDate date) {
        return flightSearch.search(source, destination, date);
    }
    public Booking bookFlight(Flight flight, Passenger passenger, Seat seat, double price) {
        return bookingManager.createBooking( passenger,flight, seat, price);
    }

    public void cancelBooking(String bookingNumber) {
        bookingManager.cancelBooking(bookingNumber);
    }

    public void processPayment(Payment payment) {
        paymentProcessor.processPayment(payment);
    }
}
