package com.adrea.jokes.models.services;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.adrea.jokes.models.dao.IFlagsDAO;
import com.adrea.jokes.models.entity.Flags;

@Service
public class FlagsServiceImpl implements IFlagsService {
	@Autowired
	private IFlagsDAO flagsDao;
	
	@Override
	@Transactional(readOnly = true)
	public List<Flags> findAll() {
		return (List<Flags>) flagsDao.findAll();
	}
	
	@Override
	@Transactional
	public Flags save(Flags flag) {
		return flagsDao.save(flag);	
	}

	@Override
	@Transactional(readOnly = true)
	public Flags findById(int id) {
		
		return flagsDao.findById(id).orElse(null);
	}

	@Override
	@Transactional
	public void delete(int id) {
		flagsDao.deleteById( id);
	}

}
