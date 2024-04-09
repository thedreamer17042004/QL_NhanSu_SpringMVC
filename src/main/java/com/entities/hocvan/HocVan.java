package com.entities.hocvan;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TrinhDoHocVans")
public class HocVan implements Serializable{
	@Id
	@Column(name = "MaTrinhDo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int maTrinhDo;

	@Column(name = "CreateDate")
	private Date createDate;

	public HocVan() {
		super();
	}

	public HocVan(int maTrinhDo, Date createDate) {
		super();
		this.maTrinhDo = maTrinhDo;
		this.createDate = createDate;
	}

	public int getMaTrinhDo() {
		return maTrinhDo;
	}

	public void setMaTrinhDo(int maTrinhDo) {
		this.maTrinhDo = maTrinhDo;
	}

	public Date getCreateDate() {
		return createDate;
	}

	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
	

}
