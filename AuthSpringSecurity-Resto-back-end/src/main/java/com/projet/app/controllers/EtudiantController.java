package com.projet.app.controllers;



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

import com.projet.app.dto.RechargerCarteRequest;
import com.projet.app.dto.TransfererRequest;
import com.projet.app.model.Etudiant;

import com.projet.app.services.EtudiantService;





@RestController

public class EtudiantController {
	@Autowired
	private EtudiantService es;
	
	
	
	@PostMapping("/rechargerCarte")
	public void rechargerCarte(@RequestBody RechargerCarteRequest request) {
	    es.rechargerCarte(request.getId(), request.getMontant());
	}
	
	@GetMapping("/api/etudiant")
	public List<Etudiant> getAll(){
		return es.getAllEtudiants();
	}
	@PostMapping("/api/etudiant")
	public Etudiant add(@RequestBody Etudiant etudiant) {
		return es.addEtudiant(etudiant);
	}
	@GetMapping("/api/etudiant/{id}")
	public Etudiant getPropById(@PathVariable ("id") Long id) {
		return es.readById(id);
	}
	
	@PutMapping("/api/etudiant/{id}")
	public ResponseEntity<Etudiant> update(@PathVariable("id") Long id,@RequestBody Etudiant etudiant){
		Etudiant etudiant2=es.updateEtudiant(id, etudiant);
		if(etudiant2 == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		return ResponseEntity.ok(etudiant2);
	}
	
	@DeleteMapping("/api/etudiant/{id}")
	public void delete(@PathVariable("id") Long id) {
		this.es.deleteEtudiant(id);
	}
	
	@GetMapping("/{id}/monsolde")
    public ResponseEntity<Double> consulterSolde(@PathVariable("id") Long idEtudiant) {
        double solde = es.consulterSolde(idEtudiant);
        return ResponseEntity.ok(solde);
    }
	
	@PostMapping("/transferersolde")
    public ResponseEntity<String> transfererSolde(@RequestBody TransfererRequest request) {
        try {
            es.transfererSolde(request.getCodeSecuriteSource(), request.getNumeroCarteDestination(), request.getMontant(), request.getId());
            return ResponseEntity.ok("Transfert de solde effectué avec succès.");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur est survenue lors du transfert de solde.");
        }
    }
	
	
	

}
