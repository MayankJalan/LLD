package restaurantManagementSystem;

import restaurantManagementSystem.payment.Payment;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

public class Restaurant {
    private static Restaurant instance;
    private final List<Reservation> reservations;
    private final List<MenuItem> menu;
    private final Map<String, Order> orders;
    private final List<Staff> staff;
    private final Map<String, Payment> payments;

    private Restaurant() {
        reservations = new CopyOnWriteArrayList<>();
        menu = new CopyOnWriteArrayList<>();
        orders = new ConcurrentHashMap<>();
        staff = new CopyOnWriteArrayList<>();
        payments = new ConcurrentHashMap<>();
    }

    public static synchronized Restaurant getInstance() {
        if (instance == null) {
            instance = new Restaurant();
        }
        return instance;
    }

    public void addMenuItem(MenuItem item) {
        menu.add(item);
    }

    public void removeMenuItem(MenuItem item) {
        menu.remove(item);
    }

    public List<MenuItem> getMenu() {
        return new ArrayList<>(menu);
    }

    public void placeOrder(Order order) {
        orders.put(order.getId(), order);
        notifyKitchen(order);
    }

    public void updateOrderStatus(String orderId, OrderStatus status) {
        Order order = orders.get(orderId);
        if (order != null) {
            order.setOrderStatus(status);
            notifyStaff(order);
        }
    }

    public void makeReservation(Reservation reservation) {
        reservations.add(reservation);
    }

    public void cancelReservation(Reservation reservation) {
        reservations.remove(reservation);
    }

    public void processPayment(Payment payment) {
        payments.put(payment.getId(), payment);
        // Process the payment through a payment gateway
        // ...
    }

    public void addStaff(Staff staff) {
        this.staff.add(staff);
    }

    public void removeStaff(Staff staff) {
        this.staff.remove(staff);
    }

    private void notifyKitchen(Order order) {
        // Notify kitchen staff to prepare the order
        // ...
        System.out.println("Kitchen notified about order : {} " + order.getId());
    }

    private void notifyStaff(Order order) {
        // Notify relevant staff about the order status update
        // ...
        System.out.println("Staff notified about order : {} " + order.getId());

    }
}