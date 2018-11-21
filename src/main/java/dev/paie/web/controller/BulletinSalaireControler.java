/**
 * 
 */
package dev.paie.web.controller;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
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
import dev.paie.repository.CotisationRepository;
import dev.paie.repository.PeriodeRepository;
import dev.paie.repository.RemunerationEmployeRepository;
import dev.paie.service.CalculerRemunerationService;

/**
 * @author formation
 *
 */
@Controller
@RequestMapping("/bulletin")
public class BulletinSalaireControler {
	@Autowired
	BulletinSalaireRepository bulletinSalaireRepository;
	@Autowired
	RemunerationEmployeRepository remunerationRepository;
	@Autowired
	PeriodeRepository periodeRepository;
	@Autowired
	CalculerRemunerationService calculerRemunerationService;
	@Autowired
	CotisationRepository cotisationRepository;

	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	public ModelAndView creerBulletin() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletin/creerSalaires");

		mv.addObject("bulletin", new BulletinSalaire());
		mv.addObject("listePeriodes", periodeRepository.findAll());
		mv.addObject("listeMatricules", remunerationRepository.findAll());

		return mv;
	}

	@RequestMapping(method = RequestMethod.POST, path = "/creer")
	public ModelAndView submitForm(@ModelAttribute("bulletin") BulletinSalaire salaire) {
		bulletinSalaireRepository.save(salaire);
		// insérer en base avec repository
		return creerBulletin();
	}

	@RequestMapping(method = RequestMethod.GET, path = "/lister")
	public ModelAndView listerSalaire() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletin/listerSalaires");

		Map<BulletinSalaire, ResultatCalculRemuneration> result = calculerRemunerationService
				.recupererTousLesBulletinsAvecLesCalculs();

		mv.addObject("mapBulletinCalcul", result);

		return mv;
	}

	@RequestMapping(method = RequestMethod.GET, path = "/recapituler")
	@Transactional
	public ModelAndView recapitulerSalaire(@RequestParam("idBulletin") Integer idBulletin) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletin/recapitulatif");

		BulletinSalaire trouver = bulletinSalaireRepository.findOne(idBulletin);
		ResultatCalculRemuneration resultat = calculerRemunerationService.calculer(trouver);

		// TODO récupérer le bulletin de salaire à partir de idBulletin
		// TODO envoyer objet bulettin à la vue (via mv.addObject)
		mv.addObject("bulletin", trouver);
		mv.addObject("Brut", resultat);

		return mv;
	}
}
