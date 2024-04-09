package com.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entities.chamcong.Cong;
import com.entities.luong.ChiTietLuong;
import com.entities.luong.Luong;
import com.entities.luong.LuongPage;
import com.services.dao.IChiTietLuong;

@Service
public class ChiTietLuongService implements IChiTietLuong {

	
	@Autowired
	com.repositories.dao.IChiTietLuong chiTietLuongRepo;
	
	@Override
	public LuongPage paginate(String keyword, int pageno, int pagesize) {
		LuongPage luongPage = chiTietLuongRepo.paginate(keyword, pageno, pagesize);
		return luongPage;
	}

	@Override
	public boolean insert(ChiTietLuong luong, String nhanSu) {
		boolean insert = chiTietLuongRepo.insert(luong);
		
		if(insert) {
			
			return true;
		}

		return false;
	}

	@Override
	public ChiTietLuong edit(int id) {
		ChiTietLuong ctl = chiTietLuongRepo.findById(id);
		return ctl;
	}

	@Override
	public boolean update(int id, ChiTietLuong luong, int nv) {
//	Luong nv1 = luongRepo.findById(id);
//		
//		if(nv1!=null) {
//		
//			nv1.setMaNhanVien(nv);
//			nv1.setLuongToiThieu(luong.getLuongToiThieu());
//			nv1.setHeSoLuong(luong.getHeSoLuong());
//			nv1.setPhuCap(luong.getPhuCap());
//			luongRepo.update(nv1);
//			
//			return true;
//		}
//		return false;
		return false;
	}

	@Override
	public boolean delete(int id) {
		boolean deleted = chiTietLuongRepo.delete(id);
		if(deleted) {
			return true;
		}
		return false;
	}

}
