package com.services.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entities.chamcong.Cong;
import com.entities.chamcong.CongPage;
import com.entities.luong.ChiTietLuong;
import com.entities.luong.Luong;
import com.entities.luong.LuongForm;
import com.entities.luong.LuongPage;
import com.entities.nhanvien.NhanVien;
import com.repositories.dao.ICong;
import com.repositories.dao.INhanVien;
import com.services.dao.IChiTietLuong;
import com.services.dao.ILuong;
import com.utils.Error;

@Service
public class LuongService implements ILuong{

	@Autowired
	com.repositories.dao.ILuong luongRepo;
	
	
	@Autowired
	IChiTietLuong chiTietLuongRepo;
	
	com.repositories.dao.IChiTietLuong chiTietLuongRepo1;
	
	@Autowired
	ICong congRepo;
	
	@Autowired
	INhanVien nhanVienRepo;
	
	
	@Override
	public LuongPage paginate(String keyword, int pageno, int pagesize) {
		
		return null;
	}

	@Override
	public boolean insert(LuongForm luong, String ns) {
		
		System.out.println("Voa đầu insert");
		//tính toán và chèn dũlieu hai bảng luong va chi tiet luong
		Cong soCongNv = congRepo.findSoCongByMaAndDate(Integer.parseInt(ns), luong.getNgayNhanLuong());
		System.out.println("Quan đây");
		if(soCongNv==null) {
			throw new Error("Nhân viên này chưa có chấm công");
		}
		
		NhanVien nv = nhanVienRepo.findById(Integer.parseInt(ns));
		Luong luong1 = new Luong();
		ChiTietLuong ctl = new ChiTietLuong();
		Float luongTotal = (luong.getLuongToiThieu()/30)*soCongNv.getSoCong();
		System.out.println(luongTotal+ " "+ soCongNv.getSoCong());
		
		luong1.setHeSoLuong(luong.getHeSoLuong());
		luong1.setLuongToiThieu(luong.getLuongToiThieu());
		luong1.setMaNhanVien(Integer.parseInt(ns));
		luong1.setPhuCap(luong.getPhuCap());
		
		boolean insert1 = luongRepo.insert(luong1);
		if(insert1) {
			System.out.println("hello");
			ctl.setLuongCoBan((float)luong.getLuongToiThieu());
			ctl.setNhanVien(nv);
			ctl.setPhuCap(luong.getPhuCap());
			ctl.setNgayNhanLuong(luong.getNgayNhanLuong());
			ctl.setSoCong(Math.round(soCongNv.getSoCong()));
			ctl.setTongTienLuong(String.valueOf(luongTotal));
			boolean inserted = chiTietLuongRepo.insert(ctl, "");
			if(inserted) {
				return true;
			}
			return false;
			
		}

		return false;
	}

	@Override
	public LuongForm edit(int id) {
		ChiTietLuong luong = chiTietLuongRepo.edit(id);
		LuongForm lf = new LuongForm();
		lf.setLuongToiThieu(Math.round(luong.getLuongCoBan()));
		lf.setNgayNhanLuong(luong.getNgayNhanLuong());
		lf.setMaNhanVien(luong.getNhanVien().getMaNhanSu());
		lf.setHeSoLuong(luong.getPhuCap());
		return lf;
	}

	@Override
	public boolean update(int id, LuongForm luong) {
		Luong nv1 = luongRepo.findById(id);
		ChiTietLuong nv2 = chiTietLuongRepo1.findChiTietByMaAndDate(luong.getMaNhanVien(), luong.getNgayNhanLuong());
		
		if(nv1!=null) {

			Cong soCongNv = congRepo.findSoCongByMaAndDate(luong.getMaNhanVien(), luong.getNgayNhanLuong());
			Float luongTotal = (luong.getLuongToiThieu()/30)*soCongNv.getSoCong() + luong.getPhuCap();
			
			nv1.setMaNhanVien(luong.getMaNhanVien());
			nv1.setLuongToiThieu(luong.getLuongToiThieu());
			nv1.setHeSoLuong(luong.getHeSoLuong());
			nv1.setPhuCap(luong.getPhuCap());
			
			nv2.setLuongCoBan((float)luong.getLuongToiThieu());
			nv2.setPhuCap(luong.getPhuCap());
			nv2.setNgayNhanLuong(luong.getNgayNhanLuong());
			nv2.setSoCong(Math.round(soCongNv.getSoCong() ));
			nv2.setTongTienLuong(String.valueOf(luongTotal));

			boolean update = luongRepo.update(nv1);
			
			
			if(update) {
				boolean inserted = chiTietLuongRepo1.update(nv2);
				if(inserted) {
					return true;
				}
				return false;
				
			}

			return false;
		
		}
		return false;
	}

	@Override
	public boolean delete(int id) {
		boolean deleted = luongRepo.delete(id);
		if(deleted) {
			return true;
		}
		return false;
	}

}
