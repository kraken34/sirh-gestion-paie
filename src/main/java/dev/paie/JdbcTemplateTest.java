package dev.paie;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.support.xml.Jdbc4SqlXmlHandler;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.DataSourcepostgreSQLConfig;
import dev.paie.config.ServicesConfig;
import dev.paie.entite.Grade;

//Sélection des classes de configuration Spring à utiliser lors du test
@ContextConfiguration(classes = { DataSourcepostgreSQLConfig.class })
//Configuration JUnit pour que Spring prenne la main sur le cycle de vie du test
@RunWith(SpringRunner.class)
public class JdbcTemplateTest {

	@Autowired
	DataSource dataSource;

	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		// TODO sauvegarder un nouveau grade
		String sql = "INSERT INTO grade (id,code, nbheuresbase, tauxbase) VALUES(?,?,?,?)";
		jdbcTemplate.update(sql, 3, "aaa", 2, 3);

		// TODO vérifier qu'il est possible de récupérer le nouveau grade
		String recup = "Select * From grade where id = 3";
		Grade grade = new Grade();
		jdbcTemplate.update(recup);
		// TODO modifier un grade

		// TODO vérifier que les modifications sont bien prises en compte
	}
}