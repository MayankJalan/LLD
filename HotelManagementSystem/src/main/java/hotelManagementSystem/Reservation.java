package hotelManagementSystem;

import java.time.LocalDate;

public class Reservation {

    private final String id;
    private final Guest guest;
    private final Room room;
    private final LocalDate checkinDate;
    private final LocalDate checkOutDate;
    private ReservationStatus status;

    public Reservation(String id, Guest guest, Room room, LocalDate checkinDate, LocalDate checkOutDate) {
        this.id = id;
        this.guest = guest;
        this.room = room;
        this.checkinDate = checkinDate;
        this.checkOutDate = checkOutDate;
        this.status = ReservationStatus.CONFIRMED;
    }
    public synchronized void cancel(){
        if(status.equals(ReservationStatus.CONFIRMED)){
            status=ReservationStatus.CANCELLED;
            room.checkout();
        }
        else{
            throw new IllegalStateException("Reservation is not confirmed");
        }
    }

    public String getId() {
        return id;
    }

    public Guest getGuest() {
        return guest;
    }

    public Room getRoom() {
        return room;
    }

    public LocalDate getCheckinDate() {
        return checkinDate;
    }

    public LocalDate getCheckOutDate() {
        return checkOutDate;
    }

    public ReservationStatus getStatus() {
        return status;
    }
}
