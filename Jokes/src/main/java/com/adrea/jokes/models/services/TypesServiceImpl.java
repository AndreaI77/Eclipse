package com.adrea.jokes.models.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.adrea.jokes.models.dao.ITypesDAO;
import com.adrea.jokes.models.entity.Types;

@Service
public class TypesServiceImpl implements ITypesService{
	@Autowired
	private ITypesDAO typeDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Types> findAll() {
		// TODO Auto-generated method stub
		return (List<Types>) typeDao.findAll();
	}
	
	@Override
	@Transactional(readOnly = true)
	public Types findById(int id) {
		
		return typeDao.findById(id).orElse(null);
	}
}
