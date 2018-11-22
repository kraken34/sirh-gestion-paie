package dev.paie.entite;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
public class RemunerationEmploye {
	@Id
	@GeneratedValue
	private Integer id;
	private String matricule;
	@ManyToOne
	private Entreprise entreprise;
	@ManyToOne
	@JoinColumn(name = "profil_remunuration")
	private ProfilRemuneration profilRemuneration;
	@ManyToOne
	private Grade grade;

	private LocalDateTime date;

	public String getDateFormat() {
		DateTimeFormatter format = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm");
		return getDate().format(format);
	}

	public LocalDateTime getDate() {
		return date;
	}

	public void setDate(LocalDateTime date) {
		this.date = date;
	}

	public String getMatricule() {
		return matricule;
	}

	public void setMatricule(String matricule) {
		this.matricule = matricule;
	}

	public Entreprise getEntreprise() {
		return entreprise;
	}

	public void setEntreprise(Entreprise entreprise) {
		this.entreprise = entreprise;
	}

	public ProfilRemuneration getProfilRemuneration() {
		return profilRemuneration;
	}

	public void setProfilRemuneration(ProfilRemuneration profilRemuneration) {
		this.profilRemuneration = profilRemuneration;
	}

	public Grade getGrade() {
		return grade;
	}

	public void setGrade(Grade grade) {
		this.grade = grade;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

}
