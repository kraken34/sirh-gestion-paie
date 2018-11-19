package dev.paie.util;
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
import dev.paie.config.JeuxDeDonneesConfig;
import dev.paie.config.ServicesConfig;
import dev.paie.entite.Grade;




//Sélection des classes de configuration Spring à utiliser lors du test
@ContextConfiguration(classes = { DataSourcePostgreConfig.class})
//Configuration JUnit pour que Spring prenne la main sur le cycle de vie du
//test
@RunWith(SpringRunner.class)
// TODO compléter la configuration
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
        
        String sql = "INSERT INTO grade(code, nbheuresbase, tauxbase) values('dzgdyge', '15', '15')";
        jdbcTemplate.update(sql);
        

        // TODO vérifier qu'il est possible de récupérer le nouveau grade
        Grade grade  = jdbcTemplate.queryForObject("SELECT * FROM grade limit 1", mapper);
        

        // TODO modifier un grade
        jdbcTemplate.update("UPDATE grade SET tauxbase=? WHERE id=?", 4565, 1);

        // TODO vérifier que les modifications sont bien prises en compte
        
        
    }
}