package concertBooking;

import concertBooking.seat.Seat;

import java.util.List;

public class Booking {
    private final String id;
    private final User user;
    private final Concert concert;
    private final List<Seat> seats;
    private final double totalPrice;
    private BookingStatus status;
    public Booking(String id, User user, Concert concert, List<Seat> seats) {
        this.id = id;
        this.user = user;
        this.concert = concert;
        this.seats = seats;
        this.totalPrice = calculateTotalPrice(seats);
        status =BookingStatus.PENDING;
    }

    private double calculateTotalPrice(List<Seat> seats) {
        return seats.stream().mapToDouble(seat -> seat.getPrice())
                .sum();
    }
    public void confirmBooking() {
        if (status == BookingStatus.PENDING) {
            status = BookingStatus.CONFIRMED;
            // Send booking confirmation to the user
            // ...
        }
    }

    public void cancelBooking() {
        if (status == BookingStatus.CONFIRMED) {
            status = BookingStatus.CANCELLED;
            seats.forEach(Seat::release);
            System.out.printf("Booking %s cancelled\n", id);
            // Send booking cancellation notification to the user
            // ...
        }
    }


    public void setStatus(BookingStatus status) {
        this.status = status;
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public Concert getConcert() {
        return concert;
    }

    public List<Seat> getSeats() {
        return seats;
    }

    public double getTotalPrice() {
        return totalPrice;
    }

    public BookingStatus getStatus() {
        return status;
    }
}
