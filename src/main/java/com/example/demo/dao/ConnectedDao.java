/*package com.example.demo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.example.demo.model.User;

public interface ConnectedDao extends CrudRepository<User,Integer>{
	
	@Query("SELECT username FROM User where id=:id")
	List<User> takeBdd(@Param ("id") int id);
	
	
	@Modifying
	@Query(value ="UPDATE user SET mail = :mail, username = :value, password = :password WHERE id = :id )")
	@Transactional
	void updateUser(@Param ("id") int id, @Param ("mail") String string, @Param ("password") String string2, @Param ("username") String string3);
}
*/