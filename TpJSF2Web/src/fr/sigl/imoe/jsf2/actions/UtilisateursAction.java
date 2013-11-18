package fr.sigl.imoe.jsf2.actions;

import java.io.Serializable;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import fr.sigl.imoe.jsf2.delegate.GroupeServiceDelegate;
import fr.sigl.imoe.jsf2.delegate.UtilisateursServiceDelegate;
import fr.sigl.imoe.jsf2.dto.Groupe;
import fr.sigl.imoe.jsf2.dto.Utilisateur;

@SessionScoped
@Named("utilisateursAction")
public class UtilisateursAction implements Serializable {

	/**
	 * Injection du service pour la gestion des groupes.
	 */
	@Inject
	private UtilisateursServiceDelegate service;

	/**
	 * Liste d'utilisateurs pour l'IHM
	 */
	private List<Utilisateur> utilisateurs;

	private static final Logger LOGGER = Logger.getLogger(UtilisateursAction.class.getName());
	private Utilisateur utilisateurEnEdition; 


	public Utilisateur getUtilisateurEnEdition() {
		return utilisateurEnEdition;
	}

	public void setUtilisateurEnEdition(Utilisateur utilisateurEnEdition) {
		this.utilisateurEnEdition = new Utilisateur();

	}

	@PostConstruct
	public void init() {
		utilisateurs = service.listerTousUtilisateurs();
	}

	public List<Utilisateur> getGroupes() {
		return utilisateurs;        
	}

	public String edit(String login) {
		Utilisateur u = service.retrouverUtilisateurParLogin(login);
		setUtilisateurEnEdition(u);
		return null;    
	}

	public String validerEdition() {
		try{
			service.sauvegarderUtilisateur(utilisateurEnEdition);
			utilisateurs= service.listerTousUtilisateurs();

			String msg = "Groupe " + utilisateurEnEdition.getLogin() + " correctement mis à jour.";
			utilisateurEnEdition = null;
			FacesContext.getCurrentInstance().addMessage(null, 
														 new FacesMessage(FacesMessage.SEVERITY_INFO, msg, msg));
		}
		catch(Exception e)
		{
			String msg = "Impossible de sauvegarder. Cause : " + e.getMessage();
			LOGGER.log(Level.SEVERE, msg);
			FacesContext.getCurrentInstance().addMessage(null, 
					new FacesMessage(FacesMessage.SEVERITY_ERROR, msg, msg));
		}
		return null;
	}

	public String annulerEdition() {
		utilisateurs = service.listerTousUtilisateurs();
		utilisateurEnEdition = null;
		return null;
	}
}
