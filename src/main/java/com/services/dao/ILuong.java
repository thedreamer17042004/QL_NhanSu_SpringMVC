package com.services.dao;

import org.springframework.stereotype.Service;

import com.entities.chamcong.Cong;
import com.entities.chamcong.CongPage;
import com.entities.luong.Luong;
import com.entities.luong.LuongForm;
import com.entities.luong.LuongPage;

@Service
public interface ILuong {
	public LuongPage paginate(String keyword, int pageno, int pagesize);
	boolean insert(LuongForm luong, String ns);
	LuongForm edit(int id);
	boolean update(int id, LuongForm luong);
	boolean delete(int id);
}
