package dev.paie.repository;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.config.ServicesConfig;
import dev.paie.entite.Avantage;

@ContextConfiguration(classes = { ServicesConfig.class })
@RunWith(SpringRunner.class)
public class JpaRepositoryTest {

	@Autowired private AvantageRepository avantageRepository;
	
	@Test
	@Transactional
	@Rollback(false)
    public void test_sauvegarder_lister_mettre_a_jour() {
        // sauvegarder un nouvel avantage
		Avantage a = new Avantage();
		a.setCode("BS");
		a.setMontant(new BigDecimal(1500));
		a.setNom("Bon Stagiaire");
		//avantageRepository.save(a);
        // vérifier qu'il est possible de récupérer le nouvel avantage via la méthode findOne
		a = null;
		a = avantageRepository.findOne(5);
		assertThat(a.getCode(), equalTo("BS"));
        // modifier un avantage
		a.setNom("Bonne Secretaire");
		avantageRepository.save(a);
        // vérifier que les modifications sont bien prises en compte via la méthode findOne
		a = null;
		a = avantageRepository.findOne(5);
		assertThat(a.getNom(), equalTo("Bonne Secretaire"));
    }
}
