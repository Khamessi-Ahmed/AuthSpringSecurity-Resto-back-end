package com.projet.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.app.model.Menu;


import java.time.LocalDate;


@Repository
public interface MenuRepository extends JpaRepository<Menu, Long>{
    Menu findByDate(LocalDate date);
}
