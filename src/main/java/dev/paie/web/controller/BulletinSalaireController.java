package dev.paie.web.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.BulletinSalaire;
import dev.paie.entite.Collegue;
import dev.paie.entite.Cotisation;
import dev.paie.entite.ResultatCalculRemuneration;
import dev.paie.repository.BulletinSalaireRepository;
import dev.paie.repository.PeriodeRepository;
import dev.paie.repository.RemunerationEmployeRepository;
import dev.paie.service.CalculerRemunerationServiceSimple;
import dev.paie.util.PaieUtils;

@Controller
@RequestMapping("/bulletin")
public class BulletinSalaireController {

	@Autowired
	private RemunerationEmployeRepository employeRepository;
	@Autowired
	private PeriodeRepository periodeRepository;
	@Autowired
	private BulletinSalaireRepository bulletinSalaireRepository;
	@Autowired
	private CalculerRemunerationServiceSimple calcRemService;

	@GetMapping("/creer")
	public ModelAndView creerBulletin() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletin/creerBulletin");

		mv.addObject("bulletin", new BulletinSalaire());

		mv.addObject("listePeriodes", periodeRepository.findAll());
		mv.addObject("listeEmployes", employeRepository.findAll());

		return mv;
	}

	@PostMapping("/creer")
	public ModelAndView submit(@ModelAttribute("bulletin") BulletinSalaire bulletinSalaire) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/menu/index");

		bulletinSalaireRepository.save(bulletinSalaire);

		return mv;
	}

	@GetMapping(path = "/lister")
	@Transactional
	public ModelAndView listerEmploye() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletin/listerBulletin");

		List<BulletinSalaire> bulletins = bulletinSalaireRepository.findAll();

		Map<BulletinSalaire, String> salairesBrut = new HashMap<>();
		Map<BulletinSalaire, String> salairesNet = new HashMap<>();
		Map<BulletinSalaire, String> salairesNetImposable = new HashMap<>();

		for (BulletinSalaire bulletin : bulletins) {
			ResultatCalculRemuneration result = calcRemService.calculer(bulletin);

			salairesBrut.put(bulletin, result.getSalaireBrut());
			salairesNet.put(bulletin, result.getNetAPayer());
			salairesNetImposable.put(bulletin, result.getNetImposable());
		}

		mv.addObject("listBulletins", bulletins);
		mv.addObject("salairesBrut", salairesBrut);
		mv.addObject("salairesNet", salairesNet);
		mv.addObject("salairesNetImposable", salairesNetImposable);

		return mv;
	}

	@GetMapping(path = "/visualiser")
	@Transactional
	public ModelAndView afficherBulletin(@RequestParam("idBulletin") int idBulletin) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("bulletin/visualiserBulletin");

		BulletinSalaire bulletin = bulletinSalaireRepository.findOne(idBulletin);
		ResultatCalculRemuneration resCalcRem = calcRemService.calculer(bulletin);

		List<Cotisation> cotisations = bulletin.getRemunerationEmploye().getProfilRemuneration().getCotisations();
		mv.addObject("cotisations", cotisations);

		List<Cotisation> cotImposables = new ArrayList<>();
		List<Cotisation> cotNonImposables = new ArrayList<>();
		for (Cotisation cotisation : cotisations) {
			if (cotisation.getImposable()) {
				cotImposables.add(cotisation);
			} else {
				cotNonImposables.add(cotisation);
			}
		}

		Map<Cotisation, String> montantSalarial = new HashMap<>();
		Map<Cotisation, String> cotisationsPatronales = new HashMap<>();
		for (Cotisation cot : cotisations) {
			BigDecimal tauxSalarial = Optional.ofNullable(cot.getTauxSalarial()).orElse(new BigDecimal("0"));
			BigDecimal cotPatronales = Optional.ofNullable(cot.getTauxSalarial()).orElse(new BigDecimal("0"));
			montantSalarial.put(cot,
					PaieUtils.formaterBigDecimal(new BigDecimal(resCalcRem.getSalaireBrut()).multiply(tauxSalarial)));
			cotisationsPatronales.put(cot,
					PaieUtils.formaterBigDecimal(new BigDecimal(resCalcRem.getSalaireBrut()).multiply(cotPatronales)));
		}

		RestTemplate rt = new RestTemplate();
		Collegue collegue = rt.getForObject("http://collegues-api.cleverapps.io/collegues?matricule="
				+ bulletin.getRemunerationEmploye().getMatricule(), Collegue[].class)[0];

		mv.addObject("collegue", collegue);
		mv.addObject("montantSalarial", montantSalarial);
		mv.addObject("cotisationsPatronales", cotisationsPatronales);
		mv.addObject("bulletin", bulletin);
		mv.addObject("resCalcRem", resCalcRem);
		mv.addObject("cotImposables", cotImposables);
		mv.addObject("cotNonImposables", cotNonImposables);

		return mv;
	}
}
