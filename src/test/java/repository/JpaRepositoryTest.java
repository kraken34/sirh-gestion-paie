package repository;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.ServicesConfig;
import dev.paie.entite.Avantage;
import dev.paie.repository.AvantageRepository;
@ContextConfiguration(classes = {ServicesConfig.class})
@RunWith(SpringRunner.class)
public class JpaRepositoryTest {
	//compléter la configuration

	@Autowired
	private AvantageRepository avantageRepository;

	@Test
	@Rollback(false)
	public void test_sauvegarder_lister_mettre_a_jour() {
		//sauvegarder un nouvel avantage
		Avantage avantage = new Avantage();
		avantage.setId(2);
		avantage.setCode("AVG1");
		avantage.setMontant(new BigDecimal("1500.00"));
		avantage.setNom("avantage1");
		//avantageRepository.save(avantage);  			-- Commenté pour ne pas sauvegarder x fois le même objet
		//vérifier qu'il est possible de récupérer le nouvel avantage via la
		// méthode findOne
		Avantage avg2 = avantageRepository.findOne(2);
			//assertThat(avantage, equalTo(avg2));
		//modifier un avantage
		
		avg2.setCode("AVG2");
		avantageRepository.save(avg2);

		// vérifier que les modifications sont bien prises en compte via la méthode
		// findOne
	}
}
