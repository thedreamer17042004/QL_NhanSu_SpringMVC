package com.services.dao;

import org.springframework.stereotype.Service;

import com.entities.chucvu.ChucVuPage;
import com.entities.chucvu.ChucVuView;
import com.entities.hocvan.HocVanPage;
import com.entities.hocvan.HocVanView;

@Service
public interface IHocVan {
	public HocVanPage paginate(String keyword, int pageno, int pagesize);
	boolean insert(String name);
	HocVanView edit(int id);
	boolean update(int id, String name);
	boolean delete(int id);
}
