package com.entities.luong;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.entities.nhanvien.NhanVien;

@Entity
@Table(name = "ChiTietLuongs")
public class ChiTietLuong implements Serializable{
	@Id
	@Column(name = "MaChiTietBangLuong")
	 @GeneratedValue(strategy = GenerationType.IDENTITY)
	private int maChiTietBangLuong;
	
	@ManyToOne
	@JoinColumn(name = "MaNhanVien")
	private NhanVien nhanVien;
	
	@Column(name = "LuongCoBan")
	private Float luongCoBan;
	
	@Column(name  = "phuCap")
	private Float phuCap;
	
	@Column(name = "TienThuong")
	private Float tienThuong;
	
	@Column(name = "SoCong")
	private int soCong;
	
	@Column(name = "TienPhat")
	private int tienPhat;
	
	@Column(name = "NgayNhanLuong")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date ngayNhanLuong;
	
	@Column(name = "TongTienLuong")
	private String tongTienLuong;

	public ChiTietLuong() {
		super();
	}

	public ChiTietLuong(int maChiTietBangLuong, NhanVien nhanVien, Float luongCoBan, Float phuCap, Float tienThuong,
			int soCong, int tienPhat, Date ngayNhanLuong, String tongTienLuong) {
		super();
		this.maChiTietBangLuong = maChiTietBangLuong;
		this.nhanVien = nhanVien;
		this.luongCoBan = luongCoBan;
		this.phuCap = phuCap;
		this.tienThuong = tienThuong;
		this.soCong = soCong;
		this.tienPhat = tienPhat;
		this.ngayNhanLuong = ngayNhanLuong;
		this.tongTienLuong = tongTienLuong;
	}

	public int getMaChiTietBangLuong() {
		return maChiTietBangLuong;
	}

	public void setMaChiTietBangLuong(int maChiTietBangLuong) {
		this.maChiTietBangLuong = maChiTietBangLuong;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public Float getLuongCoBan() {
		return luongCoBan;
	}

	public void setLuongCoBan(Float luongCoBan) {
		this.luongCoBan = luongCoBan;
	}

	public Float getPhuCap() {
		return phuCap;
	}

	public void setPhuCap(Float phuCap) {
		this.phuCap = phuCap;
	}

	public Float getTienThuong() {
		return tienThuong;
	}

	public void setTienThuong(Float tienThuong) {
		this.tienThuong = tienThuong;
	}

	public int getSoCong() {
		return soCong;
	}

	public void setSoCong(int soCong) {
		this.soCong = soCong;
	}

	public int getTienPhat() {
		return tienPhat;
	}

	public void setTienPhat(int tienPhat) {
		this.tienPhat = tienPhat;
	}

	public Date getNgayNhanLuong() {
		return ngayNhanLuong;
	}

	public void setNgayNhanLuong(Date ngayNhanLuong) {
		this.ngayNhanLuong = ngayNhanLuong;
	}

	public String getTongTienLuong() {
		return tongTienLuong;
	}

	public void setTongTienLuong(String tongTienLuong) {
		this.tongTienLuong = tongTienLuong;
	}
	
	
}
