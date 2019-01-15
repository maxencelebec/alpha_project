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
import com.example.demo.model.Post;

@Controller
public class Home {

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
	
	@RequestMapping(value="/parameters", method=RequestMethod.GET)
	public String redirectParameters () {
		return "parameters";
	}
	
	@RequestMapping(value="/myPage", method=RequestMethod.GET)
	public String redirectMyPage(Model model, HttpSession session) {
		int id_user = Integer.parseInt((String) session.getAttribute("id_user"));
		model.addAttribute("user", connexionInscriptionDao.findAll());
		model.addAttribute("friend", friendDao.listAmi(id_user));
		model.addAttribute("sujet", postDao.findAllByOrderByIdAsc());
		model.addAttribute("commentaire", commentaireDao.findAllByOrderByIdAsc());
		return "myPage";
	}
	
	@RequestMapping(value="/home", method=RequestMethod.GET)
	public String redirectHome(Model model) {
		model.addAttribute("user", connexionInscriptionDao.findAll());
		model.addAttribute("sujet", postDao.findAllByOrderByIdAsc());
		model.addAttribute("commentaire", commentaireDao.findAllByOrderByIdAsc());
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
		postDao.insertPost(date2, id_user, contenu, 0, titre);
		
		model.addAttribute("user", connexionInscriptionDao.findAll());
		model.addAttribute("sujet", postDao.findAllByOrderByIdAsc());
		model.addAttribute("commentaire", commentaireDao.findAllByOrderByIdAsc());
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
		
		model.addAttribute("user", connexionInscriptionDao.findAll());
		model.addAttribute("sujet", postDao.findAllByOrderByIdAsc());
		model.addAttribute("commentaire", commentaireDao.findAllByOrderByIdAsc());
		return "home";
	}
	
	@RequestMapping(value="/like", method=RequestMethod.GET)
	public String ajouterRetirerLike(HttpSession session, Model model,
									@RequestParam("id_post") Integer id_post) {
		
		int id_user = Integer.parseInt((String) session.getAttribute("id_user"));
		
		if (likesDao.checkLike(id_post, id_user).size()==0) {
			//ajout d'un parametre dans la table likes avec id_post et id_user
			likesDao.ajoutLike(id_post,id_user);
		}
		else {
			//retirer un parametre dans la table likes avec id_post et id_user
			likesDao.retirerLike(id_post,id_user);
		}
		
		//modifie dans la table post la valeur de list_like
		int count = 0;
		for (int k=0; k<likesDao.listLikes(id_post).size();k++) {
			count++;
		}
		likesDao.modifList_like(count,id_post);
		
		model.addAttribute("user", connexionInscriptionDao.findAll());
		model.addAttribute("sujet", postDao.findAllByOrderByIdAsc());
		model.addAttribute("commentaire", commentaireDao.findAllByOrderByIdAsc());
		return "home";
	}
	
	@RequestMapping(value="/friend", method=RequestMethod.GET)
	public String ajouterAmi(HttpSession session, Model model,
									@RequestParam("id_friend") Integer id_friend) {
		
		
		int id_user = Integer.parseInt((String) session.getAttribute("id_user"));
		model.addAttribute("user", connexionInscriptionDao.findAll());
		model.addAttribute("sujet", postDao.findAllByOrderByIdAsc());
		model.addAttribute("commentaire", commentaireDao.findAllByOrderByIdAsc());
		
		if (id_user==id_friend) {
			return "home";
		}
		else if (friendDao.checkAmitie(id_user,id_friend)!=null){
			return "home";
		}
		else {
			friendDao.ajouterAmi(id_friend, id_user);
		}
		return "home";
	}
	
}
