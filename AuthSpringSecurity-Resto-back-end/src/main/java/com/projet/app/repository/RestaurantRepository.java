package com.projet.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.app.model.Restaurant;


@Repository
public interface RestaurantRepository extends JpaRepository<Restaurant, Long>{

}
