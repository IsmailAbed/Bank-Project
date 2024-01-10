module com.example.abedbank {
    requires javafx.controls;
    requires javafx.fxml;
    requires  de.jensd.fx.glyphs.fontawesome;
    requires java.sql;
    requires mysql.connector.java;
    opens com.example.abedbank.Controllers.Admin to javafx.fxml;
    // ... for sending money...
    opens com.example.abedbank.Controllers.Client to javafx.fxml;






            
                            
    opens com.example.abedbank to javafx.fxml;
    exports com.example.abedbank;
    exports com.example.abedbank.Controllers;
    exports com.example.abedbank.Controllers.Admin;
    exports com.example.abedbank.Controllers.Client;
    exports com.example.abedbank.Models;
    exports com.example.abedbank.Views;
}