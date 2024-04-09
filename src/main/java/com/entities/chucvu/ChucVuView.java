package com.entities.chucvu;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.entities.language.Language;
import com.entities.room.Room;

@Entity
public class ChucVuView {

	@Id
	@Column(name = "MaChucVuNV")
	private String maChucVuNV;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "ChucVuId")
	private ChucVu chucvu;
	
	@ManyToOne
    @JoinColumn(name = "Language_id")
	private Language language;
		
	@Column(name = "TenChucVu")
	private String tenChucVu;
	
	
	@Column(name = "Createdate")
	private Date createDate;


	public ChucVuView() {
		super();
	}
	
	public ChucVuView(Object[] row) {
        this.maChucVuNV = (String) row[0];
        this.chucvu = (ChucVu) row[1];
        this.language = (Language) row[2];
        this.tenChucVu = (String) row[3];
        this.createDate = (Date) row[4];
       
    }

	public ChucVuView(String maChucVuNV, ChucVu chucvu, Language language, String tenChucVu, Date createDate) {
		super();
		this.maChucVuNV = maChucVuNV;
		this.chucvu = chucvu;
		this.language = language;
		this.tenChucVu = tenChucVu;
		this.createDate = createDate;
	}

	public String getMaChucVuNV() {
		return maChucVuNV;
	}

	public void setMaChucVuNV(String maChucVuNV) {
		this.maChucVuNV = maChucVuNV;
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
