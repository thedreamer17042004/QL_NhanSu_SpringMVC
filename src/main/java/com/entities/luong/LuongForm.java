package com.entities.luong;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

import com.entities.nhanvien.NhanVien;

@Entity
public class LuongForm {
	  @Id
	  @GeneratedValue(strategy = GenerationType.IDENTITY)
	 private int id;
	 
	 @Column(name = "MaNhanVien")
	 private int maNhanVien;
	 
	 @NotNull(message = "Luong chưa nhập")
	 @Column(name = "LuongToiThieu")
	 private int luongToiThieu;
	 
	 @Column(name = "HeSoLuong")
	 private Float heSoLuong;
	 
	 @Column(name = "PhuCap")
	 private Float phuCap;
	 
	
	@Column(name = "NgayNhanLuong")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Ngày chưa được nhập")
	@Past(message = "Ngày phải trong quá khứ")
	private Date ngayNhanLuong;


	public LuongForm() {
		super();
	}


	public LuongForm(int id, int maNhanVien, int luongToiThieu, Float heSoLuong, Float phuCap, Date ngayNhanLuong) {
		super();
		this.id = id;
		this.maNhanVien = maNhanVien;
		this.luongToiThieu = luongToiThieu;
		this.heSoLuong = heSoLuong;
		this.phuCap = phuCap;
		this.ngayNhanLuong = ngayNhanLuong;
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


	public Date getNgayNhanLuong() {
		return ngayNhanLuong;
	}


	public void setNgayNhanLuong(Date ngayNhanLuong) {
		this.ngayNhanLuong = ngayNhanLuong;
	}
	
	
	
}
