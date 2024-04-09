package com.services.impl;

import java.sql.Timestamp;
import java.util.Locale;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.entities.chucvu.ChucVu;
import com.entities.chucvu.ChucVuLanguage;
import com.entities.chucvu.ChucVuPage;
import com.entities.chucvu.ChucVuView;
import com.entities.language.Language;
import com.entities.room.Room;
import com.entities.room.RoomLanguage;
import com.entities.room.RoomPage;
import com.entities.room.RoomView;
import com.repositories.dao.IPhongBan;
import com.repositories.dao.IPhongBanLanguage;
import com.repositories.dao.IRole;
import com.services.dao.IRoom;

@Service
public class RoomService implements IRoom{

	
	  private static final AtomicInteger counter = new AtomicInteger(0);

	    public static int generateID() {
	        return counter.incrementAndGet();
	    }
	
	@Autowired
	IPhongBan phongBanRepo;
	
	@Autowired
	IPhongBanLanguage phongBanLanguageRepo;
	
	@Autowired
	com.repositories.dao.ILanguage languageRepo;
	
	
	@Override
	public RoomPage paginate(String keyword, int pageno, int pagesize) {
		RoomPage roomPage = phongBanRepo.paginate(keyword, pageno, pagesize);
		return roomPage;
	}

	@Override
	public boolean insert(String name, String address, String phoneRoom) {
		Locale locale = LocaleContextHolder.getLocale();
	     java.util.Date currentDate = new java.util.Date();
		Timestamp timestamp = new Timestamp(currentDate.getTime());
		RoomLanguage roomlanguage = phongBanLanguageRepo.findByName(name);
		if(roomlanguage!=null) {
			throw new com.utils.Error("Phòng ban đã tồn tại");
		}
		Room r = new Room();
		r.setId(generateID());
		r.setPhoneRoom(phoneRoom);
		r.setAddress(address);

		boolean insert = phongBanRepo.insert(r);
		
		if(insert) {
			Language currentL = languageRepo.findLanguageByCanonical(locale.getLanguage());
			RoomLanguage rl = new RoomLanguage();
			rl.setLanguage(currentL);
			rl.setRoom(r);
			rl.setRoomName(name);
			rl.setCreateDate(timestamp);
			phongBanLanguageRepo.insert(rl);

			return true;
		}

		return false;
	}

	@Override
	public RoomView edit(int id) {
		RoomView rv = phongBanRepo.edit(id);
		return rv;
	}

	@Override
	public boolean update(int id, String name,String address, String phoneRoom) {

		Locale locale = LocaleContextHolder.getLocale();
		
		  java.util.Date currentDate = new java.util.Date();
		  Timestamp timestamp = new Timestamp(currentDate.getTime());
		 
		Room cv = phongBanRepo.findById(id);
		if(cv!=null) {		
			cv.setAddress(address);
			cv.setPhoneRoom(phoneRoom);
			if(phongBanRepo.update(cv)) {
				
				Language currentL = languageRepo.findLanguageByCanonical(locale.getLanguage());
				RoomLanguage cvl = phongBanLanguageRepo.findById(id, locale.getLanguage());
				
				cvl.setLanguage(currentL);
				cvl.setRoom(cv);
				cvl.setRoomName(name);
				phongBanLanguageRepo.update(cvl);
				
				return true;
			}
			return false;
			
		
		}
		return false;
	}

	@Override
	public boolean delete(int id) {
		boolean deleted = phongBanRepo.delete(id);
		if(deleted) {
			return true;
		}
		return false;
	}

}
