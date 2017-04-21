/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package simple.resource.management;

import java.io.Serializable;
import java.sql.Time;
import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.Id;

/**
 *
 * @author panda
 */
@Embeddable
public class CompositeBookingPK implements Serializable{
    @Column(name = "date")
    private Date date;
    @Column(name = "start")
    private Time start;
    @Column(name = "end")
    private Time end;

    public CompositeBookingPK(Date date, Time start, Time end) {
        this.date = date;
        this.start = start;
        this.end = end;
    }

    public Date getDate() {
        return date;
    }

    public Time getStart() {
        return start;
    }

    public Time getEnd() {
        return end;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setStart(Time start) {
        this.start = start;
    }

    public void setEnd(Time end) {
        this.end = end;
    }

    @Override
    public String toString() {
        return "CompositeBookingPK{" + "date=" + date + ", start=" + start + ", end=" + end + '}';
    }
    
    
    
}
