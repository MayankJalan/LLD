package DesignATM;

public class Card {

    private int cardNumber;
    private int expiryDate;
    private int cvv;
    private User user;
    private UserBankAccount userBankAccount;
    static int PIN_NUMBER = 112211;

    public  boolean isCorrectPinEntered(int pin){
        return this.PIN_NUMBER==pin;
    }
    public int getBalance(){
        return userBankAccount.balance;
    }
    public void deductBankBalance(int amount){
        userBankAccount.withdrawalBalance(amount);
    }

    public void setBankAccount(UserBankAccount bankAccount) {
        userBankAccount = bankAccount;
    }



}
