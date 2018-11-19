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

import dev.paie.config.DataSourcePostgreConfig;
import dev.paie.config.JpaConfig;
import dev.paie.entite.Cotisation;

@ContextConfiguration(classes = { JpaConfig.class, DataSourcePostgreConfig.class })
@RunWith(SpringRunner.class)
public class JpaTest {

	@PersistenceContext
	EntityManager em;

	@Test
	@Transactional
	@Rollback(false)
	public void test_sauvegarder_lister_mettre_a_jour() {

		Cotisation cot = new Cotisation();
		cot.setId(27);
		cot.setCode("cocode");
		cot.setLibelle("bebel");
		cot.setTauxPatronal(new BigDecimal("25"));
		cot.setTauxSalarial(new BigDecimal("478"));

		//em.persist(cot);

		cot = (Cotisation) em.createQuery("from Cotisation").getSingleResult();
		
		em.createQuery("update Cotisation c SET c.code = 'ffdvdd' where id=27").executeUpdate();
	}
}
