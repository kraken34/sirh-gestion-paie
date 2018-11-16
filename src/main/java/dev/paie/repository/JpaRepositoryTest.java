package dev.paie.repository;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.ServicesConfig;
import dev.paie.entite.Avantage;

// TODO compléter la configuration
@ContextConfiguration(classes = { ServicesConfig.class })
@RunWith(SpringRunner.class)
public class JpaRepositoryTest {

	@Autowired
	private AvantageRepository avantageRepository;

	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
		// TODO sauvegarder un nouvel avantage
		Avantage a = new Avantage();
		a.setCode("c");
		a.setMontant(new BigDecimal("154"));
		a.setNom("ccc");
		avantageRepository.save(a);

		// TODO vérifier qu'il est possible de récupérer le nouvel avantage via la
		// méthode findOne
		Avantage b = avantageRepository.findOne(9);
		
		// TODO modifier un avantage
		b.setMontant(new BigDecimal("1000"));
		avantageRepository.save(b);

		// TODO vérifier que les modifications sont bien prises en compte via la méthode
		// findOne
		avantageRepository.findOne(9);
	}
}