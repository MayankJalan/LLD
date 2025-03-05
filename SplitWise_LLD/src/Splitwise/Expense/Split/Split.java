package Splitwise.Expense.Split;

import Splitwise.User.User;

public class Split {

    User user;
    double amoutOwe;

    public Split(User user, double amoutOwe) {
        this.user = user;
        this.amoutOwe = amoutOwe;
    }

    public User getUser() {
        return user;
    }

    public double getAmoutOwe() {
        return amoutOwe;
    }
}
