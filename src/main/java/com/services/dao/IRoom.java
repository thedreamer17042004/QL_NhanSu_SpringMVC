package com.services.dao;

import org.springframework.stereotype.Service;

import com.entities.chucvu.ChucVuPage;
import com.entities.chucvu.ChucVuView;
import com.entities.room.RoomPage;
import com.entities.room.RoomView;

@Service
public interface IRoom {
	public RoomPage paginate(String keyword, int pageno, int pagesize);
	boolean insert(String name,String address, String phoneRoom);
	RoomView edit(int id);
	boolean update(int id, String name,String address, String phoneRoom);
	boolean delete(int id);

}
