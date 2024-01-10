package com.example.abedbank.Views;

import com.example.abedbank.Controllers.Client.TransactionCellController;
//import com.example.abedbank.Models.Transaction;
import com.example.abedbank.Models.Transaction;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ListCell;

public class TransactionCellFactory extends ListCell<Transaction> {
    @Override
    public void updateItem(Transaction transaction,boolean empty){
        super.updateItem(transaction , empty);
        if(empty){
            setText(null);
            setGraphic(null);
        }else{
            FXMLLoader loader= new FXMLLoader(getClass().getResource("/Fxml/Client/TransactionCell.fxml"));
            TransactionCellController controller=new TransactionCellController(transaction);
            loader.setController(controller);
            setText(null);
            try{
                setGraphic(loader.load());
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

}
