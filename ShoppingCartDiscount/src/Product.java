public abstract class Product {
    String name;
    double price;
    ProductType productType;
    Product(){}

    public Product(String name, double price, ProductType productType) {
        this.name = name;
        this.price = price;
        this.productType = productType;
    }

    public abstract double getPrice();

    public String getName() {
        return name;
    }

    public ProductType getProductType() {
        return productType;
    }
}
