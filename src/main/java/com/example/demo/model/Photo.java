package com.example.demo.model;


import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;


@Entity
public class Photo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String url;
	@ManyToOne
	private Resto resto;
	
	
	public Photo() {
		super();
	}

	public Photo(String url) {
		super();
		this.url = url;
		
	}
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Resto getResto() {
		return resto;
	}

	public void setResto(Resto resto) {
		this.resto = resto;
	}
	


}
