package com.repositories.dao;

import org.springframework.stereotype.Repository;

import com.entities.language.Language;
import com.entities.language.LanguagePage;

@Repository
public interface ILanguage extends ICommon<Language, Integer>{
	Language findLanguageByCanonical(String cano);
	LanguagePage paginate( String keyword, int pageno, int pagesize);
	Language edit(Integer id);
	
	
	
}
