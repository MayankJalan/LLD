package InventoryManagementSystem;

import java.util.ArrayList;
import java.util.List;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Main mainObj =new Main();
        List<Warehouse> warehouseList = new ArrayList<>();
        warehouseList.add(mainObj.addWarehouseAndItsInventory());

        //2. create users in the system
        List<User> userList = new ArrayList<>();
        userList.add(mainObj.createUser());

        //3. feed the system with the initial informations
        ProductDeliverySystem productDeliverySystem = new ProductDeliverySystem(userList, warehouseList);


        mainObj.runDeliveryFlow(productDeliverySystem, 1);


    }

    private void runDeliveryFlow(ProductDeliverySystem productDeliverySystem, int userId) {

        //1. Get the user object
        User user = productDeliverySystem.getUser(userId);

        //2. get warehouse based on user preference
        Warehouse warehouse = productDeliverySystem.getWarehouse(new NearestWarehouseSelectionStrategy());

        //3. get all the inventory to show the user
        Inventory inventory = productDeliverySystem.getInventory(warehouse);

        ProductCategory productCategoryIWantToOrder = null;
        for(ProductCategory productCategory : inventory.productCategoryList){
            if(productCategory.categoryName.equals("Peppsii Large Cold Drink")){
                productCategoryIWantToOrder = productCategory;
            }
        }
        productDeliverySystem.addProductToCart(user,productCategoryIWantToOrder,1);

        Order order = productDeliverySystem.placeOrder(user, warehouse);

        productDeliverySystem.checkout(order);











    }

    private User createUser() {
        User user = new User();
        user.userId = 1;
        user.userName = "Mayank Jalan";
        user.address = new Address(230011, "city", "state");
        return user;
    }

    private Warehouse addWarehouseAndItsInventory() {
        Warehouse warehouse = new Warehouse();

        Inventory inventory = new Inventory();

        inventory.addCategory(0001, "Peppsii Large Cold Drink" , 100);
        inventory.addCategory(0004, "Doovee small Soap" , 50);

        //CREATE 3 Products

        Product product1 = new Product();
        product1.id = 1;
        product1.productName = "Peepsii";

        Product product2 = new Product();
        product2.id = 2;
        product2.productName = "Peepsii";

        Product product3 = new Product();
        product1.id = 3;
        product1.productName = "Doovee";

        inventory.addProduct(product1, 0001);
        inventory.addProduct(product2, 0001);
        inventory.addProduct(product3, 0004);

        warehouse.inventory = inventory;
        return warehouse;

    }
}