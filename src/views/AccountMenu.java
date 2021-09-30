package views;


import models.Account;

import java.util.ArrayList;
import java.util.Scanner;

@SuppressWarnings("unused")
public class AccountMenu {

    public static int displayAccounts(ArrayList<Account> accounts){
        System.out.println("Please select one of your accounts from the list below");
        for(int i = 1; i < accounts.size() + 1; i++){
            System.out.println(i + " - " + accounts.get(i - 1).getAccountName());
        }
        return Integer.parseInt(new Scanner(System.in).nextLine());
    }

    public static String openAccountGetType(){
        System.out.println("What type of account would you like to create?");
        System.out.println("1 - Current Account");
        System.out.println("2 - Standard Savings Account");
        System.out.println("3 - ISA");
        System.out.println("4 - Credit Account");
        int choice = Integer.parseInt(new Scanner(System.in).nextLine());
        switch (choice) {
            case 1:
                return "Current";
            case 2:
                return "Standard Savings";
            case 3:
                return "ISA";
            case 4:
                return "Credit";
            default:
                System.out.println("Invalid option provided, please try again");
                openAccountGetType();
        }
        return null;
    }

    public static double openAccountInitialDeposit(){
        System.out.println("Would you like to make an initial deposit to your new account? (y/n)");
        if (new Scanner(System.in).nextLine().equalsIgnoreCase("y")) {
            System.out.println("How much would you like to deposit?");
            return Double.parseDouble(new Scanner(System.in).nextLine());
        }
        return 0.0;
    }

    public static String openAccountGetName(){
        System.out.println("Would you like to name your new account? (y/n)");
        if(new Scanner(System.in).nextLine().equalsIgnoreCase("y")){
            System.out.println("Please enter the name of the account below:");
            return new Scanner(System.in).nextLine();
        }
        return null;
    }
}


//    public AccountMenu(String[] accNames) {
//        displayAccounts(accNames);
//    }
//
//    private void displayAccounts(String[] accountNames){ //4
//        System.out.println("Please select one of your accounts from the list below");
//
//        for(int i = 1; i <= accountNames.length; i++){
//            System.out.println(i + " - " + accountNames[i - 1]);
//        }
//        int choice = Integer.parseInt(scn.nextLine());
//    }

//    public static void displayAccountsStatic(String[] accounts){
//        System.out.println("Please select one of your accounts from the list below");
//        for(int i = 0; i < accounts.length + 1; i++){
//            System.out.println(i + " - " + accounts[i]);
//        }
//        int choice = Integer.parseInt(new Scanner(System.in).nextLine());
//    }