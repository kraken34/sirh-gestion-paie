package dev.paie.web.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import dev.paie.entite.Cotisation;
import dev.paie.repository.CotisationRepository;

@RestController
public class CotisationController {

	@Autowired CotisationRepository cotisationRepository;
	
	@RequestMapping(value="/api/cotisations", method = RequestMethod.GET)
	public List<Cotisation> findCotisations() {
		return cotisationRepository.findAll();
	}
	
	@GetMapping("/api/cotisations/{code}")
    public ResponseEntity<?> findCotisation(@PathVariable String code) {
		Cotisation c = this.cotisationRepository.findByCode(code);
		if(c!=null)
			return ResponseEntity.ok(c);
		else
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("{\"message\":\"Code de cotisations non trouvé\"}");
    }
	
	@PostMapping("/api/cotisations")
	public void createCotisation(@RequestBody Cotisation c) {
		this.cotisationRepository.save(c);
	}
	
	@PutMapping("/api/cotisations/{code}")
    public void updateCotisation(@PathVariable String code, @RequestBody Cotisation c) {
		Cotisation cOrigin = this.cotisationRepository.findByCode(code);
		cOrigin.setLibelle(c.getLibelle());
		cOrigin.setImposable(c.getImposable());
		cOrigin.setTauxPatronal(c.getTauxPatronal());
		cOrigin.setTauxSalarial(c.getTauxSalarial());
		this.cotisationRepository.save(cOrigin);
    }
	
	@DeleteMapping("/api/cotisations/{code}")
	public void deleteCotisation(@PathVariable String code) {
		Cotisation c = this.cotisationRepository.findByCode(code);
		this.cotisationRepository.delete(c);
	}
	

}
