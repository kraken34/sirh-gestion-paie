package dev.paie.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import dev.paie.entite.BulletinSalaire;

import dev.paie.entite.ResultatCalculRenumeration;

@Service
public class CalculerRemunerationServiceSimple implements CalculRenumerationService {

	@Override
	public ResultatCalculRenumeration calculer(BulletinSalaire bulletin) {
		// TODO Auto-generated method stub

		ResultatCalculRenumeration res = new ResultatCalculRenumeration();

		// SALAIRE_BASE = GRADE.NB_HEURES_BASE * GRADE.TAUX_BASE

		BigDecimal salaireBase = bulletin.getRemunerationEmploye().getGrade().getNbHeuresBase()
				.multiply(bulletin.getRemunerationEmploye().getGrade().getTauxBase());

		// SALAIRE_BRUT = SALAIRE_BASE + PRIME_EXCEPTIONNELLE

		BigDecimal salaireBrut = salaireBase.add(bulletin.getPrimeExceptionnelle());
		
		
		return res;
		
	}
		
		// TOTAL_RETENUE_SALARIALE = SOMME(COTISATION_NON_IMPOSABLE.TAUX_SALARIAL*SALAIRE_BRUT)
		
		//TOTAL_COTISATIONS_PATRONALES = SOMME(COTISATION_NON_IMPOSABLE.TAUX_PATRONAL*SALAIRE_BRUT)
		
				/*BigDecimal totalCotisationsPatronales = salaireBrut.multiply(bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisations().stream()
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
				
				return res;*/
			

	
	}


