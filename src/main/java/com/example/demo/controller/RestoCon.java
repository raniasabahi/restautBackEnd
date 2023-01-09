package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.model.Resto;
import com.example.demo.model.Serie;
import com.example.demo.model.Specialite;
import com.example.demo.model.Zone;
import com.example.demo.repository.RepoResto;
import com.example.demo.repository.RepoSerie;
import com.example.demo.repository.RepoSpecialite;
import com.example.demo.repository.RepoZone;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("resto")
public class RestoCon {

	@Autowired
	private RepoResto repoResto;
	@Autowired
	private RepoZone repoZone;
	@Autowired
	private RepoSerie repoSerie;
	@Autowired
	private RepoSpecialite repoSpecialite;

	@PostMapping("/save")
	public void save(@RequestBody Resto r) {
		repoResto.save(r);
	}
	

	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable(required = true) String id) {
		Resto sp = repoResto.findById(Integer.parseInt(id));
		repoResto.delete(sp);
	}

	@GetMapping("/all")
	public List<Resto> findAll() {
		return repoResto.findAll();
	}

	@GetMapping(value = "/count")
	public long countResto() {
		return repoResto.count();
	}

	@GetMapping("/byzone/{id}")
	public List<Resto> findZone(@PathVariable(required = false) String id) {
		Zone z = repoZone.findById(Integer.parseInt(id));
		return z.getRestaus();
	}
	
	@GetMapping("/byuser/{id}")
	public List<Resto> findUser(@PathVariable(required = false) String id) {
		Zone z = repoZone.findById(Integer.parseInt(id));
		return z.getRestaus();
	}

	@GetMapping(value = "/{name}")
	public List<Resto> findByNom(@PathVariable final String nom) {
		return repoResto.findByNom(nom);
	}
	@GetMapping(value = "/{rank}")
	public List<Resto> findByRank(@PathVariable final String rank) {
		return repoResto.findByRank(rank);
	}
	@GetMapping("/byserie/{id}")
	public List<Resto> findSerie(@PathVariable(required = true) String id) {
		Serie s = repoSerie.findById(Integer.parseInt(id));
		return s.getRestos();
	}
	
}
