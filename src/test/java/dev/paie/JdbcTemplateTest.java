package dev.paie;

import java.math.BigDecimal;
import javax.sql.DataSource;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import dev.paie.config.DataSourceMySQLConfig;
import dev.paie.entite.Grade;



//compléter la configuration

@ContextConfiguration(classes = {DataSourceMySQLConfig.class})
@RunWith(SpringRunner.class)

public class JdbcTemplateTest {

    @Autowired DataSource dataSource;

    @Test
    public void test_sauvegarder_lister_mettre_a_jour() {
    	
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        
        //sauvegarder un nouveau grade
        Grade grade = new Grade();
        grade.setCode("grade1");
        grade.setNbHeuresBase(new BigDecimal("151.67"));
        grade.setTauxBase(new BigDecimal("11.0984"));
        String sql = "INSERT INTO grade (code, nbHeuresBase, tauxBase) VALUES(?,?,?)";
        
        jdbcTemplate.update(sql, grade.getCode(), grade.getNbHeuresBase(), grade.getTauxBase());

        // TODO vérifier qu'il est possible de récupérer le nouveau grade
        
        

        // TODO modifier un grade

        // TODO vérifier que les modifications sont bien prises en compte
    }
}