package ParkingLot;

import ParkingLot.VehicleType.Vehicle;
import ParkingLot.VehicleType.VehicleType;

public class ParkingSpot {

    int spotNumber;
    Vehicle parkedVehicle;
    private final VehicleType vehicleType;

    public ParkingSpot(int spotNo, VehicleType vehicleType) {
        this.spotNumber = spotNo;
        this.vehicleType = vehicleType;
    }

    public synchronized boolean isAvailable() {
        return parkedVehicle == null;
    }

    public synchronized void parkVehicle(Vehicle vehicle) {
        parkedVehicle = vehicle;
    }
    public synchronized void unparkVehicle() {
        parkedVehicle = null;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public Vehicle getParkedVehicle() {
        return parkedVehicle;
    }

    public int getSpotNumber() {
        return spotNumber;
    }
}
