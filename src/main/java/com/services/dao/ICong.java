package com.services.dao;

import org.springframework.stereotype.Service;

import com.entities.chamcong.Cong;
import com.entities.chamcong.CongPage;
import com.entities.hocvan.HocVanPage;
import com.entities.hocvan.HocVanView;

@Service
public interface ICong {
	public CongPage paginate(String keyword, int pageno, int pagesize);
	boolean insert(Cong cong, String nhanSu);
	Cong edit(int id);
	boolean update(int id, Cong cong, int nv);
	boolean delete(int id);
}
