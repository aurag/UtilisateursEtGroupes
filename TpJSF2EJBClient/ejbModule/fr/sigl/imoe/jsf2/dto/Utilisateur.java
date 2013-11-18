package fr.sigl.imoe.jsf2.dto;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Utilisateur implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6325343398110076926L;
	
	public Utilisateur()
	{
		setGroupes(new ArrayList<Groupe>());
	}
	
	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}
	
	@Override
	public boolean equals(Object arg0) {
		// TODO Auto-generated method stub
		if (arg0 instanceof Utilisateur)			
			return getLogin().equals(((Utilisateur)arg0).getLogin());
		else
			return false;
	}
	
	private String _login;
	public String getLogin() {
		return _login;
	}
	public void setLogin(String _login) {
		this._login = _login;
	}
	public String getPassword() {
		return _password;
	}
	public void setPassword(String _password) {
		this._password = _password;
	}
	public List<Groupe> getGroupes() {
		return _groupes;
	}
	public void setGroupes(List<Groupe> _groupes) {
		this._groupes = _groupes;
	}

	private String _password;
	private List<Groupe> _groupes;
	
	
}
