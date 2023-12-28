package com.adrea.jokes.models.dao;

import org.springframework.data.repository.CrudRepository;

import com.adrea.jokes.models.entity.Language;

public interface ILanguageDAO extends CrudRepository<Language,Integer> {

}
