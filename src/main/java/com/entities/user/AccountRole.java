package com.entities.user;

import java.io.Serializable;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "Account_Roles")
public class AccountRole implements Serializable{
	@javax.persistence.Id
	@Column(name = "Id")
	@GeneratedValue(strategy  = GenerationType.IDENTITY)
	private int Id;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name = "accountId")
	private Account account;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
	@JoinColumn(name ="roleId")
	private Role role;

	public AccountRole() {
		super();
	}

	public AccountRole(int id, Account account, Role role) {
		super();
		Id = id;
		this.account = account;
		this.role = role;
	}

	public int getId() {
		return Id;
	}

	public void setId(int id) {
		Id = id;
	}

	public Account getAccount() {
		return account;
	}

	public void setAccount(Account account) {
		this.account = account;
	}

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}
	
	
}
