package com.projet.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.projet.app.model.Proposition;

import com.projet.app.repository.PropositionRepository;

@Service

public class PropositionService {
	
	@Autowired
	private PropositionRepository pr;
	
	public Proposition addProposition(Proposition proposition) {
		return pr.save(proposition);
	}
	
	public List<Proposition> getAll(){
		return pr.findAll();
	}
	
	public Proposition getPropositionById(Long id) {
		Optional<Proposition> prop= pr.findById(id);
		if(prop.isPresent()) {
			Proposition foundProposition=prop.get();
			return foundProposition;
		}
		return null;
	}
	
	public Proposition updateProposition(Long id, Proposition proposition) {
 
        Optional<Proposition> prop = pr.findById(id);
		if(prop.isPresent()) {
			Proposition existingProposition = prop.get();
			existingProposition.setDessert(proposition.getDessert());
	        existingProposition.setPlat(proposition.getPlat());
	        existingProposition.setVote(proposition.getVote());
	        existingProposition.setSupplement(proposition.getSupplement());
	        return pr.save(existingProposition);
			
			
	}
		return null;
	}
    
	
	public void deleteProposition(Long id) {
        Proposition proposition = getPropositionById(id);
        pr.delete(proposition);
    }

}
