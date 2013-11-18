package fr.sigl.imoe.jsf2.delegate;

import java.io.Serializable;
import java.util.List;

import fr.sigl.imoe.jsf2.dto.Groupe;

public interface GroupeServiceDelegate extends Serializable {
	
	public List<Groupe> listerTousGroupes();
	public Groupe retrouverGroupeParId(Integer id);
	public Groupe retrouverGroupeParNom(String nom);
	public Groupe sauvegarderGroupe(Groupe groupe);

}
