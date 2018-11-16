package dev.paie;

import static org.junit.Assert.assertThat;
import static org.hamcrest.CoreMatchers.equalTo;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.DataSourceMySQLConfig;
import dev.paie.entite.Grade;

@ContextConfiguration(classes = { DataSourceMySQLConfig.class })
@RunWith(SpringRunner.class)
public class JdbcTemplateTest {

	@Autowired
	DataSource dataSource;

	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		// sauvegarder un nouveau grade
		Grade g = new Grade();
		g.setCode("AZE001");
		String sql = "INSERT INTO grade (code) VALUES(?)";
		jdbcTemplate.update(sql, g.getCode());

		// TODO vérifier qu'il est possible de récupérer le nouveau grade
		sql = "Select* From grade Where code=?";
		Grade g2= jdbcTemplate.queryForObject(sql,Grade.class, "AZE001");
		assertThat(g2.getCode(),equalTo(g.getCode()));
		
		// TODO modifier un grade
		

		// TODO vérifier que les modifications sont bien prises en compte
	}
}