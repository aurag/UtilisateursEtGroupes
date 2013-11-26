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
import fr.sigl.imoe.jsf2.dto.Groupe;

@SessionScoped
@Named("groupesAction")
public class GroupesAction implements Serializable {

	/**
	 * Injection du service pour la gestion des groupes.
	 */
	@Inject
	private GroupeServiceDelegate service;

	/**
	 * Liste de groupes pour l'IHM
	 */
	private List<Groupe> groupes;

	private static final Logger LOGGER = Logger.getLogger(GroupesAction.class.getName());
	private Groupe groupeEnEdition; // Faire Getter/Setter



	public Groupe getGroupeEnEdition() {
		return groupeEnEdition;
	}

	public void setGroupeEnEdition(Groupe groupeEnEdition) {
		this.groupeEnEdition = new Groupe();
		this.groupeEnEdition.setDescription(groupeEnEdition.getDescription());
		this.groupeEnEdition.setId(groupeEnEdition.getId());
		this.groupeEnEdition.setNom(groupeEnEdition.getNom());
	}

	@PostConstruct
	public void init() {
		groupes = service.listerTousGroupes();
	}

	public List<Groupe> getGroupes() {
		return groupes;        
	}
	
	public String add() {
		Groupe g = new Groupe();
		setGroupeEnEdition(g);
		return null;    
	}

	public String edit(Integer id) {
		Groupe g = service.retrouverGroupeParId(id);
		setGroupeEnEdition(g);
		return null;    
	}

	public String validerEdition() {
		try{
			service.sauvegarderGroupe(groupeEnEdition);
			groupes= service.listerTousGroupes();

			String msg = "Groupe " + groupeEnEdition.getNom() + " correctement mis à jour.";
			groupeEnEdition = null;
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
		groupes = service.listerTousGroupes();
		groupeEnEdition = null;
		return null;
	}
}
