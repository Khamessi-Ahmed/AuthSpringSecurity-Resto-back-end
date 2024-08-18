package com.projet.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.app.model.Chef;
@Repository
public interface ChefRepository extends JpaRepository<Chef, Long>{

}
