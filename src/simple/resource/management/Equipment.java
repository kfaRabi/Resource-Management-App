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
public class Equipment extends Resource {
    private String brand;
    private String model;

    public Equipment() {
    }

    public Equipment(String brand, String model, CompositeResourceIDClass compositeID, String description) {
        super(compositeID, description);
        this.brand = brand;
        this.model = model;
    }

    

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public String toString() {
        return "Equipment: " + getCompositeID().getName();
    }

    
    
}
