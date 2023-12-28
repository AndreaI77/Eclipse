package com.adrea.jokes.models.services;

import java.util.List;

import com.adrea.jokes.models.entity.Jokes;

public interface IJokesService {
	public List<Jokes> findAll();
	public Jokes save(Jokes jokes);
	public Jokes findById(int id);
	public void delete(int id);
}
