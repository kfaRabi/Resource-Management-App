/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.resource.management;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.hibernate.Session;

/**
 *
 * @author panda
 */
public class LoginUIController implements Initializable {
    @FXML
    private TextField passwordField;
    @FXML
    private TextField userIDField;
    @FXML
    private ComboBox<UserType> userTypeComboBox;
    private Session session=null;
    private String userType;
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userTypeComboBox.getItems().setAll(UserType.values());
        try{
            session = SessionSingleton.getSessionFactory().openSession();
        }
        catch(Exception e){
            System.out.println("faileeeeeeeeeeeeeeeed");
        }
        
    }    

    private void gotoMain(ActionEvent e) throws IOException{
        //Parent landingUI = FXMLLoader.load(getClass().getResource("landingPage.fxml"));
        FXMLLoader landingUI = new FXMLLoader(getClass().getResource("MainUI.fxml"));
        Parent rootParent = (Parent)landingUI.load();
        Stage mainStage = (Stage) ((Node) e.getSource()).getScene().getWindow();
        MainUIController mainUIController = landingUI.<MainUIController>getController();
        mainUIController.setUserType(userType);
        if(!userType.equals("Admin")){
            mainUIController.hideAdminPanel();
        }
        Scene mainScene = new Scene(rootParent);
        //mainStage.hide();
        mainStage.close();
        mainStage.setScene(mainScene);
        mainStage.show();
    }
    
    @FXML
    private void handleLoginButtonAction(ActionEvent event) {
        String name = userIDField.getText();
        String password = passwordField.getText();
        UserType userTypeE = userTypeComboBox.getSelectionModel().getSelectedItem();
        if(userTypeE.equals(UserType.Admin)){
            userType="Admin";
        }
        else{
            userType="General_User";
        }
        System.out.println("user: "+name+" pass: "+password+" type: "+userType);
        Account account = (Account) session.get(Account.class, name);
        try{
            if(account.getPassword().equals(password) && account.getUserType().equals(userType)){
                try {
                    gotoMain(event);
                    session.close();
                } catch (IOException ex) {
                    System.out.println("Could not alter Stage");
             }
            }
            else{
                System.err.println("not matched");
            }
        }
        catch(Exception e){
            System.out.println("execption... probably nullPointer");
        }
        
        
    }
    
}
