package dev.paie.web.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.repository.BulletinSalaireRepository;
import dev.paie.repository.PeriodeRepository;
import dev.paie.repository.RemunerationEmployeRepository;

@Controller
@RequestMapping("/bulletins")
public class BulletinSalaireController {

	@Autowired
	private PeriodeRepository listePeriodes;
	@Autowired
	private RemunerationEmployeRepository listeEmployes;
	@Autowired
	private BulletinSalaireRepository bulSalRepo;

	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	public ModelAndView creerBulletin() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletins/creerBulletin");
		mv.addObject("bulletin", new BulletinSalaire());
		mv.addObject("listePeriodes", listePeriodes.findAll());
		mv.addObject("listeEmployes", listeEmployes.findAll());
		mv.addObject("prefixprimeExceptionnelle", "");
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST, path = "/creer")
	public ModelAndView submit(@ModelAttribute("bulletin") BulletinSalaire bulletin) {
		LocalDateTime dateT = LocalDateTime.now();
		bulletin.setDate(dateT);
		bulSalRepo.save(bulletin);
		return creerBulletin();
	}
	
	@RequestMapping(method = RequestMethod.GET, path = "/lister")
	public ModelAndView listerBulletin() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletins/listerBulletin");
		mv.addObject("bulletin", new BulletinSalaire());
		mv.addObject("listeBulletins", bulSalRepo.findAll());
		return mv;
	}
}