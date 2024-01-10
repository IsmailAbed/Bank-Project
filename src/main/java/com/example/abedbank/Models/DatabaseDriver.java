package com.example.abedbank.Models;

import java.sql.*;



public class DatabaseDriver { //u can create this class in model
   private Connection conn;

    public DatabaseDriver() {
        try {
            // Update the connection URL, username, and password for your MySQL database
            this.conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/abedbank", "root", "52146587");
        } catch (SQLException e) {
            System.out.println("Error connecting to the database");
            e.printStackTrace();
        }
    }

    /*
   *Client Section
    */
    public ResultSet getClientData(String pAddress, String password) {
        System.out.println("Executing query: ");
        ResultSet resultSet = null;

        try {
            // Use PreparedStatement to avoid SQL injection
            String query = "SELECT * FROM clients WHERE payeeAddress = ? AND password = ?";
            PreparedStatement preparedStatement = this.conn.prepareStatement(query);
            preparedStatement.setString(1, pAddress);
            preparedStatement.setString(2, password);
            System.out.println("Executing query: " + preparedStatement.toString());

            resultSet = preparedStatement.executeQuery();
            System.out.println("OK");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }//for login page


    // DatabaseDriver class

        // ... (existing code)

        public CheckingAccount getCheckingAccountInfo(String payeeAddress) {
            CheckingAccount checkingAccount = null;

            try {
                String query = "SELECT * FROM checkingaccount WHERE owner = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, payeeAddress);

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    String accountNumber = resultSet.getString("accountNumber");
                    double balance = resultSet.getDouble("balance");
                    int transactionLimit = resultSet.getInt("transactionLimit");

                    checkingAccount = new CheckingAccount(payeeAddress, accountNumber, balance, transactionLimit);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return checkingAccount;
        }

        public SavingsAccount getSavingsAccountInfo(String payeeAddress) {
            SavingsAccount savingsAccount = null;

            try {
                String query = "SELECT * FROM savingaccounts WHERE owner = ?";
                PreparedStatement preparedStatement = conn.prepareStatement(query);
                preparedStatement.setString(1, payeeAddress);

                ResultSet resultSet = preparedStatement.executeQuery();
                if (resultSet.next()) {
                    String accountNumber = resultSet.getString("accountNumber");
                    double balance = resultSet.getDouble("balance");
                    int withdrawLimit = resultSet.getInt("withdrawLimit");

                    savingsAccount = new SavingsAccount(payeeAddress, accountNumber, balance, withdrawLimit);
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }

            return savingsAccount;
        }

       // for sending Money in Dashboard
       public boolean sendMoneyFromDashboard(String senderAddress, String receiverAddress, double amount, String message) {
           try {
               // Implement the logic to update the transaction details in the database
               // For example, insert a new row in the transactions table

               String insertTransactionQuery = "INSERT INTO transactions (sender, receiver, amount, message) " +
                       "VALUES (?, ?, ?, ?)";

               PreparedStatement transactionStatement = conn.prepareStatement(insertTransactionQuery);
               transactionStatement.setString(1, senderAddress);
               transactionStatement.setString(2, receiverAddress);
               transactionStatement.setDouble(3, amount);
               transactionStatement.setString(4, message);

               int affectedRows = transactionStatement.executeUpdate();

               // Return true if the transaction was successful
               return affectedRows > 0;
           } catch (SQLException e) {
               e.printStackTrace();
               return false;
           }
       }

       // for Transaction page to show all the trans i did

    // Inside DatabaseDriver class
    public ResultSet getAllTransactions(String payeeAddress) {
        ResultSet resultSet = null;

        try {
            // Use PreparedStatement to avoid SQL injection
            String query = "SELECT * FROM transactions where  sender = ? OR receiver = ?";
            PreparedStatement preparedStatement = this.conn.prepareStatement(query);
            preparedStatement.setString(1, payeeAddress);
            preparedStatement.setString(2, payeeAddress);

            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }


















    /*
     *Admin Section
     */

    public ResultSet getAdminData(String username, String password) {//for login page
        System.out.println("Executing query: ");
        ResultSet resultSet = null;

        try {
            // Use PreparedStatement to avoid SQL injection
            String query = "SELECT * FROM Admins WHERE username = ? AND password = ?";
            PreparedStatement preparedStatement = this.conn.prepareStatement(query);
            preparedStatement.setString(1, username);
            preparedStatement.setString(2, password);
            System.out.println("Executing query: " + preparedStatement.toString());

            resultSet = preparedStatement.executeQuery();
            System.out.println("OK");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

   // to create new client by admin
   // In DatabaseDriver class
    public boolean createNewClient(String firstName, String lastName, String password, String payeeAddress,
                                  double checkingAccountBalance, double savingsAccountBalance) {
        try {

            String insertClientQuery = "INSERT INTO Clients (firstName, lastName, payeeAddress, password, dateOfBirth) " +
                    "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement clientStatement = conn.prepareStatement(insertClientQuery, Statement.RETURN_GENERATED_KEYS);
            clientStatement.setString(1, firstName);
            clientStatement.setString(2, lastName);
            clientStatement.setString(3, payeeAddress);
            clientStatement.setString(4, password);

            // Set a default dateOfBirth for simplicity. Modify as needed.
            clientStatement.setString(5, "2000-01-01");

            int affectedRows = clientStatement.executeUpdate();

            if (affectedRows == 0) {
                // Insert failed
                return false;
            }

            try (ResultSet generatedKeys = clientStatement.getGeneratedKeys()) {
                if (generatedKeys.next()) {
                    int clientId = generatedKeys.getInt(1);

                    // Insert checking account if selected
                    if (checkingAccountBalance > 0) {
                        createCheckingAccount(clientId, checkingAccountBalance);
                    }

                    // Insert savings account if selected
                    if (savingsAccountBalance > 0) {
                        createSavingsAccount(clientId, savingsAccountBalance);
                    }
                } else {
                    // No client ID was generated
                    return false;
                }
            }
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    private void createCheckingAccount(int clientId, double balance) {
        try {
            String insertCheckingAccountQuery = "INSERT INTO checkingaccount(owner, balance, transactionLimit) " +
                    "VALUES (?, ?, ?)";

            PreparedStatement checkingAccountStatement = conn.prepareStatement(insertCheckingAccountQuery);
            checkingAccountStatement.setInt(1, clientId);
            checkingAccountStatement.setDouble(2, balance);
            checkingAccountStatement.setInt(3, 1000); // Set a default transaction limit, modify as needed

            checkingAccountStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    private void createSavingsAccount(int clientId, double balance) {
        try {
            String insertSavingsAccountQuery = "INSERT INTO savingaccounts(owner, balance, withdrawLimit) " +
                    "VALUES (?, ?, ?)";

            PreparedStatement savingsAccountStatement = conn.prepareStatement(insertSavingsAccountQuery);
            savingsAccountStatement.setInt(1, clientId);
            savingsAccountStatement.setDouble(2, balance);
            savingsAccountStatement.setInt(3, 500); // Set a default withdraw limit, modify as needed

            savingsAccountStatement.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    // Inside DatabaseDriver class for searching
    public ResultSet getClientDataByPayeeAddress(String pAddress) {
        ResultSet resultSet = null;

        try {
            // Use PreparedStatement to avoid SQL injection
            String query = "SELECT * FROM clients WHERE payeeAddress = ?";
            PreparedStatement preparedStatement = this.conn.prepareStatement(query);
            preparedStatement.setString(1, pAddress);

            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }

    public ResultSet getAllClients() { //  for Clients page
        ResultSet resultSet = null;

        try {
            // Use PreparedStatement to avoid SQL injection
            String query = "SELECT * FROM clients";
            PreparedStatement preparedStatement = this.conn.prepareStatement(query);

            resultSet = preparedStatement.executeQuery();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return resultSet;
    }




    /*
     *Utility Section
     */
}
