package dev.paie.dao;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import dev.paie.entite.Cotisation;

@Repository
@ Transactional
public class CotisationDAO {
	
	@PersistenceContext 
	private EntityManager em;
	
	
	public void ajoutCotisation(Cotisation cotisation) {

		em.persist(cotisation);

	}
	

	public void mergeCotisation(Cotisation cotisation) {

		em.merge(cotisation);

	}

	public Cotisation getCotisationById(Integer id) {

		return em.find(Cotisation.class, id);

	}

}
