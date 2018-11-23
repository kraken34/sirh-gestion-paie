package dev.paie.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.RemunerationEmploye;
import dev.paie.repository.UtilisateurRepository;

@Controller
@RequestMapping("/connexion")
public class UtilisateurController {
	
	@Autowired
	private UtilisateurRepository utilsRepo;
	
	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	 @Secured("ROLE_ADMINISTRATEUR")
	public ModelAndView creerUtilisateur() {

		
		ModelAndView mv = new ModelAndView();
		
       mv.addObject("utilisateur", new RemunerationEmploye());
       
		mv.addObject("nomUtilisateur", utilsRepo.findAll());

		mv.addObject("motDePasse", utilsRepo.findAll());

		

		mv.setViewName("utilisateur/creerEmploye");

       
		return mv;
		
	
		
	}

	
}



    