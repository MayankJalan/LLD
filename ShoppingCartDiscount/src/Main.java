//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {
        Product item1=new Item1("FAN",2000,ProductType.ELECTRONIC);
        Product item2=new Item2("SOFA",5000,ProductType.FURNITURE_GOODS);

        ShoppingCart shoppingCart=new ShoppingCart();
        shoppingCart.addToCart(item1);
        shoppingCart.addToCart(item2);

        System.out.println(shoppingCart.getTotalPrice());



    }
}