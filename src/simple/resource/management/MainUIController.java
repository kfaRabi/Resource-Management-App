/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.resource.management;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import org.hibernate.Session;

/**
 * FXML Controller class
 *
 * @author panda
 */
public class MainUIController implements Initializable {
    @FXML
    private Label labaelForAdmin;

    private String subAnchor;
    @FXML
    private AnchorPane altarableAnchorPane;
    @FXML
    private GridPane adminPanel;
    
    private String userType;
    @FXML
    private Label labelForAdmin2;
    
    private Session session;


    
    public void setUserType(String userType) {
        this.userType=userType;
    }
    
    public void hideAdminPanel(){
        //labaelForAdmin.setVisible(false);
        //adminPanel.setVisible(false);
        //labelForAdmin2.setVisible(false);
        labaelForAdmin.setDisable(true);
        adminPanel.setDisable(true);
        labelForAdmin2.setDisable(true);
    }
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        try{
            session = SessionSingleton.getSessionFactory().openSession();
        }
        catch(Exception e){
            
        }
        
   
    }    
    
    
    private void loadViewAllAnchorPane(ActionEvent e) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(subAnchor));
        AnchorPane subAnchorPane = (AnchorPane)fxmlLoader.load();
        //AddNewResourceController addNewResourceController = fxmlLoader.<AddNewResourceController>getController();
        ViewAndBookAllResourcesController obj = fxmlLoader.<ViewAndBookAllResourcesController>getController();
        if(!userType.equals("Admin")){
            obj.hideAdminsButton();
        }
        
        altarableAnchorPane.getChildren().clear();
        altarableAnchorPane.getChildren().add((AnchorPane) subAnchorPane);
    }
    private void loadAddRAnchorPane(ActionEvent e) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(subAnchor));
        AnchorPane subAnchorPane = (AnchorPane)fxmlLoader.load();
        AddNewResourceController addNewResourceController = fxmlLoader.<AddNewResourceController>getController();
        altarableAnchorPane.getChildren().clear();
        altarableAnchorPane.getChildren().add((AnchorPane) subAnchorPane);
    }
    private void loadAddDeleteUserAnchorPane(ActionEvent e) throws IOException{
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(subAnchor));
        AnchorPane subAnchorPane = (AnchorPane)fxmlLoader.load();
        AddDeleteNewUserController addDeleteNewUserController = fxmlLoader.<AddDeleteNewUserController>getController();
        altarableAnchorPane.getChildren().clear();
        altarableAnchorPane.getChildren().add((AnchorPane) subAnchorPane);
    }


    @FXML
    private void handleViewAllResourcesButtonAction(ActionEvent event) {
        try {
            subAnchor="ViewAndBookAllResources.fxml";
            loadViewAllAnchorPane(event);
        } catch (IOException ex) {
            Logger.getLogger(MainUIController.class.getName()).log(Level.SEVERE, null, ex);
            System.out.println("Could not load anchorpane");
        }
    }

    @FXML
    private void handleLogOutButtonAction(ActionEvent event) {
        System.out.println("userType: "+userType);
    }

    @FXML
    private void handleUserButtonAction(ActionEvent event) {
        subAnchor="AddDeleteNewUser.fxml";
        try {
            loadAddDeleteUserAnchorPane(event);
        } catch (IOException ex) {
            Logger.getLogger(MainUIController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @FXML
    private void handleAddDeleteResourceButtonAction(ActionEvent event) {
        subAnchor="AddNewResource.fxml";
        try {
            loadAddRAnchorPane(event);
        } catch (IOException ex) {
            System.out.println("anchor changing failed");
        }
    }
    
}
