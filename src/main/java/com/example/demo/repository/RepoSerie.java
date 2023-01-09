package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Serie;


public interface RepoSerie extends JpaRepository<Serie, Integer> {
	
	Serie findById(int id);

}
