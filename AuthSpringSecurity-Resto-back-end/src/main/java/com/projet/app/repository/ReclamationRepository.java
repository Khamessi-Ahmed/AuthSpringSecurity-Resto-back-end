package com.projet.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.app.model.Reclamation;

@Repository
public interface ReclamationRepository extends JpaRepository<Reclamation, Long>{

}
