package com.rest.Register.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "booking_tbl")
public class Booking {

    @EmbeddedId
    private CompositePK compositePK;


    public Booking(){}

    public CompositePK getCompositePK() {
        return compositePK;
    }

    public void setCompositePK(CompositePK compositePK) {
        this.compositePK = compositePK;
    }

    public int getUserId(){
        return compositePK.getUserId();
    }

    public int getBookId(){
    return compositePK.getBookId();
    }

    public void setUserId(int id){
    compositePK.setUserId(id);
    }

    public void setBookId(int id){
        compositePK.setBookId(id);
    }

    public Booking(CompositePK compositePK) {
        this.compositePK = compositePK;
    }
}

