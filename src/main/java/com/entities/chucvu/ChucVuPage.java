package com.entities.chucvu;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.entities.language.Language;

@Entity
public class ChucVuPage {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ElementCollection(targetClass=ChucVuView.class)
	private List<ChucVuView> chucvus;
	
	private int totalPages;
	
	private int pageSize;
	
	private int totalRecord;
	
	private int currentPage;

	public ChucVuPage() {
		super();
	}

	public ChucVuPage(int id, List<ChucVuView> chucvus, int totalPages, int pageSize, int totalRecord,
			int currentPage) {
		super();
		this.id = id;
		this.chucvus = chucvus;
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

	public List<ChucVuView> getChucvus() {
		return chucvus;
	}

	public void setChucvus(List<ChucVuView> chucvus) {
		this.chucvus = chucvus;
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
