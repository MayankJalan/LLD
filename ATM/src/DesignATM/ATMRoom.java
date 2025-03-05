package DesignATM;

public class ATMRoom {
    ATM atm;
    User user;

    public static void main(String[] args) {
        ATMRoom atmRoom=new ATMRoom();
        atmRoom.initialize();
        atmRoom.atm.printCurrentATMStatus();
        atmRoom.atm.getCurrentATMState().inserCard(atmRoom.atm,atmRoom.user.card);
        atmRoom.atm.getCurrentATMState().authenticatePin(atmRoom.atm,atmRoom.user.card,112211);
        atmRoom.atm.getCurrentATMState().selectOperation(atmRoom.atm,atmRoom.user.card,TransactionType.CASH_WITHDRAWL);
        atmRoom.atm.getCurrentATMState().cashWithdrawl(atmRoom.atm,atmRoom.user.card,3900);
        atmRoom.atm.printCurrentATMStatus();




    }

    private void initialize() {
        atm = ATM.getATMObject();
        atm.setAtmBalance(4000,5,5,5);
        this.user=createUser();
    }

    private User createUser() {
        User user1=new User();
        user1.setCard(createCard());
        return user1;
    }
    private Card createCard(){

        Card card = new Card();
        card.setBankAccount(createBankAccount());
        return card;
    }

    private UserBankAccount createBankAccount() {

        UserBankAccount bankAccount = new UserBankAccount();
        bankAccount.balance = 5000;
        return bankAccount;

    }




}
