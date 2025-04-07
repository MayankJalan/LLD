package restaurantManagementSystem.payment;

public class Payment {
    private final String id;
    private final double amount;
    private final PaymentMethod paymentMethod;
    private  final PaymentStatus paymentStatus;

    public Payment(String id, double amount, PaymentMethod paymentMethod, PaymentStatus paymentStatus) {
        this.id = id;
        this.amount = amount;
        this.paymentMethod = paymentMethod;
        this.paymentStatus = paymentStatus;
    }

    public String getId() {
        return id;
    }

    public double getAmount() {
        return amount;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public PaymentStatus getPaymentStatus() {
        return paymentStatus;
    }
}
