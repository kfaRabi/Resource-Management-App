/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.resource.management;

import javax.persistence.Entity;

/**
 *
 * @author panda
 */
@Entity
public class Facility extends Resource {
    private String floor;
    private String size;

    public Facility() {
        
    }

    public Facility(String floor, String size, CompositeResourceIDClass compositeID, String description) {
        super(compositeID, description);
        this.floor = floor;
        this.size = size;
    }

    

    public String getFloor() {
        return floor;
    }

    public String getSize() {
        return size;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    public void setSize(String size) {
        this.size = size;
    }

    @Override
    public String toString() {
        return getCompositeID().getName();
    }
    
    

    
}
