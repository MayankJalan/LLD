package DesignATM;

public enum TransactionType {
    CASH_WITHDRAWL,
    CHECK_BALANCE;
    public static void showAllTransactionTypes(){
        for(TransactionType transactionType : TransactionType.values()){
            System.out.println(transactionType.name());
        }
    }
}
