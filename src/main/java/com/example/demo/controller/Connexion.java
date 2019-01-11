package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.dao.CommentaireDao;
import com.example.demo.dao.ConnexionInscriptionDao;
import com.example.demo.dao.PostDao;
import com.example.demo.model.User;

@Controller
public class Connexion {

	@Autowired
	private ConnexionInscriptionDao connexionInscriptionDao;
	
	@Autowired
	private PostDao postDao;
	
	@Autowired
	private CommentaireDao commentaireDao;
	
	@RequestMapping(value="/inscription", method=RequestMethod.GET)
	public String getLoginForm() {
		return "subscribe";
	}
	
	@RequestMapping(value="/connexion", method=RequestMethod.POST)
	public String Login(@ModelAttribute(name="connexionForm") User user, Model model, HttpSession session) {
		String mail = user.getMail();
		String password = user.getPassword();
		String authentification = connexionInscriptionDao.mdpUser(mail);
		String name = connexionInscriptionDao.nameUser(mail);
		String id_user = connexionInscriptionDao.idUser(mail);
		if (authentification!=null) {
			if (authentification.equals(password)) {
				session.setAttribute("name",name);
				session.setAttribute("id_user",id_user);
				model.addAttribute("user", connexionInscriptionDao.findAll());
				model.addAttribute("sujet", postDao.findAll());
				model.addAttribute("commentaire", commentaireDao.findAll());
				return "home";
			}
			else {
				return "index";
			}
		}
		return "index";
	}
	
}
