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
import dev.paie.entite.RemunerationEmploye;
import dev.paie.repository.ListEmployeRepository;

@Controller
@RequestMapping("/Employe")
public class ListEmployeController {

	@Autowired ListEmployeRepository listEmployeRepo;
	

    @RequestMapping(method = RequestMethod.GET, path = "/lister")
    public ModelAndView listEmploye() {
    
        ModelAndView mv = new ModelAndView();
        mv.setViewName("employes/listEmploye");
        List<RemunerationEmploye> listeEmploye = listEmployeRepo.findAll();
        mv.addObject("listeEmploye",listeEmploye);
        
        return mv;
    }
    

    @RequestMapping(method = RequestMethod.POST, path = "/lister")
    public ModelAndView submit(@ModelAttribute("employe") RemunerationEmploye employe) {
    	
        LocalDateTime heure = LocalDateTime.now();
        employe.setPr(heure);
        
        listEmployeRepo.save(employe);
        
        return listEmploye();
    }    
    
    
}
