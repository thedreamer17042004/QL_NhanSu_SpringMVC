package com.entities.language;

import java.util.List;

import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class LanguagePage {
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	
	@ElementCollection(targetClass=Language.class)
	private List<Language> languages;
	
	private int totalPages;
	
	private int pageSize;
	
	private int totalRecord;
	
	private int currentPage;

	public LanguagePage() {
		super();
	}

	public LanguagePage(int id, List<Language> languages, int totalPages, int pageSize, int totalRecord,
			int currentPage) {
		super();
		this.id = id;
		this.languages = languages;
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

	public List<Language> getLanguages() {
		return languages;
	}

	public void setLanguages(List<Language> languages) {
		this.languages = languages;
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
