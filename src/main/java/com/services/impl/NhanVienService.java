package com.services.impl;

import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entities.chucvu.ChucVu;
import com.entities.hocvan.HocVan;
import com.entities.nhanvien.NhanVien;
import com.entities.nhanvien.NhanVienForm;
import com.entities.nhanvien.NhanVienPage;
import com.entities.room.Room;
import com.entities.user.Account;
import com.entities.user.AccountPage;
import com.entities.user.AccountRole;
import com.entities.user.Role;
import com.repositories.dao.IChucVu;
import com.repositories.dao.IHocVan;
import com.repositories.dao.IPhongBan;
import com.services.dao.INhanVien;

@Service
public class NhanVienService implements INhanVien{

	@Autowired
	com.repositories.dao.INhanVien nhanVienRepo;
	
	@Autowired
	IChucVu chucVuRepo;
	
	@Autowired
	IPhongBan phongBanRepo;
	
	@Autowired
	IHocVan hocVanRepo;
	
	

	
	
	@Override
	public NhanVienPage paginate(Integer maCapBac, String maPhong, Integer trinhDoHocVan, String keyword, int pageno,
			int pagesize) {
		NhanVienPage result = nhanVienRepo.paginate( maCapBac,  maPhong,  trinhDoHocVan, keyword,  pageno,  pagesize);
		return result;
	}

	@Override
	public boolean delete(int id) {
		boolean delete = nhanVienRepo.delete(id);
		if(delete) {
			return true;
		}
		return false;
	}

	@Override
	public boolean insert(NhanVienForm acc) {
		 java.util.Date currentDate = new java.util.Date();
         java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
         NhanVien nv = new NhanVien();
		ChucVu cv = chucVuRepo.findById(acc.getMachucVu());
		HocVan hv = hocVanRepo.findById(Integer.parseInt(acc.getMaHocVan()));
		Room room = phongBanRepo.findById(Integer.parseInt(acc.getRoomId()));
		
		nv.setTen(acc.getTen());
		nv.setTuoi(acc.getTuoi());
		nv.setGioiTinh(acc.getGioiTinh());
		nv.setSdt(acc.getSdt());
		nv.setHinhAnh(acc.getHinhAnh());
		nv.setQueQuan(acc.getQueQuan());
		nv.setNgaySinh(acc.getNgaySinh());
		nv.setChucVu(cv);
		nv.setHocVan(hv);
		nv.setRoom(room);
		Boolean check = nhanVienRepo.insert(nv);
		if(check) {
			  	
		 return true;
		
		}
		return false;
		
	}

	@Override
	public NhanVien getById(int id) {
		NhanVien acc = nhanVienRepo.findById(id);

		return acc;
	}

	@Override
	public boolean update(NhanVienForm acc, Integer id) {
		 java.util.Date currentDate = new java.util.Date();
         java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
         System.out.println(acc.getMaNhanSu()+"mã nhân sự");
         NhanVien nv = nhanVienRepo.findById(id);
         
         if(nv.getTen()!=null) {
        	  System.out.println("vào update");
      		ChucVu cv = chucVuRepo.findById(acc.getMachucVu());
      		HocVan hv = hocVanRepo.findById(Integer.parseInt(acc.getMaHocVan()));
    		Room room = phongBanRepo.findById(Integer.parseInt(acc.getRoomId()));
      		
      		nv.setTen(acc.getTen());
      		nv.setTuoi(acc.getTuoi());
      		nv.setGioiTinh(acc.getGioiTinh());
      		nv.setSdt(acc.getSdt());
      		nv.setHinhAnh(acc.getHinhAnh());
      		nv.setQueQuan(acc.getQueQuan());
      		nv.setNgaySinh(acc.getNgaySinh());
      		nv.setChucVu(cv);
      		nv.setHocVan(hv);
      		nv.setRoom(room);
      		Boolean check = nhanVienRepo.update(nv);
      		if(check) {
      			  	
      		 return true;
      		
      		}
      		return false;
         }
         System.out.println("ra ngoài");
         return false;
       
	}

}
