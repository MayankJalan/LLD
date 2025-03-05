public class PercentageDiscount extends  DiscountDecorator{
    Product product;
    int discountPercentage;

    public PercentageDiscount(Product product, int discountPercentage) {
        this.product = product;
        this.discountPercentage = discountPercentage;
    }

    @Override
    public double getPrice() {
        return product.getPrice()*(100-discountPercentage)/100;
    }
}
