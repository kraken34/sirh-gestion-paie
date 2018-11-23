package dev.paie;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

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
		c.setCode("TAX");
		c.setImposable(true);
		c.setLibelle("Taxe pour bien te niquer");
		c.setTauxSalarial(new BigDecimal(0.000000456));
		c.setTauxPatronal(new BigDecimal(84.487));
		em.persist(c);
        // vérifier qu'il est possible de récupérer la nouvelle cotisation
		c = null;
		c = em.find(Cotisation.class, 1);
		assertThat(c.getCode(), equalTo("TAX"));
        // modifier une cotisation
		c.setLibelle("Prélevement automatique obligatoire");
		em.merge(c);
        // vérifier que les modifications sont bien prises en compte
		c = null;
		c = em.find(Cotisation.class, 1);
		assertThat(c.getLibelle(), equalTo("Prélevement automatique obligatoire"));
    }

}
