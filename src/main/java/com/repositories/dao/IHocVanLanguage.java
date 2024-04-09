package com.repositories.dao;

import org.springframework.stereotype.Repository;

import com.entities.chucvu.ChucVuLanguage;
import com.entities.hocvan.HocVanLanguage;

@Repository
public interface IHocVanLanguage extends ICommon<HocVanLanguage, Integer>{
	HocVanLanguage findByName(String name);
	HocVanLanguage findById(int id, String local);
}
