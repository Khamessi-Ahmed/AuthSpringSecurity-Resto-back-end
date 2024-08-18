package com.projet.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.projet.app.model.Paiement;

public interface PaiementRepository extends JpaRepository<Paiement, Long> {

}
