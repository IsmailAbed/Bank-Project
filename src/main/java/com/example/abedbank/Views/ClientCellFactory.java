package com.example.abedbank.Views;

import com.example.abedbank.Controllers.Admin.clientCellController;
import com.example.abedbank.Models.Client;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class ClientCellFactory extends ListCell<Client> {
    @Override
    protected void updateItem(Client client, boolean empty) { // each client info will be updated here
        // bel controller fi l attributes qw nhna hon 3m n3ml kel mra new client
        super.updateItem(client, empty);
        if(empty){
            setText(null);
            setGraphic(null);
        }else{
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/Fxml/Admin/clientCell.fxml"));
            clientCellController controller= new clientCellController((com.mysql.cj.xdevapi.Client) client);
            loader.setController(controller);
            setText(null);
            try{
                setGraphic(loader.load());
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }
}
