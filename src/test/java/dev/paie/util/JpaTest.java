package dev.paie.util;

import java.math.BigDecimal;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.config.DataSourcePostgreConfig;
import dev.paie.config.JpaConfig;
import dev.paie.entite.Cotisation;


@ContextConfiguration(classes = { JpaConfig.class, DataSourcePostgreConfig.class})
@RunWith(SpringRunner.class)
public class JpaTest {

	@PersistenceContext EntityManager em;

	@Test
	@Transactional
	@Rollback(false)
	public void test_sauvegarder_lister_mettre_a_jour() {
		// TODO sauvegarder une nouvelle cotisation
		Cotisation cot = new Cotisation();
		cot.setId(12);
		cot.setCode("dsjhdshd");
		cot.setLibelle("bebebe");
		cot.setTauxPatronal(new BigDecimal("15"));
		cot.setTauxSalarial(new BigDecimal("35"));
		//em.persist(cot);
				
		// TODO vérifier qu'il est possible de récupérer la nouvelle cotisation
		cot = (Cotisation) em.createQuery("from Cotisation").getSingleResult();

		// TODO modifier une cotisation
		em.createQuery("update Cotisation c SET c.code = 'hgdsg' where id=12").executeUpdate();

		// TODO vérifier que les modifications sont bien prises en compte
	}

}
