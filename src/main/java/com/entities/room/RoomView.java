package com.entities.room;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

import com.entities.chucvu.ChucVu;
import com.entities.language.Language;

@Entity
public class RoomView {
	@Id
	private int id;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "phoneRoom")
	private String phoneRoom;
	
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


	public RoomView() {
		super();
	}

	public RoomView(Object[] row) {
        this.id = (Integer) row[0];
        this.address = (String) row[1];
        this.phoneRoom = (String) row[2];
        this.room = (Room) row[3];
        this.language = (Language) row[4];
        this.roomName = (String) row[5];
        this.createDate = (Date) row[6];
       
    }
	public RoomView(int id, String address, String phoneRoom, Room room, Language language, String roomName,
			Date createDate) {
		super();
		this.id = id;
		this.address = address;
		this.phoneRoom = phoneRoom;
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


	public String getAddress() {
		return address;
	}


	public void setAddress(String address) {
		this.address = address;
	}


	public String getPhoneRoom() {
		return phoneRoom;
	}


	public void setPhoneRoom(String phoneRoom) {
		this.phoneRoom = phoneRoom;
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

