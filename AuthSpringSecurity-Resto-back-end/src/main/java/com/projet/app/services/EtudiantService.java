package com.projet.app.services;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.app.model.Etudiant;

import com.projet.app.repository.EtudiantRepository;

import jakarta.transaction.Transactional;

@Service
public class EtudiantService {
	
	@Autowired
	private EtudiantRepository etudiantRepository;
	
	
	
	public Etudiant addEtudiant(Etudiant etudiant) {
		return etudiantRepository.save(etudiant);
	}
	
	public List<Etudiant> getAllEtudiants(){
		return etudiantRepository.findAll();
	}
	
	public void deleteEtudiant(long id) {
		 etudiantRepository.deleteById(id);
	}
	
	public Etudiant readById(Long id) {
		Optional<Etudiant> etud = etudiantRepository.findById(id);
		if(etud.isPresent()) {
			return etud.get();
		}else {
			return null;
		}
	}
	
	public double consulterSolde(Long idEtudiant) {
        Optional<Etudiant> etudiantOptional = etudiantRepository.findById(idEtudiant);
        if (etudiantOptional.isPresent()) {
            Etudiant etudiant = etudiantOptional.get();
            return etudiant.getSoldeCarte();
        } else {
            throw new IllegalArgumentException("L'étudiant avec l'ID " + idEtudiant + " n'existe pas.");
        }
    }
	
	public Etudiant updateEtudiant(Long id, Etudiant etudiant) {
		Optional<Etudiant> etud= etudiantRepository.findById(id);
		if(etud.isPresent()) {
			Etudiant etudiantExist = etud.get();
			etudiantExist.setNom(etudiant.getNom());
			etudiantExist.setPrenom(etudiant.getPrenom());
			etudiantExist.setSoldeCarte(etudiant.getSoldeCarte());
			etudiantExist.setCodeSecurite(etudiant.getCodeSecurite());
			etudiantExist.setNumeroCarte(etudiant.getNumeroCarte());
			etudiantExist.setEmail(etudiant.getEmail());
			return etudiantRepository.save(etudiantExist);
			
		}
		return null;
	}
	
	public void rechargerCarte(Long idEtudiant, double montant) {
		Optional<Etudiant> etud = etudiantRepository.findById(idEtudiant);
		if(etud.isPresent()) {
			Etudiant etudiant=etud.get();
			double nouveauSolde=etudiant.getSoldeCarte()+montant;
			etudiant.setSoldeCarte(nouveauSolde);
			etudiantRepository.save(etudiant);
		}
	}

    @Transactional
	public void transfererSolde(int codeSecuriteSource, String numeroCarteDestination, double montant,Long id) {
        Optional<Etudiant> etudiantSourceOptional = etudiantRepository.findById(id);
        Optional<Etudiant> etudiantDestinationOptional = etudiantRepository.findByNumeroCarte(numeroCarteDestination);
        
        
        if (etudiantSourceOptional.isPresent() && etudiantDestinationOptional.isPresent()) {
        	
        	Etudiant etudiantSource = etudiantSourceOptional.get();
            Etudiant etudiantDestination = etudiantDestinationOptional.get();
        	
            if (etudiantSource.getId()==etudiantDestination.getId()) {
                throw new IllegalArgumentException("Un étudiant ne peut pas transférer de l'argent à lui-même.");
            }
        	
        	if(etudiantSource.getCodeSecurite()==(codeSecuriteSource)){
            
            
            
            if (etudiantSource.getSoldeCarte() >= montant) {
                
                double nouveauSoldeSource = etudiantSource.getSoldeCarte() - montant;
                double nouveauSoldeDestination = etudiantDestination.getSoldeCarte() + montant;
                
                etudiantSource.setSoldeCarte(nouveauSoldeSource);
                etudiantDestination.setSoldeCarte(nouveauSoldeDestination);
                
                
                etudiantRepository.save(etudiantSource);
                etudiantRepository.save(etudiantDestination);
            } else {
            throw new IllegalStateException("Solde insuffisant pour effectuer le transfert.");
        }
    } else {
        throw new IllegalArgumentException("Code Securite erroné.");
    }
        	}else {
        throw new IllegalArgumentException("Étudiant source ou destination introuvable.");
    }
}

	
	
	
}
