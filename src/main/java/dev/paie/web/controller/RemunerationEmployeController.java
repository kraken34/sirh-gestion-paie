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

@Controller
@RequestMapping("/employes")
public class RemunerationEmployeController {

//    @RequestMapping(method = RequestMethod.GET, path = "/creer")
//    public ModelAndView creerEmploye() {
//        ModelAndView mv = new ModelAndView();
//        mv.setViewName("employes/creerEmploye");
//        mv.addObject("prefixMatricule","M00");
//        return mv;
//    }

	@Autowired
	EntrepriseRepository entrepriseRepo;
	@Autowired
	ProfilRemunerationRepository profileRepo;
	@Autowired
	GradeRepository gradeRepo;
	@Autowired
	RemunerationEmployeRepository employeRepo;
	
	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	 public ModelAndView creerEmploye() {
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("employes/creerEmploye");

		mv.addObject("employe", new RemunerationEmploye());
		
		mv.addObject("listeEntreprises", entrepriseRepo.findAll());
		mv.addObject("listeProfiles", profileRepo.findAll());
		mv.addObject("listeGrades", gradeRepo.findAll());
		
		return mv;
	}
	

    @RequestMapping(method = RequestMethod.POST, path = "/creer")
    public ModelAndView saisieEmploye(@ModelAttribute("employe") RemunerationEmploye employe) {
        
        ModelAndView mv = new ModelAndView();
        
        employeRepo.save(employe);

		mv.setViewName("/creationReussie");
        	        
        return mv;
    }
    

	@RequestMapping(method = RequestMethod.GET, path = "/lister")
	 public ModelAndView listerEmployes() {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/listerEmployes");
		
		mv.addObject("listeEmployes", employeRepo.findAll());
		
		return mv;
	}

}