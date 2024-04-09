package com.services.impl;

import java.sql.Timestamp;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Service;

import com.entities.chamcong.Cong;
import com.entities.chamcong.CongPage;
import com.entities.hocvan.HocVan;
import com.entities.hocvan.HocVanLanguage;
import com.entities.hocvan.HocVanPage;
import com.entities.hocvan.HocVanView;
import com.entities.language.Language;
import com.entities.nhanvien.NhanVien;
import com.repositories.dao.INhanVien;
import com.services.dao.ICong;

@Service
public class CongService implements ICong{

	@Autowired
	com.repositories.dao.ICong congRepo;
	
	@Autowired
	INhanVien nhanVienRepo;
	

	
	@Override
	public CongPage paginate(String keyword, int pageno, int pagesize) {
		CongPage congPage = congRepo.paginate(keyword, pageno, pagesize);
		return congPage;
	}

	@Override
	public boolean insert(Cong cong, String nhanSu) {
	  
		
		NhanVien nv = nhanVienRepo.findById(Integer.parseInt(nhanSu));
		
		cong.setNhanVien(nv);
		boolean insert = congRepo.insert(cong);
		
		if(insert) {
			
			return true;
		}

		return false;
		
	}

	@Override
	public Cong edit(int id) {
		Cong ctv = congRepo.findById(id);
		return ctv;
	}

	@Override
	public boolean update(int id, Cong cong,int nv) {
	
		NhanVien nv1 = nhanVienRepo.findById(nv);
		Cong cv = congRepo.findById(id);
		if(cv!=null) {
		
			cv.setSoCong(cong.getSoCong());
			cv.setNgay(cong.getNgay());
			cv.setNhanVien(nv1);
			congRepo.update(cv);
			
			
			return true;
		}
		return false;
	}

	@Override
	public boolean delete(int id) {
		boolean deleted = congRepo.delete(id);
		if(deleted) {
			return true;
		}
		return false;
	}

}
