package com.example.abedbank.Controllers.Admin;

import com.example.abedbank.Models.Model;
import com.example.abedbank.Views.AdminMenuOptions;
import com.example.abedbank.Views.ViewFactory;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class adminMenuController implements Initializable {
    public Button create_client_btn;
    public Button clients_btn;
    public Button deposit_btn;
    public Button logout_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        logout_btn.setOnAction(event -> handleLogoutButton());
        addListeners();
    }


    public void handleLogoutButton() {
        Model model = Model.getInstance();
        model.setClientLoginSuccessFlag(false); // Reset client login flag

        Stage stage = (Stage) logout_btn.getScene().getWindow(); // Access the stage directly
        stage.close(); // Close the current stage (client window)

        ViewFactory viewFactory = model.getViewFactory();
        viewFactory.showLoginWindow(); // Show the login page
    }


    private void  addListeners(){
         create_client_btn.setOnAction(event -> onCreateClient());
        clients_btn.setOnAction(event -> onClients());
        deposit_btn.setOnAction(event -> onDeposit());
    }

    private void  onCreateClient(){
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.CREATE_CLIENT);
    }
    private void  onClients(){
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.CLIENTS);
    }

    private void  onDeposit(){
        Model.getInstance().getViewFactory().getAdminSelectedMenuItem().set(AdminMenuOptions.DEPOSIT);
    }


}
