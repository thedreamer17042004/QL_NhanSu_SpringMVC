package com.repositories.dao;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.entities.chamcong.Cong;
import com.entities.luong.ChiTietLuong;
import com.entities.luong.Luong;
import com.entities.luong.LuongPage;
import com.entities.user.Account;

@Repository
public interface IChiTietLuong extends ICommon<ChiTietLuong, Integer>{
	public LuongPage paginate(String keyword, int pageno, int pagesize);
	LuongPage edit(int id);
	boolean delete(int id);
	ChiTietLuong findChiTietByMaAndDate(int maNhanVien, Date ngayNhanluong);
}
