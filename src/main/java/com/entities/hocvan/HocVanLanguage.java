package com.entities.hocvan;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.entities.language.Language;
import com.entities.room.Room;

@Entity
@Table(name = "TrinhDoHocVansLanguages")
public class HocVanLanguage implements Serializable{

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "MaTrinhDo", referencedColumnName = "MaTrinhDo")
	private HocVan hocVan;
	
	@ManyToOne
    @JoinColumn(name = "Language_id")
	private Language language;
		
	@Column(name = "TenTrinhDo")
	private String tenTrinhDo;
	
	
	@Column(name = "Createdate")
	private Date createDate;


	public HocVanLanguage() {
		super();
	}


	public HocVanLanguage(int id, HocVan hocVan, Language language, String tenTrinhDo, Date createDate) {
		super();
		this.id = id;
		this.hocVan = hocVan;
		this.language = language;
		this.tenTrinhDo = tenTrinhDo;
		this.createDate = createDate;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public HocVan getHocVan() {
		return hocVan;
	}


	public void setHocVan(HocVan hocVan) {
		this.hocVan = hocVan;
	}


	public Language getLanguage() {
		return language;
	}


	public void setLanguage(Language language) {
		this.language = language;
	}


	public String getTenTrinhDo() {
		return tenTrinhDo;
	}


	public void setTenTrinhDo(String tenTrinhDo) {
		this.tenTrinhDo = tenTrinhDo;
	}


	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}
