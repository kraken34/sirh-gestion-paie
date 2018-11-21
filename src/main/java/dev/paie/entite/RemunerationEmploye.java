package dev.paie.entite;

import java.time.LocalDateTime;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name= "remuneration_employe")
public class RemunerationEmploye {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String matricule;
	
	@ManyToOne
	private Entreprise entreprise;
	
	@ManyToOne
	private ProfilRemuneration profilRemuneration;
	
	@ManyToOne
	private Grade grade;
	
	private LocalDateTime pr;
	
	/**
	 * @return the pr
	 */
	public LocalDateTime getPr() {
		return pr;
	}
	/**
	 * @param pr the pr to set
	 */
	public void setPr(LocalDateTime pr) {
		this.pr = pr;
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
	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "RemunerationEmploye [id=" + id + ", matricule=" + matricule + ", entreprise=" + entreprise
				+ ", profilRemuneration=" + profilRemuneration + ", grade=" + grade + "]";
	}


}
