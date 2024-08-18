package com.projet.app.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@EnableScheduling
public class ScheduledTasks {

    @Autowired
    private RestaurantService restaurantService;

    @Scheduled(cron = "0 49 15 * * ?") // Exécution quotidienne à 18:00
    public void reinitialiserNombrePlacesQuotidiennement() {
        restaurantService.reinitialiserNbrePlacesQuotidiennement();
    }
}