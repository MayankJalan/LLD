package hotelManagementSystem;

import hotelManagementSystem.payment.Payment;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class HotelManagementSystem {
    private static HotelManagementSystem instance;
    private final Map<String, Guest> guests;
    private final Map<String, Room> rooms;
    private final Map<String, Reservation> reservations;
    private final Map<String, List<Reservation>> roomBookings;

    private HotelManagementSystem() {
        guests = new ConcurrentHashMap<>();
        rooms = new ConcurrentHashMap<>();
        reservations = new ConcurrentHashMap<>();
        roomBookings = new ConcurrentHashMap<>();
    }

    public synchronized static HotelManagementSystem getInstance() {
        if (instance == null) {
            instance = new HotelManagementSystem();
        }
        return instance;
    }

    public void addGuest(Guest guest) {
        guests.put(guest.getId(), guest);
    }

    public Guest getGuest(String guestId) {
        return guests.get(guestId);
    }

    public void addRoom(Room room) {
        rooms.put(room.getId(), room);
        roomBookings.put(room.getId(), new ArrayList<>());
    }

    public Room getRoom(String roomId) {
        return rooms.get(roomId);
    }

    public synchronized boolean isRoomAvailable(Room room, LocalDate checkInDate, LocalDate checkoutDate) {
        List<Reservation> bookings = roomBookings.get(room.getId());
        for (Reservation res : bookings) {
            if (!(checkoutDate.isBefore(res.getCheckinDate()) || checkInDate.isAfter(res.getCheckOutDate()))) {
                return false; // Overlapping reservation found
            }
        }
        return true;
    }

    public synchronized Reservation bookRoom(Guest guest, Room room, LocalDate checkInDate, LocalDate checkoutDate) {
        if (isRoomAvailable(room, checkInDate, checkoutDate)) {
            room.book();
            String reservationId = generateReservationId();
            Reservation reservation = new Reservation(reservationId, guest, room, checkInDate, checkoutDate);
            reservations.put(reservationId, reservation);
            roomBookings.get(room.getId()).add(reservation);
            return reservation;
        }
        return null;
    }

    public synchronized void cancelReservation(String reservationId) {
        Reservation reservation = reservations.get(reservationId);
        if (reservation != null) {
            reservation.cancel();
            reservations.remove(reservationId);
            roomBookings.get(reservation.getRoom().getId()).remove(reservation);
        }
    }

    public synchronized void checkIn(String reservationId) {
        Reservation reservation = reservations.get(reservationId);
        if (reservation != null && reservation.getStatus().equals(ReservationStatus.CONFIRMED)) {
            reservation.getRoom().checkin();
        } else {
            throw new IllegalStateException("Invalid Reservation or Reservation not confirmed");
        }
    }

    public synchronized void checkOut(String reservationId, Payment payment) {
        Reservation reservation = reservations.get(reservationId);
        if (reservation != null && reservation.getStatus().equals(ReservationStatus.CONFIRMED)) {
            Room room = reservation.getRoom();
            double amount = room.getPrice() * ChronoUnit.DAYS.between(reservation.getCheckinDate(), reservation.getCheckOutDate());
            if (payment.processPayment(amount)) {
                room.checkout();
                reservations.remove(reservationId);
                roomBookings.get(room.getId()).remove(reservation);
            } else {
                throw new IllegalStateException("Payment Failed");
            }
        } else {
            throw new IllegalStateException("Invalid reservation or reservation not confirmed");
        }
    }

    private String generateReservationId() {
        return "RES" + UUID.randomUUID().toString().substring(0, 8).toUpperCase();
    }
}
