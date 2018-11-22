package api;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import dev.paie.entite.Cotisation;
import dev.paie.repository.CotisationRepository;

@Controller
@RequestMapping("api/cotisations")
public class CotisationApiController {

	@Autowired
	private CotisationRepository cotisationRepo;

	// Trouver la liste des cotisations au format JSON
	@RequestMapping(value = "", method = RequestMethod.GET)
	@ResponseBody // parser l'objet Cotisation
	public List<Cotisation> findCotisation() {
		return cotisationRepo.findAll();
	}
	
	
	

	// Retourne un objet cotisation à partir d'un code
	@RequestMapping(value = "/{code}", method = RequestMethod.GET)
	@ResponseBody
	public ResponseEntity<?> findCotisation(@PathVariable String code) {
		Cotisation cotisation = cotisationRepo.findByCode(code);

		// Renvoyer un message si le code de la cotisation n'est pas présent
		if ((cotisation == null) || (cotisation.equals("")) || (cotisation.equals(" "))) {
			return ResponseEntity.badRequest().body("Message : Code de cotisations non trouvé");
		} else {
			return ResponseEntity.ok(cotisationRepo.findByCode(code));
		}
	}
	
	
	

	// Insérer un objet cotisation en base de données
	@RequestMapping(value = "", method = RequestMethod.POST)
	@ResponseBody
	public Cotisation creerCotisation(@RequestBody Cotisation cotisation) {
		return cotisationRepo.save(cotisation);
	}

	
	
	// Mettre à jour un objet cotisation dans la base de données
	@RequestMapping(value = "/{code}", method = RequestMethod.PUT)
	@ResponseBody
	public ResponseEntity updateCotisation(@RequestBody Cotisation cotisationEnvoyee, @PathVariable String code) {
		Cotisation cotisationBase = cotisationRepo.findByCode(code);

		if(cotisationBase == null ) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("oooooooooooh");
		}
		
		cotisationEnvoyee.setId(cotisationBase.getId());
				
		return ResponseEntity.ok().body(cotisationEnvoyee);
	}
	
	

	// Supprimer une cotisation dont le code est fourni
	@RequestMapping(value = "/{code}", method = RequestMethod.DELETE)
	@ResponseBody
	public ResponseEntity deleteCotisation(@PathVariable String code) {
		Cotisation cotisationBase = cotisationRepo.findByCode(code);
		if(cotisationBase == null) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("La cotisation n'est pas supprimée");
		}
		cotisationRepo.delete(cotisationBase);
		return ResponseEntity.ok().body("La cotisation est supprimée");
	}
	
	
	
	
	
	
	
	
}
