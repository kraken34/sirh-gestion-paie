package dev.paie.web.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import dev.paie.entite.Utilisateur;
import dev.paie.entite.Utilisateur.ROLES;
import dev.paie.repository.UtilisateurRepository;

@Component
public class StartUpAppListener {

	@Autowired
	UtilisateurRepository utilisateurRepos;
	@Autowired
	PasswordEncoder passwordEncoder;

	@EventListener(ContextRefreshedEvent.class)
	public void contextRefreshedEvent() {
		// capture du démarrage de l'application
		// à un moment où le contexte Spring est complètement créé

		// TODO insérer des utilisateurs en base de données
		Utilisateur admin = new Utilisateur();
		admin.setNomUtilisateur("admin");
		admin.setMotDePasse(passwordEncoder.encode("admin"));
		admin.setEstActif(true);
		admin.setRole(ROLES.ROLE_ADMINISTRATEUR);

		Utilisateur randomUser = new Utilisateur();
		randomUser.setNomUtilisateur("Thomas");
		randomUser.setMotDePasse(passwordEncoder.encode("coucouthomas"));
		randomUser.setEstActif(true);
		randomUser.setRole(ROLES.ROLE_UTILISATEUR);

//		utilisateurRepos.save(admin);
//		utilisateurRepos.save(randomUser);

	}
}
