package dev.paie.webapi;

import java.net.URI;
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
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import dev.paie.entite.Cotisation;
import dev.paie.repository.CotisationRepository;

@RestController
public class CotisationController {
	
	@Autowired
	CotisationRepository cotisationRepo;

//	/**
//	 * Trouver une cotisation à partir de son Id
//	 * @param cotisationId
//	 * @return cotisation au format Json
//	 */
//	@RequestMapping(value = "/api/cotisations/{cotisationId}", method = RequestMethod.GET)
//	public Cotisation findCotisation(@PathVariable int cotisationId) {
//		
//		return cotisationRepo.findOne(cotisationId);
//	}
	

	/**
	 * Trouver une cotisation à partir de son Code
	 * @param cotisationId
	 * @return
	 */
    @GetMapping(value = "/api/cotisations/{cotisationCode}")
	public ResponseEntity<Object> findCotisationByCode(@PathVariable String cotisationCode) {
    	
    	Cotisation cotisationTrouve = cotisationRepo.findByCode(cotisationCode);
    	
    	if (cotisationTrouve==null) {
    		String codesDisponibles = cotisationRepo.findAll().stream().map(c->c.getCode()).reduce((c1,c2)->c1+" ; "+c2).get();
    		
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cotisations non trouvée. <br/> Les codes de cotisation disponibles sont les suivants :<br/><br/>"+codesDisponibles);
    	}
				
		return ResponseEntity.status(HttpStatus.OK).body(cotisationTrouve);
	}


	@GetMapping("/api/cotisations")
	public ResponseEntity<List<Cotisation>> findAll() {
		List<Cotisation> cotisations = this.cotisationRepo.findAll();

		// HttpStatus est une énumération regroupant les codes HTTP usuels
		return ResponseEntity.status(HttpStatus.OK).body(cotisations);
	}
	

	@PostMapping("/api/cotisations")
	public ResponseEntity<Cotisation> postCotisations(@RequestBody Cotisation cotisation) {
		
		cotisationRepo.save(cotisation);
		
		//on récupère l'Uri permettant l'accés à la donnée.
		URI uri= MvcUriComponentsBuilder
                .fromMethodCall(
                        MvcUriComponentsBuilder.on(CotisationController.class).findCotisationByCode(cotisation.getCode())
                ).build().encode().toUri();

		// HttpStatus est une énumération regroupant les codes HTTP usuels
		//la réponse possède en entête l'uri où la cotisation a été enregistrée. Le corps de la réponse contient la cotisation créée
		return ResponseEntity.created(uri).body(cotisation); 
	}
	
	/**
	 * Mise à jour d'une cotisation à partir de son code.
	 * @param cotisation json de la mise à jour de la cotisation
	 * @param cotisationCode Code de la cotisation à mettre à jour.
	 * @return
	 */
	@PutMapping("/api/cotisations/{cotisationCode}")
	public ResponseEntity<Object> updateCotisations(@RequestBody Cotisation cotisation, @PathVariable String cotisationCode) {
		Cotisation cotisRecherche = cotisationRepo.findByCode(cotisationCode);
		
		if(cotisRecherche == null) {

    		String codesDisponibles = cotisationRepo.findAll().stream().map(c->c.getCode()).reduce((c1,c2)->c1+" ; "+c2).get();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cotisations non trouvée. <br/> Les codes de cotisation disponibles sont les suivants :<br/><br/>"+codesDisponibles);
	    	
		}
		
		cotisation.setId(cotisRecherche.getId());
		cotisationRepo.save(cotisation);
		return ResponseEntity.ok().body(cotisation);
	}
	
	/**
	 * Suppression
	 */
	@DeleteMapping("/api/cotisations/{cotisationCode}")
	public ResponseEntity<String> suppressionCotisation(@PathVariable String cotisationCode){
		
		Cotisation cotisRecherche = cotisationRepo.findByCode(cotisationCode);
		
		if(cotisRecherche == null) {
    		String codesDisponibles = cotisationRepo.findAll().stream().map(c->c.getCode()).reduce((c1,c2)->c1+" ; "+c2).get();
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Cotisations non trouvée. <br/> Les codes de cotisation disponibles sont les suivants :<br/><br/>"+codesDisponibles);	    	
		}
		
		cotisationRepo.delete(cotisRecherche.getId());
		
		return ResponseEntity.ok().body("Cotisation supprimée avec succés.");
	}

}
