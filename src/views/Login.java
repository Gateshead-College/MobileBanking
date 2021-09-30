package views;


import controllers.InitDB;
import models.User;

import java.sql.SQLException;
import java.util.Scanner;

public class Login {

    //An instance variable holding our Scanner object
    Scanner scn = new Scanner(System.in);

    //Main method - starting point for the program
    public static void main(String[] args) {
        try {
            InitDB.databaseStartup();
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        //database startup
        //Create a new Login object and store it in the variable 'login'
        Login login = new Login();
        //Infinite loop to keep the program running
        while(true) {
            //Using the Login object created above
            //access and call the getUserDetails method
            login.getUserDetails();
        }
    }

    //Method declaration - return type void - so nothing
    public void getUserDetails() {
        System.out.println("Please enter your username");
        String username = scn.nextLine();
        System.out.println("Please enter your password");
        String password = scn.nextLine();
        User u = InitDB.checkLoginDetails(username, password);
        if(u != null){
            new MainMenu(u);
        }
        else {
            System.out.println("Invalid username / password, please try again.");
        }
    }
}
