package dev.paie.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.ServicesConfig;
import dev.paie.entite.Avantage;

@ContextConfiguration(classes = { ServicesConfig.class })
@RunWith(SpringRunner.class)
public class JpaRepositoryTest {

	@Autowired
	private AvantageRepository avantageRepository;

	@Test
	@Transactional
	@Rollback(false)
	public void test_sauvegarder_lister_mettre_a_jour() {
		// TODO sauvegarder un nouvel avantage
		/*
		 * Avantage avantages = new Avantage(); avantages.setNom("Bon stagiaire");
		 * avantages.setMontant(new BigDecimal(10.000)); avantages.setCode("STA");
		 * avantageRepository.save(avantages);
		 */

		// TODO vérifier qu'il est possible de récupérer le nouvel avantage via la
		// méthode findOne
		Avantage find = avantageRepository.findOne(5);
		assertThat(find.getCode(), equalTo("STA"));
		// TODO modifier un avantage
		find.setCode("TROU");
		avantageRepository.save(find);
		// TODO vérifier que les modifications sont bien prises en compte via la méthode
		// findOne
		Avantage follow = avantageRepository.findOne(5);
		assertThat(follow.getCode(), equalTo("TROU"));
	}
}