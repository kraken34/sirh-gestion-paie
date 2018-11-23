package dev.paie.entite;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name ="utilisateur")
public class Utilisateur {
	
	 public enum ROLES {
	        ROLE_ADMINISTRATEUR, ROLE_UTILISATEUR
	    }

	     @Id
		@GeneratedValue(strategy=GenerationType.IDENTITY)
	    private Integer id;
	     
	     @Column (name="nomUtilisateur")
	    private String nomUtilisateur;
	     @Column (name="motDePasse")
	    private String motDePasse;
	    
	    public Integer getId() {
			return id;
		}
		public void setId(Integer id) {
			this.id = id;
		}
		public String getNomUtilisateur() {
			return nomUtilisateur;
		}
		public void setNomUtilisateur(String nomUtilisateur) {
			this.nomUtilisateur = nomUtilisateur;
		}
		public String getMotDePasse() {
			return motDePasse;
		}
		public void setMotDePasse(String motDePasse) {
			this.motDePasse = motDePasse;
		}
		public Boolean getEstActif() {
			return estActif;
		}
		public void setEstActif(Boolean estActif) {
			this.estActif = estActif;
		}
		public ROLES getRole() {
			return role;
		}
		public void setRole(ROLES role) {
			this.role = role;
		}
		private Boolean estActif;
	    private ROLES role;


	

}
