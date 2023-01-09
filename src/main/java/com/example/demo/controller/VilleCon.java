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

import com.example.demo.model.Ville;
import com.example.demo.repository.RepoVille;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("ville")
public class VilleCon {

	@Autowired
	private RepoVille repoVille;
	
	@PostMapping("/save")
	public void save(@RequestBody Ville ville){
	repoVille.save(ville);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable (required = true) String id){
	Ville v = repoVille.findById(Integer.parseInt(id));
	repoVille.delete(v);
	}

	@GetMapping("/all")
	public List<Ville> findAll(){
	return repoVille.findAll();
	}
	
	@GetMapping(value = "/count")
	public long countVille() {
	return repoVille.count();
	}

}
