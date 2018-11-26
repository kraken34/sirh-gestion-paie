package dev.paie.entite;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name="profil_remuneration")
public class ProfilRemuneration {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	private String code;
	@ManyToMany
    @JoinTable(
            name = "profil_remuneration_cotisation",
            joinColumns = @JoinColumn(name="profil_remuneration_id", referencedColumnName="id"),
            inverseJoinColumns = @JoinColumn(name="cotisation_id", referencedColumnName="id")
    )
    private List<Cotisation> cotisations;

	
	@ManyToMany
	private List<Avantage> avantages;

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public List<Cotisation> getCotisations() {
		return cotisations;
	}

	public void setCotisations(List<Cotisation> cotisations) {
		this.cotisations = cotisations;
	}

	public List<Avantage> getAvantages() {
		return avantages;
	}

	public void setAvantages(List<Avantage> avantages) {
		this.avantages = avantages;
	}

}
