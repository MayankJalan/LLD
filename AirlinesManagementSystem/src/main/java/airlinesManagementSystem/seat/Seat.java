package airlinesManagementSystem.seat;

public class Seat {
    private final String id;
    private final SeatType seatType;
    private SeatStatus status;

    public Seat(String id, SeatType seatType) {
        this.id = id;
        this.seatType = seatType;
        status=SeatStatus.AVAIABLE;
    }
    public void reserve(){
        this.status=SeatStatus.RESERVED;
    }
    public void release(){
        this.status=SeatStatus.AVAIABLE;
    }
}
