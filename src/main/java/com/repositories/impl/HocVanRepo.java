package com.repositories.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.stereotype.Repository;

import com.entities.chucvu.ChucVu;
import com.entities.chucvu.ChucVuPage;
import com.entities.chucvu.ChucVuView;
import com.entities.hocvan.HocVan;
import com.entities.hocvan.HocVanPage;
import com.entities.hocvan.HocVanView;
import com.repositories.dao.IHocVan;

@Repository
public class HocVanRepo extends CommonRepo<HocVan> implements IHocVan{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public boolean delete(HocVan entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public HocVan findById(Integer value) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		try {
			HocVan category = (HocVan) session.createQuery("from HocVan where maTrinhDo = :maTrinhDo")
			.setParameter("maTrinhDo", value).uniqueResult();
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
	public HocVanPage paginate(String keyword, int pageno, int pagesize) {
		Locale locale = LocaleContextHolder.getLocale();
		 
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		int records=0;
		Query query=null;
		if( keyword.equals("")) {
			  query = session.createQuery(
					  "SELECT hv.maTrinhDo,hvl.hocVan,hvl.language, hvl.tenTrinhDo,hvl.createDate " +
		              "FROM HocVan hv " +
		              "JOIN HocVanLanguage hvl ON hv.maTrinhDo = hvl.hocVan.maTrinhDo " +
		              "WHERE hvl.language.canonical = :languageId"
				          );
		        query.setParameter("languageId", locale.getLanguage());
			records=query.getResultList().size();
			query.setFirstResult((pageno-1)*pagesize).setMaxResults(pagesize).getResultList();
		}else if( keyword!=null) {
			
			  query = session.createQuery(
					  
					  "SELECT hv.maTrinhDo,hvl.hocVan,hvl.language, hvl.tenTrinhDo,hvl.createDate " +
				              "FROM HocVan hv " +
				              "JOIN HocVanLanguage hvl ON hv.maTrinhDo = hvl.hocVan.maTrinhDo " +
				              "WHERE hvl.language.canonical = :languageId  AND hvl.tenTrinhDo like :tenTrinhDo"
					  );
		        query.setParameter("languageId", locale.getLanguage()).setParameter("tenTrinhDo", "%"+ keyword +"%");
			records=query.getResultList().size();
			query.setFirstResult((pageno-1)*pagesize).setMaxResults(pagesize);
		}
		List result=query.getResultList();
		
		
		List<HocVanView> hocVanViews = new ArrayList<>();
		for (Object resultItem : result) {
		    Object[] row = (Object[]) resultItem;
		    HocVanView hocVanView = new HocVanView(row);
		    hocVanViews.add(hocVanView);
		 
		}

		HocVanPage fp =new HocVanPage();
		fp.setHocvans(hocVanViews);
		fp.setCurrentPage(pageno);
		fp.setPageSize(pagesize);
		fp.setTotalRecord(records);
		int totalpage=records%pagesize==0?records/pagesize:(records/pagesize)+1;
		fp.setTotalPages(totalpage);
		

		
	    return fp;
	}

	@Override
	public HocVanView edit(int id) {
		Locale locale = LocaleContextHolder.getLocale();
		Session session=sessionFactory.openSession();
		session.beginTransaction();

		Query query=null;
		 query = session.createQuery( 
				  "SELECT hv.maTrinhDo,hvl.hocVan,hvl.language, hvl.tenTrinhDo,hvl.createDate " +
			         "FROM HocVan hv " +
			        "JOIN HocVanLanguage hvl ON hv.maTrinhDo = hvl.hocVan.maTrinhDo " +
			         "WHERE hvl.language.canonical = :languageId  AND hv.maTrinhDo = :maTrinhDo"

			          );
	       query.setParameter("languageId", locale.getLanguage()).setParameter("maTrinhDo", id);
	       
	      List result =  query.getResultList();
		

		
	      HocVanView hocVanView =null;
		for (Object resultItem : result) {
		    Object[] row = (Object[]) resultItem;
		    hocVanView = new HocVanView(row);
		}

	      return hocVanView;
		
	}

	@Override
	public boolean delete(int id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			var st = session.get(HocVan.class, id);

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
