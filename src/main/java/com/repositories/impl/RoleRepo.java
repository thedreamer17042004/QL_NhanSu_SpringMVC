package com.repositories.impl;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.entities.user.Role;
import com.repositories.dao.IRole;

@Repository
public class RoleRepo extends CommonRepo<Role> implements IRole {

	@Autowired
	SessionFactory sessionFactory;
	
	@Override
	public boolean delete(Role entity) {
		// TODO Auto-generated method stub
		return false;
	}

	

	@Override
	public Role findByName(String name) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		try {
			Role role = (Role) session.createQuery("from Role where roleName = :roleName")
			.setParameter("roleName", name).uniqueResult();
			return role;
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return null;
	}

	@Override
	public List<Role> list() {
		Session session = sessionFactory.openSession();
		session.beginTransaction();
		List result = session.createQuery("from Role").getResultList();

		return result;
	}



	@Override
	public Role findById(Integer value) {
		Session session = sessionFactory.openSession();
		session.beginTransaction();

		try {
			Role role = (Role) session.createQuery("from Role where roleId = :roleId")
			.setParameter("roleId", value).uniqueResult();
			return role;
		}
		catch (Exception e) {
			e.printStackTrace();
		}finally {
			session.close();
		}
		
		return null;
	}

}
