package com.example.demo.dao;

import java.util.ArrayList;
import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Likes;

public interface LikesDao extends CrudRepository<Likes,Integer>{
	ArrayList<Likes> findAll();
	
	@Modifying
	@Query(value ="INSERT INTO likes (id_post,id_user) VALUES (:id_post, :id_user)", nativeQuery =true)
	@Transactional
	void ajoutLike (@Param ("id_post") Integer integer ,@Param ("id_user") Integer integer2);
	
	@Query("SELECT id_post FROM Likes WHERE id_post = :id_post")
	List<Likes> listLikes(@Param ("id_post") Integer integer);
	
	@Query("SELECT id_post FROM Likes WHERE id_post = :id_post AND id_user = :id_user")
	List<Likes> checkLike(@Param ("id_post") Integer integer, @Param ("id_user") Integer integer2);
	
	@Modifying
	@Query(value ="DELETE FROM likes WHERE id_post = :id_post and id_user = :id_user", nativeQuery =true)
	@Transactional
	void retirerLike(@Param ("id_post") Integer integer, @Param ("id_user") Integer integer2);
	
	@Modifying
	@Query(value ="UPDATE post SET list_like = :list_like WHERE id= :id_post", nativeQuery =true)
	@Transactional
	void modifList_like(@Param ("list_like") Integer integer, @Param ("id_post") Integer integer2);


}
