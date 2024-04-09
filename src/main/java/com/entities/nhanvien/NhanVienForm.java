package com.entities.nhanvien;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.entities.chucvu.ChucVu;
import com.entities.hocvan.HocVan;
import com.entities.room.Room;

@Entity
public class NhanVienForm implements Serializable{
	  private static final Integer DEFAULT_FIRST_NAME = 0;
	 
	@Id
	private int maNhanSu;
	
	@NotEmpty(message = "Tên nhân sự không để trống")
	@Column(name = "Ten")
	private String ten;
	
	@Column(name = "Tuoi")
	@Min(value = 18, message = "Age it nhất phải 18")
	@NotNull(message = "Age không được null")
	private int tuoi = DEFAULT_FIRST_NAME;
	
	@Column(name = "GioiTinh")
	private String gioiTinh;
	
	@Column(name = "HinhAnh")
	private String hinhAnh;
	
	@NotEmpty(message = "Số điện thoại không để trống")
	@Column(name = "Std")
	private String sdt;
	
	@Column(name = "QueQuan")
	private String queQuan;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@Column(name = "NgaySinh")
	private Date ngaySinh;
	
	@NotEmpty(message = "Chức vụ không để trống")
	private String machucVu;
	
	//int
	@NotEmpty(message = "Phòng ban không để trống")
	private String roomId;
	
	//int
	@NotEmpty(message = "Học vấn không để trống")
	private String maHocVan;
	
	private String tenChucVu;
	
	private String tenHocVan;
	
	private String tenPhongBan;

	public NhanVienForm() {
		super();
	}
	
	public NhanVienForm(Object[] row) {
        this.maNhanSu = (Integer) row[0];
        this.ten = (String) row[1];
        this.tuoi = (Integer) row[2];
        this.gioiTinh = (String) row[3];
        this.hinhAnh = (String) row[4];
        this.sdt = (String) row[5];
        this.queQuan = (String) row[6];
        this.ngaySinh = (Date) row[7];
        this.machucVu = (String) row[8];
        this.roomId =  row[9].toString();
        this.maHocVan =  row[10].toString();
        this.tenChucVu = (String) row[11];
        this.tenHocVan = (String) row[12];
        this.tenPhongBan = (String) row[12];
        
    }

	public NhanVienForm(int maNhanSu, @NotEmpty(message = "Tên nhân sự không để trống") String ten,
			@Min(value = 18, message = "Age it nhất phải 18") @NotNull(message = "Age không được null") int tuoi,
			String gioiTinh, String hinhAnh, @NotEmpty(message = "Số điện thoại không để trống") String sdt,
			String queQuan, Date ngaySinh, @NotEmpty(message = "Chức vụ không để trống") String machucVu,
			@NotEmpty(message = "Phòng ban không để trống") String roomId,
			@NotEmpty(message = "Học vấn không để trống") String maHocVan, String tenChucVu, String tenHocVan,
			String tenPhongBan) {
		super();
		this.maNhanSu = maNhanSu;
		this.ten = ten;
		this.tuoi = tuoi;
		this.gioiTinh = gioiTinh;
		this.hinhAnh = hinhAnh;
		this.sdt = sdt;
		this.queQuan = queQuan;
		this.ngaySinh = ngaySinh;
		this.machucVu = machucVu;
		this.roomId = roomId;
		this.maHocVan = maHocVan;
		this.tenChucVu = tenChucVu;
		this.tenHocVan = tenHocVan;
		this.tenPhongBan = tenPhongBan;
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

	public String getMachucVu() {
		return machucVu;
	}

	public void setMachucVu(String machucVu) {
		this.machucVu = machucVu;
	}

	public String getRoomId() {
		return roomId;
	}

	public void setRoomId(String roomId) {
		this.roomId = roomId;
	}

	public String getMaHocVan() {
		return maHocVan;
	}

	public void setMaHocVan(String maHocVan) {
		this.maHocVan = maHocVan;
	}

	public String getTenChucVu() {
		return tenChucVu;
	}

	public void setTenChucVu(String tenChucVu) {
		this.tenChucVu = tenChucVu;
	}

	public String getTenHocVan() {
		return tenHocVan;
	}

	public void setTenHocVan(String tenHocVan) {
		this.tenHocVan = tenHocVan;
	}

	public String getTenPhongBan() {
		return tenPhongBan;
	}

	public void setTenPhongBan(String tenPhongBan) {
		this.tenPhongBan = tenPhongBan;
	}

	public static Integer getDefaultFirstName() {
		return DEFAULT_FIRST_NAME;
	}



	
	
	
	

}
