package com.repositories.dao;

import org.springframework.stereotype.Repository;

import com.entities.nhanvien.NhanVien;
import com.entities.nhanvien.NhanVienPage;
import com.entities.user.Account;
import com.entities.user.AccountPage;

@Repository
public interface INhanVien extends ICommon<NhanVien, Integer>{
	NhanVien findByName(String name);
	public NhanVienPage paginate(Integer maCapBac, String maPhong, Integer trinhDoHocVan,String keyword, int pageno, int pagesize);
	boolean delete(int id);
	NhanVien findById(int value);
}
