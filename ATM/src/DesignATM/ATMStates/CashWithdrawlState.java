package DesignATM.ATMStates;

import DesignATM.ATM;
import DesignATM.AmountWithdrawl.CashWithdrawlProcessor;
import DesignATM.AmountWithdrawl.FiveHundredWithdrawlProcessor;
import DesignATM.AmountWithdrawl.OneHundreadWithdrawlProcessor;
import DesignATM.AmountWithdrawl.TwoHundredWithrwalProcessor;
import DesignATM.Card;
import DesignATM.TransactionType;

public class CashWithdrawlState extends ATMState {

    @Override
    public void cashWithdrawl(ATM atm, Card card, int withdrawlAmount) {
        if (atm.getAtmBalance() < withdrawlAmount) {
            System.out.println("Insufficient Balance in ATM");
            exit(atm);
        } else if (card.getBalance() < withdrawlAmount) {
            System.out.println("InsufficientBalance");
            exit(atm);
            returnCard();
        } else {
            card.deductBankBalance(withdrawlAmount);
            atm.deductATMBalance(withdrawlAmount);

            CashWithdrawlProcessor cashWithdrawlProcessor = new FiveHundredWithdrawlProcessor(
                                                            new TwoHundredWithrwalProcessor(
                                                            new OneHundreadWithdrawlProcessor(null)));
            cashWithdrawlProcessor.withdraw(atm,withdrawlAmount);
            exit(atm);
        }
    }

    @Override
    public void exit(ATM atmObject) {
        returnCard();
        atmObject.setCurrentATMState(new IdleState());
        System.out.println("Exit happens");
    }

    @Override
    public void returnCard() {
        System.out.println("Please collect your card");
    }

}
