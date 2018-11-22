package dev.paie.web.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.BulletinSalaire;
import dev.paie.repository.BulletinSalaireRepository;
import dev.paie.repository.PeriodeRepository;
import dev.paie.repository.RemunerationEmployeRepository;
import dev.paie.service.CalculerRemunerationService;
import dev.paie.service.CalculerRemunerationServiceSimple;
import dev.paie.web.controller.viewmodel.CalculSurBulletin;

@Controller
@RequestMapping("/bulletins")
public class BulletinSalaireController {
	
	@Autowired
	PeriodeRepository periodeRepo;

	@Autowired
	RemunerationEmployeRepository employeRepo;
	

	@Autowired
	BulletinSalaireRepository bulletinRepo;
	
	@Autowired
	CalculerRemunerationService calcRemSimple;
	

	/**
	 * Requête GET : créer un bulletin.
	 * @param Bulletin bulletin
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	 public ModelAndView creerBulletin() {
		
		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("bulletins/creerBulletin");

		mv.addObject("bulletinSalaire", new BulletinSalaire());

		mv.addObject("listePeriodes", periodeRepo.findAll());
		mv.addObject("listeEmployes", employeRepo.findAll());
				
		return mv;
	}
	
	/**
	 * Requête POST : créer un bulletin.
	 * @param Bulletin bulletin
	 * @return
	 */
    @RequestMapping(method = RequestMethod.POST, path = "/creer")
    public ModelAndView saisieBulletin(@ModelAttribute("bulletinSalaire") BulletinSalaire bulletin) {
        
        ModelAndView mv = new ModelAndView();
        
        bulletinRepo.save(bulletin);

		mv.setViewName("/creationReussie");
        	        
        return mv;
    }
    
    /**
     * Requête Get : Lister les bulletins de salaires présents dans la base de données.
     * @return
     */
    @RequestMapping(method = RequestMethod.GET, path ="/lister")
    @Transactional(readOnly = true)
    public ModelAndView listerBulletins(){
    	ModelAndView mv = new ModelAndView();
    	
		mv.setViewName("bulletins/listerBulletins");
		
		
		List<BulletinSalaire> listeBulletins = bulletinRepo.findAll();
				
		mv.addObject("listeCalculSurBulletin", 
				listeBulletins.stream()
							.map(b -> new CalculSurBulletin(b, calcRemSimple.calculer(b)))
							.collect(Collectors.toList()));
    	
    	return mv;	
    }
    
//    @RequestMapping(method = RequestMethod.GET, path ="/lister")
//    @Transactional(readOnly =true)
//    public ModelAndView visualiserBulletin() {
//    	ModelAndView mv = new ModelAndView();
//    	
//    	mv.setViewName("visualiserBulletin");
//    	
//    	
//    	
//    	
//    	
//    	return mv;
//    }
    

}
