package InventoryManagementSystem;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Inventory {
    List<ProductCategory> productCategoryList;

    public Inventory() {
        productCategoryList = new ArrayList<>();
    }

    public void addCategory(int categoryId, String name, double price) {
        ProductCategory productCategory=new ProductCategory();
        productCategory.productcategoryId=categoryId;
        productCategory.categoryName=name;
        productCategory.price=price;
        productCategoryList.add(productCategory);
    }

    public void addProduct(Product product, int productCategoryId){
        for(ProductCategory productCategory : productCategoryList){
            if(productCategory.productcategoryId == productCategoryId){
                productCategory.products.add(product);
                break;
            }
        }

    }

    //remove product from the category
    public void removeItems(Map<Integer, Integer> productCategoryAndCountMap) {
        for(Map.Entry<Integer,Integer> entry : productCategoryAndCountMap.entrySet()){
            ProductCategory productCategory=getProductCategoryFromID(entry.getKey());
            productCategory.removeProduct(entry.getValue());

        }

    }

    private ProductCategory getProductCategoryFromID(int productCategoryId) {
        for(ProductCategory productCategory : productCategoryList){
            if(productCategory.productcategoryId == productCategoryId){
                return productCategory;
            }
        }

        return null;


    }
}


