package com.repositories.impl;

import java.util.List;

import javax.persistence.Query;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entities.user.Account;
import com.entities.user.AccountPage;
import com.repositories.dao.IAccount;

@Repository
public class AccountRepo extends CommonRepo<Account> implements IAccount{

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public boolean delete(Account entity) {
		// TODO Auto-generated method stub
		return false;
	}

	

	@Override
	public Account findUserByEmail(String email) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		try {
			Account account = (Account) session.createQuery("from Account where email = :email")
			.setParameter("email", email).uniqueResult();
			return account;
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally {

			session.close();
			
		}
		
		return null;
	}

	@Override
	public AccountPage paginate(String keyword, int pageno, int pagesize) {

		Session session=sessionFactory.openSession();
		session.beginTransaction();
		int records=0;
		Query query=null;
		if(keyword.equals("")) {

			query=session.createQuery("from Account");
			records=query.getResultList().size();
			query.setFirstResult((pageno-1)*pagesize).setMaxResults(pagesize).getResultList();
		}else if(keyword!=null) {

			query=session.createQuery("from Account where  email like :name").setParameter("name","%"+ keyword +"%");
			
			records=query.getResultList().size();
			query.setFirstResult((pageno-1)*pagesize).setMaxResults(pagesize);
		}	
		
		List<Account> result=query.getResultList();
	
		AccountPage fp=new AccountPage();
		fp.setAccounts(result);
		fp.setCurrentPage(pageno);
		fp.setPageSize(pagesize);
		fp.setTotalRecord(records);
		int totalpage=records%pagesize==0?records/pagesize:(records/pagesize)+1;
		fp.setTotalPages(totalpage);
		return fp;
	}



	@Override
	public Account findById(String value) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		
		try {
			Account account = (Account) session.createQuery("from Account where AccountId = :AccountId")
			.setParameter("AccountId", value).uniqueResult();
			return account;
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally {

			session.close();
			
		}
		
		return null;
	}



	@Override
	public boolean delete(String id) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		try {
			var st = session.get(Account.class, id);

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
