package com.entities.chucvu;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;

@Entity
@Table(name = "ChucVuNhanViens")
public class ChucVu implements Serializable{

	@Id
	@Column(name = "MaChucVuNV")
	private String maChucVuNV;
	
	@Column(name = "Createdate")
	private Date createDate;

	public ChucVu() {
		super();
	}

	public ChucVu(String maChucVuNV, Date createDate) {
		super();
		this.maChucVuNV = maChucVuNV;
		this.createDate = createDate;
	}

	public String getMaChucVuNV() {
		return maChucVuNV;
	}

	public void setMaChucVuNV(String maChucVuNV) {
		this.maChucVuNV = maChucVuNV;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}

	

	
	
	
}
