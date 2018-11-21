package dev.paie.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.paie.entite.Cotisation;

@Repository
public interface CotisationRepository extends JpaRepository<Cotisation, Integer> {



}


