package com.projet.app.dto;

import lombok.Data;

@Data
public class RegisterDto {
	private String email;
    private String password;
    private String role; 
    private String nom;
    private String prenom;
    

}
