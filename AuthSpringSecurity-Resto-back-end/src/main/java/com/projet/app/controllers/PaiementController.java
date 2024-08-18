package com.projet.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projet.app.dto.PaiementDTO;
import com.projet.app.dto.TransfererRequest;
import com.projet.app.services.PaiementService;

@RestController
@RequestMapping("/api/paiements")
public class PaiementController {
	@Autowired
	private PaiementService ps;
	
	
	@PostMapping
    public ResponseEntity<String> effectuerPaiement(@RequestBody PaiementDTO pDTO) {
        try {
            ps.effectuerPaiement(pDTO.getEtudiantId(), 
                    pDTO.getNumeroCarte(),
                    pDTO.getCodeSecurite());
            return ResponseEntity.ok("Paiement effectué avec succès.");
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Une erreur est survenue lors du paiement.");
        }
    }
	
	

}
