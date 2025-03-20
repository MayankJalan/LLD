import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class CoffeeVendingMachine {
    private static final CoffeeVendingMachine instance = new CoffeeVendingMachine();
    private List<Coffee> coffeeMenu;
    private final Map<String, Ingredient> ingredients;

    public static CoffeeVendingMachine getInstance() {
        return instance;
    }

    private CoffeeVendingMachine() {
        coffeeMenu = new ArrayList<>();
        ingredients = new HashMap<>();
        initializeIngredients();
        initializeCoffeeMenu();
    }

    private void initializeCoffeeMenu() {
        Map<Ingredient, Integer> espressoRecipe = new HashMap<>();
        espressoRecipe.put(ingredients.get("water"), 1);
        espressoRecipe.put(ingredients.get("coffee"), 1);
        coffeeMenu.add(new Coffee("espresso", 10, espressoRecipe));

        Map<Ingredient, Integer> americanoRecipe = new HashMap<>();
        americanoRecipe.put(ingredients.get("water"), 3);
        americanoRecipe.put(ingredients.get("coffee"), 1);
        coffeeMenu.add(new Coffee("americano", 15, americanoRecipe));

        Map<Ingredient, Integer> filterCoffeeRecipe = new HashMap<>();
        filterCoffeeRecipe.put(ingredients.get("water"), 3);
        filterCoffeeRecipe.put(ingredients.get("coffee"), 1);
        filterCoffeeRecipe.put(ingredients.get("milk"), 1);

        coffeeMenu.add(new Coffee("filterCoffee", 20, filterCoffeeRecipe));

        Map<Ingredient, Integer> cappuccinoRecipe = new HashMap<>();
        cappuccinoRecipe.put(ingredients.get("water"), 2);
        cappuccinoRecipe.put(ingredients.get("coffee"), 1);
        cappuccinoRecipe.put(ingredients.get("milk"), 2);

        coffeeMenu.add(new Coffee("cappuccino", 40, cappuccinoRecipe));


    }

    private void initializeIngredients() {

        ingredients.put("water", new Ingredient("water", 15));
        ingredients.put("milk", new Ingredient("milk", 15));
        ingredients.put("coffee", new Ingredient("coffee", 5));
        ingredients.put("cream", new Ingredient("cream", 5));

    }

    public void displayMenu() {
        System.out.println("Coffee Menu:");
        for (Coffee coffee : coffeeMenu) {
            System.out.println(coffee.getName() + " - $" + coffee.getPrice());
        }
    }

    public synchronized Coffee selectCoffee(String coffeeName) {
        for (Coffee coffee : coffeeMenu) {
            if (coffee.getName().equalsIgnoreCase(coffeeName)) {
                return coffee;
            }
        }
        return null;
    }

    public synchronized void dispenseCoffee(Coffee coffee, Payment payment) {
        if (payment.getAmount() >= coffee.getPrice()) {
            if (hasEnoughIngredients(coffee)) {
                updateIngredient(coffee.getRecipe());
                double change = payment.getAmount() - coffee.getPrice();
                if (change > 0) {
                    System.out.println("Please collect your change: $" + change);
                }
                System.out.println("Dispensing  "+coffee.getName());
            }else{
                System.out.println("We do not have enough ingredients");
            }
        }else {
            System.out.println("Insufficient payment for " + coffee.getName());
        }
    }

    private void updateIngredient(Map<Ingredient, Integer> recipe) {
        for(Map.Entry<Ingredient,Integer> entry : recipe.entrySet()){
            Ingredient ingredient=entry.getKey();
            Integer requiredQuantity=entry.getValue();
            ingredient.updateQuantity(-requiredQuantity);
            if (ingredient.getQuantity() < 3) {
                System.out.println("Low inventory alert: " + ingredient.getName());
            }

        }

    }

    private boolean hasEnoughIngredients(Coffee coffee) {
        for(Map.Entry<Ingredient, Integer> entry : coffee.getRecipe().entrySet()){
            Ingredient ingredient=entry.getKey();
            Integer requiredValue=entry.getValue();
            if(ingredient.getQuantity() <requiredValue){
                return false;
            }
        }
        return true;
    }



}
