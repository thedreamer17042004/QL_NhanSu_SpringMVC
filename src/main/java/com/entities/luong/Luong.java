package com.entities.luong;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "Luongs")
public class Luong implements Serializable{
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int id;
	 
	 @Column(name = "MaNhanVien")
	 private int maNhanVien;
	 
	 @Column(name = "LuongToiThieu")
	 private int luongToiThieu;
	 
	 @Column(name = "HeSoLuong")
	 private Float heSoLuong;
	 
	 @Column(name = "PhuCap")
	 private Float phuCap;

	public Luong() {
		super();
	}

	public Luong(int id, int maNhanVien, int luongToiThieu, Float heSoLuong, Float phuCap) {
		super();
		this.id = id;
		this.maNhanVien = maNhanVien;
		this.luongToiThieu = luongToiThieu;
		this.heSoLuong = heSoLuong;
		this.phuCap = phuCap;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getMaNhanVien() {
		return maNhanVien;
	}

	public void setMaNhanVien(int maNhanVien) {
		this.maNhanVien = maNhanVien;
	}

	public int getLuongToiThieu() {
		return luongToiThieu;
	}

	public void setLuongToiThieu(int luongToiThieu) {
		this.luongToiThieu = luongToiThieu;
	}

	public Float getHeSoLuong() {
		return heSoLuong;
	}

	public void setHeSoLuong(Float heSoLuong) {
		this.heSoLuong = heSoLuong;
	}

	public Float getPhuCap() {
		return phuCap;
	}

	public void setPhuCap(Float phuCap) {
		this.phuCap = phuCap;
	}
	 
	 
	 
	 
}
