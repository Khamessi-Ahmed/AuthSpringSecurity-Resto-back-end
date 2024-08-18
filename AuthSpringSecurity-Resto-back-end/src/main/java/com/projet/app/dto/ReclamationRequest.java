package com.projet.app.dto;

import lombok.Data;

@Data
public class ReclamationRequest {
	private Long etudiantId;
	private Long menuId;
	private String contenu;
}
