package DesignATM;

import DesignATM.ATMStates.ATMState;
import DesignATM.ATMStates.IdleState;

public class ATM {
    private static ATM atm = new ATM();
    ATMState atmState;
    private int atmBalance;
    int noOf500Notes;
    int noOf200Notes;
    int noOf100Notes;

    private ATM() {
    }
    public void setAtmBalance(int atmBalance, int noOf500Notes, int noOf200Notes, int noOf100Notes) {
        this.atmBalance = atmBalance;
        this.noOf500Notes = noOf500Notes;
        this.noOf200Notes = noOf200Notes;
        this.noOf100Notes = noOf100Notes;
    }
    public void setCurrentATMState(ATMState atmState) {
        this.atmState = atmState;
    }

    public ATMState getCurrentATMState() {
        return atmState;
    }

    public static ATM getATMObject() {
        atm.setCurrentATMState(new IdleState());
        return atm;
    }


    public int getAtmBalance() {
        return atmBalance;
    }

    public int getNoOf200Notes() {
        return noOf200Notes;
    }

    public int getNoOfFiveHundredNotes() {
        return noOf200Notes;
    }

    public int getNoOf100Notes() {
        return noOf100Notes;
    }

    public void deductATMBalance(int amount) {
        atmBalance = atmBalance - amount;
    }

    public void deductTwoHundredNotes(int number) {
        noOf200Notes = noOf200Notes - number;
    }

    public void deductFiveHundredNotes(int number) {
        noOf500Notes = noOf500Notes - number;
    }

    public void deductOneHundredNotes(int number) {
        noOf100Notes = noOf100Notes - number;
    }

    public void printCurrentATMStatus() {
        System.out.println("Balance: " + atmBalance);
        System.out.println("500Notes: " + noOf500Notes);
        System.out.println("200Notes: " + noOf200Notes);
        System.out.println("100Notes: " + noOf100Notes);

    }
}
