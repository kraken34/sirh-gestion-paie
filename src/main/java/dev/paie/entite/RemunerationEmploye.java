package dev.paie.entite;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.Table;

@Entity
@Table(name="remuneration_employe")
public class RemunerationEmploye {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	@Column(nullable=false, unique=true)
	private String matricule;
	
	@ManyToOne
	@JoinColumn(name="entreprise_id")
	private Entreprise entreprise;
	
	@ManyToOne
	@JoinColumn(name="profil_remuneration_id")
	private ProfilRemuneration profilRemuneration;
	
	@ManyToOne
	@JoinColumn(name="grade_id")
	private Grade grade;
	
	@Column
	private LocalDateTime dateCreation;
	
	
//	/**
//	 * Constructeur permettant d'initialiser la date de création
//	 * @return
//	 */
//	public RemunerationEmploye() {
//		super();
//		dateCreation = LocalDateTime.now();
//	}
	
	@PrePersist
	public void ajoutDateCreation() {
		this.dateCreation = LocalDateTime.now();
	}
	
	
	
	public String getMatricule() {
		return matricule;
	}
	public LocalDateTime getDateCreation() {
		return dateCreation;
	}
	
//	public void setDateCreation(LocalDateTime dateCreation) {
//		this.dateCreation = dateCreation;
//	}
	
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
