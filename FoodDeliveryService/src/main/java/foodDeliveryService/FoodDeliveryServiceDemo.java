package foodDeliveryService;

import foodDeliveryService.order.Order;
import foodDeliveryService.order.OrderStatus;

import java.util.List;

public class FoodDeliveryServiceDemo {
    public static void main(String[] args) {
        FoodDeliveryService foodDeliveryService = FoodDeliveryService.getInstance();

        // Register customers
        Customer customer1 = foodDeliveryService.registerCustomer("John Doe", "john@example.com", "1234567890");
        Customer customer2 = foodDeliveryService.registerCustomer("Jane Smith", "jane@example.com", "9876543210");

        // Register restaurants
        Restaurant restaurant1 = foodDeliveryService.registerRestaurant("Restaurant 1", new Location(111,222));
        foodDeliveryService.addMenuItem(restaurant1.getId(), "Burger", "Delicious burger", 9.99);
        foodDeliveryService.addMenuItem(restaurant1.getId(), "Sushi", "Cheesy pizza", 12.99);

        Restaurant restaurant2 = foodDeliveryService.registerRestaurant("Restaurant 2", new Location(112,223));
        foodDeliveryService.addMenuItem(restaurant2.getId(), "Pizza", "Fresh sushi", 15.99);
        foodDeliveryService.addMenuItem(restaurant2.getId(), "Ramen", "Delicious ramen", 10.99);

        // Register delivery agents
        DeliveryAgent agent1 = foodDeliveryService.registerDeliveryAgent("Agent 1","dn@gmail.com", "9999999999",new Location(111,223));
        DeliveryAgent agent2 = foodDeliveryService.registerDeliveryAgent("Agent 2", "ndnjd@gmail.com","8888888888",new Location(111,224));


        // Get Available Restaurants
        for (String restaurantNames: foodDeliveryService.getAvailableRestaurants()) {
            System.out.println(restaurantNames);
        }

        // Get Restaurant Menu
        for (String restaurantMenu: foodDeliveryService.getRestaurantMenu(restaurant2.getId())) {
            System.out.println(restaurantMenu);
        }

        // Place an order
        Order order = foodDeliveryService.placeOrder(customer1.getId(), restaurant1.getId(), List.of("Burger", "Sushi"),List.of(2,3));

        // Update order status
        foodDeliveryService.updateOrderStatus(order.getId(), OrderStatus.CONFIRMED);

        // Assign Delivery Agent
        foodDeliveryService.assignDeliveryAgent(order.getId());

        // Cancel an order
        Order order2 = foodDeliveryService.placeOrder(customer2.getId(), restaurant1.getId(), List.of("Burger", "Sushi"),List.of(4,6));
        foodDeliveryService.cancelOrder(order2.getId());


    }

}
