package dev.paie.config;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

	@Override
	protected void configure(HttpSecurity http) throws Exception {
		http.authorizeRequests()
				// les requêtes ci-dessous ne seront pas soumises à authentification
				.anyRequest().authenticated().and().formLogin().loginPage("/mvc/connexion").permitAll();

	}

	// injection du bean de hashage de mot de passage
	@Autowired
	private PasswordEncoder passwordEncoder;

	// injection du bean de source de données
	@Autowired
	private DataSource dataSource;

	@Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
		
		// changement de stratégie Mémoire => JDBC
        auth.jdbcAuthentication()
        
        // configuration de la source de données
        .dataSource(dataSource)
        
        // configuration de l'algorithme de hashage des mots de passe
        .passwordEncoder(passwordEncoder)
        
        // requête pour obtenir les informations d'un utilisateur en fonction du nom d'utilisateur
        .usersByUsernameQuery("select NOM_UTILISATEUR, PASSWORD, EST_ACTIF from UTILISATEUR where NOM_UTILISATEUR=?")
        
        // requête pour obtenir les rôles d'un utilisateur
        .authoritiesByUsernameQuery("select NOM_UTILISATEUR,ROLE from UTILISATEUR where NOM_UTILISATEUR = ?");
    }

}