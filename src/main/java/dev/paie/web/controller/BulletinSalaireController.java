
package dev.paie.web.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Cotisation;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.repository.BulletinSalaireRepository;
import dev.paie.repository.PeriodeRepository;
import dev.paie.repository.RemunerationEmployeRepository;
import dev.paie.service.CalculerRemunerationService;
import dev.paie.util.PaieUtils;

@Controller
@RequestMapping("/bulletinsalaire")
public class BulletinSalaireController {

	@Autowired
	RemunerationEmployeRepository employe;

	@Autowired
	PeriodeRepository periode;

	@Autowired
	BulletinSalaireRepository bulletin;

	@Autowired
	CalculerRemunerationService remuneration;

	// @RequestMapping(method = RequestMethod.GET, path = "/creer")
	@GetMapping("/creer")
	@Secured("ROLE_ADMINISTRATEUR")
	public ModelAndView creerEmploye() {

		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletinSalaire/creerBulletin");
		mv.addObject("listePeriode", periode.findAll());
		mv.addObject("listeEnploye", employe.findAll());
		mv.addObject("bulletin", new BulletinSalaire());

		mv.addObject("employe", new RemunerationEmploye());
		return mv;
	}

	@PostMapping("/creer")
	@Transactional
	@Secured("ROLE_ADMINISTRATEUR")
	public String submitForm(@ModelAttribute("bulletin") BulletinSalaire bulletinSalaire) {
		bulletinSalaire.setDateHeureCreation(LocalDateTime.now());
		bulletin.save(bulletinSalaire);
		return "redirect:list";
	}

	@RequestMapping("/list")
	@Transactional
	@Secured({"ROLE_UTILISATEUR", "ROLE_ADMINISTRATEUR"})
	public ModelAndView listBulletin() {
		ModelAndView mv = new ModelAndView();
		Map<BulletinSalaire, ResultatCalculRemuneration> mapBulletinCalcule = new HashMap<>();
		for (BulletinSalaire bulletinFor : bulletin.findAll()) {
			mapBulletinCalcule.put(bulletinFor, remuneration.calculer(bulletinFor));
		}
		mv.setViewName("bulletinSalaire/listBulletin");
		mv.addObject("listeBulletin", mapBulletinCalcule);
		return mv;
	}

	@Transactional
	@RequestMapping("/find")
	@Secured("ROLE_ADMINISTRATEUR")
	public ModelAndView employerBulletinDetail(@RequestParam("key") int id) {
		ModelAndView mv = new ModelAndView();
		BulletinSalaire myBuletin = bulletin.findOne(id);
		ResultatCalculRemuneration calculeResult = remuneration.calculer(myBuletin);
		Map<Integer, List<String>> cotisationCalcule = cotisationCalcule(calculeResult,myBuletin);
		mv.setViewName("bulletinSalaire/bulletin");
		mv.addObject("bulletin", myBuletin);
		mv.addObject("calculeSalaire", calculeResult);
		mv.addObject("calculeCotisation", cotisationCalcule);
		return mv;
	}
	
	@Transactional
	@Secured({"ROLE_UTILISATEUR", "ROLE_ADMINISTRATEUR"})
	public Map<Integer, List<String>> cotisationCalcule(ResultatCalculRemuneration calculeResult ,BulletinSalaire myBuletin){
		Map<Integer, List<String>> cotisationCalcule = new HashMap<>();
		for (Cotisation cotisation : myBuletin.getRemunerationEmploye().getProfilRemuneration().getCotisations()) {
			List<String> calcule = new ArrayList<>();
			if (cotisation.getTauxSalarial() != null) {
				calcule.add(PaieUtils.formaterBigDecimal(
						new BigDecimal(calculeResult.getSalaireBrut()).multiply(cotisation.getTauxSalarial())));
			} else {
				calcule.add(null);
			}
			if (cotisation.getTauxPatronal() != null) {
				calcule.add(PaieUtils.formaterBigDecimal(
						new BigDecimal(calculeResult.getSalaireBrut()).multiply(cotisation.getTauxPatronal())));
			} else {
				calcule.add(null);
			}
			cotisationCalcule.put(cotisation.getId(), calcule);
		}
		return cotisationCalcule;
	}
}