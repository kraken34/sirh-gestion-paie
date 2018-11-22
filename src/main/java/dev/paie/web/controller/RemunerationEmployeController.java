package dev.paie.web.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.Matricule;
import dev.paie.entite.RemunerationEmploye;
import dev.paie.repository.EntrepriseRepository;
import dev.paie.repository.GradeRepository;
import dev.paie.repository.ProfilRemunerationRepository;
import dev.paie.repository.RemunerationEmployeRepository;

@Controller
@RequestMapping("/employes")
public class RemunerationEmployeController {

	@Autowired
	private EntrepriseRepository listeEntrepriseRepos;
	@Autowired
	private ProfilRemunerationRepository listeProfilRepos;
	@Autowired
	private GradeRepository listeGradeRepos;
	@Autowired
	private RemunerationEmployeRepository remEmpRepo;

	@RequestMapping(method = RequestMethod.GET, path = "/creer")
	public ModelAndView creerEmploye() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/creerEmploye");
		mv.addObject("prefixMatricule", "M00");
		mv.addObject("employe", new RemunerationEmploye());
		mv.addObject("listeEntreprises", listeEntrepriseRepos.findAll());
		mv.addObject("listeProfils", listeProfilRepos.findAll());
		mv.addObject("listeGrades", listeGradeRepos.findAll());
		return mv;
	}

	@RequestMapping(method = RequestMethod.POST, path = "/creer")
	public ModelAndView submit(@ModelAttribute("employe") RemunerationEmploye employe) {
		LocalDateTime dateT = LocalDateTime.now();
		employe.setDate(dateT);
		RestTemplate rt = new RestTemplate();
		Matricule[] result = rt.getForObject("http://collegues-api.cleverapps.io/collegues?matricule="+ employe.getMatricule(), Matricule[].class);
		if (result.length == 0) {
			return creerEmploye();
		} else {
			remEmpRepo.save(employe);
			return creerEmploye();
		}
	}

	@RequestMapping(method = RequestMethod.GET, path = "/lister")
	public ModelAndView listerEmploye() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/listerEmploye");
		mv.addObject("employe", new RemunerationEmploye());
		mv.addObject("listeEmployes", remEmpRepo.findAll());
		return mv;
	}

}