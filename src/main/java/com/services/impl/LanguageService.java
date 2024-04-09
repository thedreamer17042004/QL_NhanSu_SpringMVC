package com.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entities.language.Language;
import com.entities.language.LanguagePage;
import com.services.dao.ILanguage;

@Service
public class LanguageService implements ILanguage{
	@Autowired
	com.repositories.dao.ILanguage languageRepo;
	
	@Override
	public LanguagePage paginate(String searchname, int pageno, int pageSize) {
		LanguagePage languagePage = languageRepo.paginate(searchname, pageno, pageSize);
		return languagePage;
	}

	@Override
	public Language edit(int id) {
		Language bn = languageRepo.edit(id);
		return bn;
	}
}
