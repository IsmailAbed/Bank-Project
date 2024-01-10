package com.example.abedbank.Models;

import com.example.abedbank.Views.AccountType;
import com.example.abedbank.Views.ViewFactory;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;

public class Model {
    // follow the Singleton design pattern,use the same obj not a copy of it
    //data that is contained  within the obj is the same in the project
    private static Model model;//private to restrict direct access from outside the class.
    private final ViewFactory viewFactory;//final to indicate that it cannot be reassigned once initialized.


    private final DatabaseDriver databaseDriver;

    public AccountType getLogingAccountType() {
        return logingAccountType;
    }

    public void setLogingAccountType(AccountType logingAccountType) {
        this.logingAccountType = logingAccountType;
    }

    private AccountType logingAccountType=AccountType.CLIENT; // hay lal tracking initialy client
    // Client Data sec
    private final Client client;
    private boolean clientLoginSuccessFlag;


    // Admin Data sec

    private final Admin admin;
    private boolean adminLoginSuccessFlag;

    private Model(){
        this.viewFactory=new ViewFactory();
        this.databaseDriver= new DatabaseDriver();
        this.clientLoginSuccessFlag=false;
        this.client= new Client("","","",null,null,null,"");
        this.admin = new Admin("","","");
    }//, which means that instances of the Model class cannot be created from outside the class

    public static synchronized Model getInstance(){// bt3tina access 3al object
        if(model == null){
            model=new Model();
        }
        return model;
    }

    public ViewFactory getViewFactory() {
        return viewFactory;
    }

    public DatabaseDriver getDatabaseDriver(){return databaseDriver;}


    /*
     *Client Method Section
     */
    public boolean getClientSuccessFlag(){return this.clientLoginSuccessFlag;}

    public void setClientLoginSuccessFlag(boolean flag) { this.clientLoginSuccessFlag = flag;
    }

    public Client getClient(){return client;}


    public CheckingAccount getCheckingAccount() {
        if (clientLoginSuccessFlag) {
            return (CheckingAccount) client.getCheckingAccount();
        }
        return null;
    }

    public SavingsAccount getSavingAccount() {
        if (clientLoginSuccessFlag) {
            return (SavingsAccount) client.getSavingAccount();
        }
        return null;
    }




    //client evaluation

    public void evaluateClientCred(String payeeAddress, String password) {
        CheckingAccount checkingAccount;
        SavingsAccount savingsAccount;
        ResultSet resultSet = databaseDriver.getClientData(payeeAddress, password);

        try {

            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    this.client.firstNameProperty().set(resultSet.getString("firstName"));
                    this.client.lastNameProperty().set(resultSet.getString("lastName"));
                    this.client.payeeAddressProperty().set(resultSet.getString("payeeAddress"));
                    this.client.setFullName(resultSet.getString("firstName") + " " + resultSet.getString("lastName"));



                    // Ensure that the column name is correct in your database
                    // If it's 'DateOfBirth', update it accordingly
                    String[] dateParts = resultSet.getString("dateOfBirth").split("-");
                    LocalDate date = LocalDate.of(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]));
                    this.client.dateCreatedProperty().set(date);
                }
                this.clientLoginSuccessFlag = true;
            } else {
                // If there is no row in the ResultSet, the credentials are invalid
                this.clientLoginSuccessFlag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Add this method to the Model class
    // Inside updateClientAccountInfo method
    public void updateClientAccountInfo() {
        String payeeAddress = this.client.getPayeeAddress();
        CheckingAccount checkingAccount = databaseDriver.getCheckingAccountInfo(payeeAddress);
        SavingsAccount savingsAccount = databaseDriver.getSavingsAccountInfo(payeeAddress);

        // Set updated accounts for the client
        this.client.setCheckingAccount(checkingAccount);
        this.client.setSavingAccount(savingsAccount);
    }

    public boolean sendMoneyFromDashboard(String payeeAddress, double amount, String message) {
        try {
            // Use the MyBankDatabaseDriver to update the transaction details
            boolean transactionSuccess = databaseDriver.sendMoneyFromDashboard(getClient().getPayeeAddress(), payeeAddress, amount, message);

            // If the transaction is successful, update client account information
            if (transactionSuccess) {
                updateClientAccountInfo();
            }

            return transactionSuccess;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    // Inside Model class
    public ResultSet getAllTransactions(String payeeAddress) {
        return databaseDriver.getAllTransactions(payeeAddress);
    }























    //Admin Section
    public boolean getAdminSuccessFlag(){return this.adminLoginSuccessFlag;}

    public void setAdminLoginSuccessFlag(boolean flag) { this.adminLoginSuccessFlag = flag;}


    public void evaluateAdminCred(String username, String password) {
        try {
            ResultSet resultSet = databaseDriver.getAdminData(username, password);

            if (resultSet.next()) {
                this.admin.idProperty().set(resultSet.getString("id"));
                this.admin.usernameProperty().set(resultSet.getString("username"));
                this.adminLoginSuccessFlag = true;
            } else {
                // If there is no row in the ResultSet, the credentials are invalid
                this.adminLoginSuccessFlag = false;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public boolean createNewClient(String firstName, String lastName, String password, String payeeAddress,
                                   double checkingAccountBalance, double savingsAccountBalance) {
        try {
            // Call the DatabaseDriver method to create a new client
            boolean success = databaseDriver.createNewClient(firstName, lastName, password, payeeAddress,
                    checkingAccountBalance, savingsAccountBalance);

            // If the client creation is successful, update the client account information
            if (success) {
                updateClientAccountInfo();
            }

            return success;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }



    // Inside Model class (for deposit page )-->Searching
    public Client getClientByPayeeAddress(String payeeAddress) {
        ResultSet resultSet = databaseDriver.getClientDataByPayeeAddress(payeeAddress);

        try {
            if (resultSet.isBeforeFirst()) {
                while (resultSet.next()) {
                    this.client.firstNameProperty().set(resultSet.getString("firstName"));
                    this.client.lastNameProperty().set(resultSet.getString("lastName"));
                    this.client.payeeAddressProperty().set(resultSet.getString("payeeAddress"));
                    this.client.setFullName(resultSet.getString("firstName") + " " + resultSet.getString("lastName"));

                    // Ensure that the column name is correct in your database
                    // If it's 'DateOfBirth', update it accordingly
                    String[] dateParts = resultSet.getString("dateOfBirth").split("-");
                    LocalDate date = LocalDate.of(Integer.parseInt(dateParts[0]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[2]));
                    this.client.dateCreatedProperty().set(date);
                }
                this.clientLoginSuccessFlag = true;
            } else {
                // If there is no row in the ResultSet, the client is not found
                this.clientLoginSuccessFlag = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return clientLoginSuccessFlag ? client : null;
    }

    // Inside Model class  (for deposit page )-->Searching
    public CheckingAccount getCheckingAccountByPayeeAddress(String payeeAddress) {
        return databaseDriver.getCheckingAccountInfo(payeeAddress);
    }

    public SavingsAccount getSavingsAccountByPayeeAddress(String payeeAddress) {
        return databaseDriver.getSavingsAccountInfo(payeeAddress);
    }


    public ResultSet getClients() {//  for Client page
        return databaseDriver.getAllClients();
    }


}