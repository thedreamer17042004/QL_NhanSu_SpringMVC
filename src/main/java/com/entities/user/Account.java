package com.entities.user;

import java.io.Serializable;
import java.util.Date;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.format.annotation.DateTimeFormat;

import com.validation.AddUserValidation;
import com.validation.RegisterValidation;

@Entity
@Table(name = "Accounts")
public class Account implements Serializable{
	@Id
	@Column(name = "AccountId")
	@GeneratedValue(generator="system-uuid")
	@GenericGenerator(name="system-uuid", strategy = "uuid")
	private String accountId;
	
	@NotEmpty(message = "Username không được để trống", groups = {RegisterValidation.class, AddUserValidation.class})
	@Column(name = "UserName")
	private String userName;
	
	@NotEmpty(message = "Password không được để trống", groups = {RegisterValidation.class,AddUserValidation.class})
	@Column(name = "Password")
	private String password;
	
	
	@Column(name = "Picture")
	private String picture;
	
	
	@NotEmpty(message="Email không để trống", groups = {RegisterValidation.class,AddUserValidation.class})
	@Email(message= "Email không đúng định dạng", groups = { RegisterValidation.class,AddUserValidation.class})
	@Column(name = "Email", unique = true, nullable = false)
	private String email;
	
	@Column(name = "Gender")
	private boolean gender;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "birthday")
	private Date birthday;
	
	@Column(name = "Address")
	private String address;
	

	@Column(name = "Phone")
	private String phone;	
	
	
	@Column(name = "Createdate")
	private Date createdDate;


	@OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
	private Set<AccountRole> accountroles;


	public Account() {
		super();
	}


	public Account(String accountId,
			@NotEmpty(message = "Username không được để trống", groups = RegisterValidation.class) String userName,
			@NotEmpty(message = "Password không được để trống", groups = RegisterValidation.class) String password,
			String picture,
			@NotEmpty(message = "Email không để trống", groups = RegisterValidation.class) @Email(message = "Email không đúng định dạng", groups = RegisterValidation.class) String email,
			boolean gender, Date birthday, String address, String phone, Date createdDate,
			Set<AccountRole> accountroles) {
		super();
		this.accountId = accountId;
		this.userName = userName;
		this.password = password;
		this.picture = picture;
		this.email = email;
		this.gender = gender;
		this.birthday = birthday;
		this.address = address;
		this.phone = phone;
		this.createdDate = createdDate;
		this.accountroles = accountroles;
	}


	public String getAccountId() {
		return accountId;
	}


	public void setAccountId(String accountId) {
		this.accountId = accountId;
	}


	public String getUserName() {
		return userName;
	}


	public void setUserName(String userName) {
		this.userName = userName;
	}


	public String getPassword() {
		return password;
	}


	public void setPassword(String password) {
		this.password = password;
	}


	public String getPicture() {
		return picture;
	}


	public void setPicture(String picture) {
		this.picture = picture;
	}


	public String getEmail() {
		return email;
	}


	public void setEmail(String email) {
		this.email = email;
	}


	public boolean isGender() {
		return gender;
	}


	public void setGender(boolean gender) {
		this.gender = gender;
	}


	public Date getBirthday() {
		return birthday;
	}


	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPhone() {
		return phone;
	}


	public void setPhone(String phone) {
		this.phone = phone;
	}


	public Date getCreatedDate() {
		return createdDate;
	}


	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}


	public Set<AccountRole> getAccountroles() {
		return accountroles;
	}


	public void setAccountroles(Set<AccountRole> accountroles) {
		this.accountroles = accountroles;
	}
	
	
}
