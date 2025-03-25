package com.cda.PayYouPayMe.service;

import java.time.LocalDate;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.cda.PayYouPayMe.model.Transaction;
import com.cda.PayYouPayMe.model.Utilisateur;
import com.cda.PayYouPayMe.repository.TransactionRepository;
import com.cda.PayYouPayMe.repository.UtilisateurRepository;

@Service
public class TransactionService {

	private final TransactionRepository transactionRepository;
    private final AuthentificationService authService;
    private final UtilisateurRepository utilisateurRepository;
    private final UtilisateurService utilisateurService;
    
	public TransactionService(TransactionRepository transactionRepository,
			AuthentificationService authService,
			UtilisateurRepository utilisateurRepository,
			UtilisateurService utilisateurService) {
		this.transactionRepository = transactionRepository;
		this.authService = authService;
		this.utilisateurRepository = utilisateurRepository;
		this.utilisateurService = utilisateurService;
	}

	public List<Transaction> getAllTransactions() {
		
		return this.transactionRepository.findAll();
	}
	    
    public List<Transaction> getCurrentUserTransactions() {
        String username = authService.getCurrentUsername();
        if (username != null) {
            return transactionRepository.findAllByUserLogin(username);
        }
        return Collections.emptyList();
    }
    
    public List<Transaction> getUserTransactions(Integer userId) {
        Optional<Utilisateur> utilisateur = utilisateurRepository.findById(userId);
        return utilisateur.map(u -> transactionRepository.findAllByUser(u))
                         .orElse(Collections.emptyList());
    }

	public Boolean createTransaction(String username, Float amount, String content) {
		Utilisateur sender = utilisateurService.getCurrentUser();
		Utilisateur receiver = utilisateurService.getUserByUserName(username);
		
		if(sender.getBalance() > amount && amount > 0 && receiver!=null) {
			Transaction transactionToCreate = new Transaction();
			transactionToCreate.setAmount(amount);
			transactionToCreate.setDate(LocalDate.now());
			transactionToCreate.setMessageContent(content);
			transactionToCreate.setReceiver(receiver);
			transactionToCreate.setSender(sender);
			
			if(sender.isConfirm()) transactionToCreate.setValidate(true);
			
			transactionRepository.save(transactionToCreate);
			receiver.setBalance(receiver.getBalance()+amount);
			sender.setBalance(sender.getBalance()-amount);
			utilisateurService.updateUser(receiver);
			utilisateurService.updateUser(sender);

			return true;
		}
		else {
			return false;
		}
			
	}

	public void validateTransaction(int id) {
		Transaction transactionToValidate = transactionRepository.findById(id).get();
		transactionToValidate.setValidate(true);
		transactionRepository.save(transactionToValidate);
		
	}

	public String rejectTransaction(int id) {
		Transaction transactionToReject = transactionRepository.findById(id).get();
		transactionToReject.setReject(true);
		transactionToReject.setValidate(true);
		
		Utilisateur sender = transactionToReject.getSender();
		Utilisateur receiver = transactionToReject.getReceiver();

		if (transactionToReject.getAmount() > 0) {

			if (receiver.getBalance() > transactionToReject.getAmount()) {
				receiver.setBalance(receiver.getBalance() - transactionToReject.getAmount());
			} else {
				return "Solde insuffisant pour annuler la transaction.";
			}

			sender.setBalance(sender.getBalance() + transactionToReject.getAmount());
			utilisateurService.updateUser(receiver);
			utilisateurService.updateUser(sender);
			transactionRepository.save(transactionToReject);
			return "Transaction annulée avec succès.";
		}
		return "";
	}

	public void reportTransaction(int id) {
		Transaction transactionToReport = transactionRepository.findById(id).get();
		transactionToReport.setReport(true);
		transactionRepository.save(transactionToReport);
	}
}