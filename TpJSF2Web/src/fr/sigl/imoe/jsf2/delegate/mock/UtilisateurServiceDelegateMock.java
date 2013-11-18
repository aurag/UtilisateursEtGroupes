package fr.sigl.imoe.jsf2.delegate.mock;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.enterprise.inject.Alternative;
import javax.inject.Inject;


import fr.sigl.imoe.jsf2.delegate.GroupeServiceDelegate;
import fr.sigl.imoe.jsf2.delegate.UtilisateursServiceDelegate;
import fr.sigl.imoe.jsf2.dto.Groupe;
import fr.sigl.imoe.jsf2.dto.Utilisateur;

@Alternative
public class UtilisateurServiceDelegateMock implements UtilisateursServiceDelegate{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2251361121918457349L;

	List<Utilisateur> utilisateurs = new ArrayList<Utilisateur>();

	private int identifiant = 0;
	

	@PostConstruct
	public void init() {
		Utilisateur u = new Utilisateur();
		u.setLogin("fioren_b");
		u.setPassword("xes");
		Groupe g = new Groupe();
		g.setDescription("admins du lab");
		g.setId(42);
		g.setNom("admins");
		List<Groupe> list = new ArrayList<Groupe>();
		list.add(g);
		u.setGroupes(list);
		utilisateurs.add(u);
	}

	public UtilisateurServiceDelegateMock() {
		utilisateurs = new ArrayList<Utilisateur>();
	}

	@Override
	public List<Utilisateur> listerTousUtilisateurs() {
		return utilisateurs;
	}

	@Override
	public Utilisateur retrouverUtilisateurParLogin(String login) {
		for (Utilisateur u : utilisateurs)
		{
			if (u.getLogin().equals(login))
				return u;
		}

		return null;
	}

	@Override
	public Utilisateur sauvegarderUtilisateur(Utilisateur utilisateur) {
		boolean inCreation = false;
		Utilisateur utilisateurExistant = null;
		Utilisateur utilisateurASauvegarder = null;
		if (utilisateur.getLogin() != null) {
			utilisateurExistant = retrouverUtilisateurParLogin(utilisateur.getLogin());
			if (utilisateurExistant == null) {
				throw new IllegalStateException ("Edition d'un groupe inexistant dans le bouch");
			}
			else
				utilisateurASauvegarder = utilisateurExistant;
		}
		else {
			utilisateurASauvegarder = new Utilisateur();
		}
			utilisateurASauvegarder.setLogin(utilisateur.getLogin());
			utilisateurASauvegarder.setPassword(utilisateur.getPassword());
			utilisateurASauvegarder.setGroupes(utilisateur.getGroupes());
		if (inCreation) {
			utilisateurs.add(utilisateurASauvegarder);
		}
		return utilisateurASauvegarder;
	}

}
