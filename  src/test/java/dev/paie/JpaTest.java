package dev.paie;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.DataSourcePostgresqlConfig;
import dev.paie.config.JpaConfig;
import dev.paie.dao.CotisationDAO;
import dev.paie.entite.Cotisation;

//TODO compléter la configuration
//Sélection des classes de configuration Spring à utiliser lors du test
@ContextConfiguration(classes = {JpaConfig.class, DataSourcePostgresqlConfig.class, CotisationDAO.class})

//Configuration JUnit pour que Spring prenne la main sur le cycle de vie du test
@RunWith(SpringRunner.class)
public class JpaTest {

//	@PersistenceContext
//	EntityManager em;


	@Autowired
	CotisationDAO cotisationDAO;
	
	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
				
		Cotisation cotisation1 = new Cotisation();
		cotisation1.setCode("code1");
		cotisation1.setLibelle("cotisation santé");
		cotisation1.setTauxPatronal(new BigDecimal(0.005));
		cotisation1.setTauxSalarial(new BigDecimal(0.004));
		cotisation1.setImposable(false);
		
		// TODO sauvegarder une nouvelle cotisation
		cotisationDAO.ajoutCotisation(cotisation1);

		cotisationDAO.mergeCotisation(cotisation1);
		
		// TODO vérifier qu'il est possible de récupérer la nouvelle cotisation
		System.out.println(cotisationDAO.getCotisationById(cotisation1.getId()).getLibelle());

		// TODO modifier une cotisation
		cotisation1.setCode("codeModif");
		cotisationDAO.mergeCotisation(cotisation1);
		
		// TODO vérifier que les modifications sont bien prises en compte
		System.out.println(cotisationDAO.getCotisationById(cotisation1.getId()).getCode());
	}
}
