import java.util.ArrayList;

public class ProductTypeDiscount extends DiscountDecorator{
   Product product;
   ProductType productType;
   int discountPercentage;
   static ArrayList<ProductType> eligibleItems=new ArrayList<>();
   static {
       eligibleItems.add(ProductType.FURNITURE_GOODS);
       eligibleItems.add(ProductType.DECORATIVE_GOODS);
   }

    public ProductTypeDiscount(Product product, ProductType productType, int discountPercentage) {
        this.product = product;
        this.productType = productType;
        this.discountPercentage = discountPercentage;
    }

    @Override
    public double getPrice() {
       double price=product.getPrice();
        for(ProductType productType1 : eligibleItems){
            if(productType.equals(productType1)){
                return price*(100-discountPercentage)/100;
            }
        }
        return price;
    }
}
