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
import com.example.demo.model.Zone;
import com.example.demo.repository.RepoVille;
import com.example.demo.repository.RepoZone;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("zone")
public class ZoneCon {
	@Autowired
	private RepoZone repoZone;
	@Autowired
	private RepoVille repoVille;

	
	@PostMapping("/save")
	public void save(@RequestBody Zone zone){
	repoZone.save(zone);
	}

	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable (required = true) String id){
	Zone z = repoZone.findById(Integer.parseInt(id));
	repoZone.delete(z);
	}

	@GetMapping("/all")
	public List<Zone> findAll(){
	return repoZone.findAll();
	}
	
	@GetMapping(value = "/count")
	public long countZones() {
	return repoZone.count();
	}

	@GetMapping("/findByIdVille/{id}")
	public List<Zone> findAllById(@PathVariable(required = false) String id){
		Ville v = repoVille.findById(Integer.parseInt(id));
        return v.getZones();
    }
}
