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
public class Serie {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id ;
	private String nom;
	@OneToMany(mappedBy = "serie", fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Resto> restos;
	
	public Serie() {
		super();
	}


	public Serie(String nom) {
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


	public List<Resto> getRestos() {
		return restos;
	}


	public void setRestos(List<Resto> restos) {
		this.restos = restos;
	}
}
