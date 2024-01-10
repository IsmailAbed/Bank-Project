package com.example.abedbank.Controllers.Admin;

import com.example.abedbank.Models.Client;
import com.example.abedbank.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

import java.net.URL;
import java.util.ResourceBundle;

public class depositController implements Initializable {
    public TextField pAddress_fld;
    public Button search_btn;
    public Text result_listview;
    public TextField amount_fld;
    public Button deposit_btn;

    // Inside depositController class
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        search_btn.setOnAction(event -> {
            String payeeAddress = pAddress_fld.getText().trim();

            // Check if the payeeAddress is not empty
            if (!payeeAddress.isEmpty()) {
                // Call a method to handle the search operation
                searchClientByPayeeAddress(payeeAddress);
            } else {
                // Display an error message or take appropriate action for empty payeeAddress
            }
        });

        // ... other initialization code ...
    }

    private void searchClientByPayeeAddress(String payeeAddress) {
        // Access the model instance to perform the search
        Model model = Model.getInstance();

        // Call the method to get client information by payeeAddress
        Client client = (Client) model.getClientByPayeeAddress(payeeAddress);

        // Check if the search was successful
        if (client != null) {
            // Update the UI with the client information
            result_listview.setText("Client found: " + client.getFullName());
            amount_fld.setDisable(false); // Enable the amount field for deposit
            deposit_btn.setDisable(false); // Enable the deposit button
        } else {
            // Display an error message or take appropriate action for unsuccessful search
            result_listview.setText("Client not found");
            amount_fld.setDisable(true); // Disable the amount field
            deposit_btn.setDisable(true); // Disable the deposit button
        }
    }

}
