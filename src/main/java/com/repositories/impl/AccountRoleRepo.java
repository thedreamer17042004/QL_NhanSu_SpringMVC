package com.repositories.impl;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entities.user.AccountRole;
import com.repositories.dao.IAccountRole;

@Repository
public class AccountRoleRepo extends CommonRepo<AccountRole> implements IAccountRole{


	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public boolean delete(AccountRole entity) {
		return false;
	}

	@Override
	public AccountRole findById(Integer value) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public boolean delete1(String accountId) {
		Session session = sessionFactory.getCurrentSession();
		session.beginTransaction();
		try {

			  String hql = "DELETE FROM AccountRole WHERE AccountId = :accountId";
		      int deletedCount = session.createQuery(hql).setParameter("accountId", accountId).executeUpdate();
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
