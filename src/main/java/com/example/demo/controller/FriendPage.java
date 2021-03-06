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
import com.example.demo.dao.PostDao;
import com.example.demo.model.Commentaire;
import com.example.demo.model.User;

@Controller
public class FriendPage {

	@Autowired
	private ConnexionInscriptionDao connexionInscriptionDao;
	
	@Autowired
	private PostDao postDao;
	
	@Autowired
	private CommentaireDao commentaireDao;
	
	@Autowired
	private FriendDao friendDao;
	
	@RequestMapping(value="/amiPage", method=RequestMethod.POST)
	public String goPageAmi(@ModelAttribute(name="amiPageForm") User user,HttpSession session,  Model model) {
			if (session==null) {return "index";}
			String pseudo = user.getUsername();
			model.addAttribute("user", connexionInscriptionDao.findAll());
			model.addAttribute("sujet", postDao.findAll());
			model.addAttribute("commentaire", commentaireDao.findAll());
			
			if (friendDao.getIdAmi(pseudo) == null) {
				return "home";
			}
			int amiId = friendDao.getIdAmi(pseudo);
			
			model.addAttribute("amiId", amiId);
			model.addAttribute("friend", friendDao.listAmi(amiId));
			
			
		return "friendPage";
	}
	
}






