package com.repositories.dao;

import org.springframework.stereotype.Repository;

import com.entities.chucvu.ChucVu;
import com.entities.chucvu.ChucVuPage;
import com.entities.chucvu.ChucVuView;
import com.entities.hocvan.HocVan;
import com.entities.hocvan.HocVanPage;
import com.entities.hocvan.HocVanView;
@Repository
public interface IHocVan extends ICommon<HocVan, Integer>{
	public HocVanPage paginate(String keyword, int pageno, int pagesize);
	HocVanView edit(int id);
	boolean delete(int id);
}
