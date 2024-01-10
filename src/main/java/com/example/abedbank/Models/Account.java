package com.example.abedbank.Models;

import javafx.beans.property.DoubleProperty;
import javafx.beans.property.SimpleDoubleProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public  abstract class Account {// every client will have an account
    private final StringProperty owner;
    private final StringProperty accountNumber;
    private final DoubleProperty balance;

    public Account(String owner ,String accountNumber, double balance){
        this.owner = new SimpleStringProperty(this , "owner" , owner);
        this.accountNumber = new SimpleStringProperty(this , "accountNumber" , accountNumber);
        this.balance = new SimpleDoubleProperty(this , "balance" , balance);

    }

    public StringProperty OwnerProperty(){return owner;}
    public StringProperty accountNumberProperty() {
        return accountNumber;
    }
    public DoubleProperty balanceProperty() {return balance;}



    public String getOwner() {
        return owner.get();
    }

    public void setOwner(String owner) {
        this.owner.set(owner);
    }

    public double getBalance() {
        return balance.get();
    }

    public void setBalance(double balance) {
        this.balance.set(balance);
    }

    public String getAccountNumber() {
        return accountNumber.get();
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber.set(accountNumber);
    }

}
