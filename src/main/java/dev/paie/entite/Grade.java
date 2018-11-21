package dev.paie.entite;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import dev.paie.util.PaieUtils;

@Entity
public class Grade {

	@Id
	private Integer id;
	private String code;
	@Column(name="nb_heures_base")
	private BigDecimal nbHeuresBase;
	@Column(name="taux_base")
	private BigDecimal tauxBase;
	
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public BigDecimal getNbHeuresBase() {
		return nbHeuresBase;
	}
	public void setNbHeuresBase(BigDecimal nbHeuresBase) {
		this.nbHeuresBase = nbHeuresBase;
	}
	public BigDecimal getTauxBase() {
		return tauxBase;
	}
	public void setTauxBase(BigDecimal tauxBase) {
		this.tauxBase = tauxBase;
	}
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getLibelle() {
		
		PaieUtils paieUtil = new PaieUtils();
		BigDecimal income = nbHeuresBase.multiply(tauxBase.multiply(new BigDecimal(12)));
		
		String resultLibelle = this.code + " - " + paieUtil.formaterBigDecimal(income) + "$ / an";
		
		return resultLibelle;
	}
	
	

}
