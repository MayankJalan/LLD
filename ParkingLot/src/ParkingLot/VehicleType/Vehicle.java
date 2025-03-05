package ParkingLot.VehicleType;

public abstract class Vehicle {
    String licensePlate;
    VehicleType vehicleType;

    public Vehicle(String licensePlate, VehicleType vehicleType) {
        this.licensePlate = licensePlate;
        this.vehicleType = vehicleType;
    }
    public VehicleType getType() {
        return vehicleType;
    }
}
