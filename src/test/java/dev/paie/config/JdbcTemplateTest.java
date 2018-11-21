package dev.paie.config;



import java.sql.ResultSet;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.entite.Grade;


@ContextConfiguration(classes = {DataSourcePostGreConfig.class })
@RunWith(SpringRunner.class)
public class JdbcTemplateTest {

    @Autowired DataSource dataSource;

    @Test
    public void test_sauvegarder_lister_mettre_a_jour() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        // TODO sauvegarder un nouveau grade

        
        RowMapper<Grade> mapper = (ResultSet rs, int rowNum) -> {
        	Grade grade = new Grade();
        	grade.setId(rs.getInt("id"));
        	grade.setCode(rs.getString("code"));
        	grade.setNbHeuresBase(rs.getBigDecimal("nbheuresbase"));
        	grade.setTauxBase(rs.getBigDecimal("tauxbase"));
        	
        	return grade;
        };
        
        
        String sql = "INSERT INTO grade(code, nbheuresbase, tauxbase) VALUES('azerty', '49', '49')";
        jdbcTemplate.update(sql);


        Grade grade = jdbcTemplate.queryForObject("SELECT * FROM grade limit 1", mapper);
        
        
        jdbcTemplate.update ("UPDATE grade SET tauxbase=? WHERE id=?", 4565, 1);
        
        
        // TODO vérifier qu'il est possible de récupérer le nouveau grade

        
        
        // TODO modifier un grade

        // TODO vérifier que les modifications sont bien prises en compte
    }
}
