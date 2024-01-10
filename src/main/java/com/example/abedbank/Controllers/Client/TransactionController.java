package com.example.abedbank.Controllers.Client;

import com.example.abedbank.Models.Model;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

// Inside TransactionController class
public class TransactionController implements Initializable {
    @FXML
    public ListView<String> transactions_listview;

    private final Model model = Model.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayTransactions();
    }

    private void displayTransactions() {
        String payeeAddress = model.getClient().getPayeeAddress();
        ResultSet resultSet = model.getAllTransactions(payeeAddress);

        try {
            while (resultSet.next()) {
                // Assuming you have columns like 'sender', 'receiver', 'amount', 'date', etc.
                String transactionInfo = String.format("Sender: %s, Receiver: %s, Amount: %s, Date: %s",
                        resultSet.getString("sender"),
                        resultSet.getString("receiver"),
                        resultSet.getString("amount"),
                        resultSet.getString("date"));

                transactions_listview.getItems().add(transactionInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

