package views;


import controllers.AccountActionsController;
import models.User;

import java.util.Scanner;

public class MainMenu {
    User currentUser; //store the object of the currently signed in user

    public MainMenu(User currentUser) {
        this.currentUser = currentUser;
        AccountActionsController.currentUser = currentUser;
        displayMenu();
    }

    private void displayMenu(){
        System.out.println("Hi, Welcome to the Mobile Banking App!");
        System.out.println("Please choose an option from below:");
        System.out.println("1 - Open a new Account");
        System.out.println("2 - View accounts");
        System.out.println("3 - Settings");
        System.out.println("4 - Logout");
        handleChoice(Integer.parseInt(new Scanner(System.in).nextLine()));
    }

    private void handleChoice(int choice) {
        switch (choice) {
            case 1:
                AccountActionsController.createAccount(currentUser);
                break;
            case 2:
                AccountActionsController.viewAccounts();
                break;
            case 3:
                System.out.println("Settings selected");
                break;
            case 4:
                System.out.println("Logout option selected");
                break;
            default:
                System.out.println("Invalid option provided");
                break;
        }
        displayMenu();
    }
}



