package com.entities.user;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.NotEmpty;

@Entity
@Table(name = "Roles")
public class Role implements Serializable{
	
	@Id
	@Column(name = "RoleId")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int roleId;
	
	@NotEmpty(message =  "Role name không được trống")
	@Column(name = "RoleName")
	private String roleName;
	
	@Column(name = "Createdate")
	private Date createDate;
	

	@OneToMany(mappedBy = "role")
	private Set<AccountRole> accountroles;


	public Role() {
		super();
	}


	public Role(int roleId, @NotEmpty(message = "Role name không được trống") String roleName, Date createDate,
			Set<AccountRole> accountroles) {
		super();
		this.roleId = roleId;
		this.roleName = roleName;
		this.createDate = createDate;
		this.accountroles = accountroles;
	}


	public int getRoleId() {
		return roleId;
	}


	public void setRoleId(int roleId) {
		this.roleId = roleId;
	}


	public String getRoleName() {
		return roleName;
	}


	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}


	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	public Set<AccountRole> getAccountroles() {
		return accountroles;
	}


	public void setAccountroles(Set<AccountRole> accountroles) {
		this.accountroles = accountroles;
	}
	
	
}
