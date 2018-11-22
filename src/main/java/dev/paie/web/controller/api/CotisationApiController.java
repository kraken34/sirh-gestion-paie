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
//@RequestMapping("/api/cotisations")
public class CotisationApiController {
	@Autowired
	private CotisationRepository cotisRepo;

	@GetMapping("/api/cotisations")
	// @ResponseBody // parser l'objet Client Si pas RestController
	public ResponseEntity<List<Cotisation>> recupCotisations() {
		List<Cotisation> listeCotis = cotisRepo.findAll();
		return ResponseEntity.status(HttpStatus.OK).body(listeCotis);
	}

	@GetMapping("/api/cotisations/{code}")
	// @ResponseBody // parser l'objet Client Si pas RestController
	public ResponseEntity<?> recupCotisations(@PathVariable String code) {
		Cotisation cotisation = this.cotisRepo.findByCode(code);
		if (cotisation != null) {
			return ResponseEntity.ok(this.cotisRepo.findByCode(code));
		} else {
			return ResponseEntity.badRequest().body("message : Le code " + code + " n'existe pas");
		}
	}

	@PostMapping("/api/cotisations")
	// @ResponseBody // plus besoin de mettre cette annotation
	public Cotisation create(@RequestBody Cotisation cot) {
		return cotisRepo.save(cot);
	}

	@PutMapping("/api/cotisations/{code}")
	// @ResponseBody // plus besoin de mettre cette annotation
	public Cotisation modifier(@RequestBody Cotisation cot, @PathVariable String code) {
		Cotisation coti = this.cotisRepo.findByCode(code);
		coti.setLibelle(cot.getLibelle());
		coti.setTauxSalarial(cot.getTauxPatronal());
		coti.setTauxSalarial(cot.getTauxSalarial());
		coti.setImposable(cot.getImposable());
		return cotisRepo.save(coti);
	}

	@DeleteMapping("/api/cotisations/{code}")
	// @ResponseBody // plus besoin de mettre cette annotation
	public void modifier(@PathVariable String code) {
		cotisRepo.delete(this.cotisRepo.findByCode(code));
	}

}
