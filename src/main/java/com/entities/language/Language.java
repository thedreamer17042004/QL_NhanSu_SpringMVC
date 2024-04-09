package com.entities.language;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Languages")
public class Language implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column(name = "name")
	private String name;
	
	@Column(name = "canonical")
	private String canonical;
	
	@Column(name = "image")
	private String image;
	
	@Column(name = "current_")
	private boolean current_;
	
	@Column(name = "Createdate")
	private Date createdate;

	public Language() {
		super();
	}

	public Language(int id, String name, String canonical, String image, boolean current_, Date createdate) {
		super();
		this.id = id;
		this.name = name;
		this.canonical = canonical;
		this.image = image;
		this.current_ = current_;
		this.createdate = createdate;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCanonical() {
		return canonical;
	}

	public void setCanonical(String canonical) {
		this.canonical = canonical;
	}

	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public boolean isCurrent_() {
		return current_;
	}

	public void setCurrent_(boolean current_) {
		this.current_ = current_;
	}

	public Date getCreatedate() {
		return createdate;
	}

	public void setCreatedate(Date createdate) {
		this.createdate = createdate;
	}
	
	
	
	
}
