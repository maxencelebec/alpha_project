package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table (name="post")
public class Post {

	@Id
	@GeneratedValue
	private int id;
	private int id_user;
	private int date;
	private String titre;
	private String contenu;
	private int list_like;
	
	public Post(int id, int id_user, int date, String titre, String contenu, int list_like) {
		this.id = id;
		this.id_user = id_user;
		this.date = date;
		this.titre = titre;
		this.contenu = contenu;
		this.list_like = list_like;
	}
	
	public Post() {
		super();
	}
	
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
	public int getDate() {
		return date;
	}
	public void setDate(int date) {
		this.date = date;
	}
	public String getTitre() {
		return titre;
	}
	public void setTitre(String titre) {
		this.titre = titre;
	}
	public String getContenu() {
		return contenu;
	}
	public void setContenu(String contenu) {
		this.contenu = contenu;
	}
	public int getList_like() {
		return list_like;
	}
	public void setList_like(int list_like) {
		this.list_like = list_like;
	}
	
}
