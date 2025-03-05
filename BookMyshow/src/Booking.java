import java.util.List;

public class Booking {

    String bookingId;
    Show show;
    List<Seat> BookedSeatList;
    Payment payment;

    public String getBookingId() {
        return bookingId;
    }

    public void setBookingId(String bookingId) {
        this.bookingId = bookingId;
    }

    public Show getShow() {
        return show;
    }

    public void setShow(Show show) {
        this.show = show;
    }

    public List<Seat> getBookedSeatList() {
        return BookedSeatList;
    }

    public void setBookedSeatList(List<Seat> bookedSeatList) {
        this.BookedSeatList = bookedSeatList;
    }

    public Payment getPayment() {
        return payment;
    }

    public void setPayment(Payment payment) {
        this.payment = payment;
    }
}
