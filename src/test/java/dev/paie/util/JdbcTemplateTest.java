/**
 * 
 */
package dev.paie.util;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.junit.Assert.assertThat;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.util.List;

import javax.sql.DataSource;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import dev.paie.config.DataSourceMySQLConfig;
import dev.paie.entite.Grade;

/**
 * @author formation
 *
 */
@ContextConfiguration(classes = { DataSourceMySQLConfig.class })
@RunWith(SpringRunner.class)
public class JdbcTemplateTest {

    @Autowired DataSource dataSource;

    @Test
    public void test_sauvegarder_lister_mettre_a_jour() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        // TODO sauvegarder un nouveau grade
        String sql = "INSERT INTO grade(id,code,nb_heures_base,taux_base) VALUES(1,'Directeur',48,0.025)";
        jdbcTemplate.update(sql);
       
        // TODO vérifier qu'il est possible de récupérer le nouveau grade
        RowMapper<Grade> mapper = (ResultSet rs, int rowNum) -> {
            Grade g = new Grade();
            g.setId(rs.getInt("id"));
            g.setCode(rs.getString("code"));
            g.setNbHeuresBase(rs.getBigDecimal("nb_heures_base"));
            g.setTauxBase(rs.getBigDecimal("taux_base"));
            return g;
        };
        
        String sqli = "SELECT * FROM grade WHERE code=?";
        List<Grade> grades = jdbcTemplate.query(sqli,mapper,"Directeur");
        assertThat(grades.get(0).getId(),equalTo(1));
        assertThat(grades.get(0).getCode(),equalTo("Directeur"));
        
     // TODO modifier un grade
        String sqlUpdate = "UPDATE grade SET nb_heures_base=? WHERE code = ? ";
        jdbcTemplate.update(sqlUpdate,new BigDecimal("12"), "Directeur");

        // TODO vérifier que les modifications sont bien prises en compte
        assertThat(grades.get(0).getNbHeuresBase(),equalTo(12));
        assertThat(grades.get(0).getCode(),equalTo("Directeur"));
       

        

       
    }
}