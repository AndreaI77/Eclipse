package com.adrea.jokes.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.adrea.jokes.models.entity.Jokes;

public interface IJokesDAO extends CrudRepository<Jokes,Long> {

}
