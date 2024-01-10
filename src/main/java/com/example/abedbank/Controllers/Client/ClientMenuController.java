package com.example.abedbank.Controllers.Client;

import com.example.abedbank.Models.Model;
import com.example.abedbank.Views.ClientMenuOptions;
import com.example.abedbank.Views.ViewFactory;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextInputDialog;
import javafx.stage.Stage;

import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

public class ClientMenuController implements Initializable {
    public Button dashboard_btn;
    public Button trans_btn;
    public Button accounts_btn;
    public Button profile_btn;
    public Button logout_btn;
    public Button report_btn;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addListeners();
        logout_btn.setOnAction(event -> handleLogoutButton());
        report_btn.setOnAction(event -> handleReportButton());
    }

    public void handleLogoutButton() {
        Model model = Model.getInstance();
        model.setClientLoginSuccessFlag(false);

        Stage stage = (Stage) logout_btn.getScene().getWindow();
        stage.close();

        ViewFactory viewFactory = model.getViewFactory();
        viewFactory.showLoginWindow();
    }

    private void addListeners() {
        dashboard_btn.setOnAction(event -> onDashboard());
        trans_btn.setOnAction(event -> onTransaction());
        accounts_btn.setOnAction(event -> onAccounts());
    }

    private void onDashboard() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.DASHBOARD);
    }

    private void onTransaction() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.TRANSACTIONS);
    }

    private void onAccounts() {
        Model.getInstance().getViewFactory().getClientSelectedMenuItem().set(ClientMenuOptions.ACCOUNTS);
    }

    private void handleReportButton() {
        // Use a TextInputDialog to get feedback from the user
        TextInputDialog feedbackDialog = new TextInputDialog();
        feedbackDialog.setTitle("Report");
        feedbackDialog.setHeaderText("Report an issue or provide feedback");
        feedbackDialog.setContentText("Tell us about your experience or report an issue:");

        Optional<String> result = feedbackDialog.showAndWait();

        // Check if the user entered feedback
        result.ifPresent(feedback -> {
            // You can process the feedback, e.g., save it to a database
            System.out.println("Feedback/Report received: " + feedback);
            // You may want to display a confirmation message to the user
            // or perform additional actions based on the feedback
        });
    }
}
