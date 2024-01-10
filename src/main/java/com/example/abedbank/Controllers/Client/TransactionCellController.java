package com.example.abedbank.Controllers.Client;

//import com.example.abedbank.Models.Transaction;
import com.example.abedbank.Models.Transaction;
import de.jensd.fx.glyphs.fontawesome.FontAwesomeIconView;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

import java.net.URL;
import java.util.ResourceBundle;

public class TransactionCellController implements Initializable {
    public FontAwesomeIconView in_icon;
    public FontAwesomeIconView out_icon;
    public Label trans_date_lbl;
    public Label sender_lbl;
    public Label receiver_lbl;
    public Label amount_lbl;
    private final Transaction transaction;


    public  TransactionCellController(Transaction transaction){
        this.transaction=transaction;
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
