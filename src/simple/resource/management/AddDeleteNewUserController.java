/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.resource.management;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * FXML Controller class
 *
 * @author panda
 */
public class AddDeleteNewUserController implements Initializable {
    @FXML
    private TextField userNameField;
    @FXML
    private ComboBox<UserType> userTypeComboBox;
    @FXML
    private TextField userPasswordField;
    @FXML
    private ListView<Account> usersListView;
    
    private Session session;
    private Transaction transaction;
    private ObservableList<Account> accounts;
    private Account selectedAccount;
    @FXML
    private Label userMsgLabel;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        userTypeComboBox.getItems().setAll(UserType.values());
        session = SessionSingleton.getSessionFactory().openSession();
        List<Account>  acc = session.createCriteria(Account.class).list();
        accounts = FXCollections.observableArrayList();
        accounts.addAll(acc);
        usersListView.setItems(accounts);
        
        usersListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Account>(){
            @Override
            public void changed(ObservableValue<? extends Account> observable,Account oldValue,Account newValue){
                selectedAccount=newValue;
                System.out.println(newValue);
            }
            
        });
    }    

    @FXML
    private void deleteUserButtonAction(ActionEvent event) {
    }

    @FXML
    private void addUserButtonAction(ActionEvent event) {
        String name = userNameField.getText();
        String pass = userPasswordField.getText();
        String type = userTypeComboBox.getSelectionModel().getSelectedItem().toString();
        Account a = new Account(name, pass, type);
        try{
            transaction = session.beginTransaction();
            session.save(a);
            transaction.commit();
            accounts.add(a);
            System.out.println("user added succesfully");
            userMsgLabel.setText("User Added");
            userNameField.clear();
            userPasswordField.clear();
        }
        catch(Exception e){
            transaction.rollback();
            System.out.println("failed to add new user");
            userMsgLabel.setText("Adding Failed");
        }
    }
    
}
