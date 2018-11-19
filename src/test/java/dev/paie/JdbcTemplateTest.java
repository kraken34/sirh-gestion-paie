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

@ContextConfiguration(classes = { DataSourceMySQLConfig.class })

@RunWith(SpringRunner.class)
public class JdbcTemplateTest {

	@Autowired
	DataSource dataSource;

	@Test
    public void test_sauvegarder_lister_mettre_a_jour() {
    	
        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
        
        // TODO sauvegarder un nouveau grade
        
        Grade grade =new Grade();
        
        grade.setCode("Test");

		grade.setNbHeuresBase(new BigDecimal("10.14"));

		grade.setTauxBase(new BigDecimal("5"));
		
        String sql = "INSERT INTO grade (code,nbreheuresbase,tauxbase) VALUES(?,?,?)";
        
        jdbcTemplate.update(sql,grade.getCode(), grade.getNbHeuresBase(), grade.getTauxBase() );
        
        
     

        // TODO vérifier qu'il est possible de récupérer le nouveau grade
        
       /* String sql = "SELECT * FROM grade WHERE code=?";
       jdbcTemplate.queryForObject(sql, new PizzaMapper(), name);*/
        

        // TODO modifier un grade
        
        String sqlUpdate = "UPDATE grade SET  = nbreheuresbase ? where code= ?";
        jdbcTemplate.update(sqlUpdate, 12.0,  grade.getCode());


      

        // TODO vérifier que les modifications sont bien prises en compte
        
        
    }
}
