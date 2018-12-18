package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
@Table (name="user")
public class Friend {
	
	@Id
	@GeneratedValue
	private int id;
	private int id_user;
	private int id_friend;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getId_user() {
		return id_user;
	}
	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	public int getId_friend() {
		return id_friend;
	}
	public void setId_friend(int id_friend) {
		this.id_friend = id_friend;
	}
	public Friend(int id, int id_user, int id_friend) {
		super();
		this.id = id;
		this.id_user = id_user;
		this.id_friend = id_friend;
	}
	
	
}