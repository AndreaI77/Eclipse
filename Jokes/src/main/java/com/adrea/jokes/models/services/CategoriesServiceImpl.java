package com.adrea.jokes.models.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.adrea.jokes.models.dao.ICategoriesDAO;
import com.adrea.jokes.models.entity.Categories;

@Service
public class CategoriesServiceImpl implements ICategoriesService{
	
	@Autowired
	private ICategoriesDAO catDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Categories> findAll() {
		// TODO Auto-generated method stub
		return (List<Categories>) catDao.findAll();
	}
	
	@Override
	@Transactional
	public Categories save(Categories cat) {
		return catDao.save(cat);	
	}

	@Override
	@Transactional(readOnly = true)
	public Categories findById(int id) {
		
		return catDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(int id) {
		catDao.deleteById( id);
	}

}
