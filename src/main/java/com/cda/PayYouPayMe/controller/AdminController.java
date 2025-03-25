package com.cda.PayYouPayMe.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.cda.PayYouPayMe.service.MessageService;
import com.cda.PayYouPayMe.service.TransactionService;
import com.cda.PayYouPayMe.service.UtilisateurService;

@Controller
@RequestMapping("/admin")
public class AdminController {

    private static final Logger logger = LoggerFactory.getLogger(AdminController.class);
	private final MessageService messageService;
	private final TransactionService transactionService;
	private final UtilisateurService utilisateurService;
	
	public AdminController(MessageService messageService,
			TransactionService transactionService,
			UtilisateurService utilisateurService) {
		this.messageService = messageService;
		this.transactionService = transactionService;
		this.utilisateurService = utilisateurService;
	}
	
	@GetMapping("/")
	public String getAdminPage(Model model) {
        logger.info("Connect to allDatas!");

		model.addAttribute("messages", messageService.getAllMessages());
		model.addAttribute("transactions", transactionService.getAllTransactions());
		model.addAttribute("utilisateurs", utilisateurService.getAllUtilisateurs());
		return "dashboard";
	}
	
	@PostMapping("/answermessage")
	public String answerMessage(Model model,  @RequestParam int id,
			@RequestParam String content) {
		messageService.answerMessage(id, content);

		model.addAttribute("messages", messageService.getAllMessages());
		model.addAttribute("transactions", transactionService.getAllTransactions());
		model.addAttribute("utilisateurs", utilisateurService.getAllUtilisateurs());
		return "dashboard";
	}
	
	@PostMapping("/suspenduser")
	public String suspendUser(Model model, @RequestParam int id) {
		utilisateurService.suspendAccountWithId(id);
		
		model.addAttribute("messages", messageService.getAllMessages());
		model.addAttribute("transactions", transactionService.getAllTransactions());
		model.addAttribute("utilisateurs", utilisateurService.getAllUtilisateurs());
		return "dashboard";
	}
	
	@PostMapping("/confirmuser")
	public String confirmerUser(Model model, @RequestParam int id) {
		utilisateurService.confirmerUser(id);
		model.addAttribute("messages", messageService.getAllMessages());
		model.addAttribute("transactions", transactionService.getAllTransactions());
		model.addAttribute("utilisateurs", utilisateurService.getAllUtilisateurs());
		return "dashboard";
	}
	
	@PostMapping("/validatetransaction")
	public String validateTransaction(Model model, @RequestParam int id) {
		transactionService.validateTransaction(id);
		model.addAttribute("messages", messageService.getAllMessages());
		model.addAttribute("transactions", transactionService.getAllTransactions());
		model.addAttribute("utilisateurs", utilisateurService.getAllUtilisateurs());
		return "dashboard";
	}
	
	@PostMapping("/rejecttransaction")
	public String rejeterTransaction(Model model, @RequestParam int id) {
		transactionService.rejectTransaction(id);
		model.addAttribute("messages", messageService.getAllMessages());
		model.addAttribute("transactions", transactionService.getAllTransactions());
		model.addAttribute("utilisateurs", utilisateurService.getAllUtilisateurs());
		return "dashboard";
	}
}
