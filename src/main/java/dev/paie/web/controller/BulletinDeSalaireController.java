package dev.paie.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.ResultatCalculRenumeration;
import dev.paie.repository.BulletinSalaireRepository;
import dev.paie.repository.PeriodeRepository;
import dev.paie.repository.RemunerationEmployeRepository;
import dev.paie.service.CalculRenumerationService;

@Controller
@RequestMapping("/bulletins")
public class BulletinDeSalaireController {
	
	@Autowired
	private BulletinSalaireRepository bulletinRepo;

	@Autowired
	private PeriodeRepository listePeriode;

	@Autowired
	private RemunerationEmployeRepository matricule;
	
	@Autowired
	CalculRenumerationService calculSrv;

	@RequestMapping(method = RequestMethod.GET, path = "/creer")

	public ModelAndView creerBulletin() {

		ModelAndView mv = new ModelAndView();
		
        mv.addObject("bulletin", new BulletinSalaire());
		mv.addObject("matricule", matricule.findAll());
		mv.addObject("periode", listePeriode.findAll());
		mv.addObject("primeExceptionnele");
		mv.setViewName("bulletins/creerBulletin");

		return mv;

	}

	@RequestMapping(method = RequestMethod.POST, path = "/creer")

	public ModelAndView envoyer(@ModelAttribute("bulletin") BulletinSalaire bulletin) {

	bulletinRepo.save(bulletin);

		return creerBulletin();
	}

	@RequestMapping(method = RequestMethod.GET, path="/lister")

	public ModelAndView listerBulletin(){

		ModelAndView mv = new ModelAndView();
		
		mv.setViewName("bulletins/listebulletin");
		
		Map<BulletinSalaire, ResultatCalculRenumeration> map = calculSrv.calculerTout();
		
		mv.addObject("map", map);

		return mv;

		

		

	}
}
