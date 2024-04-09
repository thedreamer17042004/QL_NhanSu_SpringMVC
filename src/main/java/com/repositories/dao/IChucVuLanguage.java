package com.repositories.dao;

import org.springframework.stereotype.Repository;

import com.entities.chucvu.ChucVuLanguage;

@Repository
public interface IChucVuLanguage extends ICommon<ChucVuLanguage, Integer>{
	ChucVuLanguage findByName(String name);
	ChucVuLanguage findById(String id, String local);
}
