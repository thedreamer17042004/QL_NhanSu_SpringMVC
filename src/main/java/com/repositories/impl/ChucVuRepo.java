package com.repositories.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.persistence.Query;

import org.hibernate.Session;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Repository;

import com.entities.chucvu.ChucVu;
import com.entities.chucvu.ChucVuPage;
import com.entities.chucvu.ChucVuView;
import com.repositories.dao.IChucVu;

@Repository
public class ChucVuRepo extends CommonRepo<ChucVu> implements IChucVu{

	@Override
	public boolean delete(ChucVu entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public ChucVu findById(String value) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		try {
			ChucVu category = (ChucVu) session.createQuery("from ChucVu where maChucVuNV = :maChucVuNV")
			.setParameter("maChucVuNV", value).uniqueResult();
			return category;
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally {

			session.close();
			
		}
		
		return null;
	}

	@Override
	public ChucVuPage paginate(String keyword, int pageno, int pagesize) {
		Locale locale = LocaleContextHolder.getLocale();
		 
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		int records=0;
		Query query=null;
		if( keyword.equals("")) {

			  query = session.createQuery(
					  "SELECT cv.maChucVuNV,cvl.chucvu,cvl.language, cvl.tenChucVu,cvl.createDate " +
		              "FROM ChucVu cv " +
		              "JOIN ChucVuLanguage cvl ON cv.maChucVuNV = cvl.chucvu.maChucVuNV " +
		              "WHERE cvl.language.canonical = :languageId"
				          );
		        query.setParameter("languageId", locale.getLanguage());
			records=query.getResultList().size();
			query.setFirstResult((pageno-1)*pagesize).setMaxResults(pagesize).getResultList();
		}else if( keyword!=null) {
			
			  query = session.createQuery(
					  "SELECT cv.maChucVuNV,cvl.chucvu,cvl.language, cvl.tenChucVu,cvl.createDate " +
					  "FROM ChucVu cv " +
					  "JOIN ChucVuLanguage cvl ON cv.maChucVuNV = cvl.chucvu.maChucVuNV " +
					  "WHERE cvl.language.canonical = :languageId  AND cvl.tenChucVu like :tenChucVu"
					  );
		        query.setParameter("languageId", locale.getLanguage()).setParameter("tenChucVu", "%"+ keyword +"%");
			records=query.getResultList().size();
			query.setFirstResult((pageno-1)*pagesize).setMaxResults(pagesize);
		}
		List result=query.getResultList();
		
		
		List<ChucVuView> chucVuViews = new ArrayList<>();
		for (Object resultItem : result) {
		    Object[] row = (Object[]) resultItem;
		    ChucVuView chucVuView = new ChucVuView(row);
		    chucVuViews.add(chucVuView);
		 
		}

		ChucVuPage fp=new ChucVuPage();
		fp.setChucvus(chucVuViews);
		fp.setCurrentPage(pageno);
		fp.setPageSize(pagesize);
		fp.setTotalRecord(records);
		int totalpage=records%pagesize==0?records/pagesize:(records/pagesize)+1;
		fp.setTotalPages(totalpage);
		

		
	    return fp;
	}

	@Override
	public ChucVuView edit(String id) {
		Locale locale = LocaleContextHolder.getLocale();
		Session session=sessionFactory.openSession();
		session.beginTransaction();

		Query query=null;
		 query = session.createQuery( 
				 "SELECT cv.maChucVuNV,cvl.chucvu,cvl.language, cvl.tenChucVu,cvl.createDate " +
				 "FROM ChucVu cv " +
				 "JOIN ChucVuLanguage cvl ON cv.maChucVuNV = cvl.chucvu.maChucVuNV " +
				 "WHERE cvl.language.canonical = :languageId  AND cv.id = :id"
			          );
	       query.setParameter("languageId", locale.getLanguage()).setParameter("id", id);
	       
	      List result =  query.getResultList();
		

		
	      ChucVuView chucVuView =null;
		for (Object resultItem : result) {
		    Object[] row = (Object[]) resultItem;
		    chucVuView = new ChucVuView(row);
		}

	      return chucVuView;
		
	}

	@Override
	public boolean delete(String id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			var st = session.get(ChucVu.class, id);

			session.remove(st);

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
