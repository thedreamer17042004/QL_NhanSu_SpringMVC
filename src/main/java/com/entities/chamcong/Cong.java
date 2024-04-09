package com.entities.chamcong;

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
import javax.validation.constraints.DecimalMax;
import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;

import org.springframework.format.annotation.DateTimeFormat;

import com.entities.chucvu.ChucVu;
import com.entities.nhanvien.NhanVien;

@Entity
@Table(name = "ChamCong")
public class Cong implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "MaNhanSu")
	private NhanVien nhanVien;
	
	@Column(name = "ngay")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@NotNull(message = "Ngày chưa được nhập")
	@Past(message = "Ngày phải trong quá khứ")
	private Date ngay;
	
	@Column(name = "soCong")
	 @NotNull(message = "Số công chưa được nhập")
    @DecimalMin(value = "0.0", inclusive = false, message = "Số công phải lớn hơn 0")  
	@DecimalMax(value = "32.0", inclusive = false, message = "Số công phải nhỏ hơn 31")
	private Float soCong;

	public Cong() {
		super();
	}

	public Cong(int id, NhanVien nhanVien, Date ngay, Float soCong) {
		super();
		this.id = id;
		this.nhanVien = nhanVien;
		this.ngay = ngay;
		this.soCong = soCong;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public NhanVien getNhanVien() {
		return nhanVien;
	}

	public void setNhanVien(NhanVien nhanVien) {
		this.nhanVien = nhanVien;
	}

	public Date getNgay() {
		return ngay;
	}

	public void setNgay(Date ngay) {
		this.ngay = ngay;
	}

	public Float getSoCong() {
		return soCong;
	}

	public void setSoCong(Float soCong) {
		this.soCong = soCong;
	}
	
	
	
	
	  
}
