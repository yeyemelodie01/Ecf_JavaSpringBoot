package com.cda.PayYouPayMe.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.cda.PayYouPayMe.model.Utilisateur;
import com.cda.PayYouPayMe.repository.UtilisateurRepository;

@Service
public class UtilisateurService {

	private final UtilisateurRepository utilisateurRepository;
    private final AuthentificationService authService;
	private final PasswordEncoder passwordEncoder;

	public UtilisateurService(UtilisateurRepository utilisateurRepository,
                              AuthentificationService authService, PasswordEncoder passwordEncoder) {
		this.utilisateurRepository = utilisateurRepository;
		this.authService = authService;
        this.passwordEncoder = passwordEncoder;
    }

    public Utilisateur getCurrentUser() {
        String username = authService.getCurrentUsername();
        if (username != null) {
            Optional<Utilisateur> utilisateur = utilisateurRepository.findByUsername(username);
            return utilisateur.orElse(null);
        }
        return null;
    }
	
	
	public List<Utilisateur> getAllUtilisateurs() {
		return this.utilisateurRepository.findAll();
	}

	public void createUser(Utilisateur newUser) {
		utilisateurRepository.save(newUser);
	}
	
	public void updateUser(Utilisateur userToSave) {
		Utilisateur userToModify = getCurrentUser();
		userToModify.setFirstName(userToSave.getFirstName());
		userToModify.setLastName(userToSave.getLastName());
		userToModify.setEmail(userToSave.getEmail());
		utilisateurRepository.save(userToModify);
	}

	public void addUserToContactList(String userNameToAdd) {
		Utilisateur userConnected = getCurrentUser();
		Utilisateur userToAdd = getUserByUserName(userNameToAdd);
		if(userToAdd!=null
				&& userToAdd!= userConnected
				&& !userConnected.getContact().contains(userToAdd)) {
			userConnected.getContact().add(userToAdd);
			utilisateurRepository.save(userConnected);
		}
	}
	
	public Utilisateur getUserByUserName(String usernameToFind) {
		return utilisateurRepository.findByUsername(usernameToFind).orElse(null);
	}

	public void deleteContactById(Integer id) {
		Utilisateur userConnected = getCurrentUser();
		Utilisateur contactToDelete = utilisateurRepository.findById(id).orElse(null);
		if(contactToDelete!=null) {
			userConnected.getContact().remove(contactToDelete);
		}
		utilisateurRepository.save(userConnected);
		
	}

	public void suspendAccount() {
		Utilisateur userConnected = getCurrentUser();
		userConnected.setRole("SUSPEND");
		utilisateurRepository.save(userConnected);		
	}

	public void suspendAccountWithId(int id) {
		Utilisateur userToSuspend = utilisateurRepository.findById(id).get();
		userToSuspend.setActive(false);
		userToSuspend.setRole("SUSPEND");
		utilisateurRepository.save(userToSuspend);
		
	}

	public void confirmerUser(int id) {
		Utilisateur userToConfirm = utilisateurRepository.getById(id);
		userToConfirm.setConfirm(true);
		utilisateurRepository.save(userToConfirm);
		
	}

	public void addNewUser(String userName, String password) {
		Utilisateur userToSave = new Utilisateur();
		userToSave.setUsername(userName);
		userToSave.setPassword(passwordEncoder.encode(password));
		userToSave.setActive(true);
		userToSave.setBalance(0f);
		userToSave.setRole("USER");
		userToSave.setConfirm(false);
		utilisateurRepository.save(userToSave);
	}
}
