package DesignATM.ATMStates;

import DesignATM.ATM;
import DesignATM.Card;
import DesignATM.TransactionType;

public class SelectOperationState extends ATMState {
    public SelectOperationState() {
        showOperations();
    }


    @Override
    public void selectOperation(ATM atm, Card card, TransactionType transactionType) {
        switch (transactionType) {
            case CHECK_BALANCE:
                atm.setCurrentATMState(new CheckBalanceState());
                break;
            case CASH_WITHDRAWL:
                atm.setCurrentATMState(new CashWithdrawlState());
                break;
            default: {
                System.out.println("Invalid Option");
                exit(atm);
            }
        }
    }

    private void showOperations() {
        System.out.println("Please select the Operation");
        TransactionType.showAllTransactionTypes();
    }

}