package controllers;


import models.Account;
import models.Transaction;
import models.User;
import views.AccountActionsMenu;
import views.AccountMenu;

import java.time.LocalDate;
import java.util.ArrayList;

public class AccountActionsController {

    public static User currentUser;

    //open a new account
    public static boolean createAccount(User u) {
        String accType = AccountMenu.openAccountGetType();
        double initialBalance = AccountMenu.openAccountInitialDeposit();
        String accName = AccountMenu.openAccountGetName();
        if(accName == null){
            accName = accType;
        }
        //OLD METHOD
        //u.addAccount(new Account(accName, initialBalance, accType));
        //NEW METHOD
        InitDB.addAccount(InitDB.initialiseDB(), u.getUserID(), new Account(accName, initialBalance, accType));


        return true;
    }

    public static void viewAccounts() {
        ArrayList<Account> names = InitDB.getAccountNames(InitDB.initialiseDB(), currentUser.getUserID());
        if (names != null && names.size() > 0) {
            Account selectedAccount = names.get(AccountMenu.displayAccounts(names) - 1);
            displayAccountActions(InitDB.getSelectedAccount(InitDB.initialiseDB(), selectedAccount.getAccountNumber()));
        } else {
            System.out.println("You do not currently have any accounts, please open one first.");
        }
    }

    private static void displayAccountActions(Account a){
        switch (AccountActionsMenu.menu()) {
            case 1:
                AccountActionsMenu.viewBalance(a.getAccountBalance());
                break;

            case 2:
                viewTransactions(a);
                break;

            case 3:
                if (transferFunds(a)) {
                    System.out.println("Transfer successful");
                } else {
                    System.out.println("Transfer failed, please try again");
                }
                break;
            case 4:
                if (renameAccount(a)) {
                    System.out.println("Account renamed successfully");
                } else {
                    System.out.println("The account name can't be updated at the moment, please try again later.");
                }
                break;

            case 5: //Close Account
                break;
        }
    }

//
//            //We need to go to the AccountActionsMenu from here



    private static boolean renameAccount(Account selectedAccount) {
        String newName = AccountActionsMenu.getNewAccountName(selectedAccount.getAccountName());
        if(newName != null){
            selectedAccount.setAccountName(newName);
            return true;
        }
        return false;
    }

    private static void viewTransactions(Account selectedAccount) {
        ArrayList<String> transactions = new ArrayList<>();
        for(Transaction t : selectedAccount.getTransactions()){
            transactions.add(t.toString());
        }
        AccountActionsMenu.displayTransactions(transactions);
    }

    public static boolean transferFunds(Account sourceAccount){
        //get destination account
        ArrayList<String> accNames = new ArrayList<>();
        int sourceIndex = 0;
        for(Account a : currentUser.getAccounts()){
            if(!a.equals(sourceAccount)){
                accNames.add(a.getAccountName());
            }
            else {
                sourceIndex = accNames.size();
            }
        }
        int accIndex = AccountActionsMenu.getDestinationAccount(accNames);
        Account destinationAccount;
        if(accIndex < sourceIndex){
            destinationAccount = currentUser.getAccounts().get(accIndex);
        }
        else{
            destinationAccount = currentUser.getAccounts().get(accIndex + 1);
        }
        double amount = AccountActionsMenu.getTransferAmount();
        if(sourceAccount.getAccountBalance() >= amount){
            //they have enough wonga
            sourceAccount.setAccountBalance(sourceAccount.getAccountBalance() - amount);
            sourceAccount.addTransaction(new Transaction(LocalDate.now(), destinationAccount.getAccountName(),
                    amount * -1, sourceAccount.getAccountBalance()));
            destinationAccount.setAccountBalance(destinationAccount.getAccountBalance() + amount);
            destinationAccount.addTransaction(new Transaction(LocalDate.now(), sourceAccount.getAccountName(),
                    amount, destinationAccount.getAccountBalance()));
            return true;
        }
        else {
            //you dont have enough money!
            return false;
        }
    }
}

        //Account selectedAccount = accList.get(AccountMenu.displayAccountsReturn(accountNames) - 1);
    //}
//}
