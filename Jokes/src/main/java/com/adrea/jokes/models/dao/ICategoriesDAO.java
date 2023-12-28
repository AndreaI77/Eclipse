package com.adrea.jokes.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.adrea.jokes.models.entity.Categories;

public interface ICategoriesDAO extends CrudRepository<Categories,Integer>{

}
