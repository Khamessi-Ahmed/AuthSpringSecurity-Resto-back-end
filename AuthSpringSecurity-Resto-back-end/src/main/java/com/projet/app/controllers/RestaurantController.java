package com.projet.app.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.projet.app.services.RestaurantService;

@RestController
@RequestMapping("/api/restaurant")
public class RestaurantController {
	
	@Autowired
	private RestaurantService rs;
	
	@GetMapping
	public int getNombreDePlaceResataurant() {
		return rs.getNombrePlacesDisponibles();
	}

}
