package dev.paie.config;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@ComponentScan({ "dev.paie.service", "dev.paie.util" })
//@Import({ JpaConfig.class, HerokuDBConfig.class })
@Import({ JpaConfig.class, DataSourcePostgresqlConfig.class })

@EnableJpaRepositories("dev.paie.repository") // Configuration à ajouter pour la dépendance spring-data-jpa
public class ServicesConfig {

}
