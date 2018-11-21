package dev.paie.service;

import java.util.List;
import java.util.Map;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.ResultatCalculRenumeration;

public interface CalculRenumerationService {
	
	ResultatCalculRenumeration calculer(BulletinSalaire bulletin);
	Map<BulletinSalaire, ResultatCalculRenumeration> calculerTout();

	}


