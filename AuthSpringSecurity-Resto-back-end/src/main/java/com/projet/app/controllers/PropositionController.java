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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projet.app.model.Proposition;
import com.projet.app.services.PropositionService;

@RestController
@RequestMapping("/api/proposition")
public class PropositionController {
	
	@Autowired
	private PropositionService ps;
	
	@PostMapping
	public Proposition addProposition(@RequestBody Proposition proposition) {
		return ps.addProposition(proposition);
			
	}
	
	@GetMapping
	public List<Proposition> getAll(){
		return ps.getAll();
	}
	
	@GetMapping("/{id}")
	public Proposition getProposition(@PathVariable ("id") Long id) {
		return ps.getPropositionById(id);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<Proposition> update(@PathVariable("id") Long id,@RequestBody Proposition proposition){
		Proposition p=ps.updateProposition(id, proposition);
		if(p == null) return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
		return ResponseEntity.ok(p);
	}
	
	@DeleteMapping("/{id}")
	public void delete(@PathVariable ("id") Long id) {
		ps.deleteProposition(id);
	}

}
