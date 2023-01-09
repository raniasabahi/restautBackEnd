package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Specialite;


public interface RepoSpecialite extends JpaRepository<Specialite, Integer> {
	
	Specialite findById(int id);

}