/**
 * 
 */
package dev.paie.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.Post;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.repository.BulletinSalaireRepository;
import dev.paie.repository.EntrepriseRepository;
import dev.paie.repository.GradeRepository;
import dev.paie.repository.ProfilRepository;
import dev.paie.repository.RemunerationEmployeRepository;

/**
 * @author formation
 *
 */
@Controller
@RequestMapping("/employes")
public class RenumerationEmployeController {
	
	@Autowired EntrepriseRepository entrepriseRepository;
	@Autowired ProfilRepository profilRepository;
	@Autowired GradeRepository gradeRepository;
	@Autowired RemunerationEmployeRepository remunerationRepository;
	@Autowired BulletinSalaireRepository bulletinSalaireRepository;
	

	
	
	 @RequestMapping(method = RequestMethod.GET, path = "/creer")
	    public ModelAndView creerEmploye() {
	        ModelAndView mv = new ModelAndView();
	        mv.setViewName("employes/creerEmploye");
	       
	        mv.addObject("employe", new RemunerationEmploye());
	        
	        mv.addObject("listeEntreprises", entrepriseRepository.findAll());
	        mv.addObject("listeProfils", profilRepository.findAll());
	        mv.addObject("listeGrades", gradeRepository.findAll());
	        
	        return mv;
	    }
	 @RequestMapping(method = RequestMethod.POST, path = "/creer")
	 public ModelAndView submitForm(@ModelAttribute("employe") RemunerationEmploye employe) {
		 
		 RestTemplate rt = new RestTemplate();
		 Post[] result = rt.getForObject("http://collegues-api.cleverapps.io/collegues?matricule="+employe.getMatricule(), Post[].class);
		 
		 if(result.length > 0) {
			 remunerationRepository.save(employe);
			 return creerEmploye();
		 }else {
			 return codeErreur();
		 }
		
		
		 // insérer en base avec repository
	     
	 }
	 
	 private ModelAndView codeErreur() {
		return null;
		// TODO Auto-generated method stub
		
	}
	@RequestMapping(method = RequestMethod.GET, path = "/lister")
	    public ModelAndView listerEmploye() {
	        ModelAndView mv = new ModelAndView();
	        mv.setViewName("employes/listerEmploye");
	       
	        mv.addObject("listemploye", remunerationRepository.findAll());
	      
	        
	        return mv;
	    }
	 
}
