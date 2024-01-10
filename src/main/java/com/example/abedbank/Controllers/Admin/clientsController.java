package com.example.abedbank.Controllers.Admin;

import com.example.abedbank.Models.Model;
import javafx.fxml.Initializable;
import javafx.scene.control.ListView;

import java.net.URL;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ResourceBundle;

public class clientsController implements Initializable {
    public ListView clients_listview;

    private final Model model = Model.getInstance();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        displayClients();
    }

    private void displayClients() {
        ResultSet resultSet = model.getClients();

        try {
            while (resultSet.next()) {
                //  columns like 'firstName ', 'lastName', 'payeeaddress', 'pass', etc.
                String clientInfo = String.format("firstName: %s, lastName: %s, payeeaddress: %s, pass: %s",
                        resultSet.getString("firstName"),
                        resultSet.getString("lastName"),
                        resultSet.getString("payeeAddress"),
                        resultSet.getString("password"));

                clients_listview.getItems().add(clientInfo);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
