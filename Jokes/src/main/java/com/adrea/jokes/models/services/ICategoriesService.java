package com.adrea.jokes.models.services;

import java.util.List;

import com.adrea.jokes.models.entity.Categories;

public interface ICategoriesService {
	public List<Categories> findAll();
	public Categories save(Categories cat);
	public Categories findById(int id);
	public void delete(int id);
}
