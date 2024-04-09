package com.services.impl;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.entities.user.Account;
import com.entities.user.AccountPage;
import com.entities.user.AccountRole;
import com.entities.user.Role;
import com.repositories.dao.IAccountRole;
import com.repositories.dao.IRole;
import com.services.dao.IAccount;

@Service
public class AccountService implements IAccount{

	@Autowired
	com.repositories.dao.IAccount accountRepo;
	
	@Autowired
	IRole roleRepo;

	@Autowired
	IAccountRole accountRoleRepo;
	

	@Autowired
	private BCryptPasswordEncoder bCryptPasswordEncoder;
	
	
	@Override
	public Account login(Account acc) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Account register(Account acc) {
		Account account = new Account();
		Role accountRole = roleRepo.findByName("ROLE_ADMIN");
		Set<AccountRole> set = new HashSet(Arrays.asList(accountRole));
		//check email exist in db
		if(accountRepo.findUserByEmail(acc.getEmail()) != null) {
			return null;
		}
		
		account.setEmail(acc.getEmail());
		account.setPassword(bCryptPasswordEncoder.encode(acc.getPassword()));
		account.setUserName(acc.getUserName());
		account.setGender(true);
		account.setAccountroles(null);
	
		boolean check = accountRepo.insert(account);
		if(check) {
			  	AccountRole accountRole1 = new AccountRole();
		        accountRole1.setAccount(account);
		        accountRole1.setRole(accountRole);
		        accountRoleRepo.insert(accountRole1);
		        
		        return account;
		
		}
		
		return null;
	}

	@Override
	public AccountPage paginate(String keyword, int pageno, int pagesize) {
		AccountPage result = accountRepo.paginate(keyword, pageno, pagesize);
		return result;
	}

	@Override
	public boolean insert(Account acc, int[] roleId) {
		 java.util.Date currentDate = new java.util.Date();
         java.sql.Date sqlDate = new java.sql.Date(currentDate.getTime());
		if(roleId==null) {
			Role accountRole = roleRepo.findByName("ROLE_USER");
			if(accountRepo.findUserByEmail(acc.getEmail()) != null) {
				 throw new Error("Email is exist");
			}
			
			acc.setPassword(bCryptPasswordEncoder.encode(acc.getPassword()));
			acc.setUserName(acc.getUserName());
			acc.setAccountroles(null);
			acc.setCreatedDate(sqlDate);
			Boolean check = accountRepo.insert(acc);
			if(!check.equals(null)) {
				  	AccountRole accountRole1 = new AccountRole();
			        accountRole1.setAccount(acc);
			        accountRole1.setRole(accountRole);
			        accountRoleRepo.insert(accountRole1);
			        return true;
			
			}
			
		}
		else {
			
			if(accountRepo.findUserByEmail(acc.getEmail()) != null) {
				  throw new Error("Email is exist");
			}
			
			acc.setPassword(bCryptPasswordEncoder.encode(acc.getPassword()));
			acc.setUserName(acc.getUserName());
			acc.setAccountroles(null);
			acc.setCreatedDate(sqlDate);
			Boolean check = accountRepo.insert(acc);
			if(!check.equals(null)) {
				  	AccountRole accountRole1 = new AccountRole();
				  	for (int i : roleId) {
						Role accountRole = roleRepo.findById(i);
						 accountRole1.setAccount(acc);
					     accountRole1.setRole(accountRole);
					     accountRoleRepo.insert(accountRole1);
					}
			     
			       
			        return true;
			
			}
			
			return false;
		}
		return false;
	}

	@Override
	public Account getById(String id) {
		Account acc = accountRepo.findById(id);
		return acc;
	}

	@Override
	public boolean update(Account acc, int[] roleId) {
		//nếu có
				if(roleId==null) {
			
					Role accountRole = roleRepo.findByName("ROLE_USER");
					//check email exist in db
					Account account = accountRepo.findUserByEmail(acc.getEmail());
					
					account.setUserName(acc.getUserName());
					account.setAddress(acc.getAddress());
					account.setGender(acc.isGender());
					account.setPicture(acc.getPicture());
					account.setBirthday(acc.getBirthday());


					Boolean check = accountRepo.update(account);
					
					if(check) {
						  	AccountRole accountRole1 = new AccountRole();
					        accountRole1.setAccount(account);
					        accountRole1.setRole(accountRole);
					        accountRoleRepo.insert(accountRole1);
					        return true;
					
					}
					
				}
				else {

					Account account = accountRepo.findUserByEmail(acc.getEmail());
					
					account.setUserName(acc.getUserName());
					account.setAddress(acc.getAddress());
					account.setGender(acc.isGender());
					account.setPicture(acc.getPicture());
					account.setBirthday(acc.getBirthday());

					
					Boolean check = accountRepo.update(account);
					
					if(check) {
						Boolean deletedAccountRole = accountRoleRepo.delete1(account.getAccountId());
						if(deletedAccountRole) {
							AccountRole accountRole1 = new AccountRole();
						  	for (int i : roleId) {
								Role accountRole = roleRepo.findById(i);
								 accountRole1.setAccount(account);
							     accountRole1.setRole(accountRole);
							     accountRoleRepo.insert(accountRole1);
							}	     
					
					       
					        return true;
						}else {
							return false;
						}
						  	
					
					}
					
					return false;
				}
				return false;
	}

	@Override
	public boolean delete(String id) {
		boolean delete = accountRepo.delete(id);
		if(delete) {
			return true;
		}
		return false;
	}
	
}
