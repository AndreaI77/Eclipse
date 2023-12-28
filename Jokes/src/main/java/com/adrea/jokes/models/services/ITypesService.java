package com.adrea.jokes.models.services;

import java.util.List;
import com.adrea.jokes.models.entity.Types;

public interface ITypesService {
	public List<Types> findAll();
	public Types findById(int id);
}
