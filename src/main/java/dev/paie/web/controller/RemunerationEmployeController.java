package dev.paie.web.controller;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;


import dev.paie.entite.Entreprise;
import dev.paie.entite.Grade;
import dev.paie.entite.ProfilRemuneration;
import dev.paie.entite.RemunerationEmploye;

import dev.paie.repository.EntrepriseRepository;
import dev.paie.repository.GradeRepository;
import dev.paie.repository.ListEmployeRepository;
import dev.paie.repository.ProfilRepository;

@Controller
@RequestMapping("/employes")
public class RemunerationEmployeController {
	
	@Autowired EntrepriseRepository entrepriseRepo;
	@Autowired ProfilRepository profilRepo;
	@Autowired GradeRepository gradeRepo;
	@Autowired ListEmployeRepository empRepo;
	// TODO ProfilRepo
	
    @RequestMapping(method = RequestMethod.GET, path = "/creer")
    public ModelAndView creerEmploye() {
    	
        ModelAndView mv = new ModelAndView();
        
        mv.setViewName("employes/creerEmploye");
        
        List<Entreprise> listeEntreprises = entrepriseRepo.findAll();
        
        mv.addObject("employe", new RemunerationEmploye());
        
        mv.addObject("listeEntreprises",listeEntreprises);
        
        // TODO ajouter au modèle la liste des profils
        
        List<ProfilRemuneration> listeProfils = profilRepo.findAll();
        
        mv.addObject("listeProfils", listeProfils);
        
        // Ajout des Grades
        
        List<Grade> listeGrade = gradeRepo.findAll();
        
        mv.addObject("listeGrade", listeGrade);
        
        return mv;
    }
    
    @RequestMapping(method = RequestMethod.POST, path = "/creer")
    public ModelAndView postForm(@ModelAttribute("liste") RemunerationEmploye liste) {
        ModelAndView mv = new ModelAndView();
        LocalDateTime poul = LocalDateTime.now();
        liste.setPr(poul);
        mv.setViewName("redirect:/mvc/Employe/lister");
        empRepo.save(liste);
        return mv;
    }
}
