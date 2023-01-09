package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Zone;


public interface RepoZone  extends JpaRepository<Zone, Integer> {
	
	Zone findById(int id);
	@Query("select z from Zone z where z.ville =:ville")
	List<Zone> findZoneByVille(int ville);

	
}