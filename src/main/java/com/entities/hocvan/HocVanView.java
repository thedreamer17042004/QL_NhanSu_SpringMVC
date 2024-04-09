package com.entities.hocvan;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.entities.chucvu.ChucVu;
import com.entities.language.Language;

@Entity
public class HocVanView {
	@Id
	@Column(name = "MaTrinhDo")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int maTrinhDo;

	@ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "MaTrinhDo", referencedColumnName = "MaTrinhDo", insertable =false ,updatable =false)
	private HocVan hocVan;
	
	@ManyToOne
    @JoinColumn(name = "Language_id")
	private Language language;
		
	@Column(name = "TenTrinhDo")
	private String tenTrinhDo;
	
	
	@Column(name = "Createdate")
	private Date createDate;


	public HocVanView() {
		super();
	}
	
	public HocVanView(Object[] row) {
        this.maTrinhDo = (Integer) row[0];
        this.hocVan = (HocVan) row[1];
        this.language = (Language) row[2];
        this.tenTrinhDo = (String) row[3];
        this.createDate = (Date) row[4];
       
    }

	public HocVanView(int maTrinhDo, HocVan hocVan, Language language, String tenTrinhDo, Date createDate) {
		super();
		this.maTrinhDo = maTrinhDo;
		this.hocVan = hocVan;
		this.language = language;
		this.tenTrinhDo = tenTrinhDo;
		this.createDate = createDate;
	}


	public int getMaTrinhDo() {
		return maTrinhDo;
	}


	public void setMaTrinhDo(int maTrinhDo) {
		this.maTrinhDo = maTrinhDo;
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
