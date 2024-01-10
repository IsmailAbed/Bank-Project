package com.example.abedbank.Views;

import com.example.abedbank.Controllers.Admin.adminController;
import com.example.abedbank.Controllers.Client.ClientController;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;

public class ViewFactory {

    private AccountType loginAccountType;
    // Client Views
    private final ObjectProperty<ClientMenuOptions> clientSelectedMenuItem;// KNT StringProperty l2no ma ken fi enum
    private AnchorPane dashboardView;
    private AnchorPane transactionsView;
    private AnchorPane accountsView;

    //Admin views
    private final ObjectProperty<AdminMenuOptions> AdminSelectedMenuItem;
    private AnchorPane createClientView;
    private AnchorPane clientsView;

    private AnchorPane depositView;


    public  ViewFactory(){
        this.loginAccountType=AccountType.CLIENT;
        this.clientSelectedMenuItem=new SimpleObjectProperty<>();
        this.AdminSelectedMenuItem=new SimpleObjectProperty<>();
    }

    public AccountType getLoginAccountType() {
        return loginAccountType;
    }

    public void setLoginAccountType(AccountType loginAccountType) {
        this.loginAccountType = loginAccountType;
    }

    /*
     *  Client Views Section
      *
      */






    public ObjectProperty<ClientMenuOptions> getClientSelectedMenuItem() {
        return clientSelectedMenuItem;
    }

    /*nhna bdna l dashboard & clientmenu ykouno sawa so mnhot hay*/

    public AnchorPane GetDashboardView(){
        if(dashboardView==null){
            try{
                dashboardView=new FXMLLoader(getClass().getResource("/Fxml/Client/Dashboard.fxml")).load();

            } catch (Exception e) {
               e.printStackTrace();
            }
        }
        return dashboardView;
    }

    public AnchorPane getTransactionsView() {
        if(transactionsView ==  null){
            try {
                transactionsView = new FXMLLoader(getClass().getResource("/Fxml/Client/Transactions.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return transactionsView;
    }

    public AnchorPane getAccountsView() {
        if(accountsView == null){
            try{
                accountsView = new FXMLLoader(getClass().getResource("/Fxml/Client/Accounts.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return accountsView;
    }

    public void showClientWindow(){
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/Fxml/Client/Client.fxml"));
        ClientController clientController=new ClientController();
        loader.setController(clientController);

        Scene scene=null;
        try {
            scene=new Scene(loader.load());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.setTitle("Abed Bank");
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/bank.png"))));
        stage.setResizable(false);
        stage.show();
    }

    /*ADMIN views*/

    public ObjectProperty<AdminMenuOptions> getAdminSelectedMenuItem(){return AdminSelectedMenuItem;}

    public AnchorPane getCreateClientView() {
        if (createClientView == null) {
            try {
                createClientView = new FXMLLoader(getClass().getResource("/Fxml/Admin/createClient.fxml")).load();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return createClientView;
    }

    public AnchorPane getClientsView(){
        if( clientsView == null){
            try{
                clientsView=new FXMLLoader(getClass().getResource("/Fxml/Admin/clients.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return clientsView;
    }


    public AnchorPane getDepositView() {
        if(depositView == null){
            try{
                depositView = new FXMLLoader(getClass().getResource("/Fxml/Admin/deposit.fxml")).load();
            }catch (Exception e){
                e.printStackTrace();
            }
        }
        return depositView;
    }


    public void showAdminWindow(){
        FXMLLoader loader= new FXMLLoader(getClass().getResource("/Fxml/Admin/admin.fxml"));
        adminController controller=new adminController();
        loader.setController(controller);
         createStage(loader);

    }

    public void showLoginWindow(){
        //It uses an FXMLLoader to load the login view from the FXML file /Fxml/Login.fxml.
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/Fxml/Login.fxml"));
        // it creates a new Scene with the loaded content and sets it to a new Stage
        Scene scene=null;
        try {
            scene=new Scene(loader.load());
        }
        catch (Exception e){
            e.printStackTrace();
        }
        //The Stage is then configured with a title ("Abed Bank") and displayed.
        Stage stage=new Stage();
        stage.setScene(scene);
        stage.setTitle("Abed Bank");
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/bank.png"))));
        stage.setResizable(false);
        stage.show();
    }

    private void createStage(FXMLLoader loader){
        Scene scene= null;
        try{
            scene = new Scene(loader.load());
        }catch (Exception e){
            e.printStackTrace();
        }
        Stage stage = new Stage();
        stage.setScene(scene);
        stage.getIcons().add(new Image(String.valueOf(getClass().getResource("/Images/bank.png"))));
        stage.setResizable(false);
        stage.setTitle("Abed Bank");
        stage.show();
    }

//    private static Stage currentStage;  // Add this field to keep track of the current stage
//    public void setStage(Stage stage) {
//        this.currentStage = stage;
//    }public void closeCurrentStage() {
//        if (currentStage != null) {
//            currentStage.close();
//        }
//    }




    public void closeStage(Stage stage){
        stage.close();
    }
}


