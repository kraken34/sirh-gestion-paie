package dev.paie.service;

import java.math.BigDecimal;
import java.util.HashMap;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Grade;
import dev.paie.entite.ResultatCalculRenumeration;
import dev.paie.repository.BulletinSalaireRepository;
import dev.paie.util.PaieUtils;

@Service
public class CalculerRemunerationServiceSimple implements CalculRenumerationService {
	
	@Autowired BulletinSalaireRepository bullRepo;

	@Override
	public ResultatCalculRenumeration calculer(BulletinSalaire bulletin) {
		
		ResultatCalculRenumeration res = new ResultatCalculRenumeration();
		PaieUtils pu = new PaieUtils();
		Grade grade = bulletin.getRemunerationEmploye().getGrade();
		
		//SALAIRE_BASE = GRADE.NB_HEURES_BASE * GRADE.TAUX_BASE		
		BigDecimal salaireBase = grade.getNbHeuresBase().multiply(grade.getTauxBase());
		salaireBase = new BigDecimal(pu.formaterBigDecimal(salaireBase));
		
		//SALAIRE_BRUT = SALAIRE_BASE + PRIME_EXCEPTIONNELLE
		BigDecimal salaireBrut = salaireBase.add(bulletin.getPrimeExceptionnelle());
		salaireBrut = new BigDecimal(pu.formaterBigDecimal(salaireBrut));
		
		//TOTAL_RETENUE_SALARIALE = SOMME(COTISATION_NON_IMPOSABLE.TAUX_SALARIAL*SALAIRE_BRUT)
		BigDecimal totalCotisationsSalariales = salaireBrut.multiply(bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisations().stream()
				.filter(c -> !c.getImposable() && c.getTauxSalarial() != null)
				.map(c -> c.getTauxSalarial())
				.reduce((bd1,bd2)->bd1.add(bd2)).orElse(new BigDecimal(0)));
		totalCotisationsSalariales = new BigDecimal(pu.formaterBigDecimal(totalCotisationsSalariales));
		
		//TOTAL_COTISATIONS_PATRONALES = SOMME(COTISATION_NON_IMPOSABLE.TAUX_PATRONAL*SALAIRE_BRUT)
		BigDecimal totalCotisationsPatronales = salaireBrut.multiply(bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisations().stream()
				.filter(c -> !c.getImposable() && c.getTauxPatronal() != null)
				.map(c -> c.getTauxPatronal())
				.reduce((bd1,bd2)->bd1.add(bd2)).orElse(new BigDecimal(0)));
		totalCotisationsPatronales = new BigDecimal(pu.formaterBigDecimal(totalCotisationsPatronales));
		
		//NET_IMPOSABLE = SALAIRE_BRUT - TOTAL_RETENUE_SALARIALE
		BigDecimal netImposable = salaireBrut.subtract(totalCotisationsSalariales);
		netImposable = new BigDecimal(pu.formaterBigDecimal(netImposable));

		//NET_A_PAYER = NET_IMPOSABLE - SOMME(COTISATION_IMPOSABLE.TAUX_SALARIAL*SALAIRE_BRUT)
		BigDecimal netAPayer = netImposable.subtract(salaireBrut.multiply(bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisations().stream()
				.filter(c -> c.getImposable() && c.getTauxSalarial() != null)
				.map(c -> c.getTauxSalarial())
				.reduce((bd1,bd2)->bd1.add(bd2)).orElse(new BigDecimal(0))));
		netAPayer = new BigDecimal(pu.formaterBigDecimal(netAPayer));
		
		//Use PaieUtils to round BigDecimal into String
		res.setSalaireDeBase(pu.formaterBigDecimal(salaireBase));
		res.setSalaireBrut(pu.formaterBigDecimal(salaireBrut));
		res.setTotalRetenueSalarial(pu.formaterBigDecimal(totalCotisationsSalariales));
		res.setTotalCotisationsPatronales(pu.formaterBigDecimal(totalCotisationsPatronales));
		res.setNetImposable(pu.formaterBigDecimal(netImposable));
		res.setNetAPayer(pu.formaterBigDecimal(netAPayer));
		
		return res;
	}
	
	@Override
	@Transactional
	public Map<BulletinSalaire, ResultatCalculRenumeration> calculerTout() {
		Map<BulletinSalaire,ResultatCalculRenumeration> calculBulletins = new HashMap<>();
		for (BulletinSalaire b : bullRepo.findAll())
		{
			calculBulletins.put(b, calculer(b));
		}
		
		return calculBulletins;
	}
			
}
	
	


