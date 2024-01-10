package com.example.abedbank.Controllers.Client;

import com.example.abedbank.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.layout.BorderPane;

import java.net.URL;
import java.util.ResourceBundle;

import static com.example.abedbank.Views.ClientMenuOptions.ACCOUNTS;
import static com.example.abedbank.Views.ClientMenuOptions.TRANSACTIONS;

public class ClientController implements Initializable {
    public BorderPane client_parent; // bel Client.fxml fi client_parent w hwe BorderPane
    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().addListener((observableValue, oldVal, newVal) -> {
            if (newVal.equals(TRANSACTIONS)) {
                client_parent.setCenter(Model.getInstance().getViewFactory().getTransactionsView());
            } else if (newVal.equals(ACCOUNTS)) {
                client_parent.setCenter(Model.getInstance().getViewFactory().getAccountsView());
            } else {
                client_parent.setCenter(Model.getInstance().getViewFactory().GetDashboardView());
            }
        });
    }
}
