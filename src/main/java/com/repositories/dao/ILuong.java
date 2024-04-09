package com.repositories.dao;

import org.springframework.stereotype.Repository;

import com.entities.chucvu.ChucVuPage;
import com.entities.chucvu.ChucVuView;
import com.entities.luong.Luong;
import com.entities.luong.LuongPage;
import com.entities.user.Account;

@Repository
public interface ILuong extends ICommon<Luong, Integer>{
	public LuongPage paginate(String keyword, int pageno, int pagesize);
	Luong edit(int id);
	boolean delete(int id);
}
