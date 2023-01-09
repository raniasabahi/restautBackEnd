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

import com.example.demo.model.Photo;
import com.example.demo.repository.RepoPhoto;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("photo")
public class PhotoCon {
	
	@Autowired
	private RepoPhoto repoPhoto;
	
	@PostMapping("/save")
	public void save(@RequestBody Photo p){
	repoPhoto.save(p);
	}
	
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable (required = true) String id){
	Photo sp = repoPhoto.findById(Integer.parseInt(id));
	repoPhoto.delete(sp);
	}

	@GetMapping("/all")
	public List<Photo> findAll(){
	return repoPhoto.findAll();
	}
	
	@GetMapping(value = "/count")
	public long countPhoto() {
	return repoPhoto.count();
	}

	@GetMapping("/byresto/{id}")
	public List<Photo> findbyResto(@PathVariable(required = false) String id) {
		//Zone z = repoZone.findById(Integer.parseInt(id));
		return repoPhoto.findByResto(Integer.parseInt(id));
	}
}
