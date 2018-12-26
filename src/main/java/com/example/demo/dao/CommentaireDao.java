package com.example.demo.dao;

import java.sql.Date;
import java.util.ArrayList;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Commentaire;

public interface CommentaireDao extends CrudRepository<Commentaire,Integer>{
	ArrayList<Commentaire> findAll();
	
	@Modifying 
	@Query(value ="INSERT INTO commentaire (contenu, date, id_post, id_user) VALUES (:contenu, :date, :id_post, :id_user)", nativeQuery =true)
	@Transactional
	void insertCommentaire(@Param ("contenu") String string, @Param ("date") String string2, @Param ("id_post") Integer integer, @Param ("id_user") Integer integer2 );

}
