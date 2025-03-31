package digitalwalletservice;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

public class Account {
    private final String id;
    private final String accountNumber;
    private final User user;
    private BigDecimal balance;
    private final Currency currency;
    private List<Transaction> transactions;

    public Account(String id,User user, String accountNumber, Currency currency) {
        this.id = id;
        this.accountNumber = accountNumber;
        this.user = user;
        balance = BigDecimal.ZERO;
        this.currency = currency;
        transactions=new ArrayList<>();
    }

    public synchronized void deposit(BigDecimal amount) {
        balance = balance.add(amount);
    }

    public synchronized void withdraw(BigDecimal amount) {
        if(this.balance.compareTo(amount) >=0){
            balance=balance.subtract(amount);
        }else {
            throw new InsufficientFundsException("Insufficient funds in the account.");
        }
    }
    public synchronized void addTransaction(Transaction transaction) {
        transactions.add(transaction);
    }

    public String getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public Currency getCurrency() {
        return currency;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public List<Transaction> getTransactions() {
        return transactions;
    }


}