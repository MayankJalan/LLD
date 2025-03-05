package InventoryManagementSystem;

import java.util.ArrayList;
import java.util.List;

public class ProductCategory {
    public int productcategoryId;
    public String categoryName;
    public List<Product> products=new ArrayList<>();
    double price;

    public void addProduct(Product product){
        products.add(product);
    }
    public void removeProduct(int count){
        for(int i=1;i<=count;i++){
            products.remove(0);
        }
    }
}
