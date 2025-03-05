import enums.City;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public class MovieController {
    HashMap<City,List<Movie>> cityVsMovies;
    List<Movie> movies;

    MovieController(){
        cityVsMovies=new HashMap<>();
        movies=new ArrayList<>();
    }

    Movie getMovieByName(String movieName){
        for(Movie movie : movies){
            if(movie.getMovieName().equals(movieName)){
                return movie;
            }
        }
        return null;
    }

    List<Movie> getAllMoviesByCity(City city){
        return cityVsMovies.get(city);
    }

    void addMovie(Movie movie, City city) {

        movies.add(movie);
        List<Movie> movieList = cityVsMovies.getOrDefault(city, new ArrayList<>());
        movieList.add(movie);
        cityVsMovies.put(city, movieList);
    }

}
