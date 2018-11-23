package dev.paie.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import dev.paie.entite.Utilisateur;
import dev.paie.repository.UtilisateurRepository;


@Component
public class StartUpAppListener {

	@Autowired UtilisateurRepository utilisateur;
	
	 @Autowired private PasswordEncoder passwordEncoder;

    @EventListener(ContextRefreshedEvent.class)
    public void contextRefreshedEvent() {
        // capture du démarrage de l'application
        // à un moment où le contexte Spring est complètement créé

        // insérer des utilisateurs en base de données
    	Utilisateur utilisateurAdmin=new Utilisateur();
    	utilisateurAdmin.setNomUtilisateur("todave");
    	utilisateurAdmin.setMotDePasse(passwordEncoder.encode("azertyuiop"));
    	utilisateurAdmin.setRole(Utilisateur.ROLES.ROLE_ADMINISTRATEUR);
    	utilisateurAdmin.setEstActif(true);
    	Utilisateur utilisateurLambda=new Utilisateur();
    	utilisateurLambda.setNomUtilisateur("Stéphane");
    	utilisateurLambda.setMotDePasse(passwordEncoder.encode("qsdfghjklm"));
    	utilisateurLambda.setRole(Utilisateur.ROLES.ROLE_UTILISATEUR);
    	utilisateurLambda.setEstActif(true);
//    	utilisateur.save(utilisateurAdmin);
//    	utilisateur.save(utilisateurLambda);

    }
}