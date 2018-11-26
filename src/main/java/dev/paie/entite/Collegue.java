package dev.paie.entite;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Collegue {
	String matricule;
	String nom;
	String prenom;
	String email;
	String dateNaissance;
	String sexe;
	String adresse;
	String password;
	String photo;
	String[] subalternes;
	String departement;

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

	public String getDateNaissance() {
		return dateNaissance;
	}

	public void setDateNaissance(String dateNaissance) {
		this.dateNaissance = dateNaissance;
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

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoto() {
		return photo;
	}

	public void setPhoto(String photo) {
		this.photo = photo;
	}

	public String[] getSubalternes() {
		return subalternes;
	}

	public void setSubalternes(String[] subalternes) {
		this.subalternes = subalternes;
	}

	public String getDepartement() {
		return departement;
	}

	public void setDepartement(String departement) {
		this.departement = departement;
	}

	public String getDateNaissanceFormat() {

		DateTimeFormatter dateNaissance = DateTimeFormatter.ofPattern("dd/MM/YYYY");
		LocalDate localDate = localDate.parse(getDateNaissance(), dateNaissance);
		return localDate; // Je dois d'abord passerr la date de naissance en LOCALDATE
	}
}
