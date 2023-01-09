package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Restospeci;
import com.example.demo.repository.RestoSpec;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("affecter")
public class RestoSpecCon {
	
	@Autowired
	private RestoSpec repoResto;

	@PostMapping("/save")
	public void save(@RequestBody Restospeci r) {
		repoResto.save(r);
	}
	


	@GetMapping("/all")
	public List<Restospeci> findAll() {
		return repoResto.findAll();
	}

	@GetMapping(value = "/count")
	public long countResto() {
		return repoResto.count();
	}

	
	

}
