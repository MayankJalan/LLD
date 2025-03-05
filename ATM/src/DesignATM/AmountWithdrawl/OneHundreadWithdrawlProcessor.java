package DesignATM.AmountWithdrawl;

import DesignATM.ATM;

public class OneHundreadWithdrawlProcessor extends CashWithdrawlProcessor{
    public OneHundreadWithdrawlProcessor(CashWithdrawlProcessor nextCashWithdrawlProcessor) {
        super(nextCashWithdrawlProcessor);
    }

    @Override
    public void withdraw(ATM atm, int remainingAmount) {

        int requiredNotes=remainingAmount/100;
        int balance=remainingAmount%100;

        if(requiredNotes <= atm.getNoOf100Notes()){
            atm.deductOneHundredNotes(requiredNotes);
        }
        else {
            int noOf100NotesinATM=atm.getNoOf100Notes();
            atm.deductOneHundredNotes(noOf100NotesinATM);
            balance=balance+(requiredNotes-noOf100NotesinATM)*100;
        }
        if(balance !=0){
            super.withdraw(atm,balance);
        }
    }

}
