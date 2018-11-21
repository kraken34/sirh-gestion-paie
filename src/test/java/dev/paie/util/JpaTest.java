package dev.paie.util;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.config.DataSourcePostGreConfig;
import dev.paie.config.JeuxDeDonneesConfig;
import dev.paie.config.JpaConfig;
import dev.paie.config.ServicesConfig;
import dev.paie.entite.Cotisation;


@ContextConfiguration(classes = { JpaConfig.class, DataSourcePostGreConfig.class })
@RunWith(SpringRunner.class)
public class JpaTest {

    @PersistenceContext EntityManager em;

    @Test
    @Transactional
    public void test_sauvegarder_lister_mettre_a_jour() {
        // TODO sauvegarder une nouvelle cotisation
    	
    	Cotisation cot = new Cotisation();
    	cot.setId(14);
    	cot.setCode("azertyu");
    	cot.setLibelle("oui");
    	cot.setTauxPatronal(new BigDecimal("14"));
    	cot.setTauxSalarial(new BigDecimal("15"));
    	em.persist(cot);
    	
    	
        // TODO vérifier qu'il est possible de récupérer la nouvelle cotisation
    	
    	cot = (Cotisation) em.createQuery("from Cotisation").getSingleResult();
    	
        // TODO modifier une cotisation

    	em.createQuery("update Cotisation c SET c.code ='dede' where id=14");
    	
        // TODO vérifier que les modifications sont bien prises en compte
    }
}
