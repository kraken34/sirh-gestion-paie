package dev.paie.service;

import java.math.BigDecimal;
import java.util.List;

import dev.paie.domain.Cotisation;

public interface CotisationService {
	
	List<Cotisation> lister();
	
	Cotisation sauver(String codeSaisie, String libelle, Boolean imposable, BigDecimal tauxPratonal, BigDecimal tauxSalarial);
}
