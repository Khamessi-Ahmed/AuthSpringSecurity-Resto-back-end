package com.projet.app.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.app.model.Chef;
import com.projet.app.model.Etudiant;
import com.projet.app.repository.ChefRepository;

@Service
public class ChefService {
	
	
	@Autowired
	private ChefRepository cr;
	
	public Chef create(Chef chef) {
		return cr.save(chef);
	}
	public List<Chef> getAllChefs(){
		return cr.findAll();
	}
	
	public void delete(long id) {
		this.cr.deleteById(id);
	}
	
	public Chef readById(Long id) {
		Optional<Chef> chef = cr.findById(id);
		if(chef.isPresent()) {
			return chef.get();
		}else {
			return null;
		}
	}
	
	public Chef update(long id, Chef chef) {
		Optional<Chef> chefOptional =cr.findById(id);
		if(chefOptional.isPresent()) {
			Chef existingChef= chefOptional.get();
			existingChef.setNom(chef.getNom());
			existingChef.setEmail(chef.getEmail());
			existingChef.setPrenom(chef.getPrenom());
			existingChef.setPassword(chef.getPassword());
			return cr.save(existingChef);
		}
		return null;
	}
}
