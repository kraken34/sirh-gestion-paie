package dev.paie.api.controller;

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
@RequestMapping("/api")
public class CotisationApiController {
	@Autowired CotisationRepository cotisationRepos;
	
	@RequestMapping("/cotisations")
	public ResponseEntity<List<Cotisation>> findAll() {
		List<Cotisation> cotisations = cotisationRepos.findAll();
		
		return ResponseEntity.ok(cotisations);
	}
	
    @GetMapping("/cotisations/{code}")
    public ResponseEntity<List<Cotisation>> findCotisation(@PathVariable String code) {
        return ResponseEntity.ok(this.cotisationRepos.findByCode(code));
    }
	
	@PostMapping("/cotisations/creer")
    public ResponseEntity<Cotisation> createCotisation(@RequestBody Cotisation cotisation) {
        this.cotisationRepos.save(cotisation);
        return ResponseEntity.status(HttpStatus.OK).body(cotisationRepos.findOne(cotisation.getId()));
    }
	
	@PostMapping("/cotisations/modifier/{code}")
	public ResponseEntity<List<Cotisation>> updateCotisation(@PathVariable String code, @RequestBody Cotisation cotisation) {
		List<Cotisation> cotisations = cotisationRepos.findByCode(code);
		for (Cotisation cotisation2: cotisations) {
			cotisation.setId(cotisation2.getId());
			cotisationRepos.save(cotisation);
		}
		return ResponseEntity.status(HttpStatus.OK).body(cotisations);
	}
	
	@PostMapping("cotisations/supprimer/{code}")
	public void removeCotisation(@PathVariable String code) {
		List<Cotisation> cotisations = cotisationRepos.findByCode(code);
		for (Cotisation cotisation : cotisations) {
			cotisationRepos.delete(cotisation);
		}
	}
}
