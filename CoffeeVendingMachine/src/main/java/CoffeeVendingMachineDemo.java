public class CoffeeVendingMachineDemo {
    public static void main(String[] args) {
        CoffeeVendingMachine coffeeMachine=CoffeeVendingMachine.getInstance();

        coffeeMachine.displayMenu();
        Coffee filterCoffee = coffeeMachine.selectCoffee("filterCoffee");
        coffeeMachine.dispenseCoffee(filterCoffee, new Payment(30));

        Coffee cappuccino = coffeeMachine.selectCoffee("cappuccino");
        coffeeMachine.dispenseCoffee(cappuccino, new Payment(40));


        Coffee americano = coffeeMachine.selectCoffee("americano");
        coffeeMachine.dispenseCoffee(americano, new Payment(10));
    }
}
