package com.example.abedbank.Models;

import javafx.beans.property.*;

import java.time.LocalDate;

public class Client  {

    private final StringProperty firstName;//sender Payee Address //ex abed -> pAddress : ...
    private final StringProperty lastName;
    private final StringProperty payeeAddress;
    private final ObjectProperty<Account> checkingAccount;
    private final ObjectProperty<Account> savingAccount;

    private final ObjectProperty<LocalDate> dateCreated;
    private final StringProperty fullName ;

    public Client(String firstName , String lastName ,String payeeAddress ,Account checkingAccount ,Account savingAccount, LocalDate dateCreated ,String fullName){
        this.firstName =  new SimpleStringProperty(this ," FirstName" , firstName);
        this.lastName = new SimpleStringProperty(this , "LastName" , lastName);
        this.payeeAddress = new SimpleStringProperty(this , "payee Address" , payeeAddress);
        this.checkingAccount = new SimpleObjectProperty<>(this , "checking Account" , checkingAccount);
        this.savingAccount = new SimpleObjectProperty<>(this , "saving Account" , savingAccount);
        this.dateCreated = new SimpleObjectProperty<>(this , "dateCreated",  dateCreated);
        this.fullName= new SimpleStringProperty(this , "fullname" , fullName);
    }

    public  StringProperty firstNameProperty(){return this.firstName;}
    public  StringProperty lastNameProperty(){return this.lastName;}
    public  StringProperty payeeAddressProperty(){return this.payeeAddress;}
    public  ObjectProperty<Account> checkingAccountProperty(){return this.checkingAccount;}
    public  ObjectProperty<Account>  savingAccountProperty(){return this.savingAccount;}
    public  ObjectProperty<LocalDate> dateCreatedProperty(){return this.dateCreated;}

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getLastName() {
        return lastName.get();
    }

    public void setLastName(String lastName) {
        this.lastName.set(lastName);
    }

    public String getPayeeAddress() {
        return payeeAddress.get();
    }

    public void setPayeeAddress(String payeeAddress) {
        this.payeeAddress.set(payeeAddress);
    }

    public CheckingAccount getCheckingAccount() {
        return (CheckingAccount) checkingAccount.get();
    }

    public void setCheckingAccount(Account checkingAccount) {
        this.checkingAccount.set(checkingAccount);
    }

        public SavingsAccount getSavingAccount() {
        return (SavingsAccount) savingAccount.get();
    }

    public void setSavingAccount(Account savingAccount) {
        this.savingAccount.set(savingAccount);
    }


    public LocalDate getDateCreated() {
        return dateCreated.get();
    }

    public void setDateCreated(LocalDate dateCreated) {
        this.dateCreated.set(dateCreated);
    }

    public String getFullName() {
        return fullName.get();
    }

    public StringProperty fullNameProperty() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName.set(fullName);
    }

}