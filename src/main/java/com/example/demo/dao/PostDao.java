package com.example.demo.dao;

import java.sql.Date;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Post;
import com.example.demo.model.User;

public interface PostDao extends CrudRepository<Post,Integer>{

	@Modifying
	@Query(value ="INSERT INTO post (date, id_user, contenu, list_like, titre) VALUES (:date, :id_user, :contenu, :list_like, :titre)", nativeQuery =true)
	@Transactional
	void insertPost(@Param ("date") Date date, @Param ("id_user") Integer integer2, @Param ("contenu") String string, @Param ("list_like") Integer integer3 ,@Param ("titre") String string2);

	
}
