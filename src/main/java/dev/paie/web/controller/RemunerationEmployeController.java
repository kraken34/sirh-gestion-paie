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
		remunerationEmployeRepository.save(employe);
		return creerEmploye();
	}
	
	@RequestMapping(method=RequestMethod.GET, path="/lister")
	public ModelAndView listerEmployes() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/listerEmployes");
		mv.addObject("listeEmploye",remunerationEmployeRepository.findAll());
		return mv;
	}
}
