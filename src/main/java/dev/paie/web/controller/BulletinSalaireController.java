package dev.paie.web.controller;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Collegue;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.repository.BulletinSalaireRepository;
import dev.paie.repository.PeriodeRepository;
import dev.paie.repository.RemunerationEmployeRepository;
import dev.paie.service.CalculerRemunerationService;

@Controller
@RequestMapping("/bulletins")
public class BulletinSalaireController {

	@Autowired
	RemunerationEmployeRepository remunerationEmployeRepos;
	@Autowired
	PeriodeRepository periodeRepos;
	@Autowired
	BulletinSalaireRepository bulletinSalaireRepos;
	@Autowired
	CalculerRemunerationService calculeRemServ;

	@GetMapping("/creer-bulletin")
	public ModelAndView creerBulletin() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletins/creerBulletin");
		BulletinSalaire bulletin = new BulletinSalaire();
		mv.addObject("bulletin", bulletin);
		mv.addObject("listeRemunerationEmploye", remunerationEmployeRepos.findAll());
		mv.addObject("periode", periodeRepos.findAll());
		return mv;
	}
	
	@PostMapping("/creer-bulletin")
    public ModelAndView enregistrerBulletin(@ModelAttribute("bulletin") BulletinSalaire bulletinSalaire) {
		bulletinSalaire.setDateHeureCreation(LocalDateTime.now());
    	bulletinSalaireRepos.save(bulletinSalaire);
    	ModelAndView mv = new ModelAndView();
    	mv.setViewName("redirect:liste-bulletins");
		return mv;
    }
	
    @GetMapping("/liste-bulletins")
    @Transactional
    public ModelAndView afficherBulletins() {
    	ModelAndView mv = new ModelAndView();
    	mv.setViewName("bulletins/liste-bulletins");
    	// mv.addObject("listeBulletins", bulletinSalaireRepos.findAll());
    	Map<BulletinSalaire, ResultatCalculRemuneration> calculeSalaires = new HashMap<>();
    	for (BulletinSalaire bulletin : bulletinSalaireRepos.findAll()) {
			calculeSalaires.put(bulletin, calculeRemServ.calculer(bulletin));
		}
    	mv.addObject("listeBulletinsCalculs", calculeSalaires);
    	
		return mv;
    }
    
    @GetMapping("/bulletin")
    @Transactional
    public ModelAndView afficherBulletin(@RequestParam("id")int id) {
    	ModelAndView mv = new ModelAndView();
    	mv.setViewName("/bulletins/bulletin");
    	BulletinSalaire bulletin = bulletinSalaireRepos.findOne(id);
    	mv.addObject("bulletin", bulletin);
    	mv.addObject("calculeSalaires", calculeRemServ.calculer(bulletin));
    	RemunerationEmploye remunerationEmploye = bulletin.getRemunerationEmploye();
    	RestTemplate rt = new RestTemplate();
    	Collegue[] collegue = rt.getForObject("http://collegues-api.cleverapps.io/collegues?matricule={matricule}", Collegue[].class, remunerationEmploye.getMatricule());
    	mv.addObject("collegue", collegue);
    	return mv;
    }
}