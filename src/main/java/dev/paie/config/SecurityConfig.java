package dev.paie.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(securedEnabled=true) 

public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.inMemoryAuthentication()
            .withUser("admin").password("admin").roles("ADMIN");
    }

    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeRequests()
                // les requêtes ci-dessous ne seront pas soumises à authentification
                .antMatchers("/resources/mvc/connexion", "/signup", "/about").permitAll()
                // seules les utilisateurs ayant le rôle ADMIN peuvent accéder à /admin
                .antMatchers("/admin/**").hasRole("ADMIN")
                
                // les autres requêtes sont soumises à authentification
                .anyRequest().authenticated()
                .and()
                // ...
                .formLogin();
    }

   
  /*
    @Autowired private PasswordEncoder passwordEncoder;

    // injection du bean de source de données
    @Autowired private DataSource dataSource;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        // changement de stratégie Mémoire => JDBC
        auth.jdbcAuthentication() 

            // configuration de la source de données
            .dataSource(dataSource)

            // configuration de l'algorithme de hashage des mots de passe
            .passwordEncoder(passwordEncoder)

            // requête pour obtenir les informations d'un utilisateur en fonction du nom d'utilisateur
            .usersByUsernameQuery("select NOM_UTILISATEUR, MOT_DE_PASSE, EST_ACTIF from UTILISATEUR where NOM_UTILISATEUR=?") <6>

            // requête pour obtenir les rôles d'un utilisateur
            .authoritiesByUsernameQuery("select NOM_UTILISATEUR,ROLE from UTILISATEUR where NOM_UTILISATEUR = ?"); <7>*/
    }





