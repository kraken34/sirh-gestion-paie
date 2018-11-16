package dev.paie.util;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.DataSourceMySQLConfig;
import dev.paie.config.JpaConfig;
import dev.paie.entite.Cotisation;
@ContextConfiguration(classes = { JpaConfig.class, DataSourceMySQLConfig.class })
@RunWith(SpringRunner.class) 
public class JpaTest {

    @PersistenceContext EntityManager em;

    @Test
    @Transactional
    @Rollback(false)
    public void test_sauvegarder_lister_mettre_a_jour() {
        // TODO sauvegarder une nouvelle cotisation
    	Cotisation cotisations = new Cotisation();
    	cotisations.setImposable(false);
    	cotisations.setTauxSalarial(new BigDecimal(0.025));
    	cotisations.setCode("TAX");
    	em.persist(cotisations);
    	
    
        // TODO vérifier qu'il est possible de récupérer la nouvelle cotisation
    	//em.createQuery("SELECT code FROM cotisation");

        // TODO modifier une cotisation

        // TODO vérifier que les modifications sont bien prises en compte
    }
}