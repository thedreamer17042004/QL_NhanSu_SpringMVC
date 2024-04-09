package com.services.dao;

import org.springframework.stereotype.Service;

import com.entities.chucvu.ChucVuPage;
import com.entities.chucvu.ChucVuView;
import com.entities.user.AccountPage;

@Service
public interface IChucVu {
	public ChucVuPage paginate(String keyword, int pageno, int pagesize);
	boolean insert(String name);
	ChucVuView edit(String id);
	boolean update(String id, String name);
	boolean delete(String id);

}
