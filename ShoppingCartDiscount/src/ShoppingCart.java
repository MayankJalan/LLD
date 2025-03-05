import java.util.ArrayList;
import java.util.List;

public class ShoppingCart {
    List<Product> productList;

    public ShoppingCart() {
        this.productList = new ArrayList<>();
    }
    public void addToCart(Product product){
        Product productWithEligibleDiscount=new ProductTypeDiscount(
                        new PercentageDiscount(product,10),product.getProductType(),3);
        productList.add(productWithEligibleDiscount);
    }

    public double getTotalPrice(){
        double price=0;
        for(Product product : productList){
            price+=product.getPrice();
        }
        return price;
    }
}
