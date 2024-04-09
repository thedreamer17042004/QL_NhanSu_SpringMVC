package com.entities.chucvu;

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
@Table(name = "ChucVuNhanViensLanguages")
public class ChucVuLanguage implements Serializable{
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "ChucVuId", referencedColumnName = "MaChucVuNV")
	private ChucVu chucvu;
	
	@ManyToOne
    @JoinColumn(name = "Language_id")
	private Language language;
		
	@Column(name = "TenChucVu")
	private String tenChucVu;
	
	
	@Column(name = "Createdate")
	private Date createDate;


	public ChucVuLanguage() {
		super();
	}


	public ChucVuLanguage(int id, ChucVu chucvu, Language language, String tenChucVu, Date createDate) {
		super();
		this.id = id;
		this.chucvu = chucvu;
		this.language = language;
		this.tenChucVu = tenChucVu;
		this.createDate = createDate;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public ChucVu getChucvu() {
		return chucvu;
	}


	public void setChucvu(ChucVu chucvu) {
		this.chucvu = chucvu;
	}


	public Language getLanguage() {
		return language;
	}


	public void setLanguage(Language language) {
		this.language = language;
	}


	public String getTenChucVu() {
		return tenChucVu;
	}


	public void setTenChucVu(String tenChucVu) {
		this.tenChucVu = tenChucVu;
	}


	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}


	
	
	
	
}
