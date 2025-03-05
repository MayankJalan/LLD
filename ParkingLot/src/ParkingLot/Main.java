package ParkingLot;

import ParkingLot.VehicleType.Bike;
import ParkingLot.VehicleType.Car;
import ParkingLot.VehicleType.Truck;
import ParkingLot.VehicleType.Vehicle;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Vehicle car = new Car("CAR123");
        Vehicle truck = new Truck("TRUCK123");
        Vehicle motorcycle = new Bike("BIKE123");

        ParkingLot parkingLot=ParkingLot.getInstance();
        parkingLot.addLevel(new Levels(1,80));
        parkingLot.addLevel(new Levels(2,100));

        parkingLot.parkVehicle(car);
        parkingLot.parkVehicle(truck);
        parkingLot.parkVehicle(motorcycle);

        parkingLot.displayAvailability();

        parkingLot.unparkVehicle(car);

        System.out.println("\n**********************\n");
        parkingLot.displayAvailability();



    }
}