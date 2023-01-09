package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Resto;
import com.example.demo.model.Serie;
import com.example.demo.model.User;
import com.example.demo.model.Zone;


public interface RepoResto extends JpaRepository<Resto, Integer> {
	
	Resto findById(int id);

	@Query("select r from Resto r where r.zone =:zone")
	List<Resto> findByZone(int zone);
	
	
	@Query("select re from Resto re where re.serie=:s")
	List<Resto> findBySerie(int s);
	
	
	List<Resto> findByRank(String rank);
	List<Resto> findByNom(String nom);
	//List<Resto> findBSpec(Specialite spec);
	
	
	@Query("select rs from Resto rs where rs.user.id =:user")
	List<Resto> findByUser(int user);
	
}