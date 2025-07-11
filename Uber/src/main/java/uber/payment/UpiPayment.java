package uber.payment;

public class UpiPayment implements Payment{
    @Override
    public void processPayment(double amount) {
        System.out.println("Processing payment using UPI for amount: " + amount);

    }
}
