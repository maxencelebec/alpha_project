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
public class Connexion {

	@Autowired
	private ConnexionInscriptionDao connexionInscriptionDao;
	
	@RequestMapping(value="/inscription", method=RequestMethod.GET)
	public String getLoginForm() {
		return "subscribe";
	}
	
	@RequestMapping(value="/connexion", method=RequestMethod.POST)
	public String Login(@ModelAttribute(name="connexionForm") User user, Model model) {
		String mail = user.getMail();
		String password = user.getPassword();
		String authentification = connexionInscriptionDao.authentificationUser(mail);
		if (authentification!=null) {
			if (authentification.equals(password)) {
				return "home";
			}
			else {
				return "index";
			}
		}
		return "index";
	}
	
}
