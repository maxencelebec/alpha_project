package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="likes")
public class Likes {

	@Id
	@GeneratedValue
	private int id;
	private int id_post;
	private int id_user;
	
	public Likes(int id, int id_post, int id_user) {
		this.id = id;
		this.id_post = id_post;
		this.id_user = id_user;
	}
	
	public Likes(){
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getId_post() {
		return id_post;
	}

	public void setId_post(int id_post) {
		this.id_post = id_post;
	}

	public int getId_user() {
		return id_user;
	}

	public void setId_user(int id_user) {
		this.id_user = id_user;
	}
	
}
