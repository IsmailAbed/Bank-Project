package com.example.abedbank.Controllers.Client;

import com.example.abedbank.Models.Account;
import com.example.abedbank.Models.CheckingAccount;
import com.example.abedbank.Models.Model;
import com.example.abedbank.Models.SavingsAccount;
import com.mysql.cj.xdevapi.Client;
import com.mysql.cj.xdevapi.Session;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.*;
import javafx.scene.input.DragEvent;
import javafx.scene.input.Dragboard;
import javafx.scene.text.Text;
import org.w3c.dom.events.Event;

import java.net.URL;
import java.time.LocalDate;
import java.util.ResourceBundle;

public class DashboardController implements Initializable {

    public Text user_name;
    public Label login_date;
    public Label checking_bal;
    public Label checking_accnum;
    public Label saving_bal;
    public Label saving_accnum;
    public ListView trans_listview;
    public TextField payee_fld;
    public TextField amount_fld;
    public TextArea msg_fld;
    public Button send_money_btn;



    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model model = Model.getInstance();
        if (model.getClientSuccessFlag()) {
            user_name.setText("Hi, " + model.getClient().getFullName());
            // Update client account information
            model.updateClientAccountInfo();

            // Display checking and savings account information on the dashboard
            CheckingAccount checkingAccount =  model.getClient().getCheckingAccount();
            SavingsAccount savingsAccount = model.getClient().getSavingAccount();

            // Check if accounts are not null before accessing their properties
            if (checkingAccount != null) {
                checking_bal.setText("$" + checkingAccount.getBalance());
                checking_accnum.setText(checkingAccount.getAccountNumber());
            }

            if (savingsAccount != null) {
                saving_bal.setText("$" + savingsAccount.getBalance());
                saving_accnum.setText(savingsAccount.getAccountNumber());
            }
            login_date.setText("Today " + LocalDate.now().toString());
        }
    }




    @FXML
    private void handleSendMoneyAction(ActionEvent event) {
        String payeeAddress = payee_fld.getText();
        double amount = Double.parseDouble(amount_fld.getText());
        String message = msg_fld.getText();

        // Validate input (add your validation logic)

        // Use the Model class to perform the money transfer
        Model model = Model.getInstance();
        boolean success = model.sendMoneyFromDashboard(payeeAddress, amount, message);

        if (success) {
            System.out.println(" Money transfer successful, ");
        } else {
            System.out.println(" Money transfer failed, ");
        }
    }
}