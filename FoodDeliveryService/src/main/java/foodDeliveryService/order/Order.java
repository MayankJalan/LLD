package foodDeliveryService.order;

import foodDeliveryService.Customer;
import foodDeliveryService.DeliveryAgent;
import foodDeliveryService.Restaurant;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

public class Order {
    private final String id;
    private final Customer customer;
    private final Restaurant restaurant;
    private final List<OrderItem> orderItems;
    private OrderStatus status;
    private DeliveryAgent deliveryAgent;

    public Order(Customer customer, Restaurant restaurant, List<OrderItem> orderItems) {
        this.id= UUID.randomUUID().toString();
        this.customer = customer;
        this.restaurant = restaurant;
        this.orderItems = orderItems;
        this.status = OrderStatus.PENDING;
    }

    public void addItem(OrderItem item) {
        orderItems.add(item);
    }

    public void removeItem(OrderItem item) {
        orderItems.remove(item);
    }

    public void upadateItem(OrderItem item,int quantity) {
        orderItems.stream().filter(i -> i.getMenuItem().equals(item.getMenuItem()))
                .findFirst().ifPresent(i -> i.setQuantity(quantity));
    }
    public void updateStatus(OrderStatus status) {
        this.status = status;
    }

    public void assignDeliveryAgent(DeliveryAgent agent) {
        deliveryAgent = agent;
    }
    public String getId() {
        return id;
    }
    public OrderStatus getStatus() {
        return status;
    }
    public DeliveryAgent getDeliveryAgent() {
        return deliveryAgent;
    }

    public Restaurant getRestaurant() {
        return restaurant;
    }
}
