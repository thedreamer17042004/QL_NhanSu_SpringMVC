package com.repositories.impl;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.entities.chucvu.ChucVuLanguage;
import com.repositories.dao.IChucVuLanguage;

@Repository
public class ChucVuLanguageRepo extends CommonRepo<ChucVuLanguage> implements IChucVuLanguage{

	@Override
	public boolean delete(ChucVuLanguage entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ChucVuLanguage findById(Integer value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ChucVuLanguage findByName(String name) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		try {
			ChucVuLanguage ctLanguage = (ChucVuLanguage) session.createQuery("from ChucVuLanguage where tenChucVu  =:tenChucVu")
			.setParameter("tenChucVu", name).uniqueResult();
			
			return ctLanguage;
		}
		catch (Exception e) {
			return null;
			
		}finally {

			session.close();
			
		}
	}

	@Override
	public ChucVuLanguage findById(String id, String local) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		try {
			ChucVuLanguage ctLanguage = (ChucVuLanguage) session.createQuery("from ChucVuLanguage where chucvu.maChucVuNV = :id AND language.canonical = :idLanguage")
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
