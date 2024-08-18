package com.projet.app.dto;

import lombok.Data;

@Data
public class EtudiantRegisterDto extends RegisterDto{
	
	private String numeroCarte;
    private double soldeCarte;
    private int codeSecurite;
    
}
