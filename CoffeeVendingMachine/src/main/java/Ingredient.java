public class Ingredient {
    String name;
    int quantity;

    public Ingredient(String name, int value) {
        this.name = name;
        this.quantity = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
    public void updateQuantity(int value){
        this.quantity +=value;
    }
}
