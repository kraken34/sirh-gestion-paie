package dev.paie.repository;

import java.math.BigDecimal;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.ServicesConfig;
import dev.paie.entite.Avantage;


@ContextConfiguration(classes = {ServicesConfig.class})
@RunWith(SpringRunner.class)
public class JpaRepositoryTest {
	
    @Autowired private AvantageRepository avantageRepository;
    
    @Test
    public void test_sauvegarder_lister_mettre_a_jour() {
        // TODO sauvegarder un nouvel avantage
    	Avantage avantage = new Avantage();
    	avantage.setId(22);
    	avantage.setCode("vtb");
    	avantage.setNom("kaczmarek");
    	avantage.setMontant(new BigDecimal("1515454"));
    	//avantageRepository.save(avantage);
    	
    	
        // TODO vérifier qu'il est possible de récupérer le nouvel avantage via la méthode findOne
    	Avantage av = avantageRepository.findOne(22);

        // TODO modifier un avantage
    	avantage.setCode("jshduh");
    	avantageRepository.save(avantage);
    	

        // TODO vérifier que les modifications sont bien prises en compte via la méthode findOne
    	av = avantageRepository.findOne(22); 
    	
    	
    	
    	// Tester new method 
    	List<Avantage> avantageList = avantageRepository.findByCode("jshduh");

    	
    }

}
