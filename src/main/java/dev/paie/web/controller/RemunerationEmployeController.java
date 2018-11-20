package dev.paie.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.Cotisation;
import dev.paie.entite.Entreprise;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.repository.CotisationRepository;
import dev.paie.repository.EntrepriseRepository;

@Controller
@RequestMapping("/employes")
public class RemunerationEmployeController {
	
	@Autowired EntrepriseRepository entrepriseRepo;

    @RequestMapping(method = RequestMethod.GET, path = "/creer")
    public ModelAndView creerEmploye() {
    	
        ModelAndView mv = new ModelAndView();
        
        mv.setViewName("employes/creerEmploye");
        
        List<Entreprise> listeEntreprises = entrepriseRepo.findAll();
        
        mv.addObject("employe", new RemunerationEmploye());
        
        mv.addObject("listeEntreprises",listeEntreprises);
        return mv;
    }
    
    @RequestMapping(method = RequestMethod.POST, path = "/creer")
    public ModelAndView postForm(@ModelAttribute("employe") RemunerationEmploye employe) {
    	
        ModelAndView mv = new ModelAndView();
    
        return mv;
    }
}
