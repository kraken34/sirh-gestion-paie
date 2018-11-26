package dev.paie;
import java.math.BigDecimal;

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

//  compléter la configuration
@ContextConfiguration(classes = {JpaConfig.class})
@RunWith(SpringRunner.class)
public class JpaTest {

    @PersistenceContext EntityManager em;

    @Test
    @org.springframework.transaction.annotation.Transactional
    @Rollback(false)
    public void test_sauvegarder_lister_mettre_a_jour() {
        // TODO sauvegarder une nouvelle cotisation
    	
    	/* 	private Integer id;
	private String code;
	private String libelle;
	private BigDecimal tauxSalarial;
	private BigDecimal tauxPatronal;
	private Boolean imposable = false; */
    	Cotisation cotisation = new Cotisation();
    	cotisation.setCode("SP01");
    	cotisation.setImposable(true);
    	cotisation.setLibelle("URSSAF CRDS");
    	cotisation.setTauxPatronal(new BigDecimal("0.003000"));
    	cotisation.setTauxSalarial(new BigDecimal("0.005000"));
    	
    	//em.persist(cotisation);
    	
        // TODO vérifier qu'il est possible de récupérer la nouvelle cotisation
    	
    	//Cotisation findCot = em.find(Cotisation.class, );
    	
    	
        // TODO modifier une cotisation

        // TODO vérifier que les modifications sont bien prises en compte
    	
    }
}