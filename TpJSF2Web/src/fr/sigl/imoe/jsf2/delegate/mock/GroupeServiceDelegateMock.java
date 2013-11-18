package fr.sigl.imoe.jsf2.delegate.mock;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Alternative;

import fr.sigl.imoe.jsf2.delegate.GroupeServiceDelegate;
import fr.sigl.imoe.jsf2.dto.Groupe;

@Alternative
public class GroupeServiceDelegateMock implements GroupeServiceDelegate{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2251361121918457349L;

	List<Groupe> groupes;
	
	private int identifiant = 0;
	
	@PostConstruct
	public void init() {
		Groupe groupeAdmin = new Groupe();
		groupeAdmin.setId(identifiant++);
		groupeAdmin.setNom("Administrateur");
		groupeAdmin.setDescription("Le groupe des administrateurs");
		groupes.add(groupeAdmin);
	}
	
	public GroupeServiceDelegateMock() {
		groupes = new ArrayList<Groupe>();
	}
	
	@Override
	public List<Groupe> listerTousGroupes() {
		return groupes;
	}

	@Override
	public Groupe retrouverGroupeParId(Integer id) {
		for (Groupe g : groupes)
		{
			if (g.getId() == id)
				return g;
		}
		
		return null;
	}

	@Override
	public Groupe retrouverGroupeParNom(String nom) {
		for (Groupe g : groupes)
		{
			if (g.getNom().equals(nom))
				return g;
		}
		
		return null;
	}

	@Override
	public Groupe sauvegarderGroupe(Groupe groupe) {
		boolean inCreation = false;
	    Groupe groupeExistant = null;
	    Groupe groupeASauvegarder = null;
	    if (groupe.getId() != null) {
	        groupeExistant = retrouverGroupeParId(groupe.getId());
	        if (groupeExistant == null) {
	            throw new IllegalStateException ("Edition d'un groupe inexistant dans le bouch");
	        }
	        else
	        	groupeASauvegarder = groupeExistant;
	    }
	    else {
	        groupeASauvegarder = new Groupe();
	        groupeASauvegarder.setId(identifiant++);
	    }
	    groupeASauvegarder.setNom(groupe.getNom());
	    groupeASauvegarder.setDescription(groupe.getDescription());
	    if (inCreation) {
	        groupes.add(groupeASauvegarder);
	    }
	    return groupeASauvegarder;
	}

}
