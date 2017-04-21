/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.resource.management;

import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.OneToMany;

/**
 *
 * @author panda
 */
@Entity
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class Resource {
    @Id
    @EmbeddedId
    private CompositeResourceIDClass compositeID;
    private String description;
    //@OneToMany(targetEntity = BookingInfo.class, mappedBy = "resource",cascade = CascadeType.ALL,fetch=FetchType.EAGER)
    @OneToMany
    @JoinTable(
            name="Resource_Booking",
            joinColumns = {@JoinColumn( name="resourceName"),@JoinColumn(name = "resourceType")},
            inverseJoinColumns = {@JoinColumn( name="date"),@JoinColumn( name="start"),@JoinColumn( name="end")}
    )
    private Collection<BookingInfo> timesAndDates; 
    
    
    public Resource() {
        timesAndDates = new ArrayList<>();
    }

    public Resource(CompositeResourceIDClass compositeID, String description) {
        this();
        this.compositeID = compositeID;
        this.description = description;
    }
    
    public void addTimesAndDates(BookingInfo info) {
        this.timesAndDates.add(info);
    }

    public Collection<BookingInfo> getTimesAndDates() {
        return timesAndDates;
    }

    public CompositeResourceIDClass getCompositeID() {
        return compositeID;
    }

    public String getDescription() {
        return description;
    }

    public void setCompositeID(CompositeResourceIDClass compositeID) {
        this.compositeID = compositeID;
    }

    

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public String toString() {
        return  compositeID.getName();
    }
    
    
}
