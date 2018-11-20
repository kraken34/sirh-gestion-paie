package dev.paie.config;

import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@Configuration
public class DataSourceMySQLConfig {
	
	@Bean
    public DataSource dataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        
        // TODO 2. Mettre la bonne classe du driver
        
        dataSource.setDriverClassName("org.postgresql.Driver");
        
        // TODO 3. Mettre à jour
        
        dataSource.setUrl("jdbc:postgresql://localhost:5432/sirh-paie");
        dataSource.setUsername("postgres");
        dataSource.setPassword("babaca1984");
        return dataSource;
    }

}


