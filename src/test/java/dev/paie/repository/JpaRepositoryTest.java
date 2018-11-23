package dev.paie.repository;
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

    @Autowired private AvantageRepository avantageRepository;

    @Test
    @Rollback(false)
    public void test_sauvegarder_lister_mettre_a_jour() {
        //sauvegarder un nouvel avantage
    	avantageRepository.save(new Avantage());

        //vérifier qu'il est possible de récupérer le nouvel avantage via la méthode findOne
    	Avantage avantage=avantageRepository.findOne(6);
    	System.out.println(avantage);
    	
        //modifier un avantage
    	avantage.setCode("Bite");
    	avantageRepository.save(avantage);

        // TODO vérifier que les modifications sont bien prises en compte via la méthode findOne
    }
}