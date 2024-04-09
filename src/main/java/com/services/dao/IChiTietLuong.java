package com.services.dao;

import org.springframework.stereotype.Service;

import com.entities.luong.ChiTietLuong;
import com.entities.luong.Luong;
import com.entities.luong.LuongPage;

@Service
public interface IChiTietLuong {
	public LuongPage paginate(String keyword, int pageno, int pagesize);
	boolean insert(ChiTietLuong luong, String nhanSu);
	ChiTietLuong edit(int id);
	boolean update(int id, ChiTietLuong luong, int nv);
	boolean delete(int id);
}
