package airlinesManagementSystem.payment;

public class Payment {
    private final String id;
    private final String paymentMethod;
    private final double amount;
    private PaymentStatus paymentStatus;

    public Payment(String id, String paymentMethod, double amount) {
        this.id = id;
        this.paymentMethod = paymentMethod;
        this.amount = amount;
        paymentStatus=PaymentStatus.PENDING;
    }
    public void processPayment(){
        //processPayment Logic

        paymentStatus=PaymentStatus.COMPLETED;
    }
}
