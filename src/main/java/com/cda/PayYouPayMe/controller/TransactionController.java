package com.cda.PayYouPayMe.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cda.PayYouPayMe.service.TransactionService;
import com.cda.PayYouPayMe.service.UtilisateurService;

import jakarta.validation.Valid;

@Controller
@RequestMapping("/transaction")
public class TransactionController {

    private static final Logger logger = LoggerFactory.getLogger(TransactionController.class);

	private final TransactionService transactionService;
	private final UtilisateurService utilisateurService;
	
	public TransactionController(TransactionService transactionService,
			UtilisateurService utilisateurService) {
		this.transactionService = transactionService;
		this.utilisateurService = utilisateurService;
	}

	@GetMapping("/")
	public String getTransactionPage(Model model) {
		model.addAttribute("utilisateur", utilisateurService.getCurrentUser());
		return "transaction";
	}
	
	@PostMapping("/createtransaction")
	public String createTransaction(Model model,
			@RequestParam String receiver,
			@Valid @RequestParam Float amount,
			@RequestParam String content) {
		logger.info("Tentative de création d'une transaction");
		Boolean isSuccess = transactionService.createTransaction(receiver, amount, content);
		if (!isSuccess){
			model.addAttribute("error", "La transaction a échoué verifier votre solde");
			model.addAttribute("utilisateur", utilisateurService.getCurrentUser());
			return "transaction";
		}
		model.addAttribute("utilisateur", utilisateurService.getCurrentUser());
		model.addAttribute("success", "Transaction éffectué avec succès");
		model.addAttribute("isSuccess", isSuccess);
		return "transaction";
	}

	@PostMapping("/report")
	public String reportTransaction(Model model,
			@RequestParam int id) {
		transactionService.reportTransaction(id);
		model.addAttribute("utilisateur", utilisateurService.getCurrentUser());
		return "transaction";
	}
}
