package com.projet.app.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.web.bind.annotation.RestController;

import com.projet.app.dto.AvisRequest;
import com.projet.app.model.Avis;
import com.projet.app.services.AvisService;

@RestController

public class AvisController {
    @Autowired
    private AvisService avisService;
    
    @PostMapping("/ajouterAvis")
    public ResponseEntity<Avis> ajouterAvis(@RequestBody AvisRequest avis) {
        Avis newAvis = avisService.addAvis(avis.getEtudiantId() ,
                                           avis.getMenuId(), 
                                           avis.getNote(), 
                                           avis.getCommentaire());
        if (newAvis != null) {
            return new ResponseEntity<>(newAvis, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
    
    @GetMapping("/listerAvis")
    public List<Avis> listerAvis() {
       return avisService.getAllAvis();
        
    }
}
