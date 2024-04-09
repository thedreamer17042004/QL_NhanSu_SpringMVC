package com.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.entities.user.Role;
import com.services.dao.IRole;

@Service
public class RoleService implements IRole {

	@Autowired
	com.repositories.dao.IRole roleRepo;
	
	@Override
	public List<Role> list() {
		List<Role> index = roleRepo.list();		
		return index;
	}


}
