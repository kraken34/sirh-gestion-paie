package dev.paie.service;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.ServicesConfig;
import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.service.CalculerRemunerationService;
import dev.paie.service.JeuxDeDonneesConfig;

//Sélection des classes de configuration Spring à utiliser lors du test

@ContextConfiguration(classes = { ServicesConfig.class, JeuxDeDonneesConfig.class })

//Configuration JUnit pour que Spring prenne la main sur le cycle de vie du test

@RunWith(SpringRunner.class)

public class CalculerRemunerationServiceSimpleTest {

	@Autowired
	private CalculerRemunerationService remunerationService;
	
	@Autowired
	private BulletinSalaire bulletin1;
	
	@Test
	public void test_calculer() {
		
		/* SALAIRE_BASE = GRADE.NB_HEURES_BASE * GRADE.TAUX_BASE

				SALAIRE_BRUT = SALAIRE_BASE + PRIME_EXCEPTIONNELLE

				TOTAL_RETENUE_SALARIALE = SOMME(COTISATION_NON_IMPOSABLE.TAUX_SALARIAL*SALAIRE_BRUT)

				TOTAL_COTISATIONS_PATRONALES = SOMME(COTISATION_NON_IMPOSABLE.TAUX_PATRONAL*SALAIRE_BRUT)

				NET_IMPOSABLE = SALAIRE_BRUT - TOTAL_RETENUE_SALARIALE

				NET_A_PAYER = NET_IMPOSABLE - SOMME(COTISATION_IMPOSABLE.TAUX_SALARIAL*SALAIRE_BRUT) */
		
		
		ResultatCalculRemuneration resultat = remunerationService.calculer(bulletin1);
		assertThat(resultat.getSalaireBrut(), equalTo("2683.30"));
		assertThat(resultat.getTotalRetenueSalarial(), equalTo("517.08"));
		assertThat(resultat.getTotalCotisationsPatronales(), equalTo("1096.13"));
		assertThat(resultat.getNetImposable(), equalTo("2166.22"));
		assertThat(resultat.getNetAPayer(), equalTo("2088.41"));

	}

}
