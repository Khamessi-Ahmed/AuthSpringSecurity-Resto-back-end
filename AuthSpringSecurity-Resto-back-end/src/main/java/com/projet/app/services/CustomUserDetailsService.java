package com.projet.app.services;

import java.util.ArrayList;
import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.projet.app.model.Admin;
import com.projet.app.model.Chef;
import com.projet.app.model.Etudiant;
import com.projet.app.model.UserEntity;
import com.projet.app.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public CustomUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Email not found"));

        Collection<GrantedAuthority> authorities = new ArrayList<>();
        if (user instanceof Etudiant) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ETUDIANT"));
        } else if (user instanceof Chef) {
            authorities.add(new SimpleGrantedAuthority("ROLE_CHEF"));
        } else if (user instanceof Admin) {
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        } else {
            throw new IllegalArgumentException("type d'utilisateur inconnu");
        }

        return (UserDetails) new CustomUserDetails(user, authorities);
    }
}