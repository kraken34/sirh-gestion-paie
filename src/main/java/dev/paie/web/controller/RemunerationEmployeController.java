package dev.paie.web.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.Collegue;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.repository.EntrepriseRepository;
import dev.paie.repository.GradeRepository;
import dev.paie.repository.ProfilRemunerationRepository;
import dev.paie.repository.RemunerationEmployeRepository;

@Controller
@RequestMapping("/employes")
public class RemunerationEmployeController {

	@Autowired RemunerationEmployeRepository remunerationEmployeRepos;
	@Autowired EntrepriseRepository entrepriseRepos;
	@Autowired ProfilRemunerationRepository profilRemunerationRepos;
	@Autowired GradeRepository gradeRepos;
	
    // Version générique @RequestMapping(method = RequestMethod.GET, path = "/creer")
    @GetMapping("/creer")
    public ModelAndView creerEmploye() {
        ModelAndView mv = new ModelAndView();
        mv.setViewName("employes/creerEmploye");
        RemunerationEmploye employe = new RemunerationEmploye();
        mv.addObject("employe", employe);
        mv.addObject("listeEntreprises",entrepriseRepos.findAll());
        mv.addObject("listeProfilRemuneration", profilRemunerationRepos.findAll());
        mv.addObject("listeGrades", gradeRepos.findAll());
        RestTemplate rt = new RestTemplate();
    	Collegue[] result = rt.getForObject("http://collegues-api.cleverapps.io/collegues", Collegue[].class);
    	mv.addObject("listeCollegues", result);

        return mv;
    }
    @PostMapping("/creer")
    public ModelAndView enregistrerEmployer(@ModelAttribute("employe") RemunerationEmploye remunerationEmploye) {
    	RestTemplate rt = new RestTemplate();
    	Collegue[] result = rt.getForObject("http://collegues-api.cleverapps.io/collegues?matricule={matricule}", Collegue[].class, remunerationEmploye.getMatricule());
    	if (result.length > 0) {
    	remunerationEmploye.setDateHeureCreation(LocalDateTime.now());
    	remunerationEmployeRepos.save(remunerationEmploye);
    	ModelAndView mv = new ModelAndView();
    	mv.setViewName("redirect:liste-employes");
    	return mv;
    	} else {
    		ModelAndView mv = new ModelAndView();
        	mv.setViewName("redirect:creer");
        	return mv;
    	}
		
    }
    
    @GetMapping("/liste-employes")
    public ModelAndView afficherEmployes() {
    	ModelAndView mv = new ModelAndView();
    	mv.setViewName("employes/liste-employes");
    	mv.addObject("listeEmployes", remunerationEmployeRepos.findAll());
		return mv;
    }

}
