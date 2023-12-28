package com.adrea.jokes.models.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.adrea.jokes.models.dao.IJokesDAO;
import com.adrea.jokes.models.entity.Jokes;


@Service
public class JokesServiceImpl implements IJokesService{
	
	@Autowired
	private IJokesDAO jokesDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Jokes> findAll() {
		// TODO Auto-generated method stub
		return (List<Jokes>) jokesDao.findAll();
	}
	
	@Override
	@Transactional
	public Jokes save(Jokes jokes) {
		return jokesDao.save(jokes);	
	}

	@Override
	@Transactional(readOnly = true)
	public Jokes findById(int id) {
		
		return jokesDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(int id) {
		jokesDao.deleteById( id);
	}
}
