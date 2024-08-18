package com.projet.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.app.model.Proposition;

@Repository
public interface PropositionRepository extends JpaRepository<Proposition, Long>{

}
