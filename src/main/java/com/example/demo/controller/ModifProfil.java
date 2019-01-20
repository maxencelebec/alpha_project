package com.example.demo.controller;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.demo.dao.ConnexionInscriptionDao;
import com.example.demo.dao.LikesDao;
import com.example.demo.model.User;

@Controller
public class ModifProfil {
	
	@Autowired
	private ConnexionInscriptionDao connexionInscriptionDao;
	
	@Autowired
	private LikesDao likesDao;
	
	@RequestMapping(value="/modifProfil", method=RequestMethod.POST)
	public String modifProfil(@ModelAttribute(name="modifProfilForm") User user, 
							  @RequestParam("oldPassword") String oldPassword, 
							  @RequestParam("password2") String password2,
							  HttpSession session, Model model) {
		
		if (session==null) {return "index";}
		String mail = user.getMail();
		String username = user.getUsername();
		String newPassword = user.getPassword();
		int id_user = Integer.parseInt((String) session.getAttribute("id_user"));
		String actualPassword = connexionInscriptionDao.passwordUser(id_user);
		
		if (!actualPassword.equals(oldPassword)) {
			model.addAttribute("error","le mot de passe ne correspond pas à l'ancien");
			return "parameters";
		}
		else if(!newPassword.equals(password2)) {
			model.addAttribute("error","la vérification de mot de passe est invalide");
			return "parameters";
		}
		else if(newPassword.equals(oldPassword)) {
			model.addAttribute("error","le mot de passe rentré correspond à l'ancien");
			return "parameters";
		}
		connexionInscriptionDao.modifProfil(mail, newPassword, username, id_user);
		model.addAttribute("error","Tout a fonctionné à merveille !!");
		model.addAttribute("notifs", likesDao.notifs(id_user));
		return "parameters";
	}
	
	@RequestMapping(value="/changePhoto", method=RequestMethod.POST)
	public String changePhoto(@ModelAttribute(name="changePhotoForm") User user,
								HttpSession session) {
			if (session==null) {return "index";}
			String image = user.getImage();
			int id_user = Integer.parseInt((String) session.getAttribute("id_user"));
			connexionInscriptionDao.modifPhoto(image, id_user);
			
		return "parameters";
	}
}
