package com.entities.nhanvien;

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

import com.entities.chucvu.ChucVu;
import com.entities.hocvan.HocVan;
import com.entities.room.Room;

@Entity
@Table(name = "NhanViens")
public class NhanVien implements Serializable {

	@Id
	@Column(name = "MaNhanSu")
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int maNhanSu;
	
	@Column(name = "Ten")
	private String ten;
	
	@Column(name = "Tuoi")
	private int tuoi;
	
	@Column(name = "GioiTinh")
	private String gioiTinh;
	
	@Column(name = "HinhAnh")
	private String hinhAnh;
	
	@Column(name = "Std")
	private String sdt;
	
	@Column(name = "QueQuan")
	private String queQuan;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "NgaySinh")
	private Date ngaySinh;
	
	@ManyToOne
	@JoinColumn(name = "MaCapBac", referencedColumnName = "maChucVuNV")
	private ChucVu chucVu;
	
	@ManyToOne
	@JoinColumn(name = "MaPhong", referencedColumnName = "id")
	private Room room;
	
	@ManyToOne
	@JoinColumn(name = "MaTrinhDoHocVan", referencedColumnName = "maTrinhDo")
	private HocVan hocVan;

	public NhanVien() {
		super();
	}

	public NhanVien(int maNhanSu, String ten, int tuoi, String gioiTinh, String hinhAnh, String sdt, String queQuan,
			Date ngaySinh, ChucVu chucVu, Room room, HocVan hocVan) {
		super();
		this.maNhanSu = maNhanSu;
		this.ten = ten;
		this.tuoi = tuoi;
		this.gioiTinh = gioiTinh;
		this.hinhAnh = hinhAnh;
		this.sdt = sdt;
		this.queQuan = queQuan;
		this.ngaySinh = ngaySinh;
		this.chucVu = chucVu;
		this.room = room;
		this.hocVan = hocVan;
	}

	public int getMaNhanSu() {
		return maNhanSu;
	}

	public void setMaNhanSu(int maNhanSu) {
		this.maNhanSu = maNhanSu;
	}

	public String getTen() {
		return ten;
	}

	public void setTen(String ten) {
		this.ten = ten;
	}

	public int getTuoi() {
		return tuoi;
	}

	public void setTuoi(int tuoi) {
		this.tuoi = tuoi;
	}

	public String getGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(String gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public String getHinhAnh() {
		return hinhAnh;
	}

	public void setHinhAnh(String hinhAnh) {
		this.hinhAnh = hinhAnh;
	}

	public String getSdt() {
		return sdt;
	}

	public void setSdt(String sdt) {
		this.sdt = sdt;
	}

	public String getQueQuan() {
		return queQuan;
	}

	public void setQueQuan(String queQuan) {
		this.queQuan = queQuan;
	}

	public Date getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(Date ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public ChucVu getChucVu() {
		return chucVu;
	}

	public void setChucVu(ChucVu chucVu) {
		this.chucVu = chucVu;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	public HocVan getHocVan() {
		return hocVan;
	}

	public void setHocVan(HocVan hocVan) {
		this.hocVan = hocVan;
	}
	   
	 
	 
}
