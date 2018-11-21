package dev.paie.web.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class AccueilController {

	@GetMapping(path = "")
	public ModelAndView afficherAccueil() {
		ModelAndView mv = new ModelAndView();
		mv.setViewName("menu/index");

		return mv;
	}
}
