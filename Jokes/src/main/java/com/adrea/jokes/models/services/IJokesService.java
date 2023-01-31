package com.adrea.jokes.models.services;

import java.util.List;

import com.adrea.jokes.models.entity.Jokes;

public interface IJokesService {
	public List<Jokes> findAll();

}
