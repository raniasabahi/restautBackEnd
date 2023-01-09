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

import com.example.demo.model.Serie;
import com.example.demo.repository.RepoSerie;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("serie")
public class SerieCon {
	
	@Autowired
	private RepoSerie repoSerie;
	
	@PostMapping("/save")
	public void save(@RequestBody Serie s){
	repoSerie.save(s);
	}
	
	
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable (required = true) String id){
	Serie sp = repoSerie.findById(Integer.parseInt(id));
	repoSerie.delete(sp);
	}

	@GetMapping("/all")
	public List<Serie> findAll(){
	return repoSerie.findAll();
	}
	
	@GetMapping(value = "/count")
	public long countSpec() {
	return repoSerie
			.count();
	}

}
