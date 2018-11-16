package dev.paie.repository;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.DataSourcePostgresqlConfig;
import dev.paie.config.JpaConfig;
import dev.paie.config.ServicesConfig;
import dev.paie.entite.Avantage;

//Sélection des classes de configuration Spring à utiliser lors du test
@ContextConfiguration(classes = { DataSourcePostgresqlConfig.class, ServicesConfig.class, JpaConfig.class})

//Configuration JUnit pour que Spring prenne la main sur le cycle de vie du test
@RunWith(SpringRunner.class)
public class JpaRepositoryTest {


    @Autowired private AvantageRepository avantageRepository; 

	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {

		Avantage avantage1 = new Avantage();
		avantage1.setCode("codeAvantage1");
		avantage1.setMontant(new BigDecimal(0.20));
		avantage1.setNom("client Très Fidèle");

		// TODO sauvegarder un nouvel avantage
		avantageRepository.save(avantage1);

		// TODO vérifier qu'il est possible de récupérer le nouvel avantage via la
		// méthode findOne
		
		System.out.println("Le montant de l'avantage de code \"codeAvantage1\" est : "
		+avantageRepository.findOne(avantageRepository.findByCode(avantage1.getCode()).get(0).getId()).getMontant());

		// TODO modifier un avantage
		avantage1.setMontant(new BigDecimal(0.3));
		avantageRepository.save(avantage1);

		// TODO vérifier que les modifications sont bien prises en compte via la méthode
		// findOne
		System.out.println(avantageRepository.findOne(avantage1.getId()));
	}
}
