package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.CommentaireDao;
import com.example.demo.dao.ConnexionInscriptionDao;
import com.example.demo.dao.FriendDao;
import com.example.demo.dao.LikesDao;
import com.example.demo.dao.PostDao;

@Controller
public class Admin {

	@Autowired
	private ConnexionInscriptionDao connexionInscriptionDao;
	
	@Autowired
	private PostDao postDao;
	
	@Autowired
	private CommentaireDao commentaireDao;
	
	@Autowired
	private LikesDao likesDao;
	
	@Autowired
	private FriendDao friendDao;
	
	@RequestMapping(value="/supprUser", method=RequestMethod.GET)
	public String DeleteUser(HttpSession session, Model model,
									@RequestParam("id_user") Integer id_user) {
		
		connexionInscriptionDao.supprimerUser(id_user);
		connexionInscriptionDao.retirerPost(id_user);
		connexionInscriptionDao.retirerCommentaires(id_user);
		connexionInscriptionDao.retirerAmitie(id_user);
		connexionInscriptionDao.retirerLikes(id_user);
		model.addAttribute("user", connexionInscriptionDao.findAll());
		model.addAttribute("sujet", postDao.findAllByOrderByIdAsc());
		model.addAttribute("commentaire", commentaireDao.findAllByOrderByIdAsc());
		
		return "admin";
	}
	
	@RequestMapping(value="/supprPost", method=RequestMethod.GET)
	public String DeletePost(HttpSession session, Model model,
									@RequestParam("id_post") Integer id_post) {
		
		
		connexionInscriptionDao.retirerPost2(id_post);
		connexionInscriptionDao.retirerCommentaires2(id_post);

		model.addAttribute("user", connexionInscriptionDao.findAll());
		model.addAttribute("sujet", postDao.findAllByOrderByIdAsc());
		model.addAttribute("commentaire", commentaireDao.findAllByOrderByIdAsc());
		
		return "admin";
	}
	
	@RequestMapping(value="/supprCom", method=RequestMethod.GET)
	public String DeleteCom(HttpSession session, Model model,
									@RequestParam("id_com") Integer id_com) {
		
		connexionInscriptionDao.retirerCommentaires2(id_com);

		model.addAttribute("user", connexionInscriptionDao.findAll());
		model.addAttribute("sujet", postDao.findAllByOrderByIdAsc());
		model.addAttribute("commentaire", commentaireDao.findAllByOrderByIdAsc());
		
		return "admin";
	}
}
