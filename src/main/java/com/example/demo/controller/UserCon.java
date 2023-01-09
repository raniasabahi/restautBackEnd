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

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;


@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("user")
public class UserCon {

	@Autowired
	private UserRepository repoUser;
	
	@PostMapping("/save")
	public void save(@RequestBody User u){
	repoUser.save(u);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable (required = true) String id){
	User v = repoUser.findById(Integer.parseInt(id));
	repoUser.delete(v);
	}

	@GetMapping("/all")
	public List<User> findAll(){
	return repoUser.findAll();
	}
	
	@GetMapping(value = "/count")
	public long countVille() {
	return repoUser.count();
	}

}