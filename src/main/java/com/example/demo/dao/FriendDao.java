package com.example.demo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.Friend;
import com.example.demo.model.Post;

public interface FriendDao extends CrudRepository<Friend,Integer>{

	List<Friend> findAll(); 
	
	@Modifying 
	@Query(value ="INSERT INTO friend (id_friend, id_user) VALUES (:id_friend, :id_user)", nativeQuery =true)
	@Transactional
	void ajouterAmi(@Param ("id_friend") Integer integer, @Param ("id_user") Integer integer2 );

	@Query("SELECT id FROM Friend WHERE id_user= :id_user AND id_friend=:id_friend")
	Integer checkAmitie(@Param ("id_user") Integer integer, @Param ("id_friend") Integer integer2);
	
	@Query("SELECT username FROM User WHERE id IN (SELECT id_friend FROM Friend WHERE id_user=:id_user)")
	List<String> listAmi(@Param ("id_user") Integer integer);
	
	@Query("SELECT id FROM User WHERE username= :username")
	Integer getIdAmi(@Param ("username") String string);
	
	@Modifying
	@Query(value ="DELETE FROM friend WHERE id_friend = :id_friend", nativeQuery =true)
	@Transactional
	void deleteAmi(@Param ("id_friend") Integer integer);
	
}






