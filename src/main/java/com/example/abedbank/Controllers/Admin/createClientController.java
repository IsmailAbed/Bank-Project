package com.example.abedbank.Controllers.Admin;

import com.example.abedbank.Models.Model;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class createClientController implements Initializable {
    public TextField fName_fld;
    public TextField lName_fld;
    public TextField password_fld;
    public TextField pAddress_fld;
    public CheckBox ch_Acc_box;
    public TextField ch_amount_fld;
    public CheckBox sc_acc_box;
    public TextField sv_amount_fld;
    public Button create_client_btn;
    public Label error_lbl;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        create_client_btn.setOnAction(event -> onCreateClient(event));
    }



    @FXML
    private void onCreateClient(ActionEvent event) {
        error_lbl.setText("");


        // Validate essential fields
        if (fName_fld.getText().isEmpty() || lName_fld.getText().isEmpty() ||
                password_fld.getText().isEmpty() || pAddress_fld.getText().isEmpty()) {
            error_lbl.setText("Please fill in all required fields");
            return;  // Stop the method if validation fails
        }

        String firstName = fName_fld.getText();
        String lastName = lName_fld.getText();
        String password = password_fld.getText();
        String payeeAddress = pAddress_fld.getText();
        boolean includeCheckingAccount = ch_Acc_box.isSelected();
        boolean includeSavingsAccount = sc_acc_box.isSelected();

        // Generate a random payeeAddress if selected
        //String payeeAddress = includePayeeAddress ? generateRandomPayeeAddress(firstName) : "";

        // Set optional balances
        double checkingAccountBalance = includeCheckingAccount ? Double.parseDouble(ch_amount_fld.getText()) : 0.0;
        double savingsAccountBalance = includeSavingsAccount ? Double.parseDouble(sv_amount_fld.getText()) : 0.0;

        // Create a new client account
        Model model = Model.getInstance();
        boolean success = model.getDatabaseDriver().createNewClient(firstName, lastName, password, payeeAddress,
                checkingAccountBalance, savingsAccountBalance);

        if (success) {
            error_lbl.setText("Client account created successfully");
        } else {
            error_lbl.setText("Failed to create client account");
        }
    }

//    private String generateRandomPayeeAddress(String firstName) {
//        // Add your logic to generate a random payeeAddress based on the first name
//        return "@" + firstName; // For example, concatenate '@' with the first name
//    }


}

