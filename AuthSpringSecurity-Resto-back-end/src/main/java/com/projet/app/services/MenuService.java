package com.projet.app.services;

import java.time.LocalDate;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.app.model.Menu;
import com.projet.app.repository.MenuRepository;



@Service
public class MenuService {
	private static final Logger logger = LoggerFactory.getLogger(MenuService.class);
	@Autowired
	private MenuRepository mr;
	
	
	public Menu addMenu(Menu menu) {
		LocalDate currentDate = LocalDate.now();
	    Menu existingMenu = mr.findByDate(currentDate);
	    if (existingMenu != null) {
	        
	        logger.error("Un menu a déjà été ajouté pour la date du jour : {}", currentDate);
	        return null;
	    }
	    menu.setDate(currentDate); // Set the date to the current date
	    return mr.save(menu);
	}
	
	public List<Menu> getAll(){
		return mr.findAll();
	}
	
	public Menu getMenuById(Long id) {
		Optional<Menu> menu= mr.findById(id);
		if(menu.isPresent()) {
			Menu foundMenu = menu.get();
			return foundMenu;
		}
		return null;
	}
	
	public Menu updateMenu(Long id, Menu menu) {
		
        Menu existingMenu = getMenuById(id);
        existingMenu.setDate(menu.getDate());
        existingMenu.setDescriptionPlat(menu.getDescriptionPlat());
        existingMenu.setDessert(menu.getDessert());
        existingMenu.setSupplement(menu.getSupplement());
        existingMenu.setQteDisponible(menu.getQteDisponible());
        return mr.save(existingMenu);
    }
	
	public void deleteMenu(Long id) {
        Menu menu = getMenuById(id);
        mr.delete(menu);
    }
	
	public Menu getMenuDuJourActuel() {
        LocalDate currentDate = LocalDate.now();
        Menu menu = mr.findByDate(currentDate);
        if (menu != null) {
            logger.info("Le menu du jour actuel a été récupéré avec succès : {}", menu);
        } else {
            logger.warn("Aucun menu n'a été trouvé pour la date actuelle : {}", currentDate);
        }
        return menu;
    }
	
	
	
}
