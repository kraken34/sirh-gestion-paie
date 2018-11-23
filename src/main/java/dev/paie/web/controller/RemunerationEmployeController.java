package dev.paie.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.Matricule;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.repository.EntrepriseRepository;
import dev.paie.repository.GradeRepository;
import dev.paie.repository.ProfilRemunerationRepository;
import dev.paie.repository.RemunerationEmployeRepository;



@Controller @RequestMapping("/employes") public class RemunerationEmployeController {

	@Autowired
	private EntrepriseRepository entrepriseRepository;

	@Autowired
	private GradeRepository gradeRepository;

	@Autowired
	private ProfilRemunerationRepository profilRemunerationRepository;

	@Autowired
	private RemunerationEmployeRepository  remunerationEmployeRepository;

	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	 @Secured("ROLE_ADMINISTRATEUR")
	public ModelAndView creerEmploye() {

		
		ModelAndView mv = new ModelAndView();
		
        mv.addObject("employe", new RemunerationEmploye());
        
		mv.addObject("entreprises", entrepriseRepository.findAll());

		mv.addObject("profils", profilRemunerationRepository.findAll());

		mv.addObject("grades", gradeRepository.findAll());

		mv.setViewName("employes/creerEmploye");

        
		return mv;
		
	}
		
	@RequestMapping(method = RequestMethod.POST, path = "/creer")
	@Secured({"ROLE_UTILISATEUR", "ROLE_ADMINISTRATEUR"})
    public ModelAndView envoyer ( @ModelAttribute("employe") RemunerationEmploye employe )  {
		
		
		
		RestTemplate rt = new RestTemplate();
		
		Matricule [] result = rt.getForObject("http://collegues-api.cleverapps.io/collegues?matricule="+ employe.getMatricule(), Matricule[].class, 1);
		
		if (result.length == 0) {
			
			return creerEmploye();
		}
		else {
			remunerationEmployeRepository.save(employe);
			
			return creerEmploye();
		}
		
	}

	
	@RequestMapping(method = RequestMethod.GET, path = "/lister")
	@Secured("ROLE_ADMINISTRATEUR")
	public ModelAndView afficherEmploye(){

		ModelAndView mv = new ModelAndView();

		mv.addObject("employes",remunerationEmployeRepository.findAll());

		mv.setViewName("employes/listedesemployes");

		return mv;
	

	}	

	
	
}
