package com.entities.chamcong;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.entities.nhanvien.NhanVienForm;

@Entity
public class CongPage {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ElementCollection(targetClass=Cong.class)
	private List<Cong> congs;
	
	private int totalPages;
	
	private int pageSize;
	
	private int totalRecord;
	
	private int currentPage;

	public CongPage() {
		super();
	}

	public CongPage(int id, List<Cong> congs, int totalPages, int pageSize, int totalRecord, int currentPage) {
		super();
		this.id = id;
		this.congs = congs;
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

	public List<Cong> getCongs() {
		return congs;
	}

	public void setCongs(List<Cong> congs) {
		this.congs = congs;
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
