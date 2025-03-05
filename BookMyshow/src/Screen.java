import java.util.List;

public class Screen {
    int sreenId;
    List<Seat> seatList;

    public int getSreenId() {
        return sreenId;
    }

    public void setSreenId(int sreenId) {
        this.sreenId = sreenId;
    }

    public List<Seat> getSeatList() {
        return seatList;
    }

    public void setSeatList(List<Seat> seatList) {
        this.seatList = seatList;
    }
}
