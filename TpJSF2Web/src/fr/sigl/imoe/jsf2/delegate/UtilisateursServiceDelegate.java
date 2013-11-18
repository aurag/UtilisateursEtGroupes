package fr.sigl.imoe.jsf2.delegate;

import java.io.Serializable;
import java.util.List;

import fr.sigl.imoe.jsf2.dto.Utilisateur;

public interface UtilisateursServiceDelegate extends Serializable {

	public List<Utilisateur> listerTousUtilisateurs();
	public Utilisateur retrouverUtilisateurParLogin(String login);
	public Utilisateur sauvegarderUtilisateur(Utilisateur utilisateur);
	
}
