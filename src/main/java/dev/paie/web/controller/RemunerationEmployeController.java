package dev.paie.web.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

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

    public ModelAndView envoyer ( @ModelAttribute("employe") RemunerationEmploye employe )  {
		
		remunerationEmployeRepository.save(employe);

	 
		return creerEmploye();
	}

	@RequestMapping(method = RequestMethod.GET, path = "/lister")

	public ModelAndView afficherEmploye(){

		ModelAndView mv = new ModelAndView();

		mv.addObject("employes",remunerationEmployeRepository.findAll());

		mv.setViewName("employes/listedesemployes");

		return mv;
	

	}	

}
