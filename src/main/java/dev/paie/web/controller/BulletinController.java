package dev.paie.web.controller;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Cotisation;
import dev.paie.entite.ProfilRemuneration;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.repository.BulletinRepository;
import dev.paie.repository.PeriodeRepository;
import dev.paie.repository.RemunerationRepository;
import dev.paie.service.CalculerRemunerationServiceSimple;

@Controller
@RequestMapping("/bulletin")
public class BulletinController {

	@Autowired private BulletinRepository bulletinRepo;
	@Autowired private PeriodeRepository periodeRepo;
	@Autowired private RemunerationRepository remunerationRepo;

	
	@Autowired
	private CalculerRemunerationServiceSimple calRemSimple;
	
	
		

	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	// @GetMapping("/creer")
	public ModelAndView creerBulletin() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletin/creerBulletin");

		// Code Chloé
		mv.addObject("bulletin", new BulletinSalaire());
		
		mv.addObject("listePeriode", periodeRepo.findAll());
		mv.addObject("listeEmployes", remunerationRepo.findAll());
		return mv;
	}
	
	
	
	// Créer bulletin
	@RequestMapping(method = RequestMethod.POST, path = "/creer")
	public ModelAndView submit(@ModelAttribute("bulletin") BulletinSalaire bulletinSalaire) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletin/ok");

		bulletinRepo.save(bulletinSalaire);

		return mv;
	}
	
	
	// Lister bulletin
	@RequestMapping(method = RequestMethod.GET, path ="/lister")
	@Transactional
    public ModelAndView listerBulletin() {

		ModelAndView mv = new ModelAndView();
    	mv.setViewName("/bulletin/listerBulletin");
    	
    	List<BulletinSalaire> bulletins = bulletinRepo.findAll();
    	Map<BulletinSalaire, String> salairesBrut = new HashMap<>();
    	Map<BulletinSalaire, String> salairesNet = new HashMap<>();
    	Map<BulletinSalaire, String> salairesNetImposable = new HashMap<>();
    	
    	for(BulletinSalaire bulletin:bulletins) {
    		ResultatCalculRemuneration result = calRemSimple.calculer(bulletin);
    		salairesBrut.put(bulletin, result.getSalaireBrut());
    		salairesNet.put(bulletin, result.getNetAPayer());
    		salairesNetImposable.put(bulletin, result.getNetImposable());
    	}

    	mv.addObject("listeBulletin", bulletins);
    	mv.addObject("calRemSimple", calRemSimple);
    	mv.addObject("salairesBrut", salairesBrut);
    	mv.addObject("salairesNet", salairesNet);
    	mv.addObject("salairesNetImposable", salairesNetImposable);
  	
    	return mv;
    
    }
	
	
	// Visualiser bulletin
	@GetMapping("/visualiser")
	public ModelAndView visualiserBulletin(@RequestParam("idBulletin") int idBulletin) {
		
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/bulletin/visualiserbulletin");
		
		BulletinSalaire bulletin = bulletinRepo.findOne(idBulletin);
		mv.addObject("bulletin", bulletin);
   	
//    	ResultatCalculRemuneration resCalRem = calRemSimple.calculer(bulletin);
//    	mv.addObject("calSalaireBase", resCalRem.getSalaireDeBase());
//    	
//    	mv.addObject("calCotPatronales", resCalRem.getTotalCotisationsPatronales());		
    	
//    	List<Cotisation> cotisations = proRem.getCotisations();
//		Map<Cotisation, BigDecimal> tauxSalarial = new HashMap<>();
//    	Map<Cotisation, BigDecimal> tauxPatronal = new HashMap<>();
//    	
//		for(Cotisation cotisation: cotisations) {
//			tauxSalarial.put(cotisation, resCotisation.getTauxSalarial());
//			tauxPatronal.put(cotisation, resCotisation.getTauxPatronal());
//		}
//
//		mv.addObject("tauxSalarial", tauxSalarial);
//		mv.addObject("tauxPatronal", tauxPatronal);
//		
		return mv;
		
	}
	
	
	
	

}
