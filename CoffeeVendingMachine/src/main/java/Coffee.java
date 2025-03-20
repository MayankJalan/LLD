import java.util.HashMap;
import java.util.Map;

public class Coffee {
    String name;
    double price;
    Map<Ingredient,Integer> recipe;

    public Coffee(String name, double price, Map<Ingredient, Integer> recipe) {
        this.name = name;
        this.price = price;
        this.recipe = recipe;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Map<Ingredient, Integer> getRecipe() {
        return recipe;
    }

    public void setRecipe(Map<Ingredient, Integer> recipe) {
        this.recipe = recipe;
    }
}
