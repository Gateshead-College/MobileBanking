package models;

import java.util.ArrayList;

public class User {
    private int userID;
    private String username;
    private String password;
    private String forename;
    private String surname;
    private ArrayList<Account> accounts;

    public User(int userID, String username, String password, String forename, String surname) {
        this.userID = userID;
        this.username = username;
        this.password = password;
        this.forename = forename;
        this.surname = surname;
        this.accounts = new ArrayList<>();
    }

    public int getUserID() {
        return userID;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getForename() {
        return forename;
    }

    public String getSurname() {
        return surname;
    }

    public ArrayList<Account> getAccounts() {
        return accounts;
    }

    public void addAccount(Account account) {
        this.accounts.add(account);
    }
}