package com.projet.app.controllers;

import java.util.Collection;
import com.projet.app.dto.LoginDto;
import com.projet.app.dto.LoginResponse;
import com.projet.app.services.CustomUserDetails;
import com.projet.app.services.CustomUserDetailsService;
import com.projet.app.Jwt.JwtUtil;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class LoginController {

    private final AuthenticationManager authenticationManager;
    private final CustomUserDetailsService customUserDetailsService;
    private final JwtUtil jwtUtil;

    @Autowired
    public LoginController(AuthenticationManager authenticationManager, CustomUserDetailsService customUserDetailsService, JwtUtil jwtUtil) {
        this.authenticationManager = authenticationManager;
        this.customUserDetailsService = customUserDetailsService;
        this.jwtUtil = jwtUtil;
    }

    @PostMapping
    public ResponseEntity<?> login(@RequestBody LoginDto loginRequest) {
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        } catch (BadCredentialsException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Incorrect email or password.");
        } catch (DisabledException e) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("User is not activated");
        }

        UserDetails userDetails = customUserDetailsService.loadUserByUsername(loginRequest.getEmail());

        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        String role = "ROLE_USER";
        Long userId = null;
        String numeroCarte=null;

        if (userDetails instanceof CustomUserDetails) {
            userId = ((CustomUserDetails) userDetails).getId();
            numeroCarte=((CustomUserDetails)userDetails).getNumeroCarte();
        }

        for (GrantedAuthority authority : authorities) {
            if (authority.getAuthority().equals("ROLE_ADMIN")) {
                role = "ADMIN";
                break;
            } else if (authority.getAuthority().equals("ROLE_CHEF")) {
                role = "CHEF";
                break;
            } else if (authority.getAuthority().equals("ROLE_ETUDIANT")) {
                role = "ETUDIANT";
                break;
            }
        }

        String jwt = jwtUtil.generateToken(userDetails.getUsername(), authorities);
        LoginResponse response = new LoginResponse(jwt, role, userId,numeroCarte);

        return ResponseEntity.ok(response);
    }
}