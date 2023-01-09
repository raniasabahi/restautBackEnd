package com.example.demo.model;

import java.io.Serializable;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class Restospeci implements Serializable {

	@EmbeddedId
    private restoSpecPK restoSpecPK;
	 @ManyToOne
	    @JoinColumn(name = "restoId", insertable = false, updatable = false)
	  private Resto resto;
	 @ManyToOne
	    @JoinColumn(name = "specialiteId", insertable = false, updatable = false)
	 private Specialite specialite;
	public Restospeci() {
		super();
	}
	public Restospeci(Resto resto, Specialite specialite) {
		super();
		this.resto = resto;
		this.specialite = specialite;
	}
	public restoSpecPK getRestoSpecPK() {
		return restoSpecPK;
	}
	public void setRestoSpecPK(restoSpecPK restoSpecPK) {
		this.restoSpecPK = restoSpecPK;
	}
	public Resto getResto() {
		return resto;
	}
	public void setResto(Resto resto) {
		this.resto = resto;
	}
	public Specialite getSpecialite() {
		return specialite;
	}
	public void setSpecialite(Specialite specialite) {
		this.specialite = specialite;
	}
	 
	 
	 
	 

}
