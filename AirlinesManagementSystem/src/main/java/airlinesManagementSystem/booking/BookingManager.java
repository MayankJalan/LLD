package airlinesManagementSystem.booking;

import airlinesManagementSystem.Passenger;
import airlinesManagementSystem.flight.Flight;
import airlinesManagementSystem.seat.Seat;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.ReentrantLock;

public class BookingManager {
    private static BookingManager instance;
    private final Map<String,Booking> bookings;
    private final ReentrantLock lock=new ReentrantLock();
    private final AtomicInteger bookingCounter=new AtomicInteger(0);
    private BookingManager(){
        bookings=new HashMap<>();
    }
    public static synchronized BookingManager getInstance(){
        if(instance==null)
            instance=new BookingManager();
        return instance;
    }

    public Booking createBooking(Passenger passenger, Flight flight, Seat seat, Double amount){
        String bookingNumber=generateBookingNumber();
        Booking booking=new Booking(bookingNumber, passenger,flight,seat,amount);
        lock.lock();
        bookings.put(bookingNumber,booking);
        lock.unlock();
        return booking;
    }
    public void cancelBooking(String bookingNumber){
        lock.lock();
        Booking booking=bookings.get(bookingNumber);
        if(booking != null){
            booking.cancel();
        }
    }

    private String generateBookingNumber() {
        int bookingId=bookingCounter.incrementAndGet();
        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss"));
        return "BKG"+timeStamp+String.format("%06d",bookingId);
    }


}
