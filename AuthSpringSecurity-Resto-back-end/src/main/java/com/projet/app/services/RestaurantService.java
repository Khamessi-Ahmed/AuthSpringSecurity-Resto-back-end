package com.projet.app.services;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.projet.app.model.Restaurant;
import com.projet.app.repository.RestaurantRepository;


@Service
public class RestaurantService {
	
	@Autowired
    private RestaurantRepository restaurantRepository;
	
	public int getNombrePlacesDisponibles() {
        
        Optional<Restaurant> restaurant = restaurantRepository.findById(1L); 
        if (restaurant.isPresent()) {
            Restaurant restaurant1 = restaurant.get();
            return restaurant1.getNombrePlacesDisponibles();
        } else {
            return 0;
        }
    }
	
	
	
	public void mettreAjourPlacesDisponibles(int nombrePlacesReduites) {
		
		Optional<Restaurant> restaurantOptional = restaurantRepository.findById(1L);
        if (restaurantOptional.isPresent()) {
            Restaurant restaurant = restaurantOptional.get();
            int nouveauNombrePlaces = restaurant.getNombrePlacesDisponibles() - nombrePlacesReduites;
            if (nouveauNombrePlaces < 0) {
                nouveauNombrePlaces = 0;
            }
            restaurant.setNombrePlacesDisponibles(nouveauNombrePlaces);
            restaurantRepository.save(restaurant);
            System.out.println("Nouveau nombre de places disponibles : " + nouveauNombrePlaces);
        } else {
            System.out.println("Restaurant non trouvé.");
        }
    }
	
	public void reinitialiserNbrePlacesQuotidiennement() {
        // Réinitialise le nombre de places disponibles à 300
        Restaurant restaurant = new Restaurant();
        restaurant.setId(1L);
        restaurant.setNombrePlacesDisponibles(300);
        restaurantRepository.save(restaurant);
    }

}
