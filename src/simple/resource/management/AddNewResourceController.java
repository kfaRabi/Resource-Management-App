/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.resource.management;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.control.Tab;
import javafx.scene.control.TabPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * FXML Controller class
 *
 * @author panda
 */
public class AddNewResourceController implements Initializable {
    @FXML
    private TabPane addResourceTabPane;
    @FXML
    private TextField addFacilityName;
    @FXML
    private TextField addFacilitySize;
    @FXML
    private TextField addFacilityFloor;
    @FXML
    private TextArea addFacilityDescription;
    @FXML
    private TextField addEquipmentName;
    @FXML
    private TextField addEquipmentModel;
    @FXML
    private TextField addEquipmentBrand;
    @FXML
    private TextArea addEquipmentDescription;
    @FXML
    private TextField addOtherResourceName;
    @FXML
    private TextArea addOtherResourceDescription;
    @FXML
    private Tab facaltyTab;
    @FXML
    private Tab equipmentTab;
    @FXML
    private Tab otherTab;
    
    private String name;
    private String description;
    
    private Session session = null;
    private Transaction transaction = null;
    @FXML
    private Label FacilityMessageLabel;
    @FXML
    private Label equipmentMessageLabel;
    @FXML
    private Label otherMessageLabel;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        session = SessionSingleton.getSessionFactory().openSession();
        transaction = session.beginTransaction();
    }    

    @FXML
    private void handleCreateFacilityButtonAction(ActionEvent event) {
        name = addFacilityName.getText();
        String floor = addFacilityFloor.getText();
        String size = addFacilitySize.getText();
        description = addFacilityDescription.getText();
        Facility facilty = new Facility(floor, size, new CompositeResourceIDClass(name,"Facility"),description);
        try{
            session.save(facilty);
            transaction.commit();
            System.out.println("done storing facility");
            FacilityMessageLabel.setText("Successfully Stored! :)");
        }
        catch(Exception e){
            FacilityMessageLabel.setText("Could not Store! :(");
            System.err.println("storing faicility failed");
            transaction.rollback();
        }
        
    }

    @FXML
    private void handleEquipmentButtonAction(ActionEvent event) {
        name = addEquipmentName.getText();
        String brand = addEquipmentBrand.getText();
        String model = addEquipmentModel.getText();
        description = addEquipmentDescription.getText();
        Equipment equipment = new Equipment(brand, model, new CompositeResourceIDClass(name, "Equipment"), description);
        try{
            session.save(equipment);
            transaction.commit();
            System.out.println("done storing Equipment");
            equipmentMessageLabel.setText("Successfully Stored! :)");
        }
        catch(Exception e){
            equipmentMessageLabel.setText("Could not Store! :(");
            System.err.println("storing equip failed");
            transaction.rollback();
        }
    }

    @FXML
    private void handleCreateRButtonAction(ActionEvent event) {
        name = addOtherResourceName.getText();
        description = addOtherResourceDescription.getText();
        Resource otherResource = new Resource(new CompositeResourceIDClass(name, "Other"), description);
        try{
            session.save(otherResource);
            transaction.commit();
            System.out.println("done storing resource");
            otherMessageLabel.setText("Successfully Stored! :)");
        }
        catch(Exception e){
            otherMessageLabel.setText("Could not Store! :(");
            System.err.println("storing Resource failed");
            transaction.rollback();
        }
    }
    
}
