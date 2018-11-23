package dev.paie.web.listener;

import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.context.event.EventListener;

public class StartUpAppListener {

	 @EventListener(ContextRefreshedEvent.class)
	    public void contextRefreshedEvent() {
		 
		 
	        // capture du démarrage de l'application
		 
		 
	        // à un moment où le contexte Spring est complètement créé
		 
		 

	        // TODO insérer des utilisateurs en base de données
		 
		 

	    }

	
}
