package DesignATM.ATMStates;

import DesignATM.ATM;
import DesignATM.Card;
import DesignATM.TransactionType;

public abstract class ATMState {
    public void inserCard(ATM atm, Card card){
        System.out.println("OOPS!! Something went wrong");
    }
    public void authenticatePin(ATM atm, Card card,int pin){
        System.out.println("OOPS!! Something went wrong");
    }
    public void selectOperation(ATM atm, Card card, TransactionType transactionType){
        System.out.println("OOPS!! Something went wrong");
    }
    public void cashWithdrawl(ATM atm, Card card,int withdrawlAmount){
        System.out.println("OOPS!! Something went wrong");
    }
    public void checkBalance(ATM atm, Card card){
        System.out.println("OOPS!! Something went wrong");
    }
    public void returnCard(){
        System.out.println("OOPS!! Something went wrong");
    }
    public void exit(ATM atm){
        System.out.println("OOPS!! Something went wrong");
    }
}
