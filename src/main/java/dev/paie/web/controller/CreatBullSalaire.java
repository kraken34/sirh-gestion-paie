package dev.paie.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import dev.paie.entite.BulletinSalaire;
import dev.paie.repository.CreateBullSalaire;

@Controller
@RequestMapping("/salaire")
public class CreatBullSalaire {

	@Autowired CreateBullSalaire createBullSalRepo;
	
	@RequestMapping(method = RequestMethod.GET, path = "/creation")
    public ModelAndView creerBullSal() {

		ModelAndView mv = new ModelAndView();
        
        mv.setViewName("employes/creerBullSal");
        
        List<BulletinSalaire> listBullSal = createBullSalRepo.findAll();
        
        mv.addObject("bulletin", new BulletinSalaire());
        
        mv.addObject("listBullSal", listBullSal);
		
        return mv;
	}
	
}
