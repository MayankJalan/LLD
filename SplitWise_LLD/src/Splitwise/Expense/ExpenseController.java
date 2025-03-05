package Splitwise.Expense;

import Splitwise.BalanceSheetController;
import Splitwise.Expense.Split.ExpenseSplit;
import Splitwise.Expense.Split.ExpenseSplitType;
import Splitwise.Expense.Split.Split;
import Splitwise.Expense.Split.SplitFactory;
import Splitwise.User.User;

import java.util.List;

public class ExpenseController {

    BalanceSheetController balanceSheetController;

    public ExpenseController() {
        this.balanceSheetController = new BalanceSheetController();
    }

    public Expense createExpense(String expenseId, List<Split> splitDetails, String description, double expenseAmount, User paidByUser, ExpenseSplitType splitType) {
            ExpenseSplit expenseSplit= SplitFactory.getSplitObject(splitType);
        expenseSplit.validateSplitRequest(splitDetails, expenseAmount);

        Expense expense = new Expense(expenseId, expenseAmount, description, paidByUser, splitType, splitDetails);

        balanceSheetController.updateUserExpenseBalanceSheet(paidByUser, splitDetails, expenseAmount);

        return expense;

    }
}
