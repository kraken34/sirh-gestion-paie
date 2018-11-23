package dev.paie;

import java.math.BigDecimal;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.DataSourcePostgresqlConfig;
import dev.paie.dao.GradeDAO;
import dev.paie.entite.Grade;

//Sélection des classes de configuration Spring à utiliser lors du test
@ContextConfiguration(classes =  {DataSourcePostgresqlConfig.class, GradeDAO.class})

//Configuration JUnit pour que Spring prenne la main sur le cycle de vie du test
@RunWith(SpringRunner.class)
public class JdbcTemplateTest {

	@Autowired
	GradeDAO paieDAO;

	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
		
		
//		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		//SirhPaieDAO paieDAO = new SirhPaieDAO(dataSource);

		// TODO sauvegarder un nouveau grade
		Grade grade1 = new Grade();
		grade1.setCode("007");
		grade1.setNbHeuresBase(new BigDecimal(51));
		grade1.setTauxBase(new BigDecimal(15));
		paieDAO.ajoutGrade(grade1);

		// TODO vérifier qu'il est possible de récupérer le nouveau grade
		System.out.println(paieDAO.getGradeByCode(grade1.getCode()).toString());
		
		grade1 = paieDAO.getGradeByCode(grade1.getCode());
		System.out.println("Id générée : " + grade1.getId());

		// TODO modifier un grade
		grade1.setCode("2MainNeMeurtJamais");
		paieDAO.updateGrade(grade1);
		System.out.println(paieDAO.getGradeByCode(grade1.getCode()).toString());
		
		// TODO vérifier que les modifications sont bien prises en compte
	}
}
