package models;

import java.util.ArrayList;

public class Account {

    private int accountNumber;
    private String accountName;
    private double accountBalance;
    private String accountType;
    private ArrayList<Transaction> transactions;

    public Account(int accountNumber, String accountName) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
    }

    public Account(String accountName, double accountBalance, String accountType) {
        this.accountName = accountName;
        this.accountBalance = accountBalance;
        this.accountType = accountType;
        this.transactions = new ArrayList<>();
    }

    public Account(int accountNumber, String accountName, double accountBalance, String accountType) {
        this.accountNumber = accountNumber;
        this.accountName = accountName;
        this.accountBalance = accountBalance;
        this.accountType = accountType;
        this.transactions = new ArrayList<>();
    }

    public int getAccountNumber() {
        return accountNumber;
    }

    public String getAccountName() {
        return accountName;
    }

    public void setAccountName(String accountName) {
        this.accountName = accountName;
    }

    public double getAccountBalance() {
        return accountBalance;
    }

    public void setAccountBalance(double accountBalance) {
        this.accountBalance = accountBalance;
    }

    public String getAccountType() {
        return accountType;
    }

    public ArrayList<Transaction> getTransactions() {
        return transactions;
    }

    public void addTransaction(Transaction transaction) {
        this.transactions.add(transaction);
    }
}