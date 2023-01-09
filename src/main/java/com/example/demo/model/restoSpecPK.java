package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

@Embeddable
public class restoSpecPK implements Serializable{

	

	    private int restoId;
	    private int specialiteId;

	    public restoSpecPK() {
	    }

	    public restoSpecPK(int restoId, int specialiteId) {
	        this.restoId = restoId;
	        this.specialiteId = specialiteId;
	    }
	    

	    public int getRestoId() {
	        return restoId;
	    }

	    public void setRestoId(int produitId) {
	        this.restoId = produitId;
	    }

	    public int getSpecialiteId() {
	        return specialiteId;
	    }

	    public void setSpecialiteId(int commandeId) {
	        this.specialiteId = commandeId;
	    }
	    
	    
	    
	}


