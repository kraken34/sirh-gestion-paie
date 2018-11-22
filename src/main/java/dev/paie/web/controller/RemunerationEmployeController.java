package dev.paie.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
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
import dev.paie.repository.ProfilRemunerationRepository;
import dev.paie.repository.RemunerationEmployeRepository;

@Controller
@RequestMapping("/employes")
public class RemunerationEmployeController {

	@Autowired EntrepriseRepository entrepriseRepository;
	@Autowired ProfilRemunerationRepository profilRemunerationRepository;
	@Autowired GradeRepository gradeRepository;
	@Autowired RemunerationEmployeRepository remunerationEmployeRepository;
	
	@RequestMapping(method=RequestMethod.GET, path="/creer")
	public ModelAndView creerEmploye() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/creerEmploye");
		mv.addObject("employe", new RemunerationEmploye());
		
		mv.addObject("listeEntreprises",entrepriseRepository.findAll());
		mv.addObject("listeProfilsRemuneration", profilRemunerationRepository.findAll());
		mv.addObject("listeGrade", gradeRepository.findAll());
		return mv;
	}
	
	@RequestMapping(method=RequestMethod.POST, path="/creer")
	public ModelAndView ajouterEmploye(@ModelAttribute("employe") RemunerationEmploye employe) {
		
		RestTemplate rt = new RestTemplate();
		Collegue[] result = rt.getForObject("http://collegues-api.cleverapps.io/collegues?matricule="+employe.getMatricule(), Collegue[].class);
		
		if ( result.length > 0)
		{
			remunerationEmployeRepository.save(employe);
			return creerEmploye();
		}
		else
			return mauvaisMatricule();
		
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/lister")
	public ModelAndView listerEmployes() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/listerEmployes");
		mv.addObject("listeEmploye",remunerationEmployeRepository.findAll());
		return mv;
	}
	
	public ModelAndView mauvaisMatricule() {
		RestTemplate rt = new RestTemplate();
		Collegue[] result = rt.getForObject("http://collegues-api.cleverapps.io/collegues", Collegue[].class);
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/listerMatricules");
		mv.addObject("listeCollegues",result);
		
		return mv;
	}
}
