package dev.paie.entite;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Utilisateur {

	public enum ROLES {
        ROLE_ADMINISTRATEUR, ROLE_UTILISATEUR
    }
	
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;
	@Column(name="nom_utilisateur")
    private String nomUtilisateur;
	@Column(name="mot_de_passe")
    private String motDePasse;
	@Column(name="est_actif")
    private Boolean estActif;
    @Enumerated(EnumType.STRING)
    private ROLES role;
    
    public Utilisateur() {
	}
    
    public Utilisateur(String n, String m, Boolean a, ROLES r)
    {
    	setNomUtilisateur(n);
    	setMotDePasse(m);
    	setEstActif(a);
    	setRole(r);
    }
	/**
	 * @return the id
	 */
	public Integer getId() {
		return id;
	}
	/**
	 * @param id the id to set
	 */
	public void setId(Integer id) {
		this.id = id;
	}
	/**
	 * @return the nomUtilisateur
	 */
	public String getNomUtilisateur() {
		return nomUtilisateur;
	}
	/**
	 * @param nomUtilisateur the nomUtilisateur to set
	 */
	public void setNomUtilisateur(String nomUtilisateur) {
		this.nomUtilisateur = nomUtilisateur;
	}
	/**
	 * @return the motDePasse
	 */
	public String getMotDePasse() {
		return motDePasse;
	}
	/**
	 * @param motDePasse the motDePasse to set
	 */
	public void setMotDePasse(String motDePasse) {
		this.motDePasse = motDePasse;
	}
	/**
	 * @return the estActif
	 */
	public Boolean getEstActif() {
		return estActif;
	}
	/**
	 * @param estActif the estActif to set
	 */
	public void setEstActif(Boolean estActif) {
		this.estActif = estActif;
	}
	/**
	 * @return the role
	 */
	public ROLES getRole() {
		return role;
	}
	/**
	 * @param role the role to set
	 */
	public void setRole(ROLES role) {
		this.role = role;
	}
    
}
