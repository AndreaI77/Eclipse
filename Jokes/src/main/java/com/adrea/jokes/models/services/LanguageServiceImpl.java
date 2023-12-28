package com.adrea.jokes.models.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.adrea.jokes.models.dao.ILanguageDAO;
import com.adrea.jokes.models.entity.Language;

@Service
public class LanguageServiceImpl implements ILanguageService{
	@Autowired
	private ILanguageDAO langDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Language> findAll() {
		return (List<Language>) langDao.findAll();
	}
	
	@Override
	@Transactional
	public Language save(Language lang) {
		return langDao.save(lang);	
	}

	@Override
	@Transactional(readOnly = true)
	public Language findById(int id) {
		
		return langDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(int id) {
		langDao.deleteById( id);
	}

}
