package Splitwise.Group;

import Splitwise.Expense.Expense;
import Splitwise.Expense.ExpenseController;
import Splitwise.Expense.Split.ExpenseSplitType;
import Splitwise.Expense.Split.Split;
import Splitwise.User.User;

import java.util.ArrayList;
import java.util.List;

public class Group {
    String groupId;
    String groupName;
    List<User> groupMembers;
    ExpenseController expenseController;
    List<Expense> expenseList;
    Group(){
        groupMembers = new ArrayList<>();
        expenseList = new ArrayList<>();
        expenseController = new ExpenseController();
    }

    public void addMember(User member){
        groupMembers.add(member);
    }

    public String getGroupId() {
        return groupId;
    }

    public void setGroupId(String groupId) {
        this.groupId = groupId;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Expense createExpense(String expenseId, List<Split>splitDetails, String description, double expenseAmount,
                                 User paidByUser, ExpenseSplitType splitType){
        Expense expense=expenseController.createExpense(expenseId,splitDetails,
                                                        description,expenseAmount,paidByUser,splitType);
        expenseList.add(expense);
        return expense;


    }


}
