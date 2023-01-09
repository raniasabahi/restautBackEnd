package com.example.demo.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;



@Entity
public class Zone {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nom;
	@ManyToOne
	private Ville ville;
	@OneToMany(mappedBy = "zone", fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Resto> restaus;
	
	public Zone() {
		super();
	}

	public Zone(int id, String nom, Ville ville) {
		super();
		this.id = id;
		this.nom = nom;
		this.ville=ville;
	}

	public List<Resto> getRestaus() {
		return restaus;
	}

	public void setRestaus(List<Resto> restaus) {
		this.restaus = restaus;
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

	public Ville getVille() {
		return ville;
	}

	public void setVille(Ville ville) {
		this.ville = ville;
	}
	
	

}

