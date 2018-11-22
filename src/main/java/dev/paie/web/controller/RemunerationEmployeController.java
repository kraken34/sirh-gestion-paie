package dev.paie.web.controller;

import java.time.LocalDateTime;
import java.time.ZonedDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.Collegue;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.repository.EntrepriseRepository;
import dev.paie.repository.GradeRepository;
import dev.paie.repository.ProfilRepository;
import dev.paie.repository.RemunerationRepository;

@Controller
@RequestMapping("/employes")
public class RemunerationEmployeController {

	@Autowired
	private EntrepriseRepository entrepriseRepo;
	@Autowired
	private GradeRepository gradeRepo;
	@Autowired
	private ProfilRepository profilRepo;
	@Autowired
	private RemunerationRepository remunerationRepo;

	
	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	public ModelAndView creerEmploye() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/creerEmploye");
		mv.addObject("prefixMatricule", "M00");

		// Code Chloé
		mv.addObject("employe", new RemunerationEmploye());
		mv.addObject("listeEntreprises", entrepriseRepo.findAll());
		mv.addObject("listeGrades", gradeRepo.findAll());
		mv.addObject("listeProfils", profilRepo.findAll());

		return mv;
	}

	// Créer employé
	@RequestMapping(method = RequestMethod.POST, path = "/creer")
	public ModelAndView submit(@ModelAttribute("employe") RemunerationEmploye remunerationEmploye) {
		ModelAndView mv = new ModelAndView();
		
		// Créer un collègue selon un matricule existant via API collègue

		String matricule = remunerationEmploye.getMatricule();
		
		RestTemplate rt = new RestTemplate();
		Collegue[] result = rt.getForObject("http://collegues-api.cleverapps.io/collegues?matricule=", Collegue[].class, matricule);
		
		if(result.length == 1) {
			remunerationRepo.save(remunerationEmploye);
			mv.setViewName("employes/ok");
		} else {
			mv.setViewName("employes/erreur");
		}
		return mv;
	}

	// Lister employé
	@RequestMapping(method = RequestMethod.GET, path ="/lister")
    public ModelAndView listerEmploye() {

		ModelAndView mv = new ModelAndView();
    	mv.setViewName("/employes/listerEmploye");
    	mv.addObject("listeEmployes", remunerationRepo.findAll());

    	return mv;
    
    }

}
