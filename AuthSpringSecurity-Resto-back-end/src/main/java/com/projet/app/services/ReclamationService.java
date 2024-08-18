package com.projet.app.services;


import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.app.model.Etudiant;
import com.projet.app.model.Menu;
import com.projet.app.model.Reclamation;
import com.projet.app.repository.EtudiantRepository;
import com.projet.app.repository.MenuRepository;
import com.projet.app.repository.ReclamationRepository;

@Service
public class ReclamationService {
	
	@Autowired
	private ReclamationRepository recRep;
	@Autowired
    private EtudiantRepository etudiantRepository;
	@Autowired
	private MenuService ms;
    
	
	
	public void faireReclamation(Long etudiantId,long menuId,String contenu) {
		Optional<Etudiant> etudiant =etudiantRepository.findById(etudiantId);

        if (etudiant.isPresent() ) {
            Etudiant etud = etudiant.get();
            Menu menu = ms.getMenuDuJourActuel(); 
            if(menu!=null) {
            Reclamation reclamation = new Reclamation();
            reclamation.setEtudiant(etud);
            reclamation.setMenu(menu);
            reclamation.setContenu(contenu);
            recRep.save(reclamation);
        } else {
        	throw new IllegalArgumentException("Menu du jour non trouvé");
        }
		
	}else {
		throw new IllegalArgumentException("Étudiant non trouvé");
	}
	}
	
	
	public List<Reclamation> getAll(){
		return recRep.findAll();
	}
}
