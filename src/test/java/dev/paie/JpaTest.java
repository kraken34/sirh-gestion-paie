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
import dev.paie.config.JpaConfig;
import dev.paie.entite.Cotisation;

@ContextConfiguration(classes = { JpaConfig.class })
@RunWith(SpringRunner.class)
public class JpaTest {

	@PersistenceContext EntityManager em;
    @Test
    @Rollback(false)
	public void test_sauvegarder_lister_mettre_a_jour() {
		// TODO sauvegarder une nouvelle cotisation

	}

	@Transactional
	public void create(Cotisation cotisation) {
		cotisation.setId(2);
		cotisation.setCode("thy");
		cotisation.setLibelle("hello");
		cotisation.setImposable(true);
		cotisation.setTauxPatronal( new BigDecimal ("2.55"));
		cotisation.setTauxSalarial(new BigDecimal ("12.54"));
		
		em.persist(cotisation);

		// TODO vérifier qu'il est possible de récupérer la nouvelle cotisation
		

		// TODO modifier une cotisation

		// TODO vérifier que les modifications sont bien prises en compte

	}

}
