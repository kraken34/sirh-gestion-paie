package dev.paie;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
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


    @PersistenceContext EntityManager em;

    @Test
    @Transactional
    @Rollback(false)
    public void test_sauvegarder_lister_mettre_a_jour() {
        // TODO sauvegarder une nouvelle cotisation
    	Cotisation coti=new Cotisation();
    	coti.setCode("3630allopérenoel");
    	em.persist(coti);

        // TODO vérifier qu'il est possible de récupérer la nouvelle cotisation
    	TypedQuery<Cotisation> query = em.createQuery(
			       "select distinct c " +
			       "from Cotisation c " +
			       "where c.code =:code ", Cotisation.class);
			query.setParameter("code", "3630allopérenoel");
			List<Cotisation> listcli = query.getResultList();
			for(Cotisation  cli: listcli) {
				System.out.println(cli.getCode());
			}

        // TODO modifier une cotisation

        // TODO vérifier que les modifications sont bien prises en compte
    }
}