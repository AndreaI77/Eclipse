package com.adrea.jokes.models.services;

import java.util.List;
import com.adrea.jokes.models.entity.Language;

public interface ILanguageService {
	public List<Language> findAll();
	public Language save(Language language);
	public Language findById(int id);
	public void delete(int id);

}
