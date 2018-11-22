package dev.paie.web.controller.api;

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

import org.springframework.web.bind.annotation.RestController;

import dev.paie.entite.Cotisation;
import dev.paie.repository.CotisationRepository;

@RestController
public class ListeCotisationController {

	@Autowired
	private CotisationRepository cotisRepo;

	@GetMapping("/api/cotisations")

	// @ResponseBody // plus besoin de mettre cette annotation

	public ResponseEntity<List<Cotisation>> findAll() {

		List<Cotisation> listCotisation = cotisRepo.findAll();

		return ResponseEntity.status(HttpStatus.OK).body(listCotisation);

	}

	@GetMapping("/api/cotisations/{code}")
	public ResponseEntity<?> findcotisation(@PathVariable String code) {
		Cotisation cotisation = this.cotisRepo.findByCode(code);

		if (cotisation != null) {

			return ResponseEntity.ok(cotisation);
		} else {

			return ResponseEntity.badRequest().body("Le code " + code + " n'existe pas!");
		}

	}

	@PostMapping("/api/cotisations")
	 // parser l'objet Cotisation
	public Cotisation findCotisation( @RequestBody Cotisation cotisation) {

		return cotisRepo.save(cotisation);

	}
	
	@PutMapping("/api/cotisations/{code}")
	 // parser l'objet Cotisation
	public Cotisation putCotisation( @RequestBody Cotisation cotisation, @PathVariable String code) {
		
        
		Cotisation cotis = this.cotisRepo.findByCode(code);
		
		cotis.setCode(cotis.getCode());
		cotis.setLibelle(cotis.getLibelle());
		cotis.setTauxPatronal(cotis.getTauxPatronal());
		cotis.setTauxSalarial(cotis.getTauxSalarial());
		cotis.setImposable(cotis.getImposable());
		return cotisRepo.save(cotis);
	}
	@DeleteMapping("/api/cotisations/{code}")
	 //parser l'objet Cotisation
	public void deleteCotisation ( @PathVariable String code) {
		
		cotisRepo.delete(this.cotisRepo.findByCode(code)) ;
		
		
	}
		
}

