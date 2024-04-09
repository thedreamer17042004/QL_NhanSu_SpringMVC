package com.entities.nhanvien;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.entities.user.Account;

@Entity
public class NhanVienPage {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ElementCollection(targetClass=NhanVienForm.class)
	private List<NhanVienForm> nhanViens;
	
	private int totalPages;
	
	private int pageSize;
	
	private int totalRecord;
	
	private int currentPage;

	public NhanVienPage() {
		super();
	}

	public NhanVienPage(int id, List<NhanVienForm> nhanViens, int totalPages, int pageSize, int totalRecord,
			int currentPage) {
		super();
		this.id = id;
		this.nhanViens = nhanViens;
		this.totalPages = totalPages;
		this.pageSize = pageSize;
		this.totalRecord = totalRecord;
		this.currentPage = currentPage;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public List<NhanVienForm> getNhanViens() {
		return nhanViens;
	}

	public void setNhanViens(List<NhanVienForm> nhanViens) {
		this.nhanViens = nhanViens;
	}

	public int getTotalPages() {
		return totalPages;
	}

	public void setTotalPages(int totalPages) {
		this.totalPages = totalPages;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public int getTotalRecord() {
		return totalRecord;
	}

	public void setTotalRecord(int totalRecord) {
		this.totalRecord = totalRecord;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}



	
	
	
}
