package com.entities.hocvan;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.entities.chucvu.ChucVuView;

@Entity
public class HocVanPage {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ElementCollection(targetClass=HocVanView.class)
	private List<HocVanView> hocvans;
	
	private int totalPages;
	
	private int pageSize;
	
	private int totalRecord;
	
	private int currentPage;

	public HocVanPage() {
		super();
	}

	public HocVanPage(int id, List<HocVanView> hocvans, int totalPages, int pageSize, int totalRecord,
			int currentPage) {
		super();
		this.id = id;
		this.hocvans = hocvans;
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

	public List<HocVanView> getHocvans() {
		return hocvans;
	}

	public void setHocvans(List<HocVanView> hocvans) {
		this.hocvans = hocvans;
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
