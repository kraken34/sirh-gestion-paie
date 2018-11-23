package dev.paie.web.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.repository.BulletinSalaireRepository;
import dev.paie.repository.PeriodeRepository;
import dev.paie.repository.RemunerationEmployeRepository;
import dev.paie.service.CalculerRemunerationService;

@Controller
@RequestMapping("/bulletins")
public class BulletinSalaireController {

	@Autowired BulletinSalaireRepository bulletinSalaireRepository;
	@Autowired RemunerationEmployeRepository remunerationEmployeRepository;
	@Autowired PeriodeRepository periodeRepository;
	@Autowired CalculerRemunerationService calculBulletinService;
	
	@Secured({"ROLE_UTILISATEUR", "ROLE_ADMINISTRATEUR"})
	@RequestMapping(method=RequestMethod.GET, path="/creer")
	public ModelAndView creerBulletin() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletins/creerBulletin");
		mv.addObject("bulletin", new BulletinSalaire());
		
		mv.addObject("listePeriode", periodeRepository.findAll());
		mv.addObject("listeMatricule", remunerationEmployeRepository.findAll());
		return mv;
	}
	
	@Secured({"ROLE_UTILISATEUR", "ROLE_ADMINISTRATEUR"})
	@RequestMapping(method=RequestMethod.POST, path="/creer")
	public ModelAndView ajouterBulletin(@ModelAttribute("bulletin") BulletinSalaire bulletin) {
		bulletinSalaireRepository.save(bulletin);
		return creerBulletin();
	}
	
	@Secured({"ROLE_UTILISATEUR", "ROLE_ADMINISTRATEUR"})
	@RequestMapping(method=RequestMethod.GET, path="/lister")
	@Transactional
	public ModelAndView listerBulletins() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletins/listerBulletins");
		mv.addObject("mapBulletins", calculBulletinService.calculer(bulletinSalaireRepository.findAll()));
		return mv;
	}
	
	@Secured({"ROLE_UTILISATEUR", "ROLE_ADMINISTRATEUR"})
	@RequestMapping(method=RequestMethod.GET, path="/afficher")
	@Transactional
	public ModelAndView afficherBulletin(@RequestParam("idBulletin") Integer id) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletins/afficherBulletin");
		BulletinSalaire bulletin = bulletinSalaireRepository.findOne(id);
		ResultatCalculRemuneration calcul = calculBulletinService.calculer(bulletin);
		mv.addObject("bulletin", bulletin);
		mv.addObject("calcul", calcul);
		return mv;
	}

}
