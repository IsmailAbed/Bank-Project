package com.example.abedbank.Controllers.Client;

import com.example.abedbank.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ResourceBundle;

public class AccountsController implements Initializable {
    public Label checkingAcc_num;
    public Label trans_limit;
    public Label ch_acc_date;
    public Label ch_acc_bal;
    public Label sv_acc_num;
    public Label withdraw_limit;
    public Label sv_acc_date;
    public Label sv_acc_bal;
    public TextField amount_to_sv;
    public Button trans_to_sv_btn;
    public TextField amount_to_ch;
    public Button trans_to_cv_btn;


//
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model model = Model.getInstance();
        if (model.getClientSuccessFlag()) {
            // Update the labels with client's account information
            checkingAcc_num.setText(model.getCheckingAccount().getAccountNumber());
            trans_limit.setText(String.valueOf(model.getCheckingAccount().getTransactionLimit()));
            ch_acc_date.setText(model.getClient().getDateCreated().toString());
            ch_acc_bal.setText(String.format("$%,.2f", model.getCheckingAccount().getBalance()));

            sv_acc_num.setText(model.getSavingAccount().getAccountNumber());
            withdraw_limit.setText(String.format("$%,.2f", model.getSavingAccount().getWithdrawLimit()));
            sv_acc_date.setText(model.getClient().getDateCreated().toString());
            sv_acc_bal.setText(String.format("$%,.2f", model.getSavingAccount().getBalance()));
        }
    }
}

