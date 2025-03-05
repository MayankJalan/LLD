package ParkingLot;

import ParkingLot.VehicleType.Vehicle;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    private static ParkingLot instance;
    private final List<Levels> levels;

    private ParkingLot() {
        levels = new ArrayList<>();
    }

    public static synchronized ParkingLot getInstance() {
        if (instance == null) {
            instance = new ParkingLot();
        }
        return instance;
    }

    public void addLevel(Levels level) {
        levels.add(level);
    }

    public boolean parkVehicle(Vehicle vehicle) {
        for(Levels level : levels){
            if(level.parkVehicle(vehicle)){
                System.out.println("Vehicle parked successfully.");
                return true;
            }
        }
        System.out.println("Could not Park Vehicle");
        return false;

    }


    public boolean unparkVehicle(Vehicle vehicle) {
        for(Levels level : levels){
            if(level.unparkVehicle(vehicle)){
                return true;
            }
        }
        return false;
    }

    public void displayAvailability() {
        for(Levels level : levels){
            level.displayAvailability();
        }
    }
}

