package com.services.dao;

import org.springframework.stereotype.Service;

import com.entities.user.Account;
import com.entities.user.AccountPage;

@Service
public interface IAccount {
	public Account login(Account acc);
	public Account register(Account acc);
	public AccountPage paginate(String keyword, int pageno, int pagesize);
	public boolean insert(Account acc, int[] roleId);
	public Account getById(String id);
	public boolean update(Account acc, int[] roleId);
	public boolean delete(String id);

}
