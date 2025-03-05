package DesignATM;

public class UserBankAccount {
    public int balance;
    User user;
    public void withdrawalBalance(int amount) {
        this.balance-=amount;
    }
}
