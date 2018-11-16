package dev.paie;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

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
        //sauvegarder une nouvelle cotisation
		Cotisation c = new Cotisation();
		em.persist(c);
		c.setCode("TAX");
		c.setImposable(true);
		c.setLibelle("Taxe pour bien te niquer");
		c.setTauxSalarial(new BigDecimal(0.000000456));
		c.setTauxPatronal(new BigDecimal(84.487));
        // TODO vérifier qu'il est possible de récupérer la nouvelle cotisation
		
        // TODO modifier une cotisation

        // TODO vérifier que les modifications sont bien prises en compte
    }

}
