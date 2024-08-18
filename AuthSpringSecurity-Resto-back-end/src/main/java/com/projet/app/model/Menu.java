package com.projet.app.model;

import java.time.LocalDate;


import com.fasterxml.jackson.annotation.JsonFormat;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name="Menu")
public class Menu {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	
	private Long id;
	@JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate date;
	private String descriptionPlat;
	private String supplement;
	private String dessert;
	private Long vote;
	private Long qteDisponible;
	

}
