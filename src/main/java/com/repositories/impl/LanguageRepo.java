package com.repositories.impl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entities.language.Language;
import com.entities.language.LanguagePage;
import com.repositories.dao.ILanguage;

@Repository
public class LanguageRepo extends CommonRepo<Language> implements ILanguage{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public boolean delete(Language entity) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public Language findById(Integer value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Language findLanguageByCanonical(String cano) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		try {
			Language lang = (Language) session.createQuery("from Language where canonical =:canonical")
			.setParameter("canonical", cano).uniqueResult();
			
			return lang;
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally {

			session.close();
			
		}
		
		return null;
	}

	@Override
	public LanguagePage paginate(String keyword, int pageno, int pagesize) {
		Session session=sessionFactory.openSession();
		session.beginTransaction();
		int records=0;
		Query query=null;
		if(keyword.equals("")) {

			query=session.createQuery("from Language");
			records=query.getResultList().size();
			query.setFirstResult((pageno-1)*pagesize).setMaxResults(pagesize).getResultList();
		}else if(keyword!=null) {

			query=session.createQuery("from Language where  name like :name").setParameter("name","%"+ keyword +"%");
			records=query.getResultList().size();
			query.setFirstResult((pageno-1)*pagesize).setMaxResults(pagesize);
		} 
		
		List<Language> result=query.getResultList();
		
		LanguagePage fp=new LanguagePage();
		fp.setLanguages(result);
		fp.setCurrentPage(pageno);
		fp.setPageSize(pagesize);
		fp.setTotalRecord(records);
		int totalpage=records%pagesize==0?records/pagesize:(records/pagesize)+1;
		fp.setTotalPages(totalpage);
		return fp;
	}

	@Override
	public Language edit(Integer id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		try {
			Language lg = (Language) session.createQuery("from Language where id = :id")
			.setParameter("id", id).uniqueResult();
			return lg;
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally {

			session.close();
			
		}
		
		return null;
	}

}
