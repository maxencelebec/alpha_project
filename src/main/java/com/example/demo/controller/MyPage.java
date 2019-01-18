package com.example.demo.controller;

import java.sql.Date;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.CommentaireDao;
import com.example.demo.dao.ConnexionInscriptionDao;
import com.example.demo.dao.FriendDao;
import com.example.demo.dao.LikesDao;
import com.example.demo.dao.PostDao;
import com.example.demo.model.Commentaire;

@Controller
public class MyPage {
	
	@Autowired
	private ConnexionInscriptionDao connexionInscriptionDao;
	
	@Autowired
	private PostDao postDao;
	
	@Autowired
	private CommentaireDao commentaireDao;
	
	@Autowired
	private FriendDao friendDao;
	
	@Autowired
	private LikesDao likesDao;
	
	@RequestMapping(value="/myPageCommentaire", method=RequestMethod.POST)
	public String ajouterMonCommentaire(@ModelAttribute(name="myPageCommentaireForm") Commentaire commentaire,
								 HttpSession session, Model model) {
		String contenu = commentaire.getContenu();	
		int id_post = commentaire.getId_post();
		Date date = new Date(System.currentTimeMillis());
		String date2 = date.toString();
		int id_user = Integer.parseInt((String) session.getAttribute("id_user"));
		commentaireDao.insertCommentaire(contenu,date2,id_post,id_user);
		

		model.addAttribute("user", connexionInscriptionDao.findAll());
		model.addAttribute("friend", friendDao.listAmi(id_user));
		model.addAttribute("sujet", postDao.findAllByOrderByIdAsc());
		model.addAttribute("commentaire", commentaireDao.findAllByOrderByIdAsc());
		model.addAttribute("notifs", likesDao.notifs(id_user));
		return "myPage";
	}
	
	@RequestMapping(value="/delete", method=RequestMethod.GET)
	public String supprimerPost(HttpSession session, Model model,
									@RequestParam("id_post") Integer id_post) {
		int id_user = Integer.parseInt((String) session.getAttribute("id_user"));
		postDao.retirerPost(id_post);
		postDao.retirerCommentaires(id_post);
		model.addAttribute("user", connexionInscriptionDao.findAll());
		model.addAttribute("friend", friendDao.listAmi(id_user));
		model.addAttribute("sujet", postDao.findAllByOrderByIdAsc());
		model.addAttribute("commentaire", commentaireDao.findAllByOrderByIdAsc());
		model.addAttribute("notifs", likesDao.notifs(id_user));
		return "myPage";
	}
	
	@RequestMapping(value="/delAmi", method=RequestMethod.GET)
	public String deleteAmi(HttpSession session, Model model,
									@RequestParam("id_friend") Integer id_friend) {
		int id_user = Integer.parseInt((String) session.getAttribute("id_user"));
		friendDao.deleteAmi(id_friend);
		model.addAttribute("user", connexionInscriptionDao.findAll());
		model.addAttribute("friend", friendDao.listAmi(id_user));
		model.addAttribute("sujet", postDao.findAllByOrderByIdAsc());
		model.addAttribute("commentaire", commentaireDao.findAllByOrderByIdAsc());
		model.addAttribute("notifs", likesDao.notifs(id_user));
		return "myPage";
	}
}
