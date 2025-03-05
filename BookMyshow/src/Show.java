import java.util.ArrayList;
import java.util.List;

public class Show {
    int showId;
    Movie Movie;
    int showTime;
    Screen screen;
    List<Integer> bookedSeatIds = new ArrayList<>();

    public int getShowId() {
        return showId;
    }
    public void setShowId(int showId) {
        this.showId = showId;
    }
    public Movie getMovie() {
        return Movie;
    }
    public void setMovie(Movie movie) {
        Movie = movie;
    }
    public int getShowTime() {
        return showTime;
    }
    public void setShowTime(int showTime) {
        this.showTime = showTime;
    }
    public List<Integer> getBookedSeatIds() {
        return bookedSeatIds;
    }
    public void setBookedSeatIds(List<Integer> bookedSeatIds) {
        this.bookedSeatIds = bookedSeatIds;
    }

    public Screen getScreen() {
        return screen;
    }

    public void setScreen(Screen screen) {
        this.screen = screen;
    }
}
