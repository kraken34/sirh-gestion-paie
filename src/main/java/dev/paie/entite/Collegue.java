package dev.paie.entite;

import org.springframework.web.client.RestTemplate;

public class Collegue {

	private String matricule;
	private String nom;
	private String prenom;
	private String email;
	private String sexe;
	private String adresse;

	public Collegue(String matricule, String nom, String prenom, String email, String sexe, String adresse) {
		this.matricule = matricule;
		this.nom = nom;
		this.prenom = prenom;
		this.email = email;
		this.sexe = sexe;
		this.adresse = adresse;
	}
	
	public Collegue() {
		
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}

	public String getPrenom() {
		return prenom;
	}

	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSexe() {
		return sexe;
	}

	public void setSexe(String sexe) {
		this.sexe = sexe;
	}

	public String getAdresse() {
		return adresse;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	
	

	
}
