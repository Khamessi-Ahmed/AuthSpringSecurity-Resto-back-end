package com.projet.app.services;

import java.time.LocalDate;
import java.util.List;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.app.model.Avis;
import com.projet.app.model.Etudiant;
import com.projet.app.model.Menu;
import com.projet.app.repository.AvisRepository;
import com.projet.app.repository.EtudiantRepository;
import com.projet.app.repository.MenuRepository;

@Service
public class AvisService {
    private static final Logger logger = LoggerFactory.getLogger(AvisService.class);
    
    @Autowired
    private AvisRepository avisRepository;
    
    @Autowired
    private EtudiantRepository etudiantRepository;
    
    @Autowired
    private MenuRepository menuRepository;
    
    public Avis addAvis(Long etudiantId, Long menuId, int note, String commentaire) {
        LocalDate datedujour = LocalDate.now();
        Etudiant etudiant = etudiantRepository.findById(etudiantId).orElse(null);
        Menu menu = menuRepository.findById(menuId).orElse(null);
        
        if (etudiant == null || menu == null) {
            logger.error("etudiant ou Menu non trouvée");
            return null;
        }
        
        Avis avis = new Avis();
        avis.setEtudiant(etudiant);
        avis.setMenu(menu);
        avis.setNote(note);
        avis.setCommentaire(commentaire);
        avis.setDateAvis(datedujour);
        
        return avisRepository.save(avis);
    }
    
    public List<Avis> getAllAvis() {
        return avisRepository.findAll();
    }
}
