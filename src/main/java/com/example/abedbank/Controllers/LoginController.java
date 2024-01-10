package com.example.abedbank.Controllers;

import com.example.abedbank.Models.Model;
import com.example.abedbank.Views.AccountType;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class LoginController implements Initializable {
    public ChoiceBox<AccountType> acc_selector; // bas khl2na enum AccountType
    public Label paye_address_lbl;
    public TextField payee_address_fld;
    public TextField pass_fld;
    public Button login_btn;
    public Label error_lbl;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        // Use a more descriptive name for the ChoiceBox
        acc_selector.setItems(FXCollections.observableArrayList(AccountType.CLIENT, AccountType.ADMIN));
        acc_selector.setValue(Model.getInstance().getViewFactory().getLoginAccountType());

        acc_selector.valueProperty().addListener(observable -> {
            // Update account type when the ChoiceBox value changes
            Model.getInstance().getViewFactory().setLoginAccountType(acc_selector.getValue());
        });

        login_btn.setOnAction(event -> onLogin());
    }

    private void onLogin() {
        Stage stage = (Stage) error_lbl.getScene().getWindow();

        if (Model.getInstance().getViewFactory().getLoginAccountType() == AccountType.CLIENT) {
            Model.getInstance().evaluateClientCred(payee_address_fld.getText(), pass_fld.getText());

            if (Model.getInstance().getClientSuccessFlag()) {
                Model.getInstance().getViewFactory().showClientWindow();
                Model.getInstance().getViewFactory().closeStage(stage);
            }else {
                payee_address_fld.setText("");
                pass_fld.setText("");
                error_lbl.setText("Invalid client credentials");
            }
        }else if (Model.getInstance().getViewFactory().getLoginAccountType() == AccountType.ADMIN) {
            Model.getInstance().evaluateAdminCred(payee_address_fld.getText(), pass_fld.getText());


            if (Model.getInstance().getAdminSuccessFlag()) {
                Model.getInstance().getViewFactory().showAdminWindow();
                Model.getInstance().getViewFactory().closeStage(stage);
            } else {
                // Handle incorrect admin credentials
                // Clear fields and show an error message
                payee_address_fld.setText("");
                pass_fld.setText("");
                error_lbl.setText("Invalid admin credentials");
            }
        }
    }



}
