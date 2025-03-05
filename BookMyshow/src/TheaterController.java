import enums.City;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TheaterController {
    HashMap<City,List<Theater>> cityVsTheater;
    List<Theater> allTheater;
    public TheaterController() {
        cityVsTheater=new HashMap<>();
        allTheater=new ArrayList<>();
    }
    void addTheater(Theater theater, City city){
        allTheater.add(theater);

        List<Theater> theaters=cityVsTheater.getOrDefault(city, new ArrayList<>());
        theaters.add(theater);
        cityVsTheater.put(city,theaters);
    }

    Map<Theater, List<Show>> getAllShows(Movie movie, City city){
        List<Theater> theaters=cityVsTheater.get(city);
        Map<Theater, List<Show>> theatreVsShows = new HashMap<>();
        for(Theater theater : theaters){

            List<Show> givenMovieShows = new ArrayList<>();
            List<Show> shows=theater.getShows();
            for(Show show : shows){
                if(show.getMovie().getMovieId() == movie.getMovieId()){
                    givenMovieShows.add(show);
                }
            }
            if(givenMovieShows.size()>0){
                theatreVsShows.put(theater,givenMovieShows);
            }
        }
        return theatreVsShows;
    }

}
