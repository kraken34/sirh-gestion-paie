package dev.paie.service;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Grade;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.util.PaieUtils;

@Service
public class CalculerRemunerationServiceSimple implements CalculerRemunerationService {


//	@Autowired
//	private PaieUtils paieUtils;
	
	@Override
	public ResultatCalculRemuneration calculer(BulletinSalaire bulletin) {
		
		PaieUtils paieUtils = new PaieUtils();
		
		ResultatCalculRemuneration resultat = new ResultatCalculRemuneration();
		
		
		//Salaire de base :
		BigDecimal salaireDeBase = bulletin.getRemunerationEmploye().getGrade().getNbHeuresBase().multiply(
				bulletin.getRemunerationEmploye().getGrade().getTauxBase());
		
		
		resultat.setSalaireDeBase(paieUtils.formaterBigDecimal(salaireDeBase));
		salaireDeBase = new BigDecimal(resultat.getSalaireDeBase()); //On conserve l'arrondi

		//Salaire brut : salaire de base + primes :
		BigDecimal salaireBrut = salaireDeBase.add(bulletin.getPrimeExceptionnelle());
		
		resultat.setSalaireBrut(paieUtils.formaterBigDecimal(salaireBrut));
		salaireBrut =new BigDecimal(resultat.getSalaireBrut()); //On conserve l'arrondi

		//Total retenue salariale
	    BigDecimal totalRetenueSalarial = salaireBrut.multiply(bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisations()
				.stream().filter(c -> (c.getTauxSalarial() != null)&&(!c.getImposable())).map(c -> c.getTauxSalarial()).reduce((bd1,bd2) -> bd1.add(bd2)).orElse(new BigDecimal("0")));
	    resultat.setTotalRetenueSalarial(paieUtils.formaterBigDecimal(totalRetenueSalarial));
	    totalRetenueSalarial = new BigDecimal(resultat.getTotalRetenueSalarial());
	    

		//Total Cotisation Patronale
	    BigDecimal totalCotisationsPatronales= salaireBrut.multiply(bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisations()
				.stream()
				.filter(c -> (c.getTauxPatronal() != null)&&(!c.getImposable()))
				.map(c -> c.getTauxPatronal())
				.reduce((bd1,bd2) -> bd1.add(bd2))
				.orElse(new BigDecimal("0")));
	    resultat.setTotalCotisationsPatronales(paieUtils.formaterBigDecimal(totalCotisationsPatronales));
	    totalCotisationsPatronales = new BigDecimal(resultat.getTotalCotisationsPatronales());
	    

		//Net imposable
	    BigDecimal netImposable = salaireBrut.subtract(totalRetenueSalarial);
	    resultat.setNetImposable(paieUtils.formaterBigDecimal(netImposable));
	    netImposable = new BigDecimal(resultat.getNetImposable());

		//Net à payer
	    BigDecimal netAPayer = netImposable.subtract(
	    		salaireBrut.multiply(bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisations()
	    				.stream()
	    				.filter(c -> (c.getTauxSalarial() != null)&&(c.getImposable()))
	    				.map(c -> c.getTauxSalarial())
	    				.reduce((bd1,bd2) -> bd1.add(bd2))
	    				.orElse(new BigDecimal("0"))));
	    resultat.setNetAPayer(paieUtils.formaterBigDecimal(netAPayer));
		
		return resultat;
	}
    
}