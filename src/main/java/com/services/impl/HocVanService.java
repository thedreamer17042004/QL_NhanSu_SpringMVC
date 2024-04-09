package com.services.impl;

import java.sql.Timestamp;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.entities.chucvu.ChucVu;
import com.entities.chucvu.ChucVuLanguage;
import com.entities.chucvu.ChucVuPage;
import com.entities.chucvu.ChucVuView;
import com.entities.hocvan.HocVan;
import com.entities.hocvan.HocVanLanguage;
import com.entities.hocvan.HocVanPage;
import com.entities.hocvan.HocVanView;
import com.entities.language.Language;
import com.repositories.dao.IHocVanLanguage;
import com.services.dao.IHocVan;

@Service
public class HocVanService implements IHocVan{

	@Autowired
	com.repositories.dao.IHocVan hocVanRepo;
	
	@Autowired
	IHocVanLanguage hocVanLanguageRepo;
	
	@Autowired
	com.repositories.dao.ILanguage languageRepo;
	
	@Override
	public HocVanPage paginate(String keyword, int pageno, int pagesize) {
		HocVanPage hocvanPage = hocVanRepo.paginate(keyword, pageno, pagesize);
		return hocvanPage;
	}

	@Override
	public boolean insert(String name) {
		Locale locale = LocaleContextHolder.getLocale();
	     java.util.Date currentDate = new java.util.Date();
		Timestamp timestamp = new Timestamp(currentDate.getTime());
		HocVanLanguage hvl = hocVanLanguageRepo.findByName(name);
		if(hvl!=null) {
			throw new com.utils.Error("Học vấn đã tồn tại");
		}
		HocVan cv = new HocVan();
		cv.setCreateDate(timestamp);

		boolean insert = hocVanRepo.insert(cv);
		
		if(insert) {

			Language currentL = languageRepo.findLanguageByCanonical(locale.getLanguage());
			HocVanLanguage hvl1 = new HocVanLanguage();
			hvl1.setHocVan(cv);
			hvl1.setLanguage(currentL);
			hvl1.setTenTrinhDo(name);
			hvl1.setCreateDate(timestamp);
			hocVanLanguageRepo.insert(hvl1);
			
			
			return true;
		}

		return false;
	}

	@Override
	public HocVanView edit(int id) {
		HocVanView ctv = hocVanRepo.edit(id);
		return ctv;
	}

	@Override
	public boolean update(int id, String name) {
		Locale locale = LocaleContextHolder.getLocale();
		
		  java.util.Date currentDate = new java.util.Date();
		  Timestamp timestamp = new Timestamp(currentDate.getTime());
		 
		HocVan cv = hocVanRepo.findById(id);
		if(cv!=null) {
		
			Language currentL = languageRepo.findLanguageByCanonical(locale.getLanguage());
			HocVanLanguage cvl = hocVanLanguageRepo.findById(id, locale.getLanguage());
			
			cvl.setHocVan(cv);
			cvl.setLanguage(currentL);
			cvl.setTenTrinhDo(name);
			hocVanLanguageRepo.update(cvl);
			
			
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(int id) {
		boolean deleted = hocVanRepo.delete(id);
		if(deleted) {
			return true;
		}
		return false;
	}

}
