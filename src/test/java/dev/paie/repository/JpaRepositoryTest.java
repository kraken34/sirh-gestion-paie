package dev.paie.repository;

import java.math.BigDecimal;

import org.hibernate.criterion.Example;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.ServicesConfig;
import dev.paie.entite.Avantage;

@ContextConfiguration(classes = { ServicesConfig.class })
@RunWith(SpringRunner.class)
public class JpaRepositoryTest {

	@Autowired
	private AvantageRepository avantageRepository;

	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
		Avantage avantage = new Avantage();
		avantage.setId(13);
		avantage.setCode("coood");
		avantage.setMontant(new BigDecimal("12311"));
		avantage.setNom("monpetitnom");
		
		

		avantageRepository.save(avantage);

		Avantage av=avantageRepository.findOne(13);

		avantage.setCode("C");
		avantageRepository.save(avantage);

		av=avantageRepository.findOne(13);
	}
}
