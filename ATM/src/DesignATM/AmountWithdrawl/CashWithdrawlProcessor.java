package DesignATM.AmountWithdrawl;

import DesignATM.ATM;

public class CashWithdrawlProcessor {
    CashWithdrawlProcessor nextCashWithdrawlProcessor;

    public CashWithdrawlProcessor(CashWithdrawlProcessor nextCashWithdrawlProcessor) {
        this.nextCashWithdrawlProcessor = nextCashWithdrawlProcessor;
    }
    public void withdraw(ATM atm, int remainingAmount){
        if(nextCashWithdrawlProcessor != null){
            nextCashWithdrawlProcessor.withdraw(atm,remainingAmount);
        }
    }
}
