package dev.paie.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

import dev.paie.domain.Cotisation;

public class CotisationServiceJpa implements CotisationService {

	@Override
	public List<Cotisation> lister() {
		return Arrays.asList(new Cotisation("COTIS_1"), new Cotisation("COTIS_2"));
	}

	@Override
	public Cotisation sauver(String code, String libelle, Boolean imposable, BigDecimal tauxPratonal,
			BigDecimal tauxSalarial) {
		
	
		return null;
	}

}
