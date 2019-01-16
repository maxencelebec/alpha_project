package com.example.demo.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dao.ConnexionInscriptionDao;
import com.example.demo.model.User;


@Controller
public class Inscription {
	
	@Autowired
	private ConnexionInscriptionDao connexionInscriptionDao;
	
	@RequestMapping(value="/inscription", method=RequestMethod.POST)
	public String Login(@ModelAttribute(name="inscriptionForm") User user, Model model, 
						@RequestParam("confirmMail") String confirmMail, 
						@RequestParam("confirmPassword") String confirmPassword) {
		
		String mail = user.getMail();
		
		String password = user.getPassword();
		String name = user.getName();
		String surname = user.getSurname();
		String username = user.getUsername();
		String image = user.getImage();
		
		Pattern VALID_EMAIL_ADDRESS_REGEX = Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
		Matcher matcher = VALID_EMAIL_ADDRESS_REGEX .matcher(mail);
		
		if (matcher.find() && mail.equals(confirmMail) && password.equals(confirmPassword)) {
			connexionInscriptionDao.insertBdd(mail,password,name, surname, username, image);
		}
		else {
			return "subscribe";
		}
		return "index";
	}
}




