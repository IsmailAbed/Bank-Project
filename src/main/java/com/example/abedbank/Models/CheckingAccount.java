package com.example.abedbank.Models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.IntegerProperty;
import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.StringProperty;

public class CheckingAccount extends Account {
    // the number of transactions the client is allowed to do per day
    private final IntegerProperty transactionLimit;

    public CheckingAccount(String owner, String accountNumber, double balance, int limit) {
        super(owner, accountNumber, balance);
        this.transactionLimit = new SimpleIntegerProperty(this, "limit", limit);
    }

    public IntegerProperty transactionLimitProperty() {
        return transactionLimit;
    }

    public int getTransactionLimit() {
        return transactionLimit.get();
    }

    public void setTransactionLimit(int transactionLimit) {
        this.transactionLimit.set(transactionLimit);
    }
}