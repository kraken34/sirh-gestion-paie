package dev.paie.web.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import dev.paie.entite.Cotisation;
import dev.paie.repository.CotisationRepository;

@RestController
@RequestMapping("api/cotisations")
public class CotisationApiController {

	@Autowired
	private CotisationRepository cotisationRepository;

	@RequestMapping(value = "", method = RequestMethod.GET)
	public List<Cotisation> findCotisations() {
		return cotisationRepository.findAll();
	}

	@RequestMapping(value = "/{code}", method = RequestMethod.GET)
	public ResponseEntity<?> findCotisation(@PathVariable String code) {
		Optional<Cotisation> cotisation = cotisationRepository.findByCode(code);

		if (cotisation.isPresent()) {
			return ResponseEntity.ok(cotisation.get());
		}
		return ResponseEntity.badRequest().body("message : Code de cotisations non trouvé");
	}

	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public Cotisation createCotisation(@RequestBody Cotisation cotisation) {
		return cotisationRepository.save(cotisation);
	}

	@RequestMapping(value = "", method = RequestMethod.PUT)
	@ResponseBody
	public Cotisation updateCotisation(@RequestBody Cotisation cotisation) {
		Cotisation cot = cotisationRepository.findByCode(cotisation.getCode()).get();

		cot.setImposable(cotisation.getImposable());
		cot.setLibelle(cotisation.getLibelle());
		cot.setTauxPatronal(cotisation.getTauxPatronal());
		cot.setTauxSalarial(cotisation.getTauxSalarial());

		return cotisationRepository.save(cot);
	}

	@RequestMapping(value = "", method = RequestMethod.DELETE)
	public void deleteCotisation(@RequestParam String code) {
		cotisationRepository.delete(cotisationRepository.findByCode(code).get());
	}
}
