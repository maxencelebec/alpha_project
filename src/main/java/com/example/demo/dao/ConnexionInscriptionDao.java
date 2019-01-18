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
	String mdpUser(@Param ("mail") String mail);
	
	@Query("SELECT password FROM User WHERE id = :id ")
	String passwordUser(@Param ("id") Integer integer);
	
	@Query("SELECT name FROM User WHERE mail = :mail ")
	String nameUser(@Param ("mail") String mail2);
	
	@Query("SELECT id FROM User WHERE mail = :mail ")
	String idUser(@Param ("mail") String mail2);
	
	@Query("SELECT type FROM User WHERE mail = :mail ")
	int typeUser(@Param ("mail") String mail2);
	
	@Modifying
	@Query(value ="INSERT INTO user (mail, password, name, surname, username, image, type) VALUES (:mail, :password, :name, :surname, :username, :image, 0)", nativeQuery =true)
	@Transactional
	void insertBdd(@Param ("mail") String string, @Param ("password") String string2, @Param ("name") String string3, @Param ("surname") String string4 ,@Param ("username") String string5, @Param ("image") String string6);

	@Modifying
	@Query(value ="UPDATE user SET mail= :mail, password = :password, username= :username WHERE id= :id", nativeQuery =true)
	@Transactional
	void modifProfil(@Param ("mail") String string, @Param ("password") String string2 ,@Param ("username") String string3, @Param ("id") Integer integer);

	@Modifying
	@Query(value ="DELETE FROM user WHERE id = :id_user", nativeQuery =true)
	@Transactional
	void supprimerUser(@Param ("id_user") Integer integer);
	
	@Modifying
	@Query(value ="DELETE FROM post WHERE id_user = :id_user", nativeQuery =true)
	@Transactional
	void retirerPost(@Param ("id_user") Integer integer);
	
	@Modifying
	@Query(value ="DELETE FROM post WHERE id = :id", nativeQuery =true)
	@Transactional
	void retirerPost2(@Param ("id") Integer integer);
	
	@Modifying
	@Query(value ="DELETE FROM commentaire WHERE id_user = :id_user", nativeQuery =true)
	@Transactional
	void retirerCommentaires(@Param ("id_user") Integer integer);
	
	@Modifying
	@Query(value ="DELETE FROM commentaire WHERE id = :id", nativeQuery =true)
	@Transactional
	void retirerCommentaires2(@Param ("id") Integer integer);
	
	@Modifying
	@Query(value ="DELETE FROM friend WHERE id_user = :id_user OR id_friend= :id_user", nativeQuery =true)
	@Transactional
	void retirerAmitie(@Param ("id_user") Integer integer);
	
	@Modifying
	@Query(value ="DELETE FROM likes WHERE id_user = :id_user", nativeQuery =true)
	@Transactional
	void retirerLikes(@Param ("id_user") Integer integer);
	
	@Modifying
	@Query(value ="UPDATE user SET image= :image WHERE id= :id", nativeQuery =true)
	@Transactional
	void modifPhoto(@Param ("image") String string, @Param ("id") Integer integer);
	
	
}
