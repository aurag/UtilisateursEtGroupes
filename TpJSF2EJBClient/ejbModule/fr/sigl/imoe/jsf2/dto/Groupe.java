package fr.sigl.imoe.jsf2.dto;

import java.io.Serializable;

public class Groupe implements Serializable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4995259856352441573L;
	private Integer _id;
	private String _nom;
	private String _description;
	
	public Integer getId() {
		return _id;
	}
	public void setId(Integer _id) {
		this._id = _id;
	}
	public String getNom() {
		return _nom;
	}
	public void setNom(String _nom) {
		this._nom = _nom;
	}
	public String getDescription() {
		return _description;
	}
	public void setDescription(String _description) {
		this._description = _description;
	}

}
