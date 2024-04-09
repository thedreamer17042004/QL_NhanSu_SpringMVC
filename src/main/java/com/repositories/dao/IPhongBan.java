package com.repositories.dao;

import org.springframework.stereotype.Repository;

import com.entities.chucvu.ChucVu;
import com.entities.chucvu.ChucVuPage;
import com.entities.chucvu.ChucVuView;
import com.entities.room.Room;
import com.entities.room.RoomPage;
import com.entities.room.RoomView;

@Repository
public interface IPhongBan extends ICommon<Room, Integer>{
	public RoomPage paginate(String keyword, int pageno, int pagesize);
	RoomView edit(int id);
	boolean delete(int id);
}
