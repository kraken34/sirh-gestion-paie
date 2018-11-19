package dev.paie;

import java.sql.ResultSet;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.DataSourcePostgreConfig;
import dev.paie.entite.Grade;

@ContextConfiguration(classes = { DataSourcePostgreConfig.class })
@RunWith(SpringRunner.class)
public class JdbcTemplateTest {

	@Autowired
	DataSource dataSource;

	@Test
	public void test_sauvegarder_lister_mettre_a_jour() {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

		RowMapper<Grade> mapper = (ResultSet rs, int rowNum) -> {
			Grade grade = new Grade();
			grade.setId(rs.getInt("id"));
			grade.setCode(rs.getString("code"));
			grade.setNbHeuresBase(rs.getBigDecimal("nb_heures_base"));
			grade.setTauxBase(rs.getBigDecimal("taux_base"));

			return grade;
		};

		jdbcTemplate.update("insert into grade(code,nb_heures_base,taux_base) values ('abc',10,10)");

		Grade grade = jdbcTemplate.queryForObject("select * from grade limit 1", mapper);

		jdbcTemplate.update("update grade set taux_base=? where id=?",22215, 1);
	}
}