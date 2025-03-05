package CarRental;

import CarRental.Product.Bike;
import CarRental.Product.Car;
import CarRental.Product.Vehicle;
import CarRental.Product.VehicleType;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        List<User> users = addUsers();
        List<Vehicle> vehicles = addVehicles();
        List<Store> stores = addStores(vehicles);

        VehicleRentalSystem rentalSystem = new VehicleRentalSystem(stores, users);

        //User comes
        User user = users.get(0);
        //1. user search store based on location
        Location location=new Location(800001,"Patna","Bihar","India");
        Store store = rentalSystem.getStore(location);

        //2. get All vehicles you are interested in (based upon different filters)
        List<Vehicle> storeVehicles = store.getVehicles(VehicleType.CAR);

        //3.reserving the particular vehicle
        Reservation reservation = store.createReservation(storeVehicles.get(0), user);

        //4. generate the bill
        Bill bill = new Bill(reservation);

        //5. make payment
        Payment payment = new Payment();
        payment.payBill(bill);

        //6. trip completed, submit the vehicle and close the reservation
        store.completeReservation(reservation.reservationId);
    }

    private static List<Store> addStores(List<Vehicle> vehicles) {
        List<Store> stores=new ArrayList<>();
        Store store=new Store();
        store.storeId=1;
        store.setVehicles(vehicles);
        stores.add(store);
        return stores;
    }

    private static List<Vehicle> addVehicles() {
        List<Vehicle> vehicles = new ArrayList<>();

        Vehicle vehicle1 = new Car();
        vehicle1.setVehicleID(1);
        vehicle1.setVehicleType(VehicleType.CAR);

        Vehicle vehicle2 = new Car();
        vehicle1.setVehicleID(2);
        vehicle1.setVehicleType(VehicleType.CAR);

        vehicles.add(vehicle1);
        vehicles.add(vehicle2);

        return vehicles;
    }

    private static List<User> addUsers() {
        User user1=new User(1,"Mayank","BR01343");
        User user2=new User(2,"Kush", "HR91231");
        List<User> users=new ArrayList<>();
        users.add(user1);
        users.add(user2);
        return users;
    }

}