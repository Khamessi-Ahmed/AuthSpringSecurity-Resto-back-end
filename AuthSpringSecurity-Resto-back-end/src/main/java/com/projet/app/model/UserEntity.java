package com.projet.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.Data;


@Entity
@Table(name = "users")
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
public class UserEntity {
	 @Id
	 @GeneratedValue(strategy = GenerationType.TABLE)
	    private Long id;
	    private String nom;
	    private String prenom;
	    private String email;
	    private String password;
	    private String role;
	    
}
