package com.repositories.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.repositories.dao.ICommon;

@Repository
public class CommonRepo<K> {

	@Autowired
	SessionFactory sessionFactory;
	
	public boolean update(K entity) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			session.update(entity);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
	
		return false;
	}


	public boolean insert(K entity) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			session.save(entity);
			session.getTransaction().commit();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
	
		return false;
	}



}
