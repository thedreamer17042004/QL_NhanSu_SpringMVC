package com.services.impl;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.entities.user.Account;
import com.entities.user.AccountDetails;
import com.entities.user.AccountRole;
import com.repositories.dao.IAccount;

@Service
public class AccountDetailsService implements UserDetailsService{

	@Autowired
	private IAccount accountDao;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		// TODO Auto-generated method stub
		return getAccount(email);
	}
	private AccountDetails getAccount(String email)
	{
		Account acc = accountDao.findUserByEmail(email);
		if(acc==null)
		{
			throw new UsernameNotFoundException("User not found with username: " + email);
		}
//		xử lý lấy roles của người dùng đưa vào Authority
		Collection<GrantedAuthority> grantedAuthoritySet = new HashSet<GrantedAuthority>();
		
		Set<AccountRole> roles = acc.getAccountroles();
		for(AccountRole accountRole: roles)
		{
			String rolename = accountRole.getRole().getRoleName();
			grantedAuthoritySet.add(new SimpleGrantedAuthority(rolename));
		}
		return new AccountDetails(
				grantedAuthoritySet,
				acc.getEmail(),
				acc.getPassword(),
				acc.getUserName(),
				acc.isGender(),
				acc.getPicture(),
				acc.getPhone(),
				true, 
				true,
				true,
				true);
	}

}
