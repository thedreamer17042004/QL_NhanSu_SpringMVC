package com.repositories.dao;

import org.springframework.stereotype.Repository;

import com.entities.chucvu.ChucVu;
import com.entities.chucvu.ChucVuPage;
import com.entities.chucvu.ChucVuView;

@Repository
public interface IChucVu extends ICommon<ChucVu, String>{
	public ChucVuPage paginate(String keyword, int pageno, int pagesize);
	ChucVuView edit(String id);
	boolean delete(String id);
}
