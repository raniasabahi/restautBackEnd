package com.example.demo.model;

import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


@Entity
public class Resto {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String nom;
	private String adresse;
	private String rank;
	private Double log, lat;
	@Temporal(TemporalType.TIME)
	@JsonFormat(pattern="HH:mm")
	private Date open;
	@Temporal(TemporalType.TIME)
	@JsonFormat(pattern="HH:mm")
	private Date close;
	private boolean weekend;
	private String urlphoto;
	private String etat;
	@ManyToOne
	private Serie serie;
	@ManyToOne
	private User user;
	@ManyToOne
	private Zone zone;
	@OneToMany(mappedBy = "resto", fetch = FetchType.EAGER)
	@JsonIgnore
	private List<Photo> photos;

	public Resto() {
		super();
	}

	

	public Resto(int id, String nom, String adresse, String rank, Double log, Double lat, Date open, Date close,
			boolean weekend, Serie serie, Zone zone, String urlphoto, String etat, User user) {
		super();
		this.id = id;
		this.nom = nom;
		this.adresse = adresse;
		this.rank = rank;
		this.log = log;
		this.lat = lat;
		this.open = open;
		this.close = close;
		this.weekend = weekend;
		this.serie = serie;
		this.zone = zone;
		this.urlphoto= urlphoto;
		this.etat = etat;
		this.user= user;
	}
	

	public String getEtat() {
		return etat;
	}



	public void setEtat(String etat) {
		this.etat = etat;
	}



	public User getUser() {
		return user;
	}



	public void setUser(User user) {
		this.user = user;
	}



	public String getUrlphoto() {
		return urlphoto;
	}



	public void setUrlphoto(String urlPhoto) {
		this.urlphoto = urlPhoto;
	}




	public Resto(String nom, String adresse, String rank, Double log, Double lat, Date open, Date close,
			boolean weekend, Serie serie, Zone zone, List<Specialite> specialites) {
		super();
		this.nom = nom;
		this.adresse = adresse;
		this.rank = rank;
		this.log = log;
		this.lat = lat;
		this.open = open;
		this.close = close;
		this.weekend = weekend;
		this.serie = serie;
		this.zone = zone;
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

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public String getRank() {
		return rank;
	}

	public void setRank(String rank) {
		this.rank = rank;
	}

	public Double getLog() {
		return log;
	}

	public void setLog(Double log) {
		this.log = log;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}

	public Date getOpen() {
		return open;
	}

	public void setOpen(Date open) {
		this.open = open;
	}

	public Date getClose() {
		return close;
	}

	public void setClose(Date close) {
		this.close = close;
	}

	public boolean isWeekend() {
		return weekend;
	}

	public void setWeekend(boolean weekend) {
		this.weekend = weekend;
	}

	public Zone getZone() {
		return zone;
	}

	public void setZone(Zone zone) {
		this.zone = zone;
	}

	public Serie getSerie() {
		return serie;
	}

	public void setSerie(Serie serie) {
		this.serie = serie;
	}
	
	public List<Photo> getPhotos() {
		return photos;
	}



	public void setPhotos(List<Photo> photos) {
		this.photos = photos;
	}


}
