package InventoryManagementSystem;

public class CashPaymentMode implements PaymentMode{


    @Override
    public boolean makePayment() {
        return false;
    }
}
