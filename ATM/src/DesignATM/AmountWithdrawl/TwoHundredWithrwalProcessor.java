package DesignATM.AmountWithdrawl;

import DesignATM.ATM;

public class TwoHundredWithrwalProcessor extends CashWithdrawlProcessor{
    public TwoHundredWithrwalProcessor(CashWithdrawlProcessor nextCashWithdrawlProcessor) {
        super(nextCashWithdrawlProcessor);
    }

    @Override
    public void withdraw(ATM atm, int remainingAmount) {

        int requiredNotes=remainingAmount/200;
        int balance=remainingAmount%200;

        if(requiredNotes <= atm.getNoOfFiveHundredNotes()){
            atm.deductTwoHundredNotes(requiredNotes);
        }
        else {
            int noOf200NotesinATM=atm.getNoOfFiveHundredNotes();
            atm.deductTwoHundredNotes(noOf200NotesinATM);
            balance=balance+(requiredNotes-noOf200NotesinATM)*200;
        }
        if(balance !=0){
            super.withdraw(atm,balance);
        }
    }

}
