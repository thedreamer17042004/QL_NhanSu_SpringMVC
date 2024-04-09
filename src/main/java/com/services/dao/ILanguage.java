package com.services.dao;

import org.springframework.stereotype.Service;

import com.entities.language.Language;
import com.entities.language.LanguagePage;

@Service
public interface ILanguage {
	LanguagePage paginate(String searchname, int pageno, int pageSize);
	Language edit(int id);
}
