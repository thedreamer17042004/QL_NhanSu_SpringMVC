package com.services.impl;

import java.sql.Timestamp;
import java.util.Locale;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.entities.chucvu.ChucVu;
import com.entities.chucvu.ChucVuLanguage;
import com.entities.chucvu.ChucVuPage;
import com.entities.chucvu.ChucVuView;
import com.entities.language.Language;
import com.repositories.dao.IChucVuLanguage;
import com.services.dao.IChucVu;
import com.services.dao.ILanguage;

@Service
public class ChucVuService implements IChucVu{

	@Autowired
	com.repositories.dao.IChucVu chucVuRepo;
	
	@Autowired
	IChucVuLanguage chucVuLanguageRepo;
	
	@Autowired
	com.repositories.dao.ILanguage languageRepo;
	
	
	@Override
	public ChucVuPage paginate(String keyword, int pageno, int pagesize) {
		ChucVuPage brandPage = chucVuRepo.paginate(keyword, pageno, pagesize);
		return brandPage;
	}


	@Override
	public boolean insert(String name) {
		Locale locale = LocaleContextHolder.getLocale();
	     java.util.Date currentDate = new java.util.Date();
		Timestamp timestamp = new Timestamp(currentDate.getTime());
		ChucVuLanguage chucvu = chucVuLanguageRepo.findByName(name);
		if(chucvu!=null) {
			throw new com.utils.Error("Chức vụ đã tồn tại");
		}
		ChucVu cv = new ChucVu();
		String id = generateId();
		cv.setMaChucVuNV(id);
		cv.setCreateDate(timestamp);

		boolean insert = chucVuRepo.insert(cv);
		
		System.out.println("qUA ĐÂY 1");
		if(insert) {
			System.out.println("qUA ĐÂY");
			Language currentL = languageRepo.findLanguageByCanonical(locale.getLanguage());
			ChucVuLanguage cvl = new ChucVuLanguage();
			cvl.setChucvu(cv);
			cvl.setLanguage(currentL);
			cvl.setTenChucVu(name);
			cvl.setCreateDate(timestamp);
			chucVuLanguageRepo.insert(cvl);
			System.out.println("qUA ĐÂY 22");
			
			
			return true;
		}

		return false;
	}
	
	
	  private String generateId() {
	        // Generate a UUID and truncate it to ensure length is less than 11 characters
	        UUID uuid = UUID.randomUUID();
	        String uuidString = uuid.toString().replace("-", ""); // Remove hyphens
	       return uuidString.substring(0, 3); // Truncate to 10 characters
	    }


	@Override
	public ChucVuView edit(String id) {
		ChucVuView ctv = chucVuRepo.edit(id);
		return ctv;
	}


	@Override
	public boolean update(String id, String name) {
		Locale locale = LocaleContextHolder.getLocale();
	
		  java.util.Date currentDate = new java.util.Date();
		  Timestamp timestamp = new Timestamp(currentDate.getTime());
		 
		ChucVu cv = chucVuRepo.findById(id);
		if(cv!=null) {
		
			Language currentL = languageRepo.findLanguageByCanonical(locale.getLanguage());
			ChucVuLanguage cvl = chucVuLanguageRepo.findById(id, locale.getLanguage());
			
			cvl.setChucvu(cv);
			cvl.setLanguage(currentL);
			cvl.setTenChucVu(name);
			chucVuLanguageRepo.update(cvl);
			
			
			return true;
		}
		return false;
	}


	@Override
	public boolean delete(String id) {
		boolean deleted = chucVuRepo.delete(id);
		if(deleted) {
			return true;
		}
		return false;
	}
}
