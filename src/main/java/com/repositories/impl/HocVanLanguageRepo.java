package com.repositories.impl;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.entities.chucvu.ChucVuLanguage;
import com.entities.hocvan.HocVanLanguage;
import com.repositories.dao.IHocVanLanguage;

@Repository
public class HocVanLanguageRepo extends CommonRepo<HocVanLanguage> implements IHocVanLanguage{

	@Override
	public boolean delete(HocVanLanguage entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public HocVanLanguage findById(Integer value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public HocVanLanguage findByName(String name) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		try {
			HocVanLanguage ctLanguage = (HocVanLanguage) session.createQuery("from HocVanLanguage where tenTrinhDo  =:tenTrinhDo")
			.setParameter("tenTrinhDo", name).uniqueResult();
			
			return ctLanguage;
		}
		catch (Exception e) {
			return null;
			
		}finally {

			session.close();
			
		}
	}

	@Override
	public HocVanLanguage findById(int id, String local) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		try {
			HocVanLanguage ctLanguage = (HocVanLanguage) session.createQuery("from HocVanLanguage where hocVan.maTrinhDo = :maTrinhDo AND language.canonical = :idLanguage")
			.setParameter("maTrinhDo", id).setParameter("idLanguage", local).uniqueResult();
		
			return ctLanguage;
		}
		catch (Exception e) {
			return null;
			
		}finally {

			session.close();
			
		}
	}

}
