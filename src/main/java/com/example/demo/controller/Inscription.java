package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.ConnexionInscriptionDao;
import com.example.demo.model.User;


@Controller
public class Inscription {
	
	@Autowired
	private ConnexionInscriptionDao connexionInscriptionDao;
	
	@RequestMapping(value="/inscription", method=RequestMethod.POST)
	public String Login(@ModelAttribute(name="inscriptionForm") User user, Model model) {
		String mail = user.getMail();
		String password = user.getPassword();
		String name = user.getName();
		String surname = user.getSurname();
		String username = user.getUsername();
		String image = user.getImage();

		connexionInscriptionDao.insertBdd(mail,password,name, surname, username, image);
		
		return "index";
	}
}
