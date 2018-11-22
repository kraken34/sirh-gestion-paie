package dev.paie.web.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.Collegue;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.repository.EntrepriseRepository;
import dev.paie.repository.GradeRepository;
import dev.paie.repository.ProfilRepository;
import dev.paie.repository.RemunerationEmployeRepository;

@Controller
@RequestMapping("/employes")
public class RemunerationEmployeController {

	@Autowired
	private EntrepriseRepository entrepriseRepository;
	@Autowired
	private ProfilRepository profilRepository;
	@Autowired
	private GradeRepository gradeRepository;
	@Autowired
	private RemunerationEmployeRepository remunerationEmployeRepository;

	@GetMapping(path = "/creer")
	public ModelAndView creerEmploye() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/creerEmploye");

		mv.addObject("employe", new RemunerationEmploye());

		mv.addObject("listeEntreprises", entrepriseRepository.findAll());
		mv.addObject("listeProfiles", profilRepository.findAll());
		mv.addObject("listeGrades", gradeRepository.findAll());

		return mv;
	}

	@PostMapping(path = "/creer")
	public ModelAndView submit(@ModelAttribute("employe") RemunerationEmploye remunerationEmploye) {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("/menu/index");

		RestTemplate rt = new RestTemplate();
		Collegue[] collegue = rt.getForObject("http://collegues-api.cleverapps.io/collegues?matricule=" + remunerationEmploye.getMatricule(),
				Collegue[].class);

		if (collegue.length > 0) {
			remunerationEmployeRepository.save(remunerationEmploye);
			mv.setViewName("/menu/index");
		} else {
			mv.setViewName("/employes/creer");//ou rediriger vers un message d'erreur [...]
		}

		return mv;
	}

	@GetMapping(path = "/lister")
	public ModelAndView listerEmploye() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/listerEmploye");

		mv.addObject("listEmployes", remunerationEmployeRepository.findAll());

		return mv;
	}
}
