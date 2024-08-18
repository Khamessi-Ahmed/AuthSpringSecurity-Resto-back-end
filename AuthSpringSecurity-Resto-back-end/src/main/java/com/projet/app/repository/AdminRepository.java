package com.projet.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.projet.app.model.Admin;

@Repository
public interface AdminRepository extends JpaRepository<Admin, Long>{

}
