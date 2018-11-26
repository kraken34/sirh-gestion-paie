package dev.paie.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Cotisation;
import dev.paie.entite.Grade;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.util.PaieUtils;

@Service
public class CalculerRemunerationServiceSimple implements CalculerRemunerationService{

	@Override
	public ResultatCalculRemuneration calculer(BulletinSalaire bulletin) {
		ResultatCalculRemuneration resultCalcul = new ResultatCalculRemuneration();
		
		Grade grade = bulletin.getRemunerationEmploye().getGrade();
		BigDecimal SALAIRE_BASE = grade.getNbHeuresBase().multiply(grade.getTauxBase());
		BigDecimal SALAIRE_BRUT = SALAIRE_BASE.add(bulletin.getPrimeExceptionnelle());
		resultCalcul.setSalaireDeBase(PaieUtils.formaterBigDecimal(SALAIRE_BASE));
		resultCalcul.setSalaireBrut(PaieUtils.formaterBigDecimal(SALAIRE_BRUT));		
		
		BigDecimal TOTAL_RETENUE_SALARIALE = new BigDecimal(0.0);
        for (Cotisation cotisation : bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisations()) {
            if (!cotisation.getImposable()) {
                if (cotisation.getTauxSalarial() != null) {
                    TOTAL_RETENUE_SALARIALE = TOTAL_RETENUE_SALARIALE
                            .add(cotisation.getTauxSalarial().multiply(SALAIRE_BRUT));
                }
            }
        }
        resultCalcul.setTotalRetenueSalarial(PaieUtils.formaterBigDecimal(TOTAL_RETENUE_SALARIALE));
        TOTAL_RETENUE_SALARIALE=new BigDecimal(PaieUtils.formaterBigDecimal(TOTAL_RETENUE_SALARIALE));

        BigDecimal TOTAL_COTISATIONS_PATRONALES = new BigDecimal(0.0);
        for (Cotisation cotisation : bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisations()) {
            if (!cotisation.getImposable()) {
                if (cotisation.getTauxPatronal() != null) {
                    TOTAL_COTISATIONS_PATRONALES = TOTAL_COTISATIONS_PATRONALES
                            .add(cotisation.getTauxPatronal().multiply(SALAIRE_BRUT));
                }
            }
        }
        resultCalcul.setTotalCotisationsPatronales(PaieUtils.formaterBigDecimal(TOTAL_COTISATIONS_PATRONALES));

        BigDecimal NET_IMPOSABLE = SALAIRE_BRUT.subtract(TOTAL_RETENUE_SALARIALE);
        resultCalcul.setNetImposable(PaieUtils.formaterBigDecimal(NET_IMPOSABLE));
        NET_IMPOSABLE=new BigDecimal(PaieUtils.formaterBigDecimal(NET_IMPOSABLE));


        BigDecimal TOTAL_RETENUE_SALARIALE_imposable = new BigDecimal(0.0);
        for (Cotisation cotisation : bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisations()) {
            if (cotisation.getImposable()) {
                if (cotisation.getTauxSalarial() != null) {
                    TOTAL_RETENUE_SALARIALE_imposable = TOTAL_RETENUE_SALARIALE_imposable
                            .add(cotisation.getTauxSalarial().multiply(SALAIRE_BRUT));
                }
            }
        }

        BigDecimal NET_A_PAYER = NET_IMPOSABLE.subtract(TOTAL_RETENUE_SALARIALE_imposable);
        resultCalcul.setNetAPayer(PaieUtils.formaterBigDecimal(NET_A_PAYER));
		
		return resultCalcul;
	}
	
}
