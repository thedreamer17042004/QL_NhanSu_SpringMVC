package com.repositories.dao;

import com.entities.chucvu.ChucVuLanguage;
import com.entities.room.RoomLanguage;

public interface IPhongBanLanguage extends ICommon<RoomLanguage, Integer>{
	RoomLanguage findByName(String name);
	RoomLanguage findById(int id, String local);
}
