package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.demo.model.Resto;
import com.example.demo.model.Restospeci;
import com.example.demo.model.restoSpecPK;

public interface RestoSpec  extends JpaRepository<Restospeci, Integer> {
	
}
