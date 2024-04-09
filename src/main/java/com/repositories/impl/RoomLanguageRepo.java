package com.repositories.impl;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.entities.chucvu.ChucVuLanguage;
import com.entities.room.RoomLanguage;
import com.repositories.dao.IPhongBanLanguage;

@Repository
public class RoomLanguageRepo extends CommonRepo<RoomLanguage> implements IPhongBanLanguage{

	@Override
	public boolean delete(RoomLanguage entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public RoomLanguage findById(Integer value) {
		return null;
	}

	@Override
	public RoomLanguage findByName(String name) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		try {
			RoomLanguage rlLanguage = (RoomLanguage) session.createQuery("from RoomLanguage where roomName  =:roomName")
			.setParameter("roomName", name).uniqueResult();
			
			return rlLanguage;
		}
		catch (Exception e) {
			return null;
			
		}finally {

			session.close();
			
		}
	}

	@Override
	public RoomLanguage findById(int id, String local) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		try {
			RoomLanguage ctLanguage = (RoomLanguage) session.createQuery("from RoomLanguage where room.id = :id AND language.canonical = :idLanguage")
			.setParameter("id", id).setParameter("idLanguage", local).uniqueResult();
		
			return ctLanguage;
		}
		catch (Exception e) {
			return null;
			
		}finally {

			session.close();
			
		}
	}

}
