package com.example.demo.controller;

import java.sql.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;

import com.example.demo.dao.ConnexionInscriptionDao;
import com.example.demo.dao.PostDao;
import com.example.demo.model.Post;
import com.example.demo.model.User;

@Controller
public class Home {

	@Autowired
	private ConnexionInscriptionDao connexionInscriptionDao;
	
	@Autowired
	private PostDao postDao;
	
	@RequestMapping(value="/parameters", method=RequestMethod.GET)
	public String redirectParameters (Model model) {
		return "parameters";
	}
	
	@RequestMapping(value="/myPage", method=RequestMethod.GET)
	public String redirectMyPage() {
		return "myPage";
	}
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String redirectHome() {
		return "home";
	}
	
	@RequestMapping(value="/connexion", method=RequestMethod.GET)
	public String redirectConnexion() {
		return "index";
	}
	
	@RequestMapping(value="/publication", method=RequestMethod.POST)
	public String ajouterContenu(@ModelAttribute(name="publicationForm") Post post, HttpSession session) {
		String contenu = post.getContenu();
		String titre = post.getTitre();
		Date date = new Date(System.currentTimeMillis());
		int id_user = Integer.parseInt((String) session.getAttribute("id_user"));
		postDao.insertPost(date, id_user, contenu, 0, titre);
		return "home";
	}
	
}
