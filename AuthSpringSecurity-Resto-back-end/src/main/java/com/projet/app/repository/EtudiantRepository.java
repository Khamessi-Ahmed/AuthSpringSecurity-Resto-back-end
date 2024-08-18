package com.projet.app.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.app.model.Etudiant;



@Repository
public interface EtudiantRepository extends JpaRepository<Etudiant,Long>{
	Optional<Etudiant> findByNumeroCarte(String numeroCarte);

	Optional<Etudiant> findByCodeSecurite(int codeSecuriteSource);
	
}
