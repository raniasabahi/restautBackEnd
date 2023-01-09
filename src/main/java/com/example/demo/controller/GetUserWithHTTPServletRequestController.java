package com.example.demo.controller;

import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;

@Controller
public class GetUserWithHTTPServletRequestController {
	
	@Autowired 
	private UserRepository up;
 
    @RequestMapping(value = "/username", method = RequestMethod.GET)
    @ResponseBody
    public String currentUserNameSimple(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        return principal.getName();
    }
    
    @RequestMapping(value = "/user", method = RequestMethod.GET)
    @ResponseBody
    public User currentUserSimple(HttpServletRequest request) {
        Principal principal = request.getUserPrincipal();
        return up.findByUsername(principal.getName());
    }
   
}