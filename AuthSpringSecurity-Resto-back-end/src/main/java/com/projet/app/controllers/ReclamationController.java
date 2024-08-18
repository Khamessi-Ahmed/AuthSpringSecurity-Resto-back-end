package com.projet.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.projet.app.dto.ReclamationRequest;
import com.projet.app.model.Reclamation;
import com.projet.app.services.ReclamationService;

@RestController
public class ReclamationController {
	@Autowired
	private ReclamationService rs;
	
	@PostMapping("/reclamation")
    public ResponseEntity<String>  faireReclamation(@RequestBody ReclamationRequest reclamationRequest) {
		
		try {
			rs.faireReclamation(reclamationRequest.getEtudiantId(), reclamationRequest.getMenuId(), reclamationRequest.getContenu());
			return ResponseEntity.ok("Reclamation envoyée avec succée.");
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur est survenue lors de l'envoi de la réclamation.");
		}
	}
	
	@GetMapping("/reclamations")
	public List<Reclamation> getAllReclamations(){
		return rs.getAll();
	}
}