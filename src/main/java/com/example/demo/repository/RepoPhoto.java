package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.example.demo.model.Photo;


public interface RepoPhoto extends JpaRepository<Photo, Integer> {
	
	Photo findById(int id);
	@Query("select p from Photo p where p.resto.id =:resto")
	List<Photo> findByResto(int resto);
}