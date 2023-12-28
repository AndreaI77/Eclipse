package com.adrea.jokes.models.services;

import java.util.List;

import com.adrea.jokes.models.entity.Flags;

public interface IFlagsService {
	public List<Flags> findAll();
	public Flags save(Flags flag);
	public Flags findById(int id);
	public void delete(int id);

}
