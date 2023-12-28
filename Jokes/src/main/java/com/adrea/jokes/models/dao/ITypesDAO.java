package com.adrea.jokes.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.adrea.jokes.models.entity.Types;

public interface ITypesDAO extends CrudRepository<Types,Integer> {

}
