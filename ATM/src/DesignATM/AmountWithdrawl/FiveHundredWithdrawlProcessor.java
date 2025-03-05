package DesignATM.AmountWithdrawl;

import DesignATM.ATM;

public class FiveHundredWithdrawlProcessor extends CashWithdrawlProcessor{
    public FiveHundredWithdrawlProcessor(CashWithdrawlProcessor nextCashWithdrawlProcessor){
        super(nextCashWithdrawlProcessor);
    }
    @Override
    public void withdraw(ATM atm, int remainingAmount) {

        int requiredNotes=remainingAmount/500;
        int balance=remainingAmount%500;

        if(requiredNotes <= atm.getNoOfFiveHundredNotes()){
            atm.deductFiveHundredNotes(requiredNotes);
        }
        else {
            int noOf500NotesinATM=atm.getNoOfFiveHundredNotes();
            atm.deductFiveHundredNotes(noOf500NotesinATM);
            balance=balance+(requiredNotes-noOf500NotesinATM)*500;
        }
        if(balance !=0){
            super.withdraw(atm,balance);
        }
    }
}
