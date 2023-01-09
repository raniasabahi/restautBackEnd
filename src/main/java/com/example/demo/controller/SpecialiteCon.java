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

import com.example.demo.model.Specialite;
import com.example.demo.repository.RepoSpecialite;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("specialite")
public class SpecialiteCon {
	
	@Autowired
	private RepoSpecialite repoSpec;
	
	@PostMapping("/save")
	public void save(@RequestBody Specialite s){
	repoSpec.save(s);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable (required = true) String id){
	Specialite sp = repoSpec.findById(Integer.parseInt(id));
	repoSpec.delete(sp);
	}

	@GetMapping("/all")
	public List<Specialite> findAll(){
	return repoSpec.findAll();
	}
	
	@GetMapping(value = "/count")
	public long countSpec() {
	return repoSpec.count();
	}

}
