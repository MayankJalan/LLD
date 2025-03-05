package InventoryManagementSystem;

import java.util.HashMap;
import java.util.Map;

public class Cart {
    Map<Integer, Integer> productCategoryIdVsCountMap;

    public Cart() {
        productCategoryIdVsCountMap = new HashMap<>();
    }

    public void addItemInCart(int productCategoryId, int count) {
        productCategoryIdVsCountMap.put(productCategoryId, productCategoryIdVsCountMap.getOrDefault(productCategoryId, 0) + count);
    }

    public void removeItemFromCart(int productCategoryId, int count) {

        int itemCount=productCategoryIdVsCountMap.getOrDefault(productCategoryId,0);
        if(itemCount>count){
            productCategoryIdVsCountMap.put(productCategoryId,itemCount-count);
        }
        else
            productCategoryIdVsCountMap.remove(productCategoryId);

    }

    public void emptyCart() {
        productCategoryIdVsCountMap = new HashMap<>();
    }

    //View Cart
    public Map<Integer, Integer> getCartItems() {
        return productCategoryIdVsCountMap;
    }

}
