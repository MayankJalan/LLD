package DesignATM;

public class User {
    Card card;
    UserBankAccount bankAccount;
    String name;

    public Card getCard() {
        return card;
    }

    public void setCard(Card card) {
        this.card = card;
    }

}
