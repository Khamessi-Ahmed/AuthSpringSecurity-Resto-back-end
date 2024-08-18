package com.projet.app.dto;

import lombok.Data;

@Data
public class AvisRequest {
	private Long etudiantId;
	private Long menuId;
	private String commentaire;
	private int note;
}
