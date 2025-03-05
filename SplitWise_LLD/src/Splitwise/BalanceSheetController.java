package Splitwise;

import Splitwise.Expense.Split.Split;
import Splitwise.User.User;

import java.util.List;
import java.util.Map;

public class BalanceSheetController {
    public void updateUserExpenseBalanceSheet(User paidByUser, List<Split> splitDetails, double expenseAmount) {

        UserExpenseSheet paidUserBalanceSheet=paidByUser.getUserExpenseSheet();
        paidUserBalanceSheet.setTotalPayment(paidUserBalanceSheet.getTotalPayment()+expenseAmount);

        for(Split split : splitDetails){
            User userOwe = split.getUser();
            UserExpenseSheet oweUserExpenseSheet = userOwe.getUserExpenseSheet();
            double oweAmount = split.getAmoutOwe();

            if(paidByUser.getUserId().equals(userOwe.getUserId())){
                paidUserBalanceSheet.setTotalYourExpense(paidUserBalanceSheet.getTotalYourExpense()+oweAmount);
            }else {

                paidUserBalanceSheet.setTotalYouGetBack(paidUserBalanceSheet.getTotalYouGetBack()+ oweAmount);
                Balance userOweBalance;
                if(paidUserBalanceSheet.getUserVsBalance().containsKey(userOwe.getUserId())){
                    userOweBalance=paidUserBalanceSheet.getUserVsBalance().get(userOwe.getUserId());
                }
                else{
                    userOweBalance=new Balance();
                    paidUserBalanceSheet.getUserVsBalance().put(userOwe.getUserId(), userOweBalance);
                }
                userOweBalance.setAmountGetBack(userOweBalance.getAmountOwe() + oweAmount);


                // update oweUser Balance sheet

                oweUserExpenseSheet.setTotalYourExpense(oweUserExpenseSheet.getTotalYourExpense() + oweAmount);
                oweUserExpenseSheet.setTotalYouOwe(oweUserExpenseSheet.getTotalYouOwe() + oweAmount);

                Balance userPaidBalance;
                if(oweUserExpenseSheet.getUserVsBalance().containsKey(paidByUser.getUserId())){
                    userPaidBalance=oweUserExpenseSheet.getUserVsBalance().get(paidByUser.getUserId());
                }
                else{
                    userPaidBalance=new Balance();
                    oweUserExpenseSheet.getUserVsBalance().put(paidByUser.getUserId(), userPaidBalance);
                }
                userPaidBalance.setAmountOwe(oweAmount);
            }

        }

    }

    public void showBalanceSheetOfUser(User user){

        System.out.println("---------------------------------------");

        System.out.println("Balance sheet of user : " + user.getUserId());

        UserExpenseSheet userExpenseBalanceSheet =  user.getUserExpenseSheet();

        System.out.println("TotalYourExpense: " + userExpenseBalanceSheet.getTotalYourExpense());
        System.out.println("TotalGetBack: " + userExpenseBalanceSheet.getTotalYouGetBack());
        System.out.println("TotalYourOwe: " + userExpenseBalanceSheet.getTotalYouOwe());
        System.out.println("TotalPaymnetMade: " + userExpenseBalanceSheet.getTotalPayment());
        for(Map.Entry<String, Balance> entry : userExpenseBalanceSheet.getUserVsBalance().entrySet()){

            String userID = entry.getKey();
            Balance balance = entry.getValue();

            System.out.println("userID:" + userID + " YouGetBack:" + balance.getAmountGetBack() + " YouOwe:" + balance.getAmountOwe());
        }

        System.out.println("---------------------------------------");

    }


}
