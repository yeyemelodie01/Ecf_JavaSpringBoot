package com.cda.PayYouPayMe.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cda.PayYouPayMe.service.UtilisateurService;

@Controller
public class HomeController {

    private final UtilisateurService utilisateurService;
    
    public HomeController(UtilisateurService utilisateurService) {
    	this.utilisateurService = utilisateurService;
    }

	@GetMapping("/")
	public String getHomePage(Model model) {
		if (utilisateurService.getCurrentUser() != null) {
			model.addAttribute("utilisateur", utilisateurService.getCurrentUser());
			return "home";
		}
		return "home";
	}
	
	@PostMapping("/signUp")
	public String signUp(Model model, @RequestParam String userName,
			@RequestParam String password) {
		utilisateurService.addNewUser(userName, password);

		return "home";
	}
}
