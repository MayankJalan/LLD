package concertBooking;

import concertBooking.seat.Seat;
import concertBooking.seat.SeatNotAvailableException;
import concertBooking.seat.SeatStatus;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class ConcertManagement {
    private static ConcertManagement instance;
    private final Map<String, Booking> bookings;
    private final Map<String, Concert> concerts;

    private ConcertManagement() {
        bookings = new ConcurrentHashMap<>();
        concerts = new ConcurrentHashMap<>();
    }

    public synchronized static ConcertManagement getInstance() {
        if (instance == null)
            instance = new ConcertManagement();
        return instance;
    }

    public void addConcert(Concert concert) {
        concerts.put(concert.getId(), concert);
    }

    public Concert getConcert(String concertId) {
        return concerts.get(concertId);
    }

    public List<Concert> searchConcerts(String artist, String venue, LocalDateTime dateTime) {
        return concerts.values().stream().
                filter(concert -> concert.getArtist().equalsIgnoreCase(artist) ||
                        concert.getVenue().equalsIgnoreCase(venue) ||
                        concert.getTime().equals(dateTime))
                .collect(Collectors.toList());
    }
    public synchronized Booking bookTickets(User user, Concert concert, List<Seat> seats) {
        for(Seat seat : seats){
            if(seat.getStatus() != SeatStatus.AVAILABLE){
                throw new SeatNotAvailableException("Seat is already booked or reserved.");
            }
        }

        seats.forEach(Seat::book);
        // creatingBooking
        String bookingId=generateBookingId();
        Booking booking=new Booking(bookingId,user,concert,seats);
        bookings.put(bookingId,booking);

        //processPayment
        if(processPayment(booking.getTotalPrice())) {
            booking.confirmBooking();
            System.out.println("Booking " + booking.getId() + " - " + booking.getSeats().size() + " seats booked");

        }

        return booking;
    }
    public void cancelBooking(String bookingId) {
        Booking booking = bookings.get(bookingId);
        if (booking != null) {
            booking.cancelBooking();
            bookings.remove(bookingId);
        }
    }

    private boolean processPayment(double totalPrice) {
        //processPayment
        return true;
    }

    private String generateBookingId() {
        return "BKG"+UUID.randomUUID();
    }


}
