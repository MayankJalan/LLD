import enums.City;
import enums.SeatCategory;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class BookMyShow {
    MovieController movieController;
    TheaterController theaterController;

    BookMyShow() {
        movieController = new MovieController();
        theaterController = new TheaterController();
    }

    public static void main(String[] args) {
        BookMyShow bookMyShow = new BookMyShow();
        bookMyShow.initialize();
        bookMyShow.createBooking(City.BANGALORE, "Bahuballi");
        bookMyShow.createBooking(City.BANGALORE, "Bahuballi");

    }

    private void createBooking(City city, String movieName) {

        //1. search movie by my location
        List<Movie> moviesInCity=movieController.getAllMoviesByCity(city);

        //2. select the movie which you want to see. i want to see Baahubali
        Movie intrestedMovie=null;
        for(Movie movie : moviesInCity){
            if(movie.getMovieName().equals(movieName)){
                intrestedMovie=movie;
                break;
            }
        }
        //3. get all show of this movie in Bangalore location
        List<Show> shows=new ArrayList<>();
        Map<Theater,List<Show>> showsTheatreWise=theaterController.getAllShows(intrestedMovie,city);

        //4. select the particular show user is interested in
        Map.Entry<Theater,List<Show>> entry = showsTheatreWise.entrySet().iterator().next();
        List<Show> runningShows = entry.getValue();
        Show interestedShow = runningShows.get(0);

        //5. select the seat
        int seatNumber=40;
        List<Integer> bookedSeats = interestedShow.getBookedSeatIds();
        if(!bookedSeats.contains(seatNumber)){
            bookedSeats.add(seatNumber);
            Booking booking=new Booking();
            booking.setBookingId("1321aj23");
            List<Seat> myBookedSeats = new ArrayList<>();
            for(Seat seat : interestedShow.getScreen().getSeatList()){
                if(seat.getSeatId()==seatNumber){
                    myBookedSeats.add(seat);
                }
            }
            interestedShow.setBookedSeatIds(bookedSeats);
            booking.setBookedSeatList(myBookedSeats);
            booking.setShow(interestedShow);
        }
        else {
            //throw exception
            System.out.println("seat already booked, try again");
            return;

        }
        System.out.println("BOOKING SUCCESSFUL");


    }

    private void initialize() {

        //create movies
        createMovies();        //create theater with screens, seats and shows
        createTheatre();
    }

    private void createTheatre() {
        Movie avengers = movieController.getMovieByName("Avengers");
        Movie bahuballi= movieController.getMovieByName("Bahuballi");

        Theater inox=new Theater();
        inox.setTheaterId(1);
        inox.setScreens(createScreens());
        inox.setAddress("Outer Ring Road, Bengaluru");
        inox.setCity(City.BANGALORE);
        List<Show> inoxShows = new ArrayList<>();
        Show inoxMorningShow = createShows(1, inox.getScreens().get(0), avengers, 8);
        Show inoxEveningShow = createShows(2, inox.getScreens().get(0), bahuballi, 16);
        inoxShows.add(inoxEveningShow);
        inoxShows.add(inoxMorningShow);
        inox.setShows(inoxShows);


        Theater pvr=new Theater();
        pvr.setTheaterId(2);
        pvr.setScreens(createScreens());
        pvr.setAddress("Rajiv Chowk, Delhi");
        pvr.setCity(City.DELHI);
        List<Show> pvrShows = new ArrayList<>();
        Show pvrMorningShow = createShows(1, pvr.getScreens().get(0), avengers, 13);
        Show pvrEveningShow = createShows(2, pvr.getScreens().get(0), bahuballi, 21);
        pvrShows.add(pvrEveningShow);
        pvrShows.add(pvrMorningShow);
        pvr.setShows(pvrShows);

        theaterController.addTheater(pvr,City.DELHI);
        theaterController.addTheater(inox,City.BANGALORE);

    }

    private Show createShows(int i, Screen screen, Movie movie, int showStartTime) {
        Show show=new Show();
        show.setShowId(i);
        show.setScreen(screen);
        show.setMovie(movie);
        show.setShowTime(showStartTime);
        return show;
    }

    private List<Screen> createScreens() {
        List<Screen> screens=new ArrayList<>();

        Screen screen1=new Screen();
        screen1.setSreenId(1);
        screen1.setSeatList(createSeats());
        screens.add(screen1);
        return screens;
    }
    private List<Seat> createSeats() {
        List<Seat> seats=new ArrayList<>();
        //SILVER
        for(int i=0;i<40;i++){
            Seat seat=new Seat();
            seat.setSeatId(i);
            seat.setRow(i/10);
            seat.setSeatCategory(SeatCategory.SILVER);
            seats.add(seat);
        }
        //GOLD
        for(int i=40;i<70;i++){
            Seat seat=new Seat();
            seat.setSeatId(i);
            seat.setRow(i/10);
            seat.setSeatCategory(SeatCategory.SILVER);
            seats.add(seat);
        }

        //PLATINUM
        for(int i=70;i<80;i++){
            Seat seat=new Seat();
            seat.setSeatId(i);
            seat.setRow(i/10);
            seat.setSeatCategory(SeatCategory.PLATINUM);
            seats.add(seat);
        }
        return seats;

    }
    private void createMovies() {
        Movie avengers=new Movie();
        avengers.setMovieId(1);
        avengers.setMovieName("Avengers");
        avengers.setMovieDurationInMinutes(180);

        Movie bahuballi=new Movie();
        bahuballi.setMovieId(2);
        bahuballi.setMovieName("Bahuballi");
        bahuballi.setMovieDurationInMinutes(200);

        movieController.addMovie(avengers,City.BANGALORE);
        movieController.addMovie(avengers,City.DELHI);

        movieController.addMovie(bahuballi,City.BANGALORE);
        movieController.addMovie(bahuballi,City.DELHI);
    }


}