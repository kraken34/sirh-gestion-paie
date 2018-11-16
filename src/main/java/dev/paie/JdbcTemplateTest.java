package dev.paie;

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
import dev.paie.util.PaieUtils;

//Sélection des classes de configuration Spring à utiliser lors du test
@ContextConfiguration(classes = { DataSourceMySQLConfig .class })
//Configuration JUnit pour que Spring prenne la main sur le cycle de vie du test
@RunWith(SpringRunner.class)
public class JdbcTemplateTest {
	@Autowired DataSource dataSource;

    @Test
    public void test_sauvegarder_lister_mettre_a_jour() {
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        PaieUtils pu = new PaieUtils();
        
        // sauvegarder un nouveau grade
        String sql = "INSERT INTO grade VALUES(1,'NICO',45,0.025)";
        //jdbcTemplate.update(sql);
        // vérifier qu'il est possible de récupérer le nouveau grade
        RowMapper<Grade> mapper = (ResultSet rs, int rowNum) -> {
        	Grade g = new Grade();
            g.setId(rs.getInt("id"));
            g.setCode(rs.getString("code"));
            g.setNbHeuresBase(rs.getBigDecimal("nb_heures_base"));
            g.setTauxBase(rs.getBigDecimal("taux_base"));
            return g;
        };
        sql = "SELECT * FROM grade WHERE id=?";
        List<Grade> grades = jdbcTemplate.query(sql, mapper, 1);
        
        assertThat(grades.get(0).getCode(), equalTo("NICO"));
        assertThat(pu.formaterBigDecimal(grades.get(0).getNbHeuresBase()), equalTo(pu.formaterBigDecimal(new BigDecimal(45))));
        assertThat(pu.formaterBigDecimal(grades.get(0).getTauxBase()), equalTo(pu.formaterBigDecimal(new BigDecimal(0.025))));
        // modifier un grade
        sql = "UPDATE grade SET nb_heures_base=? WHERE id=?";
        jdbcTemplate.update(sql, new BigDecimal(45.666),1);

        // vérifier que les modifications sont bien prises en compte
        sql = "SELECT * FROM grade WHERE id=?";
        grades = jdbcTemplate.query(sql, mapper, 1);
        assertThat(pu.formaterBigDecimal(grades.get(0).getNbHeuresBase()), equalTo(pu.formaterBigDecimal(new BigDecimal(45.666))));
    }

}
