
package dev.paie.web.controller.api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import dev.paie.entite.Cotisation;
import dev.paie.repository.CotisationRepository;

@RestController
@RequestMapping("api/cotisations")
public class CotisationController {
	
	@Autowired CotisationRepository cotRepo;

    @GetMapping("/list")
    public ResponseEntity<List<Cotisation>> findAll() {
        List<Cotisation> allCotisation = cotRepo.findAll();

        return ResponseEntity.status(HttpStatus.OK).body(allCotisation);
    }
    
    @GetMapping("/list/{code}")
    public ResponseEntity<List<Cotisation>> findCotisation(@PathVariable String code) {
        List<Cotisation> cotisation = cotRepo.findByCode(code);
        return ResponseEntity.status(HttpStatus.OK).body(cotisation);
    }
    @PostMapping("/creer")
    public ResponseEntity create(@RequestBody Cotisation cotisation) {	
    	cotRepo.save(cotisation);
    	return ResponseEntity.status(HttpStatus.OK).body(cotRepo.findOne(cotisation.getId()));
    }

    @PostMapping("/modifier/{code}")
    public ResponseEntity<List<Cotisation>> updateCotisation(@PathVariable String code,@RequestBody Cotisation cotisation) {
    	List<Cotisation> listCotisation = cotRepo.findByCode(code);
    	for (Cotisation cotisation2 : listCotisation) {
    		cotisation.setId(cotisation2.getId());
    		cotRepo.save(cotisation);
		}	
    	return ResponseEntity.status(HttpStatus.OK).body(listCotisation);
    }
    
    
    @PostMapping("/supprimer/{code}")
    public void deleteCotisation(@PathVariable String code) {
        List<Cotisation> listCotisation = cotRepo.findByCode(code);
        for (Cotisation cotisation : listCotisation) {
        	cotRepo.delete(cotisation);
		}
    }
}