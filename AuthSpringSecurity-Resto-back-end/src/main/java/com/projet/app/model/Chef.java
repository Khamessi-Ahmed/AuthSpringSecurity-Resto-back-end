package com.projet.app.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
@AllArgsConstructor
@Entity
@Table(name="chef")
@Data
@Getter
@Setter
public class Chef extends UserEntity{

}
