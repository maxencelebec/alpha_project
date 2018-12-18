package com.example.demo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.User;

public interface ConnexionInscriptionDao extends CrudRepository<User,Integer>{
	List<User> findByUsername (String username);
	List<User> findAll();
	
	@Query("SELECT username FROM User where id=:id")
	List<User> takeBdd(@Param ("id") int id);
	
	@Query("SELECT password FROM User WHERE mail = :mail ")
	String authentificationUser(@Param ("mail") String mail);
	
	@Modifying
	@Query(value ="INSERT INTO user (mail, password, name, surname, username) VALUES (:mail, :password, :name, :surname, :username)", nativeQuery =true)
	@Transactional
	void insertBdd(@Param ("mail") String string, @Param ("password") String string2, @Param ("name") String string3, @Param ("surname") String string4 ,@Param ("username") String string5);
}