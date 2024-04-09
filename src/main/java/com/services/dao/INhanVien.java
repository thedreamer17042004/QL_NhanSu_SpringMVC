package com.services.dao;

import org.springframework.stereotype.Service;

import com.entities.nhanvien.NhanVien;
import com.entities.nhanvien.NhanVienForm;
import com.entities.nhanvien.NhanVienPage;
import com.entities.user.Account;
import com.entities.user.AccountPage;

@Service
public interface INhanVien {
	public NhanVienPage paginate(Integer maCapBac, String maPhong, Integer trinhDoHocVan,String keyword, int pageno, int pagesize);
	boolean delete(int id);
	public boolean insert(NhanVienForm acc);
	public NhanVien getById(int id);
	public boolean update(NhanVienForm acc,Integer id);
}
