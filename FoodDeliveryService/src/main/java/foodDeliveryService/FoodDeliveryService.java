package foodDeliveryService;

import foodDeliveryService.order.Order;
import foodDeliveryService.order.OrderItem;
import foodDeliveryService.order.OrderStatus;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class FoodDeliveryService {
    private static FoodDeliveryService instance;
    private final Map<String, Customer> customers;
    private final Map<String, Restaurant> restaurants;
    private final Map<String, Order> orders;
    private final Map<String, DeliveryAgent> deliveryAgents;

    private FoodDeliveryService() {
        customers = new ConcurrentHashMap<>();
        restaurants = new ConcurrentHashMap<>();
        orders = new ConcurrentHashMap<>();
        deliveryAgents = new ConcurrentHashMap<>();
    }

    public static FoodDeliveryService getInstance() {
        if (instance == null) {
            synchronized (FoodDeliveryService.class) {
                if (instance == null) {
                    instance = new FoodDeliveryService();
                }
            }
        }
        return instance;
    }

    public Customer registerCustomer(String name, String email, String phone) {
        Customer customer = new Customer(name, email, phone);
        customers.put(customer.getId(), customer);
        return customer;
    }

    public Restaurant registerRestaurant(String name, Location address) {
        Restaurant restaurant = new Restaurant(name, address);
        restaurants.put(restaurant.getId(), restaurant);
        return restaurant;
    }

    public DeliveryAgent registerDeliveryAgent(String name, String email, String phone, Location location) {
        DeliveryAgent agent = new DeliveryAgent(name, email, phone, location);
        deliveryAgents.put(agent.getId(), agent);
        return agent;
    }

    public List<String> getAvailableRestaurants() {
        List<String> restaurantNames = new ArrayList<>();
        for (Restaurant restaurant : restaurants.values()) {
            restaurantNames.add(restaurant.getName());
        }
        return restaurantNames;
    }

    public List<String> getRestaurantMenu(String restaurantId) {
        List<String> restaurantMenu = new ArrayList<>();
        Restaurant restaurant = restaurants.get(restaurantId);
        if (restaurant != null) {
            for (MenuItem menuItem : restaurant.getMenu().values()) {
                restaurantMenu.add(menuItem.getMenuItem());
            }
        }
        return restaurantMenu;
    }

    public void addMenuItem(String restaurantId, String name, String description, double price) {
        Restaurant restaurant = restaurants.get(restaurantId);
        if (restaurant == null) throw new IllegalArgumentException("Invalid restaurant");
        restaurant.addMenuItem(new MenuItem(name, description, price));
    }

    public Order placeOrder(String userId, String restaurantId, List<String> itemNames, List<Integer> quantity) {
        Restaurant restaurant=restaurants.get(restaurantId);
        Customer customer=customers.get(userId);
        if(customer==null || restaurant==null){
            throw new IllegalStateException("Customer / Restaurant not found");
        }
        List<OrderItem> orderItems=new ArrayList<>();
        Map<String,MenuItem> menuItems=restaurant.getMenu();
        for(int i=0;i<itemNames.size();i++){
            MenuItem menuItem=menuItems.get(itemNames.get(i));
            OrderItem orderItem=new OrderItem(menuItem,quantity.get(i));
            orderItems.add(orderItem);
        }

        Order order=new Order(customer,restaurant,orderItems);
        orders.put(order.getId(),order);
        notifyRestaurant(order);

        return order;

    }

    public void updateOrderStatus(String orderId, OrderStatus status) {
        Order order=orders.get(orderId);
        if (order != null) {
            order.updateStatus(status);
            System.out.println("Order " + orderId + " updated to " + status);

            notifyCustomer(order);

            if (status == OrderStatus.DELIVERED && order.getDeliveryAgent() != null) {
                order.getDeliveryAgent().release();
            }
        }

    }
    public void cancelOrder(String orderId) {
        Order order=orders.get(orderId);

        if(order != null){
            order.updateStatus(OrderStatus.CANCELLED);
            notifyRestaurant(order);
            notifyCustomer(order);
            System.out.println("Order cancelled: " + order.getId());
        }

    }

    public synchronized void assignDeliveryAgent(String orderId) {
        Order order = orders.get(orderId);

        if(order == null) {
            throw new IllegalArgumentException("Order not found");
        }

        for (DeliveryAgent agent : deliveryAgents.values()) {
            if (agent.isAvailable()) {
                if(agent.getCurrentLocation().distanceTo(order.getRestaurant().getLocation())< 60000) {
                    agent.assign();
                    order.assignDeliveryAgent(agent);
                    notifyDeliveryAgent(order);
                    System.out.println("Agent " + agent.getName() + " assigned to order " + orderId);
                    return;
                }
            }
        }
        throw new IllegalStateException("No available delivery agent");
    }

    private void notifyCustomer(Order order) {
        // Send notification to the customer about the order status update
        // ...
    }

    private void notifyRestaurant(Order order) {
        // Send notification to the restaurant about the new order or order status update
        // ...
    }

    private void notifyDeliveryAgent(Order order) {
        // Send notification to the delivery agent about the assigned order
        // ...
    }
}


