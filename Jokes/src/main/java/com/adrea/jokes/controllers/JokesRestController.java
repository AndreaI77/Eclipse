package com.adrea.jokes.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adrea.jokes.models.entity.Jokes;
import com.adrea.jokes.models.services.IJokesService;

@CrossOrigin(origins = {"*"})
@RestController
@RequestMapping("/api")
public class JokesRestController {
	
	@Autowired
	private IJokesService jokesService;
	
	@GetMapping("/jokes")
	public List<Jokes> index(){
		return jokesService.findAll();
		
	}
}
