/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.resource.management;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 *
 * @author panda
 */
@Entity
public class Account {
    @Id
    private String name;
    private String password;
    @Column(columnDefinition = "varchar(20)")
    private String userType;

    public Account() {
    }
    
    
    public Account(String name, String password, String userType) {
        this.name = name;
        this.password = password;
        this.userType = userType;
    }

    public String getName() {
        return name;
    }

    public String getPassword() {
        return password;
    }

    public String getUserType() {
        return userType;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setUserType(String userType) {
        this.userType = userType;
    }

    @Override
    public String toString() {
        return "name = " + name + ", userType = " + userType;
    }
    
    
}
