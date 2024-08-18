package com.projet.app.services;

import java.util.Collection;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.projet.app.model.Etudiant;
import com.projet.app.model.UserEntity;

public class CustomUserDetails extends User {

    private Long id;
    private String numeroCarte;

    public CustomUserDetails(UserEntity user, Collection<? extends GrantedAuthority> authorities) {
        super(user.getEmail(), user.getPassword(), authorities);
        this.id = user.getId();
        if (user instanceof Etudiant) { 
            this.numeroCarte = ((Etudiant) user).getNumeroCarte();
        }
    }

    public Long getId() {
        return id;
    }
    public String getNumeroCarte() { 
        return numeroCarte;
    }
}
