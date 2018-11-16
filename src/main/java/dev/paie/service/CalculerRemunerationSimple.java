/**
 * 
 */
package dev.paie.service;

import java.math.BigDecimal;

import org.springframework.stereotype.Service;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Cotisation;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.util.PaieUtils;

/**
 * @author formation
 *
 */
@Service
public class CalculerRemunerationSimple implements CalculerRemunerationService {

	@Override
	public ResultatCalculRemuneration calculer(BulletinSalaire bulletin) {
		ResultatCalculRemuneration result = new ResultatCalculRemuneration();
		BigDecimal salaire_base = bulletin.getRemunerationEmploye().getGrade().getTauxBase().multiply(bulletin.getRemunerationEmploye().getGrade().getNbHeuresBase());
		PaieUtils pu = new PaieUtils();
		
		BigDecimal salaire_brut = salaire_base.add(bulletin.getPrimeExceptionnelle());
		
		
	BigDecimal totalCotisationSalaraiales =	salaire_brut.multiply(bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisations().stream()
				.filter(c-> c.getTauxSalarial()!=null && !c.getImposable())
				.map(c-> c.getTauxSalarial())
				.reduce((bd1,bd2)->bd1.add(bd2)).orElse(new BigDecimal(0.0)));
	
	
		return result;
	}

}
