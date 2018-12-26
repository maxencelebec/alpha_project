package com.example.demo.controller;

import java.sql.Date;

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
import com.example.demo.model.Commentaire;
import com.example.demo.model.Post;

@Controller
public class Home {

	@Autowired
	private ConnexionInscriptionDao connexionInscriptionDao;
	
	@Autowired
	private PostDao postDao;
	
	@Autowired
	private CommentaireDao commentaireDao;
	
	@RequestMapping(value="/parameters", method=RequestMethod.GET)
	public String redirectParameters () {
		return "parameters";
	}
	
	@RequestMapping(value="/myPage", method=RequestMethod.GET)
	public String redirectMyPage(Model model) {
		model.addAttribute("sujet", postDao.findAll());
		model.addAttribute("commentaire", commentaireDao.findAll());
		return "myPage";
	}
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String redirectHome(Model model) {
		model.addAttribute("sujet", postDao.findAll());
		model.addAttribute("commentaire", commentaireDao.findAll());
		return "home";
	}
	
	@RequestMapping(value="/connexion", method=RequestMethod.GET)
	public String redirectConnexion() {
		return "index";
	}
	
	@RequestMapping(value="/publication", method=RequestMethod.POST)
	public String ajouterContenu(@ModelAttribute(name="publicationForm") Post post,
								 HttpSession session, Model model) {
		String contenu = post.getContenu();
		String titre = post.getTitre();
		Date date = new Date(System.currentTimeMillis());
		String date2 = date.toString();
		int id_user = Integer.parseInt((String) session.getAttribute("id_user"));
		postDao.insertPost(date2, 3, contenu, 0, titre);
		model.addAttribute("sujet", postDao.findAll());
		model.addAttribute("commentaire", commentaireDao.findAll());
		return "home";
	}
	
	@RequestMapping(value="/commentaire", method=RequestMethod.POST)
	public String ajouterCommentaire(@ModelAttribute(name="commentaireForm") Commentaire commentaire,
								 HttpSession session, Model model) {
		String contenu = commentaire.getContenu();	
		int id_post = commentaire.getId_post();
		Date date = new Date(System.currentTimeMillis());
		String date2 = date.toString();
		int id_user = Integer.parseInt((String) session.getAttribute("id_user"));
		commentaireDao.insertCommentaire(contenu,date2,id_post,id_user);
		model.addAttribute("sujet", postDao.findAll());
		model.addAttribute("commentaire", commentaireDao.findAll());
		return "home";
	}
	
}
