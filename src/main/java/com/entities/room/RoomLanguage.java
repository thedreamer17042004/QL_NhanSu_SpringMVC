package com.entities.room;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.entities.language.Language;

@Entity
@Table(name = "PhongBansLanguages")
public class RoomLanguage implements Serializable{

	@Id
	@GeneratedValue(strategy =  GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(cascade = CascadeType.REMOVE)
    @JoinColumn(name = "room_id")
	private Room room;
	
	@ManyToOne
    @JoinColumn(name = "Language_id")
	private Language language;
		
	@Column(name = "roomName")
	private String roomName;
	
	
	@Column(name = "Createdate")
	private Date createDate;


	public RoomLanguage() {
		super();
	}


	public RoomLanguage(int id, Room room, Language language, String roomName, Date createDate) {
		super();
		this.id = id;
		this.room = room;
		this.language = language;
		this.roomName = roomName;
		this.createDate = createDate;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Room getRoom() {
		return room;
	}


	public void setRoom(Room room) {
		this.room = room;
	}


	public Language getLanguage() {
		return language;
	}


	public void setLanguage(Language language) {
		this.language = language;
	}


	public String getRoomName() {
		return roomName;
	}


	public void setRoomName(String roomName) {
		this.roomName = roomName;
	}


	public Date getCreateDate() {
		return createDate;
	}


	public void setCreateDate(Date createDate) {
		this.createDate = createDate;
	}
	
	
}
