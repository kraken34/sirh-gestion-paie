/**
 * 
 */
/**
 * @author formation
 *
 */
package dev.paie.web.controller;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.RemunerationEmploye;
import dev.paie.repository.EntrepriseRepository;
import dev.paie.repository.GradeRepository;
import dev.paie.repository.ProfilRemunerationRepository;
import dev.paie.repository.RemunerationEmployeRepository;
import dev.paie.web.controller.api.Collegue;

@Controller
@RequestMapping("/employes")
public class RemunerationEmployeController {

	@Autowired
	RemunerationEmployeRepository employe;
	@Autowired
	EntrepriseRepository entreprise;
	@Autowired
	ProfilRemunerationRepository profil;
	@Autowired
	GradeRepository grade;

	// @RequestMapping(method = RequestMethod.GET, path = "/creer")
	@GetMapping("/creer")
	public ModelAndView creerEmploye() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/creerEmploye");
		mv.addObject("listeEntreprises", entreprise.findAll());
		mv.addObject("listProfil", profil.findAll());
		mv.addObject("listGrade", grade.findAll());
		mv.addObject("employe", new RemunerationEmploye());
		return mv;
	}

	@PostMapping("/creer")
	public ModelAndView submitForm(@ModelAttribute("employe") RemunerationEmploye remunerationEmploye) {
		ModelAndView mv = new ModelAndView();
		remunerationEmploye.setDateHeureCreation(LocalDateTime.now());
		RestTemplate rt = new RestTemplate();
		Collegue[] result = rt.getForObject("http://collegues-api.cleverapps.io/collegues?matricule={matricule}",
				Collegue[].class, remunerationEmploye.getMatricule());
		if (result.length>0) {
			employe.save(remunerationEmploye);
			mv.setViewName("redirect:list");
		} else {
			mv.setViewName("employes/creerEmploye");
			mv.addObject("error", "le matricule n'exist pas dans l'API");
			mv.addObject("listeEntreprises", entreprise.findAll());
			mv.addObject("listProfil", profil.findAll());
			mv.addObject("listGrade", grade.findAll());
		}
		return mv;
	}

	@RequestMapping("/list")
	public ModelAndView listEmploye() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("employes/listEmployes");
		mv.addObject("listeEmploye", employe.findAll());
		return mv;
	}

}