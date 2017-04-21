/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.resource.management;

import java.net.URL;
import java.sql.Time;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.ResourceBundle;
import java.util.function.Predicate;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import org.hibernate.Session;
import org.hibernate.Transaction;

/**
 * FXML Controller class
 *
 * @author panda
 */
public class ViewAndBookAllResourcesController implements Initializable {
    @FXML
    private ListView<Facility> facilitiesListView;
    @FXML
    private Button facilitiesEditButton;
    @FXML
    private Button equipmentsEditButton;
    @FXML
    private TextField facilitiesSizeField;
    @FXML
    private TextField facilitiesBookingStartingTime;
    @FXML
    private TextField facilitiesNameField;
    @FXML
    private TextField facilitiesFloorField;
    @FXML
    private DatePicker facilitiesBookingDatePicker;
    @FXML
    private TextArea facilitiesDescriptionArea;
    @FXML
    private TextField facilitiesBookingEndingTime;
    @FXML
    private TextField equipmentsModelField;
    @FXML
    private TextField equipmentsBookingStartingTime;
    @FXML
    private TextField equipmentsNameField;
    @FXML
    private TextField equipmentsBrandField;
    @FXML
    private DatePicker equipmentsBookingDatePicker;
    @FXML
    private TextArea equipmentsDescriptionArea;
    @FXML
    private TextField equipmentsBookingEndingTime;
    @FXML
    private ListView<Equipment> equipmentsListView;
    @FXML
    private ListView<BookingInfo> facilitiesBookingList;
    @FXML
    private ListView<BookingInfo> equipmentBookingList;
    @FXML
    private Label bookingMsg;
    @FXML
    private Label eBookingMsg;
    @FXML
    private ListView<Resource> otherResourceListView;
    @FXML
    private TextArea otherRDescField;
    @FXML
    private TextField otherRNameField;
    @FXML
    private TextField otherREndFiled;
    @FXML
    private DatePicker otehrRDP;
    @FXML
    private TextField otherRStartField;    
    
    private Session session;
    private Transaction transaction;
    private ObservableList<Facility> facilities;
    private ObservableList<BookingInfo> bookingInfos;
    private ObservableList<Equipment> equipments;
    private ObservableList<Resource> resources;
    private FilteredList<Resource> filteredResources;
    
    private Facility selectedFacility;
    private Equipment selectedEquipment;
    private Resource selectedResource;
    @FXML
    private Label otherBookingMsg;


    public void hideAdminsButton() {
        facilitiesEditButton.setDisable(true);
        equipmentsEditButton.setDisable(true);
    }

    /**
     * Initializes the controller class.
     */
    
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        session = SessionSingleton.getSessionFactory().openSession();
        facilities = FXCollections.observableArrayList();
        equipments = FXCollections.observableArrayList();
        bookingInfos = FXCollections.observableArrayList();
        resources = FXCollections.observableArrayList();
        List<Facility> f = session.createCriteria(Facility.class).list();
        facilities.addAll(f);
        List<Equipment> e = session.createCriteria(Equipment.class).list();
        equipments.addAll(e);
        List<Resource> o = session.createCriteria(Resource.class).list();
        //resources.addAll(o);
        List<Resource> temp = new ArrayList<>();
        for(Resource rs:o){
            if(rs.getCompositeID().getResourceType().equals("Other")){
                temp.add(rs);
            }
        }
        resources.addAll(temp);
        equipmentsListView.setItems(equipments);
        facilitiesListView.setItems(facilities);
        otherResourceListView.setItems(resources);
        
        
        
        
        facilitiesListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Facility>(){
            @Override
            public void changed(ObservableValue<? extends Facility> observable,Facility oldValue,Facility newValue){
                selectedFacility=newValue;
                System.out.println(newValue);
                facilitiesNameField.setText(newValue.getCompositeID().getName());
                facilitiesFloorField.setText(newValue.getFloor());
                facilitiesSizeField.setText(newValue.getSize());
                facilitiesDescriptionArea.setText(newValue.getDescription());
                bookingInfos.addAll(newValue.getTimesAndDates());
                for(BookingInfo bi:bookingInfos)
                    System.out.println("time: "+bi.getCompositeBookingPK().getStart());
                facilitiesBookingList.setItems(bookingInfos);
            }
            
        });
        equipmentsListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Equipment>(){
            @Override
            public void changed(ObservableValue<? extends Equipment> observable,Equipment oldValue,Equipment newValue){
                selectedEquipment=newValue;
                System.out.println(newValue);
                equipmentsNameField.setText(newValue.getCompositeID().getName());
                equipmentsBrandField.setText(newValue.getBrand());
                equipmentsModelField.setText(newValue.getModel());
                equipmentsDescriptionArea.setText(newValue.getDescription());
                bookingInfos.addAll(selectedEquipment.getTimesAndDates());
                for(BookingInfo bi:bookingInfos)
                    System.out.println("time: "+bi.getCompositeBookingPK().getStart());
                equipmentBookingList.setItems(bookingInfos);
            }
            
        });
        otherResourceListView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Resource>(){
            @Override
            public void changed(ObservableValue<? extends Resource> observable,Resource oldValue,Resource newValue){
                selectedResource=newValue;
                System.out.println(newValue);
                otherRNameField.setText(newValue.getCompositeID().getName());
                otherRDescField.setText(newValue.getDescription());
                bookingInfos.addAll(selectedResource.getTimesAndDates());
                for(BookingInfo bi:bookingInfos)
                    System.out.println("time: "+bi.getCompositeBookingPK().getStart());
                //equipmentBookingList.setItems(bookingInfos);
            }
            
        });
    }    

    @FXML
    private void handleFacilitiesBookThisButtonAction(ActionEvent event) {
        try{
            Date date = Date.from(facilitiesBookingDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());

            Time start = Time.valueOf(facilitiesBookingStartingTime.getText());
            Time end = Time.valueOf(facilitiesBookingEndingTime.getText());
            BookingInfo bookingInfo = new BookingInfo(new CompositeBookingPK(date, start, end));
            selectedFacility.addTimesAndDates(bookingInfo);
            transaction = session.beginTransaction();
            session.update(selectedFacility);
            //session.update(bookingInfo);
            transaction.commit();
            System.out.println("booked!!");
            bookingMsg.setText("Booked :)");
            facilitiesBookingStartingTime.clear();
            facilitiesBookingEndingTime.clear();
        }
        catch(Exception e){
               System.out.println("could not book!");
               transaction.rollback();
        }
    }
    
    @FXML
    private void handleEquipmentsBookThisButtonAction(ActionEvent event) {
        try{
            Date date = Date.from(equipmentsBookingDatePicker.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            Time start = Time.valueOf(equipmentsBookingStartingTime.getText());
            Time end = Time.valueOf(equipmentsBookingEndingTime.getText());
            BookingInfo bookingInfo = new BookingInfo(new CompositeBookingPK(date, start, end));
            selectedEquipment.addTimesAndDates(bookingInfo);
            transaction = session.beginTransaction();
            session.update(selectedEquipment);
            //session.update(bookingInfo);
            transaction.commit();
            System.out.println("booked!!");
            eBookingMsg.setText("Booked :)");
            equipmentsBookingStartingTime.clear();
            equipmentsBookingEndingTime.clear();
        }
        catch(Exception e){
               System.out.println("could not book!");
               transaction.rollback();
        }
    }
    
    @FXML
    private void otherRBookButtonAction(ActionEvent event) {
        try{
            Date date = Date.from(otehrRDP.getValue().atStartOfDay(ZoneId.systemDefault()).toInstant());
            Time start = Time.valueOf(otherRStartField.getText());
            Time end = Time.valueOf(otherREndFiled.getText());
            BookingInfo bookingInfo = new BookingInfo(new CompositeBookingPK(date, start, end));
            selectedResource.addTimesAndDates(bookingInfo);
            transaction = session.beginTransaction();
            session.update(selectedResource);
            //session.update(bookingInfo);
            transaction.commit();
            System.out.println("booked!!");
            otherBookingMsg.setText("Booked :)");
            otherRStartField.clear();
            otherREndFiled.clear();
        }
        catch(Exception e){
               System.out.println("could not book!");
               transaction.rollback();
        }
    }
    
    @FXML
    private void handleFacilitiesEditButtonAction(ActionEvent event) {
    }

    @FXML
    private void equipmentsEditValuesButtonAction(ActionEvent event) {
    }

    @FXML
    private void otherEditButtonAction(ActionEvent event) {
    }
   
    
}
