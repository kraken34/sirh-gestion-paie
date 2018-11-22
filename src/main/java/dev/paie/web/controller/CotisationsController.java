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
public class CotisationsController {

	@Autowired
	CotisationRepository cotisationRepository;

	@RequestMapping(value = "/api/cotisations/", method = RequestMethod.GET)
	// @ResponseBody // plus besoin de mettre cette annotation
	public ResponseEntity<List<Cotisation>> findCotisation() {

		return ResponseEntity.status(200).body(cotisationRepository.findAll());
	}

	@GetMapping("/api/cotisations/{code}")
	public ResponseEntity<?> findCotisations(@PathVariable String code) {
		Cotisation cotiser = cotisationRepository.findByCode(code);
		// En précisant en "dur" le code de la réponse via la méthode statique status
		// return ResponseEntity.status(200).body(allGrades);
		if (cotiser != null) {
			return ResponseEntity.ok(cotiser);
		} else {
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("{ \"message\" : \"Code de cotisations non trouvé\" }");
		}
		// HttpStatus est une énumération regroupant les codes HTTP usuels

	}

	@PostMapping("/api/cotisations/")
	public void createCotisation(@RequestBody Cotisation cotis) {

		cotisationRepository.save(cotis);
	}

	@PutMapping("/api/cotisations/{code}")
	public void updateCotisations(@RequestBody Cotisation cotis, @PathVariable String code) {
		Cotisation cotiser = cotisationRepository.findByCode(code);
		cotiser.setLibelle(cotis.getLibelle());
		cotiser.setTauxPatronal(cotis.getTauxPatronal());
		cotiser.setTauxSalarial(cotis.getTauxSalarial());
		cotiser.setImposable(cotis.getImposable());
		cotisationRepository.save(cotiser);
	}

	@DeleteMapping("/api/cotisations/{code}")
	public void deleteCotisationsById(@PathVariable String code) {
		Cotisation cotiser = cotisationRepository.findByCode(code);
		cotisationRepository.delete(cotiser);

	}
}
