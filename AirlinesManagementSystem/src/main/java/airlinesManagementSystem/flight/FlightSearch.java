package airlinesManagementSystem.flight;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

public class FlightSearch {
    private final List<Flight> flights;

    public FlightSearch(List<Flight> flights) {
        this.flights = flights;
    }

    public List<Flight> search(String source, String destination, LocalDate date) {
        return flights.stream().filter(f ->
                f.getSource().equals(source) &&
                f.getDestination().equals(destination) &&
                f.getDepartureTime().toLocalDate().equals(date)
        ).collect(Collectors.toList());
    }
}
