package com.example.demo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Ville {
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private int id;
	private String nom;
	@OneToMany(mappedBy = "ville", fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Zone> zones;
	
	public Ville() {
		super();
	}


	public Ville(String nom) {
		super();
		this.nom = nom;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public String getNom() {
		return nom;
	}


	public void setNom(String nom) {
		this.nom = nom;
	}


	public List<Zone> getZones() {
		return zones;
	}


	public void setZones(List<Zone> zones) {
		this.zones = zones;
	}

	
}
