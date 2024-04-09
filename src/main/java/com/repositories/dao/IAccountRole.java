package com.repositories.dao;

import org.springframework.stereotype.Repository;

import com.entities.user.AccountRole;

@Repository
public interface IAccountRole extends ICommon<AccountRole, Integer>{
	boolean delete1(String accountId);
}
