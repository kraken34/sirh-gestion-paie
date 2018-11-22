package dev.paie.config;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@EnableTransactionManagement
@Configuration
public class JpaConfig {

 	
    @Bean
      public PlatformTransactionManager transactionManager(EntityManagerFactory emf) {
        JpaTransactionManager txManager = new JpaTransactionManager();
        txManager.setEntityManagerFactory(emf);
        return txManager;
      }

////==========================================================================================================================
//    //Configuration avant intégration Web
////========================================    
//    
//    @Bean
//    // Cette configuration nécessite une source de données configurée.
//    // Elle s'utilise donc en association avec un autre fichier de configuration définissant un bean DataSource.
//    public LocalContainerEntityManagerFactoryBean entityManagerFactory(DataSource dataSource) {
//
//        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
//        vendorAdapter.setGenerateDdl(true);
//        
//        // activer les logs SQL
//        vendorAdapter.setShowSql(true);
//
//        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
//        factory.setJpaVendorAdapter(vendorAdapter);
//        
//        // alternative au persistence.xml
//        factory.setPackagesToScan("dev.paie.entite");
//        factory.setDataSource(dataSource);
//        factory.afterPropertiesSet();
//
//        return factory;
//    }
  //==========================================================================================================================
    
    @Bean
    // Cette configuration nécessite une source de données configurée.
    // Elle s'utilise donc en association avec un autre fichier de configuration
    // d éfinissant un bean DataSource.
    public EntityManagerFactory entityManagerFactory(DataSource dataSource) {
        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();

//        vendorAdapter.setGenerateDdl(true); // <1>  
        //Set whether to generate DDL after the EntityManagerFactory has been initialized, * creating/updating all relevant tables.
        //Non utilisé ici car on va utiliser les Jpa properties pour supprimer et créer les tables lors de la création du context.
        //Permet de mettre à jours les donnnées. Si on le comment, on ne fait rien.

        // activer les logs SQL
        vendorAdapter.setShowSql(true);
        LocalContainerEntityManagerFactoryBean factory = new LocalContainerEntityManagerFactoryBean();
        factory.setJpaVendorAdapter(vendorAdapter);
        
        // alternative au persistence.xml
        factory.setPackagesToScan("dev.paie.entite");
        factory.setDataSource(dataSource);

        //Configuration du mode drop-and-create.
        Properties jpaProperties = new Properties(); // <2>
        //Je commente les 2 lignes suivantes car j'ai déjà créé ma base de données et ne souhaite pas la regénéré à chaque redémarrage
        jpaProperties.setProperty("javax.persistence.schema-generation.database.action", "drop-and-create"); // <2>
        jpaProperties.setProperty("javax.persistence.sql-load-script-source", "data.sql"); // <3>

        factory.setJpaProperties(jpaProperties); // ajout d'un script SQL pour des données initiales.

        factory.afterPropertiesSet();
        return factory.getObject();
    }

}