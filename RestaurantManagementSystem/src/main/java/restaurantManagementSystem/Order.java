package restaurantManagementSystem;

import java.sql.Timestamp;
import java.util.List;

public class Order {
    private final String id;
    private final List<MenuItem> items;
    private final double amount;
    private OrderStatus orderStatus;
    private final Timestamp timestamp;

    public Order(String id, List<MenuItem> items, double amount,OrderStatus orderStatus, Timestamp timestamp) {
        this.id = id;
        this.items = items;
        this.amount = amount;
        this.orderStatus=orderStatus;
        this.timestamp = timestamp;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getId() {
        return id;
    }

    public List<MenuItem> getItems() {
        return items;
    }

    public double getAmount() {
        return amount;
    }

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public Timestamp getTimestamp() {
        return timestamp;
    }
}
