package models;

import java.time.LocalDate;

public class Transaction {

    private LocalDate transactionDate;
    private String transactionParty;
    private double transactionAmount;
    private double newBalance;

    public Transaction(LocalDate transactionDate, String transactionParty, double transactionAmount, double newBalance) {
        this.transactionDate = transactionDate;
        this.transactionParty = transactionParty;
        this.transactionAmount = transactionAmount;
        this.newBalance = newBalance;
    }

    public LocalDate getTransactionDate() {
        return transactionDate;
    }

    public String getTransactionParty() {
        return transactionParty;
    }

    public double getTransactionAmount() {
        return transactionAmount;
    }

    @Override
    public String toString() {
        return transactionDate + "    -    " + transactionParty + "    -    " + transactionAmount + "    -    " + newBalance;
    }
}
