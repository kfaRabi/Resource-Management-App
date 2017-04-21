/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.resource.management;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Embeddable;

/**
 *
 * @author panda
 */
@Embeddable
public class CompositeResourceIDClass implements Serializable{
    @Column(name = "resourceName")
    private String name;
    @Column(name = "resourceType",columnDefinition = "varchar(20)")
    private String resourceType;

    public CompositeResourceIDClass() {
    }
    
    

    public CompositeResourceIDClass(String name, String resourceType) {
        this.name = name;
        this.resourceType = resourceType;
    }

    public String getName() {
        return name;
    }

    public String getResourceType() {
        return resourceType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setResourceType(String resourceType) {
        this.resourceType = resourceType;
    }
    
}
