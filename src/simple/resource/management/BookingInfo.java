/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.resource.management;

import java.sql.Time;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

/**
 *
 * @author panda
 */
@Entity
public class BookingInfo {
    @EmbeddedId
    private CompositeBookingPK compositeBookingPK;

    public BookingInfo(CompositeBookingPK compositeBookingPK) {
        this.compositeBookingPK = compositeBookingPK;
    }

    public CompositeBookingPK getCompositeBookingPK() {
        return compositeBookingPK;
    }

    public void setCompositeBookingPK(CompositeBookingPK compositeBookingPK) {
        this.compositeBookingPK = compositeBookingPK;
    }

    @Override
    public String toString() {
        return  compositeBookingPK.getDate()+" : Start:- "+compositeBookingPK.getStart();
    }
    
    
    
    
}
