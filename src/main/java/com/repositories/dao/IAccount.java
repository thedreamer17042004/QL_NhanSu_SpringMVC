package com.repositories.dao;

import org.springframework.stereotype.Repository;

import com.entities.user.Account;
import com.entities.user.AccountPage;

@Repository
public interface IAccount extends ICommon<Account, String> {
	Account findUserByEmail(String email);
	public AccountPage paginate(String keyword, int pageno, int pagesize);
	boolean delete(String id);
}
