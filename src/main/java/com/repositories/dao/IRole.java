package com.repositories.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.entities.user.Account;
import com.entities.user.Role;

@Repository
public interface IRole extends ICommon<Role, Integer>{
	Role findByName(String name);
	List<Role> list();
}
