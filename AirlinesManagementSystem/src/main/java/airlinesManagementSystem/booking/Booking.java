package airlinesManagementSystem.booking;

import airlinesManagementSystem.Passenger;
import airlinesManagementSystem.flight.Flight;
import airlinesManagementSystem.seat.Seat;

public class Booking {
    private final String bookingNumber;
    private final Passenger passenger;
    private final Flight flight;
    private final Seat seat;
    private final double amount;
    private  BookingStatus bookingStatus;

    public Booking(String bookingNumber, Passenger passenger, Flight flight, Seat seat, double amount) {
        this.bookingNumber = bookingNumber;
        this.passenger = passenger;
        this.flight = flight;
        this.seat = seat;
        this.amount = amount;
        bookingStatus=BookingStatus.CONFIRMED;
    }
    public void cancel(){
        bookingStatus=BookingStatus.CANCELLED;
    }

    public String getBookingNumber() {
        return bookingNumber;
    }
}
