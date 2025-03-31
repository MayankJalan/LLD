package digitalwalletservice;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;

public class DigitalWallet {
    private static DigitalWallet instance;
    private final Map<String,User> users;
    private final Map<String,Account> accounts;
    private final Map<String,PaymentMethod> paymentMethods;

    public DigitalWallet() {
        users=new ConcurrentHashMap<>();
        accounts=new ConcurrentHashMap<>();
        paymentMethods=new ConcurrentHashMap<>();
    }
    public static synchronized DigitalWallet getInstance() {
        if (instance == null) {
            instance = new DigitalWallet();
        }
        return instance;
    }
    public void createUser(User user) {
        users.put(user.getId(), user);
    }

    public User getUser(String userId) {
        return users.get(userId);
    }
    public void createAccount(Account account) {
        accounts.put(account.getId(), account);
        account.getUser().addAccount(account);
    }

    public Account getAccount(String accountId) {
        return accounts.get(accountId);
    }

    public void addPaymentMethod(PaymentMethod paymentMethod) {
        paymentMethods.put(paymentMethod.getId(), paymentMethod);
    }
    public PaymentMethod getPaymentMethod(String paymentMethodId) {
        return paymentMethods.get(paymentMethodId);
    }

    public synchronized void transferFunds(Account sourceAccount, Account destinationAccount, BigDecimal amount, Currency currency){
        if(sourceAccount.getCurrency() == currency){
            sourceAccount.withdraw(amount);
        }
        else {
            BigDecimal sourceAmount=CurrencyConverter.convert(amount,sourceAccount.getCurrency(),currency);
            sourceAccount.withdraw(sourceAmount);
        }
        if(destinationAccount.getCurrency() == currency){
            destinationAccount.deposit(amount);
        }
        else {
            BigDecimal destinationAmount=CurrencyConverter.convert(amount,destinationAccount.getCurrency(),currency);
            sourceAccount.withdraw(destinationAmount);
        }
        String transactionId=generateTransactionId();
        Transaction transaction=new Transaction(transactionId,sourceAccount,destinationAccount,amount,currency);
        sourceAccount.addTransaction(transaction);
        destinationAccount.addTransaction(transaction);
    }
    public List<Transaction> getTransactionHistory(Account account) {
        return account.getTransactions();
    }

    private String generateTransactionId() {
        return "TXN"+UUID.randomUUID().toString().substring(0,8).toUpperCase();
    }


}
