package com.projet.app.dto;

import lombok.Data;

@Data
public class TransfererRequest {
	private int codeSecuriteSource;
    private String numeroCarteDestination;
    private double montant;
    private Long id;

}
