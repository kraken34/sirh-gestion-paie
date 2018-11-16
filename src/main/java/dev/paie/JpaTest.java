package dev.paie;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.JpaConfig;
import dev.paie.entite.Cotisation;

@ContextConfiguration(classes = { JpaConfig.class })
@RunWith(SpringRunner.class)
public class JpaTest {
	// TODO compléter la configuration

	@PersistenceContext
	EntityManager em;

	@Test
	@Transactional
	@Rollback(false)
	public void test_sauvegarder_lister_mettre_a_jour() {
		// TODO sauvegarder une nouvelle cotisation
		Cotisation cot = new Cotisation();
		cot.setLibelle("cfaafafazd");
		em.persist(cot);

		// TODO vérifier qu'il est possible de récupérer la nouvelle cotisation

		// TODO modifier une cotisation

		// TODO vérifier que les modifications sont bien prises en compte
	}
}
