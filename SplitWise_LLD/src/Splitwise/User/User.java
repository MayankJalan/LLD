package Splitwise.User;

import Splitwise.UserExpenseSheet;

public class User {
    String userId;
    String userName;
    UserExpenseSheet userExpenseSheet;

    public User(String userId, String userName) {
        this.userId = userId;
        this.userName = userName;
        this.userExpenseSheet = new UserExpenseSheet();
    }

    public String getUserId() {
        return userId;
    }

    public UserExpenseSheet getUserExpenseSheet() {
        return userExpenseSheet;
    }
}
