package com.repositories.dao;

import java.util.Date;

import org.springframework.stereotype.Repository;

import com.entities.chamcong.Cong;
import com.entities.chamcong.CongPage;
import com.entities.hocvan.HocVanPage;
import com.entities.hocvan.HocVanView;
import com.entities.user.Account;

@Repository
public interface ICong extends ICommon<Cong, Integer> {
	public CongPage paginate(String keyword, int pageno, int pagesize);
	Cong edit(int id);
	boolean delete(int id);
	Cong findSoCongByMaAndDate(int maNhanVien, Date ngayNhanluong);
	
}
