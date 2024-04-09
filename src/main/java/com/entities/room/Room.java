package com.entities.room;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "PhongBans")
public class Room implements Serializable{
	
	@Id
	private int id;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "phoneRoom")
	private String phoneRoom;

	public Room() {
		super();
	}

	public Room(int id, String address, String phoneRoom) {
		super();
		this.id = id;
		this.address = address;
		this.phoneRoom = phoneRoom;
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
	
	

}
