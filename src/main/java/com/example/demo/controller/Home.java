package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.example.demo.dao.ConnexionInscriptionDao;

@Controller
public class Home {

	@Autowired
	private ConnexionInscriptionDao connexionInscriptionDao;
	
	@RequestMapping(value="/parameters", method=RequestMethod.GET)
	public String redirectParameters() {
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
}
