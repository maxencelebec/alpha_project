package com.example.demo.dao;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Post;

public interface PostDao extends CrudRepository<Post,Integer>{
	ArrayList<Post> findAll();
	
	@Modifying
	@Query(value ="INSERT INTO post (date, id_user, contenu, list_like, titre) VALUES (:date, :id_user, :contenu, :list_like, :titre)", nativeQuery =true)
	@Transactional
	void insertPost(@Param ("date") String string, @Param ("id_user") Integer integer2, @Param ("contenu") String string3, @Param ("list_like") Integer integer3 ,@Param ("titre") String string2);

	@Query("SELECT titre FROM Post")
	List<Post> listPost();
	
	@Modifying
	@Query(value ="DELETE FROM post WHERE id = :id_post", nativeQuery =true)
	@Transactional
	void retirerPost(@Param ("id_post") Integer integer);
	
	@Modifying
	@Query(value ="DELETE FROM commentaire WHERE id_post = :id_post", nativeQuery =true)
	@Transactional
	void retirerCommentaires(@Param ("id_post") Integer integer);
}
