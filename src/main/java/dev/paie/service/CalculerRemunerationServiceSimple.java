package dev.paie.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Cotisation;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.util.PaieUtils;

@Service

public class CalculerRemunerationServiceSimple implements CalculerRemunerationService {

	@Override
	public ResultatCalculRemuneration calculer(BulletinSalaire bulletin) {
		// TODO Auto-generated method stub

		ResultatCalculRemuneration res = new ResultatCalculRemuneration();
		PaieUtils pU = new PaieUtils();
//		TOTAL_RETENUE_SALARIALE = SOMME(COTISATION_NON_IMPOSABLE.TAUX_SALARIAL*SALAIRE_BRUT)
//		TOTAL_COTISATIONS_PATRONALES = SOMME(COTISATION_NON_IMPOSABLE.TAUX_PATRONAL*SALAIRE_BRUT)
//		NET_IMPOSABLE = SALAIRE_BRUT - TOTAL_RETENUE_SALARIALE
//		NET_A_PAYER = NET_IMPOSABLE - SOMME(COTISATION_IMPOSABLE.TAUX_SALARIAL*SALAIRE_BRUT)
		BigDecimal salaireBase = bulletin.getRemunerationEmploye().getGrade().getNbHeuresBase()
				.multiply(bulletin.getRemunerationEmploye().getGrade().getTauxBase());

		BigDecimal salaireBrut = salaireBase.add(bulletin.getPrimeExceptionnelle());

		BigDecimal totalRetenuSalariale = new BigDecimal("0.000");
		int taille = bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisations().size();
		for (int i = 0; i < taille; i++) {
			Cotisation a = bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisations().get(i);
			if (a.getImposable().equals(false)) {
				totalRetenuSalariale.equals(a.getTauxSalarial().multiply(salaireBrut).add(totalRetenuSalariale));
			}
		}
	
		res.setSalaireBrut(pU.formaterBigDecimal(salaireBrut));

		return null;
	}

}